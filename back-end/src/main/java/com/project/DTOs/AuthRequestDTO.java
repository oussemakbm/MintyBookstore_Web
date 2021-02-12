package com.project.DTOs;

import lombok.Data;

@Data
public class AuthRequestDTO {

	private String username;
	
	
	private String password;

	public AuthRequestDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthRequestDTO() {
		super();
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
