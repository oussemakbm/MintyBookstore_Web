package com.project.repos;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.entities.CommandLine;

@Repository
public interface CommandLineRepo extends CrudRepository<CommandLine, Long> {
	
	/** Retourner Nombre d'achat par Book **/
	@Query("Select sum(c.quantity) from CommandLine c WHERE c.book.id = :idbook")
	public Long  getNumberOfPurchases(@Param("idbook") long idbook);
	
}