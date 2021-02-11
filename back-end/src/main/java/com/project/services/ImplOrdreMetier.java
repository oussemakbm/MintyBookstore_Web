package com.project.services;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.entities.Ordre;
import com.project.entities.OrdreVente;
import com.project.repos.OrdreRepo;


@Service
public class ImplOrdreMetier implements OrdreMetier {

	@Autowired
	OrdreRepo ordreRepo ;
	
	

	@Override
	public Ordre AjouterOrdreVente(OrdreVente vente) {
		// TODO Auto-generated method stub
		return ordreRepo.save(vente);
	}

	@Override
	public Page<Ordre> GetOrdresBySociete(String soc, Pageable p) {
		// TODO Auto-generated method stub
		return ordreRepo.findBySociete(soc, p);
	}

	@Override
	public double getTotauxActionBySociete(String Soc) {
		// TODO Auto-generated method stub
		
		return ordreRepo.GetTotauxBySociete(Soc);
	}

	@Override
	public double getMoyActionBySociete(String soc) {
		// TODO Auto-generated method stub
		return ordreRepo.GetMoyBySociete(soc);
	}

	@Override
	public Page<OrdreVente> getVentes(Pageable p) {
		// TODO Auto-generated method stub
		return ordreRepo.findVentes(p);
	}

	@Override
	public double getTotauxAction() {
		// TODO Auto-generated method stub
		return ordreRepo.GetTotaux();
	}

	@Override
	public double getMoyAction() {
		// TODO Auto-generated method stub
		return ordreRepo.GetMoyenne();
	}

	@Override
	public Ordre AjouterOrdre(Ordre ordre) {
		// TODO Auto-generated method stub
		ordre.setDateOrd(new Date());
		return ordreRepo.save(ordre);
	}

	

	@Override
	public void deleteOrdre(long id) {
		// TODO Auto-generated method stub
		 ordreRepo.deleteById(id);
	}

	@Override
	public Ordre updateOrdre(Ordre ordre) {
		// TODO Auto-generated method stub
		return ordreRepo.save(ordre);
	}

	@Override
	public List<Ordre> GetOrdresBySociete(String soc) {
		// TODO Auto-generated method stub
		return ordreRepo.findBySociete(soc);
	}

	@Override
	public Page<Ordre> getLastOrdres(Pageable p) {
		// TODO Auto-generated method stub
		return ordreRepo.lastOrdres(p);
	}
	
	

	
}
