CREATE TABLE IF NOT EXISTS catalogs ( 
		catalog_id INT NOT NULL,
		catalog_Name CHAR(32) NOT NULL, 
        PRIMARY KEY (catalog_id)
        );  

CREATE TABLE IF NOT EXISTS products ( 
        product_id INT NOT NULL,
		product_sku INT NOT NULL, 
        product_name CHAR(128) NOT NULL DEFAULT '', 
        product_unitOfMeasure CHAR(32) NOT NULL, 
        product_price FLOAT NOT NULL, 
        product_availableQuantity INT NOT NULL,
        catalog_id INT NOT NULL,
        created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (product_id),
        FOREIGN KEY(catalog_id) REFERENCES catalogs(catalog_id)
        
        );  
