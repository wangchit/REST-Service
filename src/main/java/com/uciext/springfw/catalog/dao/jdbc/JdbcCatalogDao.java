package com.uciext.springfw.catalog.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.uciext.springfw.hw.catalog.dao.CatalogDao;
import com.uciext.springfw.hw.catalog.model.Catalog;
import com.uciext.springfw.hw.common.Util;

public class JdbcCatalogDao implements CatalogDao{

	protected static Logger logger = Logger.getLogger(JdbcProductDao.class.getName());
	
	// SQL statements
	private static final String SQL_INSERT_CATALOG = 
		    "INSERT INTO catalogs (catalog_id, catalog_name) VALUES (?, ?)";
	private static final String SQL_FIND_CATALOGS = 
			"SELECT * FROM catalogs";
	private static final String SQL_FIND_CATALOG_BY_ID = 
			"SELECT * FROM catalogs WHERE catalog_id = ?";
	private static final String SQL_UPDATE_CATALOG = 
			"UPDATE catalogs SET catalog_name = ? WHERE catalog_id = ?";
	private static final String SQL_DELETE_CATALOG = 
			"DELETE FROM catalogs WHERE catalog_id = ?";
	
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	// DB methods
	@Override
	public void insertCatalog(Catalog catalog) {
		logger.info("In insertProduct catalog=" + catalog);
		Connection conn = null;

			
		try {
			
			catalog.setCatalogId(Util.getRandomInt());
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CATALOG);
			ps.setInt(1, catalog.getCatalogId());
			ps.setString(2, catalog.getCatalogName());
			
		
			ps.executeUpdate();
			ps.close();
		} 
		catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	    } 
	
	}
	
	@Override
	public Catalog findCatalogById(int catalogId) {
		logger.info("In findByCatalogId catalogId=" + catalogId);
		Connection conn = null;
		Catalog catalog = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_CATALOG_BY_ID);
			ps.setInt(1, catalogId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				catalog = new Catalog(
					rs.getInt("catalog_id"),
					rs.getString("catalog_name")
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
		logger.info("exit findByCatalogId catalog=" + catalog);
		return catalog;
	}
	
	@Override
	public List<Catalog> findCatalogs() {
		logger.info("In findCatalogs");
		Connection conn = null;
		List<Catalog> catalogs = new ArrayList<Catalog>();
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_CATALOGS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				catalogs.add(new Catalog(
					rs.getInt("catalog_id"),
					rs.getString("catalog_name")
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
		logger.info("exit findCatalogs catalogs.size=" + (catalogs != null ? catalogs.size() : 0));
		return catalogs;
	}
	
	@Override
	public void updateCatalog(int catalogId, Catalog catalog) {
		logger.info("In updateCatalog: catalogId=" + catalogId + ", catalog=" + catalog);
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_CATALOG);
			ps.setString(1, catalog.getCatalogName());
			ps.setInt(2, catalogId);
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
	public void deleteCatalog(int catalogId) {
		logger.info("In deleteCatalog catalogId=" + catalogId);
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_DELETE_CATALOG);
			ps.setInt(1, catalogId);
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
		logger.info("exit deleteCatalog");
	}
	
	
	
	
	
}
