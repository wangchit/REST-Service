package com.uciext.springfw.catalog.dao.jdbc;

import com.uciext.springfw.hw.catalog.dao.ProductDao;
import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.catalog.model.Product;
import com.uciext.springfw.hw.catalog.service.CatalogService;
import com.uciext.springfw.hw.common.Util;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class JdbcProductDao implements ProductDao {

	protected static Logger logger = Logger.getLogger(JdbcProductDao.class.getName());
	
	// SQL statements
	private static final String SQL_INSERT_PRODUCT = 
	"INSERT INTO products (product_id, product_sku, product_name, product_unitOfMeasure, product_price, product_availableQuantity, catalog_id)"
	+ " VALUES (?, ?, ?, ?, ?, ?,?)"; //7 values
	
	private static final String SQL_FIND_PRODUCT_BY_ID = 
			"SELECT * FROM products WHERE product_id = ?";
	private static final String SQL_FIND_PRODUCTS = 
			"SELECT * FROM products";
	private static final String SQL_FIND_PRODUCTS_BY_CATALOG_ID = 
			"SELECT * FROM products WHERE product_id = ?";
	private static final String SQL_UPDATE_PRODUCT = 
			"UPDATE products SET product_name = ? WHERE product_id = ?";
	private static final String SQL_DELETE_PRODUCT = 
			"DELETE FROM products WHERE product_id = ?";
			


	
	// Datasource
	private DataSource dataSource;
		 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		

	private CatalogService catalogService;
		 
	public void setCatalogService(CatalogService catalogService) {
		this.catalogService = catalogService;
	}
	
	// DB methods
	@Override
	public void insertProduct(Product product) {
		logger.info("In insertProduct product=" + product);
		Connection conn = null;
			
		try {
			product.setProductId(Util.getRandomInt());
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_PRODUCT);
			ps.setInt(1, product.getProductId());
			ps.setInt(2, product.getSku());
			ps.setString(3, product.getName());
			ps.setString(4, product.getUnitOfMeasure());
			ps.setFloat(5, product.getPrice());
			ps.setInt(6, product.getAvailableQuantity());
			ps.setInt(7, product.getCatalog().getCatalogId());
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
		} 
	
	}
	
	@Override
	public Product findProductById(int productId) {
		logger.info("In findProductById productId=" + productId);
		Connection conn = null;
		Product product = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCT_BY_ID);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product(
					rs.getInt("product_id"),
					rs.getInt("product_sku"),
					rs.getString("product_name"),
					rs.getString("product_unitOfMeasure"),
					rs.getFloat("product_price"),
					rs.getInt("product_availableQuantity"),
					catalogService.getCatalog(rs.getInt("catalog_id")),
					new Date(rs.getTimestamp("created").getTime())
				);
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit findByProductId product=" + product);
		return product;
	}
	
	
	@Override
	public List<Product> findProducts() {
		logger.info("In findProducts");
		Connection conn = null;
		List<Product> products = new ArrayList<Product>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCTS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new Product(
						rs.getInt("product_id"),
						rs.getInt("product_sku"),
						rs.getString("product_name"),
						rs.getString("product_unitOfMeasure"),
						rs.getFloat("product_price"),
						rs.getInt("product_availableQuantity"),
						catalogService.getCatalog(rs.getInt("catalog_id")),
						new Date(rs.getTimestamp("created").getTime())
				));
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit findProducts products.size=" + (products != null?products.size():0));
		return products;
	}

	@Override
	public List<Product> findProductsByCatalog(Catalog catalog) {
		logger.info("In findProductsByCatalog catalog=" + catalog);
		Connection conn = null;
		List<Product> products = new ArrayList<Product>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_PRODUCTS_BY_CATALOG_ID);
			ps.setInt(1, catalog.getCatalogId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new Product(
						rs.getInt("product_id"),
						rs.getInt("product_sku"),
						rs.getString("product_name"),
						rs.getString("product_unitOfMeasure"),
						rs.getFloat("product_price"),
						rs.getInt("product_availableQuantity"),
						catalogService.getCatalog(rs.getInt("catalog_id")),
						new Date(rs.getTimestamp("created").getTime())
				));
			}
			rs.close();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit findProductsByCatalogId products.size=" + (products != null?products.size():0));
		return products;
	}

	
	@Override
	public void updateProduct(int productId, Product product) {
		logger.info("In updateProduct: productId=" + productId + ", product=" + product);
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_PRODUCT);
			ps.setString(1, product.getName());
			ps.setInt(2, productId);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
 
	@Override
	public void deleteProduct(int productId) {
		logger.info("In deleteProduct productId=" + productId);
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_PRODUCT);
			ps.setInt(1, productId);
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		logger.info("exit deleteProduct");
	}	

	
}
