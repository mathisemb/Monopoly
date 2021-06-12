package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CarteLiberePrison extends Carte{

	public CarteLiberePrison(String nom) {
		super(nom);
	}

	@Override
	public void actionCarte() {
		Plateau plateau = Plateau.getInstance();
		try {
			plateau.getJoueurPresent().seDeplacerDe(plateau.lancerLesDes());
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}

}
