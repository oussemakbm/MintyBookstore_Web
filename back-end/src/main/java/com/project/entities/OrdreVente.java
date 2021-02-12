package com.project.entities;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;

@Entity
@DiscriminatorValue("Vente")
@XmlType(name="Vente")
public class OrdreVente extends Ordre {
	
	public OrdreVente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdreVente( Date dateOrd, long nbr, double prix) {
		super( dateOrd, nbr, prix);
		// TODO Auto-generated constructor stub
	}

	public OrdreVente( Date dateOrd, long nbr, double prix, Societe societe) {
		super(dateOrd, nbr, prix, societe);
		// TODO Auto-generated constructor stub
	}

}