package com.uciext.springfw.hw.frontend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uciext.springfw.hw.catalog.service.CatalogService;
import com.uciext.springfw.hw.catalog.model.Catalog;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	private CatalogService catalogService;
	
//	@Inject
	@Autowired
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	@RequestMapping("/list")
	public ModelAndView catalogList(Model model) {
		System.out.println("======= in catalogList");
		List<Catalog> catalogs = catalogService.getCatalogs();
		model.addAttribute("catalogList", catalogs);
		return new ModelAndView("catalog/catalogList");
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCatalog(Model model) {
		System.out.println("======= in addCatalog");
		model.addAttribute(new Catalog());
		
		
		return "catalog/addCatalog";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addCatalogSave(@Valid Catalog catalog, BindingResult bindingResult) {
		System.out.println("======= in addCatalogSave");
		if(bindingResult.hasErrors()){
			return "catalog/addCatalog";
		}
		catalogService.insertCatalog(catalog);
		return "redirect:/catalog/list.html";
	}

}
