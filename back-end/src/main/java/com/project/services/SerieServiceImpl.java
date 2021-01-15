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
	public Long addOrUpdateSerie(Serie serie) {
		return serieRepo.save(serie).getId();
	}

	@Override
	public void deleteSerie(Serie serie) {
		serieRepo.delete(serie);
	}

	@Override
	public void deleteById(Long id) {
		serieRepo.deleteById(id);		
	}

	@Override
	public Serie findSerieById(Long id) {
		return serieRepo.findById(id).get();
	}

	@Override
	public List<Serie> getSeries() {
		return (List<Serie>) serieRepo.findAll();
	}
}
