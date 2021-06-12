package modele.cases;

import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

public abstract class Propriete extends Case {
	private Joueur proprietaire;
	private int prixAchat;

	public Propriete(int numero, String nom, int prix) throws MonopolyException {
		super(numero, nom);
		this.prixAchat = prix;
		this.proprietaire = null;
	}
	
	public int getPrixAchat() {
		return prixAchat;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	@Override
	public void action() throws MonopolyException {
		if (this.getProprietaire() != null) {
			if (!Plateau.getInstance().getJoueurPresent().getLesProprietes().contains(this)) { // la propriete n'appartient pas au joueur présent donc il doit payer
				if (this.getLoyer() > Plateau.getInstance().getJoueurPresent().getArgent())
					throw new MonopolyException("Le joueur " + Plateau.getInstance().getJoueurPresent() + " n'a plus assez d'argent pour payer le loyer de " + this.getLoyer() + "€");
				Plateau.getInstance().getJoueurPresent().payer(this.getLoyer(), this.getProprietaire());
			}
		}
	}
	
	public abstract int getLoyer();

}
