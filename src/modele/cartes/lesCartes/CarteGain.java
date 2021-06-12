package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class CarteGain extends Carte{
	private int montant;
	
	public CarteGain(String nom, int montant) {
		super(nom);
		this.montant = montant;
	}

	public int getMontant() {
		return montant;
	}


	@Override
	public void actionCarte() {
		try {
			Plateau.getInstance().getJoueurPresent().recoitArgent(montant);
		} catch (MonopolyException e) {
			e.printStackTrace();
		};
	}

}
