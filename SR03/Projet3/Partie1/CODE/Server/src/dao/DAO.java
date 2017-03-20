package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T>{
	
	private Connection conn;
	
	public DAO(Connection c) {
		this.conn = c;
	}
	
	/**
	 * Returns the DB connection
	 * @return The DB connection
	 */
	protected Connection getConn() {
		return this.conn;
	}

	/**
	 * Counts the number or records in DB
	 * @return The number of records in DB for this table
	 * @throws SQLException When an error occurs
	 */
	public int count() throws SQLException {
		ResultSet rs = getAllFromDB();

		int res = 0;
		while (rs.next()) {
			res++;
		}
		return res;
	}
	
	private final ResultSet getAllFromDB() throws SQLException {
		PreparedStatement findReq = null;
		
		String findString = "SELECT * FROM "+getTableName();
		
		findReq = getConn().prepareStatement(findString);
		return findReq.executeQuery();
	}
	/**
	 * Finds all the element for this table
	 * @return The list of elements
	 * @throws SQLException When an error occurs
	 */
	public final List<T> findAll() throws SQLException {
		ResultSet rs = getAllFromDB();
		ArrayList<T> res = new ArrayList<T>();
		
		while (rs.next()) {
			res.add(toBean(rs,null));
		}
		return res;
	}
	
	/**
	 * Transform a SQL query result into a bean model.
	 * @param rs The SQL query result
	 * @param m Optional (can be null): if given, this model will be modified by the result and returned
	 * @return The bean model populated from the DB
	 */
	protected abstract T toBean(ResultSet rs, T m) throws SQLException;
	
	/**
	 * Gives the table's name in DB
	 * @return The name in DB used for this DAO
	 */
	public abstract String getTableName();
	
	protected abstract String getSelectString();
	protected abstract String getInsertString();
	protected abstract String getUpdateString();
	protected abstract String getDeleteString();
	
	/**
	 * Prepare a {@link PrepareStatement} by fulfilling the fields
	 * @param ps The {@link PrepareStatement} to use
	 * @param u The bean model to use to fulfill the statement
	 * @return The ps argument
	 * @return The SQL request about to be executed
	 * @throws SQLException In case something wrong happens
	 */
	protected abstract PreparedStatement prepareStatementFromBean(PreparedStatement ps, T u, String req) throws SQLException;
	
	
	/**
	 * Find a specific element
	 * @param o The element to use to find the complement one in DB
	 * @param query An optional query
	 * @return The complete element or null if not found
	 * @throws SQLException 
	 */
	public T find(T o) throws SQLException {
		PreparedStatement req = genericRequest(o, getSelectString());
		ResultSet res = req.executeQuery();
		if (!res.first())
			return null;
		return toBean(res,o);
	}
	
	/**
	 * Inserts an element in DB
	 * @param o The element to insert
	 * @return True if added, false otherwise
	 * @throws SQLException 
	 */
	public final boolean insert(T o) throws SQLException {
		PreparedStatement req = genericRequest(o, getInsertString());
		int res = req.executeUpdate();
		return res == 1;
	}
	
	/**
	 * Update an element in DB
	 * @param o The element to update; the search for this element will be based on its ID
	 * @return True if updated, false otherwise
	 * @throws SQLException 
	 */
	public boolean update(T o) throws SQLException {
		PreparedStatement req = genericRequest(o, getUpdateString());
		int res = req.executeUpdate();
		return res == 1;
	}
	
	/**
	 * Delete an element from DB
	 * @param o The element to update; the search for this element will be based on its ID
	 * @return True if deleted, false otherwise
	 * @throws SQLException 
	 */
	public boolean delete(T o) throws SQLException {
		PreparedStatement req = genericRequest(o, getDeleteString());
		int res = req.executeUpdate();
		return res == 1;
	}
	
	/**
	 * Create a basic generic SQL request
	 * @param o The object one which the request will be based
	 * @param reqString The SQL request itself
	 * @return The {@link PreparedStatement}
	 * @throws SQLException When something wrong happens
	 */
	protected PreparedStatement genericRequest(T o, String reqString) throws SQLException {
		PreparedStatement req = getConn().prepareStatement(reqString);
		prepareStatementFromBean(req, o, reqString);
		return req;
	}
	
	/**
	 * Retrives the last ID created following an INSERT
	 * @return The ID if any, or -1
	 * @throws SQLException When something wrong happens
	 */
	public final int getLastId() throws SQLException {
		PreparedStatement req = null;
		req = getConn().prepareStatement("select LAST_INSERT_ID() as id");
		
		ResultSet res = req.executeQuery();
		if (!res.first())
			return -1;
		return res.getInt("id");
	}
}