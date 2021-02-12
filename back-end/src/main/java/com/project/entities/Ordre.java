package com.project.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length=10)


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,include=JsonTypeInfo.As.PROPERTY,property="type")

@JsonSubTypes({
	
	@Type(name="Vente",value=OrdreVente.class)
})

@XmlSeeAlso({OrdreVente.class})
public abstract class Ordre implements Serializable {
   
	@Id
	@GeneratedValue
	private long idOrdre;
	private Date dateOrd;
	private long nbr;
	private double prix;
	
	@ManyToOne
	@JoinColumn(name="idSociete")
	@JsonProperty("societe")
	private Societe societe;

	public Ordre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ordre( Date dateOrd, long nbr, double prix, Societe societe) {
		super();
		this.dateOrd = dateOrd;
		this.nbr = nbr;
		this.prix = prix;
		this.societe = societe;
	}

	public Ordre( Date dateOrd, long nbr, double prix) {
		super();
		this.dateOrd = dateOrd;
		this.nbr = nbr;
		this.prix = prix;
	}

	public long getIdOrdre() {
		return idOrdre;
	}

	public void setIdOrdre(long idOrdre) {
		this.idOrdre = idOrdre;
	}

	public Date getDateOrd() {
		return dateOrd;
	}

	public void setDateOrd(Date dateOrd) {
		this.dateOrd = dateOrd;
	}

	public long getnbr() {
		return nbr;
	}

	public void setnbr(long nbr) {
		this.nbr = nbr;
	}

	public Ordre(long nbr, double prix, Societe societe) {
		super();
		this.nbr = nbr;
		this.prix = prix;
		this.societe = societe;
	}

	public double getprix() {
		return prix;
	}

	public void setprix(double prix) {
		this.prix = prix;
	}

	@JsonIgnore
	@XmlTransient
	public Societe getSociete() {
		return societe;
	}
    
	@JsonSetter
	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@Override
	public String toString() {
		return "Ordre [idOrdre=" + idOrdre + ", dateOrd=" + dateOrd + ", nbr=" + nbr + ", prix="
				+ prix + ", societe=" + societe + "]";
	}
	
}