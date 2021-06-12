package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CarteDeplacement extends Carte{
	private Case destination;
	
	public CarteDeplacement(String nom, Case destination) {
		super(nom);
		this.destination = destination;
	}

	public Case getDestination() {
		return destination;
	}


	@Override
	public void actionCarte() {
		 try {
			Plateau.getInstance().getJoueurPresent().setPosition(destination);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}

}
