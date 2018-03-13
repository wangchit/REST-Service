package com.uciext.springfw.hw.catalog.model;

import java.lang.StringBuilder;
import java.util.Map;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "catalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

	//@Size(min=3, max=15, message="Catalog name must be between 3 and 15 characters long.") 
	@XmlElement
    private int catalogId;
	private String catalogName;
    
	public Catalog() {
		
	}
	
	public Catalog(int catalogId, String catalogName) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;
	}
    
    public int getCatalogId() {
        return catalogId;
    }
    public void setCatalogId(int value) {
        this.catalogId = value;
    }
    
    public String getCatalogName() {
        return catalogName;
    }
    public void setCatalogName(String value) {
        this.catalogName = value;
    }
    
   
 

}
