package com.example.address.service;

import org.springframework.stereotype.Service;

import com.example.address.dto.AddressDto;
import com.example.address.form.EditForm;

@Service
public class EditService {

	public AddressDto setDto(EditForm form, AddressDto dto) {

		String id 			= form.getSelectId();
		String zipcode		= form.getSelectZipcode();
		String jiscode		= form.getSelectJiscode();
		String pref			= form.getSelectPref();
		String prefKana	= form.getSelectPrefKana();
		String city			= form.getSelectCity();
		String cityKana	= form.getSelectCityKana();
		String street		= form.getSelectStreet();
		String streetKana	= form.getSelectStreetKana();
		String edit			= form.getEdit();

		dto.setId(id);
		dto.setZipcode(zipcode);
		dto.setJiscode(jiscode);
		dto.setPref(pref);
		dto.setPrefKana(prefKana);
		dto.setCity(city);
		dto.setCityKana(cityKana);
		dto.setStreet(street);
		dto.setStreetKana(streetKana);
		dto.setEdit(edit);

		return dto;
	}
}
