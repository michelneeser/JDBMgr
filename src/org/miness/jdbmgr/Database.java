package org.miness.jdbmgr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Database {

	private Connection connection;
	private Statement statement;
	private Map<String, Savepoint> savepoints;
	
	protected Database(Connection connection) {
		try {
			this.connection = connection;
			this.statement = connection.createStatement();
			this.connection.setAutoCommit(false);
			savepoints = new HashMap<String, Savepoint>();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean execute(String sql) {
		boolean retVal = false;
		try {
			retVal = statement.execute(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public ResultSet executeQuery(String sql) {
		ResultSet returner = null;
		try {
			returner = statement.executeQuery(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return returner;
	}
	
	public void commit() {
		try {
			connection.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			connection.rollback();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(String savepoint) {
		try {
			connection.rollback(savepoints.get(savepoint));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setSavepoint(String name) {
		try {
			savepoints.put(name, connection.setSavepoint(name));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void clearSavepoints() {
		savepoints.clear();
	}
	
}