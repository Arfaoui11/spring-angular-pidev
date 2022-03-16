package com.quizapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Questions")
public class Question{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionId;
	
	@Column(name = "question_title", nullable = false)
	private String title;
	
	@OneToMany(targetEntity=Option.class, mappedBy="question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Option> options; //value to text
	
	
	@Column(name = "option_correct", nullable = false)
	private String optionCorrect;
	
	@ManyToOne()
	@JoinColumn(name="quiz_id")
	private Quiz quiz;
	
	@Column(name="subject")
	private String subject;
	
	private String optionChosen =null;


	
	
}
