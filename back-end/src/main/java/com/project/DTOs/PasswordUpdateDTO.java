package com.project.DTOs;

public class PasswordUpdateDTO {
	private long idUser;
	private String newPassword;
	
	public PasswordUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
	
}
