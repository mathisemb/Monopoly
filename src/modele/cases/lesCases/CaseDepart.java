package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;

public class CaseDepart extends Case {
	private int somme;
	public CaseDepart(int numero, String nom, int somme) throws MonopolyException {
		super(numero, nom);
		this.somme = somme;
	}
	
	public int getSomme() {
		return somme;
	}

	
	@Override
	public void action() {
		
	}
}
