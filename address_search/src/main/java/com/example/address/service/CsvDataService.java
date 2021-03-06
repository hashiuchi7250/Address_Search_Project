package com.example.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.address.dto.AddressDto;

@Service
public class CsvDataService {

	@Autowired
	AddressService addressService;

	public String makeCsvData(AddressDto dto, StringBuilder sb) {

		String CSVData = null;

		List<AddressDto> findResultAddress = addressService.findAddress(dto);
		for (AddressDto adrDto : findResultAddress) {

			String zipcode	= adrDto.getZipcode();
			String pref		= adrDto.getPref();
			String city		= adrDto.getCity();
			String street  = adrDto.getStreet();

			sb.append(zipcode+","+pref+","+city+","+street+"\n");
			CSVData =sb.toString();
		}
		return CSVData;
	}
}
