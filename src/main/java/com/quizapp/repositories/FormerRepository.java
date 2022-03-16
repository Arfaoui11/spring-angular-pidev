package com.quizapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.models.Former;
import org.springframework.stereotype.Repository;

@Repository
public interface FormerRepository extends JpaRepository<Former, Long>{

	void deleteById(Long Id);
	Former findByusername(String username);
}
