package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Book;
import com.project.entities.Serie;
import com.project.repos.SerieRepo;

@Service
public class SerieServiceImpl implements SerieService{
	
	@Autowired
	SerieRepo serieRepo;
	
	@Override
	public long addOrUpdateSerie(Serie serie) {
		return serieRepo.save(serie).getId();
	}

	@Override
	public void deleteSerie(long id) {
		serieRepo.deleteById(id);		
	}

	@Override
	public Serie findSerieById(long id) {
		return serieRepo.findById(id).get();
	}

	@Override
	public List<Serie> getSeries() {
		return (List<Serie>) serieRepo.findAll();
	}
}
