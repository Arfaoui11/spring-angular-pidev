package com.javachinna.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javachinna.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	boolean existsByEmail(String email);
	@Query("select u from User u where u.profession='Doctor' order by u.Score desc ")
	List<User> classementDoctor();

}
