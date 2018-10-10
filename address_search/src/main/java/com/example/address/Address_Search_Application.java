package com.example.address;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("TestMapper")
public class Address_Search_Application {

	public static void main(String[] args) {
		SpringApplication.run(Address_Search_Application.class, args);
	}
}
