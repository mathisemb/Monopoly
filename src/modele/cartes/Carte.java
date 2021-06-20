package modele.cartes;

/**
 * Classe abstraite servant de base � toutes les cartes du Monopoly
 * @author Mathis
 * @version 1.0
 */

public abstract class Carte {
	private String nom;
	
	/**
	 * Retourne le nom de la carte (ce qu'il y a �crit dessus)
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Pour construire une carte on a seulement besoin de ce qu'il y a �crit dessus donc son nom
	 * @param nom le nom de la carte
	 */
	public Carte(String nom) {
		this.nom = nom;
	}

	/**
	 * M�thode abstraite qui execute l'action que demande une carte lorsqu'elle est piochee
	 * Differentes action possible => implement� plus tard
	 */
	public abstract void actionCarte();
	
}
