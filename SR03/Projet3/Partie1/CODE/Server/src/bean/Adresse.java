package bean;

public class Adresse {
	private int adresse_id;
	private String rue;
	private String ville;
	private String codepostal;
	
	public Adresse() { }

	public Adresse(int adresse_id, String rue, String ville, String codepostal) {
		super();
		this.adresse_id = adresse_id;
		this.rue = rue;
		this.ville = ville;
		this.codepostal = codepostal;
	}

	public int getAdresse_id() {
		return adresse_id;
	}

	public void setAdresse_id(int adresse_id) {
		this.adresse_id = adresse_id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
}