package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

/**
 * La classe permettant de ranger toutes les cartes provoquant un d�placement ensemble
 * Elle etend une classe plus generale qui est la classe abstraite Carte
 * @author Mathis
 * @version 1.0
 */

public class CarteDeplacement extends Carte{
	private Case destination;
	
	/**
	 * Le constructeur a besoin d'un nom (Carte) et de la destination puisqu'il y a deplacement
	 * @param nom le nom de la carte qui est pass� au constructeur de la classe Carte
	 * @param destination la case o� le joueur qui tire la carte va se retrouver 
	 */
	public CarteDeplacement(String nom, Case destination) {
		super(nom);
		this.destination = destination;
	}

	/**
	 * Retourne la destination indiquee sur la carte de deplacement
	 * @return la destination
	 */
	public Case getDestination() {
		return destination;
	}

	/**
	 * Impl�mentation de actionCarte() declaree dans la classe abstraite Carte
	 * Ici, on met seulement � jour la position du joueur par "teleportation" avec setPosition
	 * @throws MonopolyException si jamais la destination est null
	 */
	@Override
	public void actionCarte() {
		 try {
			Plateau.getInstance().getJoueurPresent().setPosition(destination);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}

}
