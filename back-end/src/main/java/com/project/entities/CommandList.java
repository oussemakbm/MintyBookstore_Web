package com.project.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandLists")
public class CommandList implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long idUser;
	private String status;
	private float totalPrice;
	
	public CommandList() {
		super();
	}
	
	public CommandList(long id, long idUser, String status, float totalPrice) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	
	public CommandList(long idUser, String status, float totalPrice) {
		super();
		this.idUser = idUser;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandList other = (CommandList) obj;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandList [id=" + id + ", idUser=" + idUser + ", status=" + status + ", totalPrice=" + totalPrice
				+ "]";
	}

	
	
}
