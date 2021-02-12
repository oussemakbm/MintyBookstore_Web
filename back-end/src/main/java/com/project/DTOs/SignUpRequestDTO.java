package com.project.DTOs;

import lombok.Data;

@Data
public class SignUpRequestDTO {
	
	private String email;
	private String username;
	private String password;
	private String name;
	
	
	@Override
	public String toString() {
		return "SignUpRequestDTO [email=" + email + ", username=" + username + ", password=" + password + ", name="
				+ name + "]";
	}
	
	
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SignUpRequestDTO() {
		super();
	}
	public SignUpRequestDTO(String email, String username, String password, String name) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	
	
	
}
