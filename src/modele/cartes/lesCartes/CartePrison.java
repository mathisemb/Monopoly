package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CartePrison extends Carte{

	public CartePrison(String nom) {
		super(nom);
	}

	@Override
	public void actionCarte() {
		Plateau plateau = Plateau.getInstance();
		try {
			plateau.getJoueurPresent().setPosition(plateau.getLaCase(40));
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}

}
