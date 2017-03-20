package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Adresse;
import bean.Annonce;
import bean.Categorie;

public class AnnonceDAO extends DAO<Annonce> {
	
	private final static String TABLE        = "ANNONCE";
	private final static String SELECT_QUERY = "SELECT * FROM "+TABLE+" WHERE annonce_id = ?";
	private final static String INSERT_QUERY = "INSERT INTO "+TABLE+"(nom, categorie_id, adresse_id, telephone) VALUES (?,?,?,?)";
	private final static String UPDATE_QUERY = "UPDATE "+TABLE+" SET nom = ?, categorie_id = ?, telephone = ? where annonce_id = ?";
	private final static String DELETE_QUERY = "DELETE FROM "+TABLE+" WHERE annonce_id = ?";
	
	private final static String SEARCH       = "SELECT a.*, adr.rue, adr.ville, adr.codepostal, c.nom "
			+ "FROM ANNONCE a, ADRESSE adr, CATEGORIE c "
			+ "WHERE a.adresse_id = adr.adresse_id AND a.categorie_id = c.categorie_id AND "
			+ "a.nom LIKE ? AND c.nom LIKE ? AND adr.rue LIKE ? and adr.ville LIKE ? and adr.codepostal LIKE ?";
	
	public AnnonceDAO(Connection c) {
		super(c);
	}
	
	@Override
	public String getTableName() {
		return TABLE;
	}

	public List<Annonce> findByParams(Annonce a, Categorie c, Adresse adr) throws SQLException {
		PreparedStatement req = getConn().prepareStatement(SEARCH);
		req.setString(1, "%"+a.getNom()+"%");
		req.setString(2, "%"+c.getNom()+"%");
		req.setString(3, "%"+adr.getRue()+"%");
		req.setString(4, "%"+adr.getVille()+"%");
		req.setString(5, "%"+adr.getCodepostal()+"%");
		ResultSet rs = req.executeQuery();
		
		ArrayList<Annonce> res = new ArrayList<Annonce>();
		while (rs.next()) {
			res.add(toBean(rs,null));
		}
		return res;
	}
	
	@Override
	protected Annonce toBean(ResultSet res, Annonce a) throws SQLException {
		if (a == null) {
			a = new Annonce();
		}
		a.setAnnonce_id(res.getInt("annonce_id"));
		a.setNom(res.getString("nom"));
		a.setCategorie_id(res.getInt("categorie_id"));
		a.setAdresse_id(res.getInt("adresse_id"));
		a.setTelephone(res.getString("telephone"));
		return a;
	}
	
	@Override
	protected PreparedStatement prepareStatementFromBean(PreparedStatement ps, Annonce a, String req) throws SQLException {
		switch (req) {
			case SELECT_QUERY:
			case DELETE_QUERY:
				ps.setInt(1, a.getAnnonce_id());
				break;
			case INSERT_QUERY:
				ps.setString(1, a.getNom());
				ps.setInt(2, a.getCategorie_id());
				ps.setInt(3, a.getAdresse_id());
				ps.setString(4, a.getTelephone());
				break;
			case UPDATE_QUERY:
				ps.setString(1, a.getNom());
				ps.setInt(2, a.getCategorie_id());
				ps.setString(3, a.getTelephone());
				ps.setInt(4, a.getAnnonce_id());
				break;
			default:
				System.out.println("Problem dao annonce");
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