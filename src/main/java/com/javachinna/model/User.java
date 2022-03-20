package com.javachinna.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {


	private static final long serialVersionUID = 65981149772133526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	private String firstName;
	private String lastName;

	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	private String password;

	private String provider;

	@Enumerated(EnumType.STRING)
	private Profession profession;

	private Integer priceconsultation;

	private Integer Score;

	//@Positive
	private Integer salary;

	//@Positive
	private Integer tarifHoraire;

//	@NotNull
//	@Email(message = "Email should be valid")
	private String email;

	//@Min(value = 18, message = "Age should not be less than 18")
	//@Max(value = 150, message = "Age should not be greater than 150")
	private Integer age;
	private String Nationality;
	private String phoneNumber;


	// bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;


	@JsonIgnore
	@ManyToMany(mappedBy = "users",cascade =CascadeType.ALL)
	private Set<Subscription> subscs;


	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="User")
	@JsonIgnore
	private Session session;

	//private Integer nb_subsc;
	@OneToMany
	@JsonIgnore
	private Set<Appointment> appointments;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Complaint> complaints;
	@OneToMany(mappedBy ="users",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<ComplaintResponse> complaintResponses;
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "doctor")
	@JsonIgnore
	private Set<Appointment> appointmentsDocteur;

	@ManyToMany (mappedBy = "doctors",cascade =CascadeType.ALL)
	@JsonIgnore
	private Set<Clinical> clinical;

	@OneToMany(mappedBy ="users" ,fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
			})
	@JsonIgnore
	private Set<Offres> offer;


	@OneToMany(mappedBy ="usersW",fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.REMOVE
			} )
	//hh
	@JsonIgnore
	private  Set<Candidacy> candidacy;



	@OneToMany(mappedBy = "formateur")
	@JsonIgnore
	private Set<Formation> formationF;


	@ManyToMany(mappedBy = "apprenant", fetch = FetchType.EAGER
			,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private Set<Formation> formationA;


	@OneToMany(mappedBy = "sUser"
			,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JsonIgnore
	private Set<Result> results;
	@OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<PartnerInstitution> partnerInstitutions ;

	@OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<RatingPartner>ratingPartners;

	@OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<CandidacyUniversity>candidacies;

	@OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST, CascadeType.REMOVE},
			fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<DatabaseFile>fileUploads;

}