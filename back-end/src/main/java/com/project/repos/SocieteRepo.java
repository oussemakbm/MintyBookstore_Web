package com.project.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entities.Societe;



public interface SocieteRepo extends JpaRepository<Societe, Long> {

	 @Query("select s from Societe s where s.codeSociete like :code")
     public Societe findSocietebyCode(@Param("code") String code);
}