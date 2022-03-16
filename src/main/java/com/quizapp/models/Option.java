package com.quizapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	

	private String optionText;
	
	@ManyToOne()
	@JoinColumn(name="question_id")
	private Question question;

	public Option() {

	}
	public Option(String optionText) {
		super();
		this.optionText = optionText;
	}


}
