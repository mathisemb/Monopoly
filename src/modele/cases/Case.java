package modele.cases;

import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;
import ui.UICase;

/**
 * Classe abstraite servant de base à toutes les case du plateau de Monopoly
 * @author Mathis
 * @version 1.0
 */

public abstract class Case {
	private int numero;
	private String nom;
	private UICase ui;
	
	
	/**
	 * Le constructeur de la classe abstraite Case prend seulement le numero et le nom de la case pour la créer
	 * @param numero le numero de la case (correspondant à son emplacement sur le plateau)
	 * @param nom le nom de la case
	 * @throws MonopolyException si jamais le nom n'a pas une forme correcte
	 */
	public Case(int numero, String nom) throws MonopolyException {
		this.numero = numero;
		if (nom == null || nom.trim().equals(""))
			throw new MonopolyException("Erreur : nom case");
		this.nom = nom;
	}

	/**
	 * Retourne le numero de la case
	 * @return le numero de la case
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Retourne le nom de la case
	 * @return le nom de la case
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne la partie interface associée à la case
	 * @return le champs ui (la partie interface) de la case
	 */
	public UICase getUi() {
		return ui;
	}
	
	/**
	 * Retourne la case venant après celle ci sur le plateau de Monopoly
	 * @return la case suivante
	 */
	public Case getSuivant() {
		return Plateau.getInstance().getLesCases().get(numero+1);
	}
	
	/**
	 * Execute l'action que provoque l'arret sur cette case
	 * @throws MonopolyException si jamais l'action ne peut pas se dérouler comme prevue (suivant les conditions de la partie)
	 */
	public abstract void action() throws MonopolyException;
	
	/** Retourne vrai si obj correspond à la case,
	 * retourne faux sinon
	 * @return true ssi obj correspond exactement à la case
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	/**
	 * Retourne la chaine de caractère qui représente la case (destinee à etre affiche lors de messages d'erreur ou autre)
	 * @return une chaine de caractère permettant de comprendre de quelle case il s'agit
	 */
	@Override
	public String toString() {
		return "Case n°" + numero + " : " + nom;
	}

}
