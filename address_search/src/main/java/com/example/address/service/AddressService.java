package com.example.address.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.address.dto.AddressDto;
import com.example.address.entity.AddressResult;
import com.example.address.mapper.AddressMapper;

@Service
public class AddressService {

	@Autowired
	private AddressMapper addressMapper;

	public List<AddressDto> findAddress(AddressDto dto) {
		List<AddressResult> resultAddressData = addressMapper.doSearch(dto);
		List<AddressDto> dtoResultAddressData = convertToDto(resultAddressData);
	    return dtoResultAddressData;
	}

	private List<AddressDto> convertToDto(List<AddressResult> resultAddressData) {

	    List<AddressDto> endConvertToDto = new LinkedList<>();

	    for (AddressResult entity : resultAddressData) {
	        AddressDto addressDto = new AddressDto();
	        BeanUtils.copyProperties(entity, addressDto);
	        endConvertToDto.add(addressDto);
	    }

	    return endConvertToDto;
	}

	public void exeInsert(AddressDto dto) {
		addressMapper.insCity(dto);
		addressMapper.insJiscode(dto);
		addressMapper.insKana(dto);
		addressMapper.insPref(dto);
		addressMapper.insZipcode(dto);
	}

	public void exeUpDate(AddressDto dto) {
		addressMapper.exeUpDate(dto);
	}

	public void exeDelete(AddressDto dto) {
		addressMapper.exeDelete(dto);
	}
}
