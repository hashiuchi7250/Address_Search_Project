package com.example.address.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AddressForm {

	@NotBlank
	@Length(min = 3, max = 7)
	private String zipcode;
}
