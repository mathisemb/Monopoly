package modele.cases.lesCases;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseCaisseCommunaute extends Case {

	public CaseCaisseCommunaute(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}

	@Override
	public String getTypeCase() {
		return "caisse communaute";
	}

	@Override
	public void action() {
		Plateau plateau = Plateau.getInstance();
		try {
			Carte laCarte = plateau.getJoueurPresent().tirerCarte(plateau.getLesCartesCommunaute());
			laCarte.actionCarte();
			plateau.mettreAuFondPileCommunaute(laCarte);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
}
