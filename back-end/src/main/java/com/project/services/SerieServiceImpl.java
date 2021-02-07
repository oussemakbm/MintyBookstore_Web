package com.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.Serie;
import com.project.repos.SerieRepo;

@Service
public class SerieServiceImpl implements SerieService{
	
	@Autowired
	SerieRepo serieRepo;
	
	@Override
	public Serie addOrUpdateSerie(Serie serie) {
		serieRepo.save(serie);
		return serie;
	}

	@Override
	public boolean deleteSerie(long serie_id) {
		if(!(serieRepo.existsById(serie_id)))
			return false;
		serieRepo.deleteById(serie_id);
		return true;
	}

	@Override
	public Serie findSerieById(long serie_id) {
		if(!(serieRepo.existsById(serie_id)))
			return null;
		return serieRepo.findById(serie_id).get();
	}

	@Override
	public List<Serie> getSeries() {
		return (List<Serie>) serieRepo.findAll();
	}

	@Override
	public List<Serie> findSerieByName(String name) {
		List<Serie> series =  (List<Serie>) serieRepo.findAll();
		return series.stream().filter(s -> s.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
	}
}
