package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;

public class CaseSimpleVisite extends Case {
	public CaseSimpleVisite(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}

	@Override
	public String getTypeCase() {
		return "simple visite";
	}

	@Override
	public void action() {
		
	}
}
