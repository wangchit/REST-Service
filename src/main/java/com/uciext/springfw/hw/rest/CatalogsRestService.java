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

import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.CatalogList;
import com.uciext.springfw.hw.catalog.service.CatalogService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("/rest")
public class CatalogsRestService {
		
	private CatalogService catalogService;
	
	@Autowired
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// VIEW LIST OF catalogs

	@RequestMapping(value="/catalogs", method=RequestMethod.GET)
	public @ResponseBody CatalogList catalogsList() {
		System.out.println("======= in catalogsList (REST)");
		List<Catalog> ctlgList = catalogService.getCatalogs();
		
		// Convert
		CatalogList catalogList = new CatalogList();
		for (Catalog Catalog : ctlgList) {
			catalogList.getCatalogs().add(Catalog);
		}
		
		return catalogList;
	}
	
	// VIEW Catalog DETAILS

	@RequestMapping(value="/catalogs/{ctlgId}", method=RequestMethod.GET)
	public @ResponseBody Catalog catalogView(@PathVariable("ctlgId") int cltgId) {
		System.out.println("======= in CatalogView (REST)");
		Catalog catalog = catalogService.getCatalog(cltgId);
		return catalog;
	}

	// UPDATE Catalog //Why is there difference between catalogId & ctlgId?
    /*the {catalogId} will pass to the first parameter of the method updateCatalog
	the catalog info will be written in xml format and pass to catalog object. See below example.*/
	@RequestMapping(value="/catalogs/{catalogId}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void updateCatalog(@PathVariable int catalogId, @Valid @RequestBody Catalog catalog) 
	{
		System.out.println("======= in updateCatalog (REST)");
		catalogService.updateCatalog(catalogId, catalog);
	}

	/*curl -X PUT -H "Content-Type:application/xml" -v --data 
	"<catalog><catalogId>166002</catalogId><catalogName>Networking3</catalogName></catalog>" 
	"http://localhost:8080/rest/catalogs/166002"*/
	
	// ADD Catalog
	
	@RequestMapping(value="/catalogs", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public void addCatalog(@Valid @RequestBody Catalog catalog) {
		System.out.println("======= in addCatalog (REST)");
		catalogService.insertCatalog(catalog);
	}

	// DELETE Catalog

	@RequestMapping(value="/catalogs/{ctlgId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void deleteCatalog(@PathVariable int ctlgId) {
		System.out.println("======= in deleteCatalog  (REST)");
	    catalogService.deleteCatalog(ctlgId);
	}
}
