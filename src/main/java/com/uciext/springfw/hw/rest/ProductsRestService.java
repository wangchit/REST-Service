package com.uciext.springfw.hw.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uciext.springfw.hw.catalog.model.Product;
import com.uciext.springfw.hw.catalog.model.ProductList;
import com.uciext.springfw.hw.catalog.service.CatalogService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/rest")
public class ProductsRestService {
		
	private CatalogService catalogService;
	
	@Autowired
	public void setProductService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF products

	@RequestMapping(value="/products", method=RequestMethod.GET)
	public @ResponseBody ProductList productsList() {
		System.out.println("======= in productsList (REST)");
		List<Product> prdtList = catalogService.getProducts();
		
		// Convert
		ProductList productList = new ProductList();
		for (Product Product : prdtList) {
			productList.getProducts().add(Product);
		}
		
		return productList;
	}
	
	// VIEW Product DETAILS

	@RequestMapping(value="/products/{prdtId}", method=RequestMethod.GET)
	public @ResponseBody Product productView(@PathVariable("prdtId") int cltgId) {
		System.out.println("======= in ProductView (REST)");
		Product product = catalogService.getProduct(cltgId);
		return product;
	}

	// UPDATE Product //Why is there difference between productId & prdtId?
	@RequestMapping(value="/products/{productId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void updateProduct(@PathVariable int productId, @Valid @RequestBody Product product) 
	{
		System.out.println("======= in updateProduct (REST)");
		catalogService.updateProduct(productId, product);
	}
	
	// ADD Product
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public void addProduct(@Valid @RequestBody Product product) {
		System.out.println("======= in addProduct (REST)");
		catalogService.insertProduct(product);
	}

	// DELETE Product

	@RequestMapping(value="/products/{prdtId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void deleteProduct(@PathVariable int prdtId) {
		System.out.println("======= in deleteProduct  (REST)");
	    catalogService.deleteProduct(prdtId);
	}
}
