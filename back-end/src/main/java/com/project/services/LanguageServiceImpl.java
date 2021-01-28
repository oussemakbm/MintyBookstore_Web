package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.Langue;
import com.project.repos.LanguageRepo;

public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	LanguageRepo languageRepo;

	@Override
	public void deleteLangue(Langue langue) {
		languageRepo.delete(langue);
		
	}

	@Override
	public void deleteById(Long id) {
		languageRepo.deleteById(id);
		
	}

	@Override
	public Langue findLangueById(long id) {
		// TODO Auto-generated method stub
		return languageRepo.findById(id).get();
	}

	@Override
	public List<Langue> getLangues() {
		// TODO Auto-generated method stub
		return (List<Langue>) languageRepo.findAll();
	}

	@Override
	public Long addOrUpdateLangue(Langue langue) {
		languageRepo.save(langue);
		return langue.getId();
	}

}
