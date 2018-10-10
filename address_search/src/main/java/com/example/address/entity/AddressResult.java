package com.example.address.entity;

import lombok.Data;

@Data
public class AddressResult {
	private String id;
	private String zipcode;
	private String jiscode;
	private String pref;
	private String city;
	private String street;
}
