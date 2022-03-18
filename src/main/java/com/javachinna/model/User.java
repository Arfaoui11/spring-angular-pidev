package com.javachinna.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	//@Column(name = "USER_ID")
	private Long id;

	//@Column(name = "PROVIDER_USER_ID")
	private String providerUserId;

	private String email;
	private String firstName;
	private String lastName;

	//@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	//@Column(name = "DISPLAY_NAME")
	private String displayName;

	//@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	private String password;

	private String provider;
	@Enumerated(EnumType.STRING)
	private Profession profession;
	private int priceconsultation;

	private int Score;


	// bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;


	@JsonIgnore
	@ManyToMany
	private List<Subscription> subscs;


	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="User")
	private Session session;

	//private Integer nb_subsc;
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
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
	@JsonIgnore
	private  Set<Candidacy> candidacy;

}