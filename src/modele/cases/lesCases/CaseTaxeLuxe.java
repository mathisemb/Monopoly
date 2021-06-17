package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseTaxeLuxe extends Case {
	public CaseTaxeLuxe(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}
	
	
	@Override
	public void action() {
		try {
			Plateau.getInstance().getJoueurPresent().payer(100);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
}
