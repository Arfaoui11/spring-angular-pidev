package com.javachinna.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.javachinna.model.Profession;
import com.javachinna.validator.PasswordMatches;

import lombok.Data;

/**
 * @author User
 * @since 26/3/22
 */
@Data
@PasswordMatches
public class SignUpRequest {

	private Long userID;

	private String providerUserId;

	@NotEmpty
	private String displayName;

	@NotEmpty
	private String email;

	private SocialProvider socialProvider;

	@Size(min = 6, message = "{Size.userDto.password}")
	private String password;

	@NotEmpty
	private String matchingPassword;

	private Profession profession;


	private String firstName;
	private String lastName;
	private int priceconsultation;

	private int Score;

	private double salary;

	private Integer tarifHoraire;
	private int age;

	public SignUpRequest(String providerUserId, String displayName, String email, String password, SocialProvider socialProvider,Profession profession, String firstName ,String lastName,int priceconsultation,int score,double salary,Integer tarifHoraire,int age)
	{
		this.providerUserId = providerUserId;
		this.displayName = displayName;
		this.email = email;
		this.password = password;
		this.socialProvider = socialProvider;
		this.profession = profession;
		this.lastName=lastName;
		this.firstName=firstName;
		this.age=age;
		this.salary=salary;
		this.tarifHoraire=tarifHoraire;
		this.Score=score;
		this.priceconsultation = priceconsultation;
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {
		private String providerUserID;
		private String displayName;
		private String email;
		private String password;
		private SocialProvider socialProvider;
		private Profession profession;
		private String firstName;
		private String lastName;
		private int priceconsultation;

		private int Score;

		private double salary;

		private Integer tarifHoraire;
		private int age;


		public Builder addProviderUserID(final String userID) {
			this.providerUserID = userID;
			return this;
		}

		public Builder addDisplayName(final String displayName) {
			this.displayName = displayName;
			return this;
		}

		public Builder addEmail(final String email) {
			this.email = email;
			return this;
		}
		public Builder addLastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}
		public Builder addFirstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}
		public Builder addAge(final int age) {
			this.age = age;
			return this;
		}
		public Builder addScore(final int Score) {
			this.Score = Score;
			return this;
		}
		public Builder addSalary(final double salary) {
			this.salary = salary;
			return this;
		}
		public Builder addTarifHoraire(final Integer tarifHoraire) {
			this.tarifHoraire = tarifHoraire;
			return this;
		}
		public Builder addPriceConsultation(final Integer priceconsultation) {
			this.priceconsultation = priceconsultation;
			return this;
		}

		public Builder addPassword(final String password) {
			this.password = password;
			return this;
		}

		public Builder addSocialProvider(final SocialProvider socialProvider) {
			this.socialProvider = socialProvider;
			return this;
		}
		public Builder addProfession(final Profession profession) {
			this.profession = profession;
			return this;
		}

		public SignUpRequest build() {
			return new SignUpRequest(providerUserID, displayName, email, password, socialProvider,profession,firstName,lastName,priceconsultation,Score,salary,tarifHoraire,age);
		}
	}
}
