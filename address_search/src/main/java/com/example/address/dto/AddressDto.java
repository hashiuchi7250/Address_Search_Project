package com.example.address.dto;

import lombok.Data;

@Data
public class AddressDto {

	private String id;
	private String zipcode;
	private String jiscode;
	private String pref;
	private String prefKana;
	private String city;
	private String cityKana;
	private String street;
	private String streetKana;
	private String keyword;
	private String edit;
}
