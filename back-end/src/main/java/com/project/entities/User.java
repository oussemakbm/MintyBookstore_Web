package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User {
	
	private long id;
	private String name;
	private String email;
	

}
