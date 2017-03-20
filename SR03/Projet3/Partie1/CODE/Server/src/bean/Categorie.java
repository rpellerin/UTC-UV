package bean;

public class Categorie {
	private int categorie_id;
	private String nom;
	
	public Categorie() {}

	public Categorie(int categorie_id, String nom) {
		super();
		this.categorie_id = categorie_id;
		this.nom = nom;
	}

	public int getCategorie_id() {
		return categorie_id;
	}

	public void setCategorie_id(int categorie_id) {
		this.categorie_id = categorie_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
