package webservice;

import java.util.List;

import bean.Adresse;
import bean.Annonce;
import bean.Categorie;
import dao.AdresseDAO;
import dao.AnnonceDAO;
import dao.CategorieDAO;
import dao.ConnectionDB;

public class AdminAPI extends API {	
	
	/**
	 * Creates a new category
	 * @param name The name of the category
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String createCategory(String name) {
		try {
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			Categorie c = new Categorie();
			c.setNom(name);
			boolean ret = cdao.insert(c);
			if (!ret) {
				return json.toJson("Category couldn't be created.");
			}
			else {
				Categorie cat = new Categorie(cdao.getLastId(), name);
				return json.toJson(cat);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Deletes a category
	 * @param name The id of the category to delete
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String deleteCategorie(int categorie_id) {
		try {
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			Categorie c = new Categorie();
			c.setCategorie_id(categorie_id);
			boolean ret = cdao.delete(c);
			if (!ret) {
				return json.toJson("Category couldn't be deleted.");
			}
			else {
				return json.toJson(true);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Updates a category
	 * @param categorie_id The id of the category to update
	 * @param name The new name of the category
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String updateCategorie(int categorie_id, String name) {
		try {
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			Categorie c = new Categorie(categorie_id, name);
			boolean ret = cdao.update(c);
			if (!ret) {
				return json.toJson("Category couldn't be updated.");
			}
			else {
				return json.toJson(true);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Get all categories
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String getAllCategorie() {
		try {
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			List<Categorie> ret = cdao.findAll();
			return json.toJson(ret);
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Find a given category
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String getCategory(int category_id) {
		try {
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			Categorie c = new Categorie(category_id, null);
			c = cdao.find(c);
			return json.toJson(c);
			
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Creates a new ad
	 * @param name The name of the ad
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String createAd(String name, int categorie_id, String rue, String ville, String codepostal, String telephone) {
		try {
			AdresseDAO adresseDAO = new AdresseDAO(ConnectionDB.getInstance());
			AnnonceDAO annonceDAO = new AnnonceDAO(ConnectionDB.getInstance());
			
			Adresse adresse = new Adresse(-1,rue,ville,codepostal);
			Annonce annonce = new Annonce(-1,name,categorie_id,-1,telephone);
			
			if (adresseDAO.insert(adresse)) {
				annonce.setAdresse_id(adresseDAO.getLastId());
				if (!annonceDAO.insert(annonce)) {
					return json.toJson("Ad couldn't be created.");
				}
				else {
					annonce.setAnnonce_id(annonceDAO.getLastId());
					return json.toJson(annonce);
				}
			}
			else {
				return json.toJson("Address couldn't be created.");
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Deletes an ad
	 * @param name The id of the ad to delete
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String deleteAd(int annonce_id) {
		try {
			AnnonceDAO adao = new AnnonceDAO(ConnectionDB.getInstance());
			Annonce a = new Annonce();
			a.setAnnonce_id(annonce_id);
			
			boolean ret = adao.delete(a);
			if (!ret) {
				return json.toJson("Ad couldn't be deleted.");
			}
			else {
				return json.toJson(true);
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
		
	/**
	 * Updates an ad
	 * @param annonce_id The id of the ad to update
	 * @param name
	 * @param categorie_id
	 * @param rue
	 * @param ville
	 * @param codepostal
	 * @param telephone
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String updateAd(int annonce_id, String name, int categorie_id, String rue, String ville, String codepostal, String telephone) {
		try {
			AdresseDAO adresseDAO = new AdresseDAO(ConnectionDB.getInstance());
			AnnonceDAO annonceDAO = new AnnonceDAO(ConnectionDB.getInstance());
			
			Adresse adresse = new Adresse(-1,rue,ville,codepostal);
			Annonce annonce = new Annonce();
			annonce.setAnnonce_id(annonce_id);
			
			annonce = annonceDAO.find(annonce);
			if (annonce == null) {
				return json.toJson("Ad doesnt't exist.");
			}
			else {
				adresse.setAdresse_id(annonce.getAdresse_id());
				annonce.setNom(name);
				annonce.setCategorie_id(categorie_id);
				annonce.setTelephone(telephone);
				
				if (adresseDAO.update(adresse)) {
					if (annonceDAO.update(annonce)) {
						return json.toJson(true);
					}
					else {
						return json.toJson("Couldn't update the ad.");
					}
				}
				else {
					return json.toJson("Couldn't update the adress.");
					
				}
			}
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Get all ads
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String getAllAd() {
		try {
			AnnonceDAO adao = new AnnonceDAO(ConnectionDB.getInstance());
			
			AdresseDAO addao = new AdresseDAO(ConnectionDB.getInstance());
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			
			List<Annonce> ret = adao.findAll();
			
			Adresse adr = null;
			Categorie cat = null;
			
			for (Annonce a : ret) {
				adr = new Adresse();
				cat = new Categorie();
				adr.setAdresse_id(a.getAdresse_id());
				cat.setCategorie_id(a.getCategorie_id());
				a.setAdresseObjet(addao.find(adr));
				a.setCategorieOjet(cdao.find(cat));
			}
			return json.toJson(ret);
			
		} catch (Exception e) {
			return handleException(e);
		}
	}
	
	/**
	 * Find a given ad
	 * @return A JSON indicating whether everything went fine or not
	 */
	public String getAd(int annonce_id) {
		try {
			AnnonceDAO dao = new AnnonceDAO(ConnectionDB.getInstance());
			
			AdresseDAO addao = new AdresseDAO(ConnectionDB.getInstance());
			CategorieDAO cdao = new CategorieDAO(ConnectionDB.getInstance());
			
			Annonce a = new Annonce();
			a.setAnnonce_id(annonce_id);
			a = dao.find(a);
			
			Adresse adr = new Adresse();
			Categorie cat = new Categorie();
			
			adr.setAdresse_id(a.getAdresse_id());
			cat.setCategorie_id(a.getCategorie_id());
			a.setAdresseObjet(addao.find(adr));
			a.setCategorieOjet(cdao.find(cat));
			
			return json.toJson(a);
			
		} catch (Exception e) {
			return handleException(e);
		}
	}
}
