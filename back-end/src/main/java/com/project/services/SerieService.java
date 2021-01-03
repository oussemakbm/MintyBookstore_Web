package com.project.services;

import java.util.List;

import com.project.entities.Serie;

public interface SerieService {
	
	public Long addSerie(Serie serie);
	
	public void deleteSerie(Serie serie);
	
	public void deleteById(Long id);
	
	public Serie findSerieById(Long id);
	
	public List<Serie> getSeries();
	
	public Long updateSerie(Serie serie);

}
