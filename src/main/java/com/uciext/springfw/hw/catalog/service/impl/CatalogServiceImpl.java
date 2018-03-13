package com.uciext.springfw.hw.catalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalog.dao.CatalogDao;
import com.uciext.springfw.hw.catalog.dao.ProductDao;
import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.Product;
import com.uciext.springfw.hw.catalog.service.CatalogService;


public class CatalogServiceImpl implements CatalogService {

    //private static Logger logger = Logger.getLogger("com.uciext.springfw.hw.catalog.service.impl.CatalogServiceImpl");
    private static Logger logger = Logger.getLogger(CatalogServiceImpl.class.getName());
	
    //Dao
    private ProductDao productDao;
    private CatalogDao catalogDao;
    

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	    
	public void setCatalogDao(CatalogDao catalogDao) {
	 	this.catalogDao = catalogDao;
	}
	
	//Product
	@Override
	public void insertProduct(Product product){
		System.out.println("--- insertProduct() product=" + product);
		productDao.insertProduct(product);
	}   
	
	@Override
	public Product getProduct(int productId) {
		System.out.println("--- getProduct() productId=" + productId);
		return productDao.findProductById(productId);
	}

	@Override
	public List<Product> getProducts() {
		System.out.println("--- getProducts");
		return productDao.findProducts();
	}

	@Override
	public List<Product> getProductsByCatalog(Catalog catalog) {
		System.out.println("--- getProductsByCatalog() catalog=" + catalog);
		return productDao.findProductsByCatalog(catalog);
	}
	
	@Override
	public void updateProduct(int productId, Product product) {
		System.out.println("--- updateProduct() productId=" + productId + ", product=" + product);
		productDao.updateProduct(productId, product);
	}

	@Override
	public void deleteProduct(int productId) {
		System.out.println("--- deleteProduct() productId=" + productId);
		productDao.deleteProduct(productId);
	}

	//Catalog
	@Override
	public void insertCatalog(Catalog catalog){
		System.out.println("--- insertCatalog() catalog=" + catalog);
	    catalogDao.insertCatalog(catalog);
	}
	
    @Override
	public Catalog getCatalog(int catalogId) {
		System.out.println("--- getCatalog() catalogId=" + catalogId);
		return catalogDao.findCatalogById(catalogId);
	}
	
    @Override
	public List<Catalog> getCatalogs() {
		System.out.println("--- getCatalogs()");
		return catalogDao.findCatalogs();
	}
    
    @Override
	public void updateCatalog(int catalogId, Catalog catalog) {
		System.out.println("--- updateCatalog() catalogId=" + catalogId + ", catalog=" + catalog);
		catalogDao.updateCatalog(catalogId, catalog);
	}
	

    @Override
    public void deleteCatalog(int catalogId) {
		System.out.println("--- deleteCatalog() catalogId=" + catalogId);
		catalogDao.deleteCatalog(catalogId);
	}
	    
	
	
}
	
	
	
	

