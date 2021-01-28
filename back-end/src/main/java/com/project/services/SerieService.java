package com.project.services;

import java.util.List;

import com.project.entities.Serie;

public interface SerieService {
	
	public long addOrUpdateSerie(Serie serie);
		
	public void deleteSerie(long id);
	
	public Serie findSerieById(long id);
	
	public List<Serie> getSeries();
}
