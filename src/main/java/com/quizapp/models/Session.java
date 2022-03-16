package com.quizapp.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="sessions")
public class Session {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Learner_id")
	private Learner Learner;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "session_id")
	private Quiz quiz;
	
	@Column(name = "start_time")
	private Timestamp start_time;
	
	@Column(name = "end_time")
    private Timestamp end_time;
	
	@Column(name = "score")
	private double score;

	@Column(name = "msg")
	private String msg;
	
	@Column(name="current_Learner_id")
	private Long LearnerId;


	
	private String LearnerName;
	
}
