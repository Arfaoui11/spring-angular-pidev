package com.quizapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Getter
@Setter

@Entity
@Table(name = "Quizes")
public class Quiz {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long quizId;
	
	@Column(name = "subject")
	private String subject;
	
	@OneToMany(targetEntity=Question.class, mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Question> questions;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "quiz")
	private Session session;
	
	
	public Quiz() {

	}

	public Quiz(String subject, List<Question> questions, Session session) {
		super();
		this.subject = subject;
		this.questions = questions;
		this.session = session;
	}


	
	
}
