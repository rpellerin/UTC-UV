package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private static Connection singletonInstance;
	
	private ConnectionDB() {
		// Forbid instantiating
	}
	
	public static synchronized Connection getInstance() throws ClassNotFoundException, SQLException {
		if (null == singletonInstance) {
			Class.forName("com.mysql.jdbc.Driver");
			singletonInstance = DriverManager.getConnection("jdbc:mysql://tuxa.sme.utc/sr03p036?useUnicode=true&characterEncoding=UTF-8", "sr03p036", "AAtdzAs0");
        }
        return singletonInstance;
	}
}