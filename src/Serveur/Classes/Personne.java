package Serveur.Classes;

public class Personne{
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	
	public Personne(String _nom, String _prenom, String _login, String _mdp)
	{
		this.nom = _nom;
		this.prenom = _prenom;
		this.login = _login;
		this.mdp = _mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String _nom) {
		this.nom = _nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String _prenom) {
		this.prenom = _prenom;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String _login) {
		this.login = _login;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String _mdp) {
		this.mdp = _mdp;
	}

	
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", login="
				+ login + ", mdp=" + mdp + "]";
	}
	
	
}
