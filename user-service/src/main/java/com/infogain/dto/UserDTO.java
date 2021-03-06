package com.infogain.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserDTO {

	 private Long id;
	 @NotBlank(message = "name field can not be null or empty")   
	 private String name;
	 //@Size(min = 10, max = 12)   
	 private Long mobileNo;
	 @NotBlank(message = "email field can not be null or empty") 
	 private String email;
	 @NotBlank(message = "password field can not be null or empty")
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	 
}
