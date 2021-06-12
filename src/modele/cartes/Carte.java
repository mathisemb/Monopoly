package modele.cartes;

public abstract class Carte {
	private String nom;
	
	public String getNom() {
		return nom;
	}

	public Carte(String nom) {
		this.nom = nom;
	}

	public abstract void actionCarte();
}
