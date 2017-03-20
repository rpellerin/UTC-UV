package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Categorie;

public class CategorieDAO extends DAO<Categorie> {
	
	private final static String TABLE        = "CATEGORIE";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE categorie_id = ?";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(nom) VALUES (?)";
	private final static String UPDATE_QUERY = "UPDATE "+TABLE+" SET nom = ? WHERE categorie_id = ?";
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE categorie_id = ?";
	
	public CategorieDAO(Connection c) {
		super(c);
	}
	
	@Override
	public String getTableName() {
		return TABLE;
	}
	
	@Override
	protected Categorie toBean(ResultSet res, Categorie c) throws SQLException {
		if (c == null) {
			c = new Categorie();
		}
		c.setCategorie_id(res.getInt("categorie_id"));
		c.setNom(res.getString("nom"));
		return c;
	}
	
	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Categorie a, String req) throws SQLException {
		switch (req) {
			case SELECT_QUERY:
			case DELETE_QUERY:
				ps.setInt(1, a.getCategorie_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, a.getNom());
				break;
			case UPDATE_QUERY:
				ps.setString(1, a.getNom());
				ps.setInt(2, a.getCategorie_id());
				break;
			default:
				System.out.println("Problem dao categorie");
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