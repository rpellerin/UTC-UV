package bean;

public class Annonce {
	private int annonce_id;
	private String nom;
	private int categorie_id;
	private int adresse_id;
	private String telephone;
	
	private Categorie categorieObjet;
	private Adresse adresseObjet;
	
	public Annonce() {}

	public Annonce(int annonce_id, String nom, int categorie_id, int adresse_id, String telephone) {
		super();
		this.annonce_id = annonce_id;
		this.nom = nom;
		this.categorie_id = categorie_id;
		this.adresse_id = adresse_id;
		this.telephone = telephone;
	}

	public int getAnnonce_id() {
		return annonce_id;
	}

	public void setAnnonce_id(int annonce_id) {
		this.annonce_id = annonce_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCategorie_id() {
		return categorie_id;
	}

	public void setCategorie_id(int categorie_id) {
		this.categorie_id = categorie_id;
	}

	public int getAdresse_id() {
		return adresse_id;
	}

	public void setAdresse_id(int adresse_id) {
		this.adresse_id = adresse_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Categorie getCategorieOjet() {
		return categorieObjet;
	}

	public void setCategorieOjet(Categorie categorieObjet) {
		this.categorieObjet = categorieObjet;
	}

	public Adresse getAdresseObjet() {
		return adresseObjet;
	}

	public void setAdresseObjet(Adresse adresseObjet) {
		this.adresseObjet = adresseObjet;
	}
	
}
