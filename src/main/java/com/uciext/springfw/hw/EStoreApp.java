package com.uciext.springfw.hw;

import java.util.List;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.Product;
import com.uciext.springfw.hw.catalog.service.CatalogService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource(locations = {
"file:./src/main/resources/META-INF/spring/EStoreConfig.xml"
})
public class EStoreApp {

	public static void main(String[] args) throws Exception {
		//Springboot get up
		SpringApplication.run(EStoreApp.class,args);
		System.out.println("Application Started.");
		
		EStoreApp app = new EStoreApp();
        app.run();
    }
	

  public void run() {
	
	// Retrieve the bean using application context
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/EStoreConfig.xml");
	CatalogService catalogService = (CatalogService)context.getBean("catalogService");
	
	
	catalogService.insertCatalog(new Catalog(0, "Java Book"));
	catalogService.insertCatalog(new Catalog(0, "MicroService Book"));
	List<Catalog> catalogs = catalogService.getCatalogs();
	
	catalogService.insertProduct(new Product(0, 10, "Spring MVC", "e2", 12.5F, 2, catalogs.get(0), null));
	catalogService.insertProduct(new Product(0, 12, "Java EE", "e4", 14.5F, 4, catalogs.get(0), null));
	catalogService.insertProduct(new Product(0, 14, "Docker", "e6", 16.5F, 6, catalogs.get(1), null));
	catalogService.insertProduct(new Product(0, 16, "AWS Solutions", "e8", 18.5F, 8, catalogs.get(1), null));


	
  }
}
