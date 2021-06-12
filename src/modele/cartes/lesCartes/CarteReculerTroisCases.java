package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

public class CarteReculerTroisCases extends Carte{

	public CarteReculerTroisCases(String nom) {
		super(nom);
	}

	@Override
	public void actionCarte() {
		Plateau plateau = Plateau.getInstance();
		Joueur joueur = plateau.getJoueurPresent();
		try {
			joueur.setPosition(plateau.getLaCase(joueur.getPosition().getNumero()-3));
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}

}
