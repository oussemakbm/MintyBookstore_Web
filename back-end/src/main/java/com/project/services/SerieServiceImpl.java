package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.repos.SerieRepo;

public class SerieServiceImpl implements SerieService{
	
	@Autowired
	SerieRepo serieRepo;
	
	@Override
	public Long addSerie(Serie serie) {
		if(serie == null)
			return null;
		return serieRepo.save(serie).getId();
	}

	@Override
	public void deleteSerie(Serie serie) {
		if(serie != null)
			serieRepo.delete(serie);
	}

	@Override
	public void deleteById(Long id) {
		if(id != null)
			serieRepo.deleteById(id);		
	}

	@Override
	public Serie findSerieById(Long id) {
		if(id == null)
			return null;
		return serieRepo.findById(id).get();
	}

	@Override
	public List<Serie> getSeries() {
		return (List<Serie>) serieRepo.findAll();
	}

	@Override
	public Long updateSerie(Serie serie) {
		if(serie == null)
			return null;
		return serieRepo.save(serie).getId();
	}

}
