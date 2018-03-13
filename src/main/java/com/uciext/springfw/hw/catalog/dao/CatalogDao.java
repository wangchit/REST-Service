package com.uciext.springfw.hw.catalog.dao;



import java.util.List;

import com.uciext.springfw.hw.catalog.model.Catalog;


public interface CatalogDao {
	
	public void insertCatalog(Catalog catalog);
	public Catalog findCatalogById(int catalogId);
	public List<Catalog> findCatalogs();
	public void updateCatalog(int catalogId, Catalog catalog);
	public void deleteCatalog(int catalogId);
	
	

}
