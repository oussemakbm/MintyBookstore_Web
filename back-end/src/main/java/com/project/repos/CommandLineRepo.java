package com.project.repos;

import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;
import com.project.entities.CommandLine;

@Repository
public interface CommandLineRepo extends CrudRepository<CommandLine, Long> {
	
	/** Retourner Nombre d'achat par Book **/
	@Query("Select sum(c.quantity) from CommandLine c WHERE c.book.id = :idbook")
	public Long  getNumberOfPurchases(@Param("idbook") long idbook);

	//** top five books **//
	@Query (value= "select b.title from command_lines  join books b on command_lines.book_id=b.id group by book_id order by count(*) DESC LIMIT 5",nativeQuery= true )
	List<String> gettopfiveofbooks();
	
     

}