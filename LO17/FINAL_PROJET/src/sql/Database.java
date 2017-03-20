package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConsoleOutputBuffer;

public class Database {
	
	private final static String username = "lo17xxx";
	private final static String password = "dblo17";
	private final static String url = "jdbc:postgresql://tuxa.sme.utc/dblo17";
	
	private static Connection connection;
	
	private Database() {
		// No instanciation
	}
	
	private synchronized static Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch(java.lang.ClassNotFoundException e) {
				ConsoleOutputBuffer.getInstance().addErrorLine("ClassNotFoundException: ");
				ConsoleOutputBuffer.getInstance().addErrorLine(e.getMessage());
				ConsoleOutputBuffer.getInstance().addStackTrace(e.getStackTrace());
			}
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
	
	public static class Tuple<A,B> {
		public final A headers;
		public final B array;
		
		public Tuple(A a, B b) {
			this.headers = a;
			this.array = b;
		}
	}
	
	public static Tuple<ArrayList<String>,ArrayList<ArrayList<String>>> doRequest(String request, Connection con) throws SQLException {
		if (con == null)
			con = getConnection();
		
		ArrayList<String> headers = new ArrayList<String>();
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(request);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int i;
			for (i = 1; i<=rsmd.getColumnCount();i++) {
				headers.add(rsmd.getColumnName(i));
			}
			
			while (rs.next()) {
				ArrayList<String> innerArray = new ArrayList<String>();
				for (i = 1; i<=rsmd.getColumnCount();i++) {
					innerArray.add(String.valueOf(rs.getObject(i)));
				}
				res.add(innerArray);
			}
		}
		return new Tuple<ArrayList<String>,ArrayList<ArrayList<String>>>(headers, res);
	}
}
