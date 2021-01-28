package com.project.services;

import java.util.List;

import com.project.entities.Langue;



public interface LanguageService {
    public void deleteLangue(Langue langue);
	
	public void deleteById(Long id);
	
	public Langue findLangueById(long id);
	
	public List<Langue> getLangues();
	
	public Long addOrUpdateLangue(Langue category);

}
