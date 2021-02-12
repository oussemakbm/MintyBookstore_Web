package com.project.services;

import java.util.List;

import com.project.entities.Serie;

public interface SerieService {
	
	public Serie addOrUpdateSerie(Serie serie);
		
	public boolean deleteSerie(long id);
	
	public Serie findSerieById(long id);
	
	public List<Serie> getSeries();
	
	public List<Serie> findSerieByName(String name);
}
