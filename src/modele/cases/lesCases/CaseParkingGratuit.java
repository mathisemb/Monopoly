package modele.cases.lesCases;

import modele.cases.Case;
import modele.exceptions.MonopolyException;

public class CaseParkingGratuit extends Case {
	private int sommePosee;

	public int getSommePosee() {
		return sommePosee;
	}

	public void setSommePosee(int sommePosee) throws MonopolyException {
		if (sommePosee < 0)
			throw new MonopolyException("Erreur : somme négative sur parking gratuit");
		this.sommePosee = sommePosee;
	}
	
	public CaseParkingGratuit(int numero, String nom) throws MonopolyException {
		super(numero, nom);
		this.sommePosee = 0;
	}

	@Override
	public String getTypeCase() {
		return "parking gratuit";
	}

	@Override
	public void action() {
		
	}
	
}
