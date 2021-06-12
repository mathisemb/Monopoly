package modele.cases.lesCases;

import modele.cases.Propriete;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CaseCompagnie extends Propriete {
	private String typeCase = "compagnie";
	public CaseCompagnie(int numero, String typeCase, String nom, int prix) throws MonopolyException {
		super(numero, nom, prix);
		this.typeCase = typeCase;
	}
	
	@Override
	public String getTypeCase() {
		return typeCase;
	}

	@Override
	public int getLoyer() {
		int i = 0; // nb de compagnie du propriétaire
		for(Propriete prop : this.getProprietaire().getLesProprietes()) {
			if (prop.getClass().getName().equals(CaseCompagnie.class.getName())) { // la propriété est une compagnie
				i++;
			}		
		}
		
		switch (i) {
			case 1 : return 4*Plateau.getInstance().getLesDes(); 
			case 2 : return 10*Plateau.getInstance().getLesDes();
			default : return 0;
		}
	}
	
}
