package webservice;

import java.util.List;

import bean.Adresse;
import bean.Annonce;
import bean.Categorie;
import dao.AdresseDAO;
import dao.AnnonceDAO;
import dao.CategorieDAO;
import dao.ConnectionDB;

public class ClientAPI extends API {
	
	/**
	 * Get all ads that match arguments
	 * @param name
	 * @param categoryName
	 * @param street
	 * @param city
	 * @param postcode
	 * @return A JSON indicating whether everything went fine (with results) or not
	 */
	public String getByParams(String name, String categoryName, String street, String city, String postcode) {
		try {
			AnnonceDAO adao = new AnnonceDAO(ConnectionDB.getInstance());
			
			AdresseDAO addao = new AdresseDAO(ConnectionDB.getInstance());
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			
			Annonce a = new Annonce();
			a.setNom(name==null?"":name);
			
			Categorie c = new Categorie(-1, categoryName==null?"":categoryName);
			
			Adresse adr = new Adresse(-1, street==null?"":street, city==null?"":city, postcode==null?"":postcode);
			
			List<Annonce> ret = adao.findByParams(a, c, adr);
			
			Adresse adrOwn = null;
			Categorie catOwn = null;
			
			for (Annonce loop : ret) {
				adrOwn = new Adresse();
				catOwn = new Categorie();
				adrOwn.setAdresse_id(loop.getAdresse_id());
				catOwn.setCategorie_id(loop.getCategorie_id());
				loop.setAdresseObjet(addao.find(adrOwn));
				loop.setCategorieOjet(cdao.find(catOwn));
			}
			
			return json.toJson(ret);
			
		} catch (Exception e) {
			return handleException(e);
		}
	}
}
