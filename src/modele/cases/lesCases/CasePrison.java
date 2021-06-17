package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;

public class CasePrison extends Case {
	public CasePrison(int numero, String nom) throws MonopolyException {
		super(numero, nom);
	}

	
	@Override
	public void action() {
		
	}
}
