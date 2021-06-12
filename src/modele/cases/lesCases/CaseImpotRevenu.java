package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseImpotRevenu extends Case {
	private int somme;
	public CaseImpotRevenu(int numero, String nom, int somme) throws MonopolyException {
		super(numero, nom);
		this.somme = somme;
	}
	
	public int getSomme() {
		return somme;
	}

	@Override
	public String getTypeCase() {
		return "import sur le revenu";
	}

	@Override
	public void action() {
		try {
			Plateau.getInstance().getJoueurPresent().payer(somme);
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
}
