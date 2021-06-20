package modele.cases;

import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

/**
 * Classe abstraite qui �tend Case mais servant de base � toutes les case pouvant s'acheter au Monopoly : les proprietes
 * @author Mathis
 * @version 1.0
 */

public abstract class Propriete extends Case {
	private Joueur proprietaire;
	private int prixAchat;

	/**
	 * Le constructeur de la classe abstraite Propriete utilise le numero et le nom (Case) + le prix d'achat
	 * Le champs proprietaire est initialis� � null lors de la construction
	 * @param numero le numero de la case (correspondant � son emplacement sur le plateau)
	 * @param nom le nom de la case
	 * @param prix le prix d'achat de la propriete
	 * @throws MonopolyException si jamais le nom n'a pas une forme correcte
	 */
	public Propriete(int numero, String nom, int prix) throws MonopolyException {
		super(numero, nom);
		this.prixAchat = prix;
		this.proprietaire = null;
	}
	
	/**
	 * Retourne le prix d'achat de la propriete
	 * @return le prix d'achat
	 */
	public int getPrixAchat() {
		return prixAchat;
	}

	/**
	 * Retourne le joueur possedant la propriete
	 * @return le proprietaire (un Joueur) de la propriete
	 */
	public Joueur getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * Met � jour le proprietaire de la propriete
	 * @param proprietaire le nouveau proprietaire de la propriete
	 */
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	/**
	 * Impl�mente la m�thode action() de Case
	 * Dans le cas d'une propriete l'action est de faire payer le loyer au joueur arr�t� sur la case
	 * @throws MonopolyException si jamais le joueur present sur la propriete n'a plus assez d'argent pour payer le loyer
	 */
	@Override
	public void action() throws MonopolyException {
		if (this.getProprietaire() != null) {
			if (!Plateau.getInstance().getJoueurPresent().getLesProprietes().contains(this)) { // la propriete n'appartient pas au joueur pr�sent donc il doit payer
				if (this.getLoyer() > Plateau.getInstance().getJoueurPresent().getArgent())
					throw new MonopolyException("Le joueur " + Plateau.getInstance().getJoueurPresent() + " n'a plus assez d'argent pour payer le loyer de " + this.getLoyer() + "�");
				Plateau.getInstance().getJoueurPresent().payer(this.getLoyer(), this.getProprietaire());
			}
		}
	}
	
	/**
	 * M�thode abstraite donnant le loyer d'une propriete
	 * @return le loyer � payer lors d'un arr�t sur cette propriete
	 */
	public abstract int getLoyer();

}
