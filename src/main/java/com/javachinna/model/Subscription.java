package com.javachinna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

	@Max(5)
	@Min(0)
	private Double Rating;
	@Temporal (TemporalType.TIMESTAMP)
	private Date start;
	@Temporal (TemporalType.TIMESTAMP)
	private Date end;
	private Integer nbrParticipant;



	@JsonIgnore
	@ManyToMany
	private Set<User> users;
}
