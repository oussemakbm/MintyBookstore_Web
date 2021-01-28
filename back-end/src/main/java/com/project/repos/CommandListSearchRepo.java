package com.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.project.entities.CommandList;

@RepositoryRestResource
public interface CommandListSearchRepo extends JpaRepository<CommandList, Long>, JpaSpecificationExecutor<CommandList> {

}
