package com.project.entities;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Societe implements Serializable {

	@Id
	@GeneratedValue
	private long idSociete;
	private String codeSociete;
	private String nomSociete;
	
	@OneToMany(mappedBy="societe",fetch=FetchType.LAZY)
	private Collection<Ordre> listOrdre;

	public Societe(String codeSociete, String nomSociete) {
		super();
		this.codeSociete = codeSociete;
		this.nomSociete = nomSociete;
	}

	public Societe() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getCodeSociete() {
		return codeSociete;
	}

	public void setCodeSociete(String codeSociete) {
		this.codeSociete = codeSociete;
	}

	public long getIdSociete() {
		return idSociete;
	}

	public void setIdSociete(long idSociete) {
		this.idSociete = idSociete;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	@JsonIgnore
	@XmlTransient
	public Collection<Ordre> getListOrdre() {
		return listOrdre;
	}

	@JsonSetter
	public void setListOrdre(Collection<Ordre> listOrdre) {
		this.listOrdre = listOrdre;
	}

	@Override
	public String toString() {
		return  nomSociete ;
	}
	
	
	
}
