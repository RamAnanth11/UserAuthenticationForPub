package com.ananth.k2.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Visitor {
	private Integer id;
	private Integer age;
	private String name;
	private String email;
	private String phoneNo;
	private String gender;
	private String address;
	private Date dob;
	private String passWord;
}