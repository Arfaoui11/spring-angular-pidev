package com.javachinna.service;

import java.util.*;

import com.javachinna.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.javachinna.dto.LocalUser;
import com.javachinna.dto.SignUpRequest;
import com.javachinna.dto.SocialProvider;
import com.javachinna.exception.OAuth2AuthenticationProcessingException;
import com.javachinna.exception.UserAlreadyExistAuthenticationException;
import com.javachinna.model.Role;
import com.javachinna.model.User;
import com.javachinna.repo.RoleRepository;
import com.javachinna.repo.UserRepository;
import com.javachinna.security.oauth2.user.OAuth2UserInfo;
import com.javachinna.security.oauth2.user.OAuth2UserInfoFactory;
import com.javachinna.util.GeneralUtils;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public User addUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public void updateUser(User u, Long idU) {
		User user=userRepository.findById(idU).orElse(null);
		userRepository.save(user);
	}

	@Override
	public User retrieveUser(Long id) {
		User u = userRepository.findById(id).orElse(null);
		return u;

	}

	@Override
	public List<User> retrieveAllUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(user -> {
			users.add(user);
		});
		return users;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}

		User user = buildUser(signUpRequest);
		Date now = Calendar.getInstance().getTime();
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		user.setAge(signUpRequest.getAge());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setSalary(signUpRequest.getSalary());
		user.setTarifHoraire(signUpRequest.getTarifHoraire());
		user.setPriceconsultation(signUpRequest.getPriceconsultation());
		user.setProfession(signUpRequest.getProfession());
		user.setScore(signUpRequest.getScore());
		user.setNationality(signUpRequest.getNationality());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());
		user = userRepository.save(user);
		userRepository.flush();
		return user;

	}

	private User buildUser(final SignUpRequest formDTO) {
		User user = new User();
		user.setDisplayName(formDTO.getDisplayName());
		user.setEmail(formDTO.getEmail());
		user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
		final HashSet<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName(Role.ROLE_USER));
		user.setRoles(roles);
		user.setAge(formDTO.getAge());
		user.setFirstName(formDTO.getFirstName());
		user.setLastName(formDTO.getLastName());
		user.setSalary(formDTO.getSalary());
		user.setTarifHoraire(formDTO.getTarifHoraire());
		user.setPriceconsultation(formDTO.getPriceconsultation());
		user.setProfession(formDTO.getProfession());
		user.setScore(formDTO.getScore());
		user.setProvider(formDTO.getSocialProvider().getProviderType());
		user.setEnabled(true);
		user.setProviderUserId(formDTO.getProviderUserId());
		return user;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setDisplayName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}
////////////ahaya lezem tetbadel ////////////////////////////////  voir ici pls

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
				.addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User excluded(Long id) {
		User user =  userRepository.findById(id).get();
		user.setState(State.EXCLUDED);
		return userRepository.save(user);
	}

	@Override
	public User warned(Long id) {
		User user =  userRepository.findById(id).get();
		user.setState(State.WARNED);
		return userRepository.save(user);
	}

	@Override
	public User punished(Long id) {
		User user =  userRepository.findById(id).get();
		user.setState(State.PUNISHED);
		return userRepository.save(user);
	}


	//OMAR
	@Override
	public int  numStudentWithoutRatings() {
		List<User>listStudent= (List<User>) userRepository.findStudentWithoutRatings();
		int num=0;
		for(User u :listStudent){
			{
				num=num+1;
			}
		}
		return num;
	}

	@Override
	public int numStudentsWithRatingsByUniversity(Integer idUniversity) {
		List<User> listS=userRepository.findStudentWithRatingByUniversity( idUniversity);
		int num=0;
		for (User u : listS){
			num+=1;
		}

		return  num;

	}


	@Override
	public List<User> acceptedStudentsByUniversity(Integer idUniversity) {
		return userRepository.acceptedStudentsByUniversity(idUniversity);
	}

	@Override
	public int studentDemands(Long IdStudent,Integer IdUniversity) {
		return userRepository.studentDemands(IdStudent,IdUniversity);
	}
}
