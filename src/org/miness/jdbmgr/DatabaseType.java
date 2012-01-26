package org.miness.jdbmgr;

public enum DatabaseType {

	DERBY_EMBEDDED("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:"),
	DERBY_SERVER("org.apache.derby.jdbc.ClientDriver", "jdbc:derby:"),
	MYSQL("com.mysql.jdbc.Driver", "jdbc:mysql:"),
	POSTGRES("org.postgresql.Driver", "jdbc:postgresql:");
	
	private final String driver;
	private final String protocol;
	
	private DatabaseType(String driver, String protocol) {
		this.driver = driver;
		this.protocol = protocol;
	}
	
	public String getDriverName() {
		return driver;
	}
	
	public String getProtocol() {
		return protocol;
	}
	
}