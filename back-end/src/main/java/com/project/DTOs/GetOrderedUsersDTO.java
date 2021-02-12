package com.project.DTOs;

import lombok.Data;

@Data
public class GetOrderedUsersDTO {
	private int order;
	private boolean asc;
	
	public GetOrderedUsersDTO() {
		super();
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
		
}
