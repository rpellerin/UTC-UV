package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Course;
import beans.User;

public class UsersDAO extends DAO<User> {
	
	private final static String TABLE        = "USER";
	private final static String SELECT_QUERY = "SELECT * FROM USER WHERE email = ? and password = ?";
	private final static String SELECT_QUERY2 = "SELECT * FROM USER WHERE name LIKE ?";
	private final static String INSERT_QUERY = "INSERT INTO USER(email,password,name,company,phoneNumber,isAdmin) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String UPDATE_QUERY = ""; // TODO if need be
	private final static String DELETE_QUERY = "DELETE FROM USER WHERE email = ?";
	
	public UsersDAO(Connection c) {
		super(c);
	}
	
	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected User toBean(ResultSet res, User u) throws SQLException {
		if (u == null) {
			u = new User();
		}
		u.setEmail(res.getString("email"));
		u.setPassword(res.getString("password"));
		u.setActive(res.getBoolean("isActive"));
		u.setAdmin(res.getBoolean("isAdmin"));
		u.setCompany(res.getString("company"));
		u.setName(res.getString("name"));
		u.setPhoneNumber(res.getString("phoneNumber"));
		u.setCreation(res.getString("creation"));
		return u;
	}

	@Override
	public User find(User c) throws SQLException {
		if (c.getEmail() != null) return super.find(c);
		PreparedStatement req = super.genericRequest(c, SELECT_QUERY2);
		ResultSet res = req.executeQuery();
		if (!res.first()) {
			System.out.println("null");
			return null;
		}
		return toBean(res,c);
	}
	
	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, User u, String req) throws SQLException {
		switch (req) {
			case SELECT_QUERY:
				ps.setString(1, u.getEmail());
				ps.setString(2, u.getPassword());
				break;
			case SELECT_QUERY2:
				ps.setString(1, "%"+u.getName()+"%");
				break;
			case INSERT_QUERY:
				ps.setString(1, u.getEmail());
				ps.setString(2, u.getPassword());
				ps.setString(3, u.getName());
				ps.setString(4, u.getCompany());
				ps.setString(5, u.getPhoneNumber());
				ps.setBoolean(6, u.isAdmin());
				break;
			case DELETE_QUERY:
				ps.setString(1, u.getEmail());
				break;
		}
		return ps;
	}

	@Override
	protected String getSelectString() {
		return SELECT_QUERY;
	}

	@Override
	protected String getInsertString() {
		return INSERT_QUERY;
	}
	
	@Override
	protected String getUpdateString() {
		return UPDATE_QUERY;
	}
	
	@Override
	protected String getDeleteString() {
		return DELETE_QUERY;
	}
}