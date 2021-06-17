package modele.cases.lesCases;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseChance extends Case {

	public CaseChance(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}


	@Override
	public void action() {
		Plateau plateau = Plateau.getInstance();
		try {
			Carte laCarte = plateau.getJoueurPresent().tirerCarte(plateau.getLesCartesChance());
			laCarte.actionCarte();
			plateau.mettreAuFondPileChance(laCarte);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
}
