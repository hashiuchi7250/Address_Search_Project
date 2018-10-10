package com.example.address.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class KeyWordForm {

	@NotBlank
	private String keyword;
}
