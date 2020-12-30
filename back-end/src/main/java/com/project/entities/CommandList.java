package com.project.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="commandLists")
public class CommandList implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "commandlist")
	private List<CommandLine> commandLines;
	
	private String status;
	private float totalPrice;
	
	public CommandList() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommandLine> getCommandLines() {
		return commandLines;
	}

	public void setCommandLines(List<CommandLine> commandLines) {
		this.commandLines = commandLines;
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
	

	public CommandList(long id, User user, List<CommandLine> commandLines, String status, float totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.commandLines = commandLines;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public CommandList(User user, List<CommandLine> commandLines, String status, float totalPrice) {
		super();
		this.user = user;
		this.commandLines = commandLines;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandLines == null) ? 0 : commandLines.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (commandLines == null) {
			if (other.commandLines != null)
				return false;
		} else if (!commandLines.equals(other.commandLines))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandList [id=" + id + ", user=" + user + ", commandLines=" + commandLines + ", status=" + status
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
