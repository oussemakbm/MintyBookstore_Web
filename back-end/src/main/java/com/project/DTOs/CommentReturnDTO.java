package com.project.DTOs;

import java.sql.Date;
import com.project.entities.User;

public class CommentReturnDTO {
	
	private long id;
	private String body;
	private Date createdAt;
	private Date modifiedAt;
	private User user;
	
	public long getId() {
		return id;
	}
	public String getBody() {
		return body;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public User getUser() {
		return user;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
