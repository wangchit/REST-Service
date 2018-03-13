package com.uciext.springfw.hw.catalog.service;

import java.util.List;

import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.Product;


public interface CatalogService {
	
	//Product
	public void insertProduct(Product product);
	public Product getProduct(int productId);
	public List<Product> getProducts();
	public List<Product> getProductsByCatalog(Catalog catalog);
	public void updateProduct(int productId, Product product);
	public void deleteProduct(int productId);
	
	
	//Catalog
	public void insertCatalog(Catalog catalog);
	public Catalog getCatalog(int catalogId);
	public List<Catalog> getCatalogs();
	public void updateCatalog(int catalogId, Catalog catalog);
	public void deleteCatalog(int catalogId);
	


}
