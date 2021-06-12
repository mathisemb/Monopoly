package modele.cases.lesCases;

import modele.cases.Propriete;
import modele.exceptions.MonopolyException;

public class CaseGare extends Propriete {
	private String typeCase = "gare";
	public CaseGare(int numero, String typeCase, String nom) throws MonopolyException {
		super(numero, nom, 200); //  le prix d'achat d'une gare est fixe et est de 200€
		this.typeCase = typeCase;
		
	}
	
	@Override
	public String getTypeCase() {
		return typeCase;
	}

	@Override
	public int getLoyer() {
		int i = 0; // nb de gare du propriétaire et coeff en même temps
		for(Propriete prop : this.getProprietaire().getLesProprietes()) {
			if (prop.getClass().getName().equals(CaseGare.class.getName())) { // la propriété est une gare
				i++;
			}		
		}
		return i*50;
	}
}
