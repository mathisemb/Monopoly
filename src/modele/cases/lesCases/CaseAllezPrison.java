package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseAllezPrison extends Case {

	public CaseAllezPrison(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}

	@Override
	public String getTypeCase() {
		return "allez en prison";
	}

	@Override
	public void action() {
		try {
			Plateau.getInstance().getJoueurPresent().setPosition(Plateau.getInstance().getLaCase(40));
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
}
