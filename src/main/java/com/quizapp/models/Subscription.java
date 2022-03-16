package com.quizapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Subscription implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubscription;
	private String Title;
	private String Description;
	private Float price;
	private Date startDate;
	private Date endDate;

	
	//@JsonIgnore
	//@ManyToMany
	//private Set<User> users;;
}
