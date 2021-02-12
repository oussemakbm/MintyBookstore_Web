package com.project.services;

import java.util.Date;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.entities.Ordre;
import com.project.entities.OrdreVente;

public interface OrdreMetier  {

	public Ordre AjouterOrdre(Ordre ordre);
	public Ordre AjouterOrdreVente(OrdreVente vente); 
	public Page<OrdreVente> getVentes(Pageable p);
	public Page<Ordre> GetOrdresBySociete(String soc, Pageable p);
	public List<Ordre> GetOrdresBySociete(String soc);
	public double getTotauxActionBySociete(String Soc);
	public double getMoyActionBySociete(String soc);
	public double getTotauxAction();
	public double getMoyAction();
	public void deleteOrdre(long id);
	public Ordre updateOrdre(Ordre ordre);
	public Page<Ordre> getLastOrdres(Pageable p);
	
}