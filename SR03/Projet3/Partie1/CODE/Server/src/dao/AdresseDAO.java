package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Adresse;

public class AdresseDAO extends DAO<Adresse> {
	
	private final static String TABLE        = "ADRESSE";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE adresse_id = ?";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(rue, ville, codepostal) VALUES (?,?,?)";
	private final static String UPDATE_QUERY = "UPDATE "+TABLE+" SET rue = ?, ville = ?, codepostal = ? where adresse_id = ?";
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE adresse_id = ?";
	
	public AdresseDAO(Connection c) {
		super(c);
	}
	
	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	protected Adresse toBean(ResultSet res, Adresse a) throws SQLException {
		if (a == null) {
			a = new Adresse();
		}
		a.setAdresse_id(res.getInt("adresse_id"));
		a.setRue(res.getString("rue"));
		a.setVille(res.getString("ville"));
		a.setCodepostal(res.getString("codepostal"));
		return a;
	}
	
	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Adresse a, String req) throws SQLException {
		switch (req) {
			case SELECT_QUERY:
			case DELETE_QUERY:
				ps.setInt(1, a.getAdresse_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, a.getRue());
				ps.setString(2, a.getVille());
				ps.setString(3, a.getCodepostal());
				break;
			case UPDATE_QUERY:
				ps.setString(1, a.getRue());
				ps.setString(2, a.getVille());
				ps.setString(3, a.getCodepostal());
				ps.setInt(4, a.getAdresse_id());
				break;
			default:
				System.out.println("Problem dao adresse");
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