package com.project.DTOs;

public class UserDTO {
		private long id;
		private String email;
		private String numTel;
		private String adresse;
		private String role;
		private String picUrl;
		private String name;
		private String username;
		
		public UserDTO() {
			super();
		}
		
		
		
		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getNumTel() {
			return numTel;
		}
		public void setNumTel(String numTel) {
			this.numTel = numTel;
		}
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		
}
