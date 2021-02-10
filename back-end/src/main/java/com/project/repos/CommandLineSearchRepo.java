package com.project.repos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.entities.Book;
import com.project.entities.CommandLine;

@Repository
public interface CommandLineSearchRepo extends PagingAndSortingRepository<CommandLine, Long>, JpaSpecificationExecutor<CommandLine> {

}
