package com.blogging.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

	private int id;
	@NotEmpty(message = "name sholud not be empty")
	@Size(min = 3, max = 10, message = "name should be at least 3 char")
	private String name;
	@NotEmpty(message = "password should not be empty")
	private String password;
	@NotEmpty(message = "password should not be empty")
	private String about;
	@NotEmpty(message = "password should not be empty")
	@Email
	private String email;

	public UserDto() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
