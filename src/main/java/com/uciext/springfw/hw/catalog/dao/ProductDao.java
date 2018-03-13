package com.uciext.springfw.hw.catalog.dao;

import java.util.List;

import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.Product;

public interface ProductDao {
	
	public void insertProduct(Product product);
	public Product findProductById(int productId);
	public List<Product> findProducts();
	public List<Product> findProductsByCatalog(Catalog catalog);
	public void updateProduct(int productId, Product product);
	public void deleteProduct(int productId);

}
