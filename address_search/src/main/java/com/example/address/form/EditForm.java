package com.example.address.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class EditForm {

	@NotBlank
	@Length(min = 3, max = 7)
	private String insZipcode;
	private String insPref;
	private String insCity;
	private String insStreet;

	@NotBlank
	@Length(min = 3, max = 7)
	private String selectZipcode;
	private String selectId;
	private String selectJiscode;
	private String selectPref;
	private String selectPrefKana;
	private String selectCity;
	private String selectCityKana;
	private String selectStreet;
	private String selectStreetKana;
	private String edit;
}
