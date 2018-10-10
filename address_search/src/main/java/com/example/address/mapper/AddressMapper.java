package com.example.address.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.address.dto.AddressDto;
import com.example.address.entity.AddressResult;

@Mapper
public interface AddressMapper {

	List<AddressResult> doSearch(AddressDto dto);

	void insCity(AddressDto dto);
	void insJiscode(AddressDto dto);
	void insKana(AddressDto dto);
	void insPref(AddressDto dto);
	void insZipcode(AddressDto dto);
	void exeUpDate(AddressDto dto);
	void exeDelete(AddressDto dto);
}
