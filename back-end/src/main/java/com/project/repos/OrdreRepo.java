package com.project.repos;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entities.Ordre;
import com.project.entities.OrdreVente;

public interface OrdreRepo extends JpaRepository<Ordre, Long>  {

	
	@Query("select a from OrdreVente a ")
	public Page<OrdreVente> findVentes(Pageable p);
	
	@Query("select o from Ordre o Order by o.dateOrd DESC ")
	public Page<Ordre> lastOrdres(Pageable p);
	
	@Query("select o from Ordre o where o.societe.codeSociete = :soc Order by o.dateOrd DESC ")
	public Page<Ordre> findBySociete(@Param("soc") String Societe,Pageable p);
	
	@Query("select o from Ordre o where o.societe.codeSociete = :soc ")
	public List<Ordre> findBySociete(@Param("soc") String Societe);
	
	@Query("select SUM(o.nbAction*o.prixAction) as total from Ordre o where o.societe.codeSociete = :soc ")
	public double GetTotauxBySociete(@Param("soc") String Societe);
	
	@Query("select SUM(o.nbAction*o.prixAction)/SUM(o.nbAction) as moyenne from Ordre o where o.societe.codeSociete = :soc ")
	public double GetMoyBySociete(@Param("soc") String Societe);

	
	@Query("select SUM(o.nbAction*o.prixAction) as total from Ordre o ")
	public double GetTotaux();
	
	@Query("select SUM(o.nbAction*o.prixAction)/SUM(o.nbAction) as moyenne from Ordre o  ")
	public double GetMoyenne();
	
}
