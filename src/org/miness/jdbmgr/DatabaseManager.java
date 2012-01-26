package org.miness.jdbmgr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public final class DatabaseManager {
	
	private static DatabaseManager instance;
	private Map<String, Connection> connections;
	
	private DatabaseManager() {
		connections = new HashMap<String, Connection>();
	}
	
	public static DatabaseManager getInstance()  {
		if (instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public void addDatabase(String name, String driver, String url) {
		try {
			Class.forName(driver).newInstance();
			connections.put(name, DriverManager.getConnection(url));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addDatabase(String name, DatabaseType type, String database, String params) {
		addDatabase(name, type.getDriverName(), type.getProtocol() + database + (params == null ? "" : params));
	}
	
	public Database getDatabase(String name) {
		return new Database(connections.get(name));
	}
	
}