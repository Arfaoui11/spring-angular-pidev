package com.quizapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Learners")
public class Learner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String pic;
	private String socialSituation;
	private int age;

	@Enumerated(EnumType.STRING)
	private Profession profession;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="Learner")
	private Session session;
	

	@Column(name="user_role")
	private String role = "Learner";


}
