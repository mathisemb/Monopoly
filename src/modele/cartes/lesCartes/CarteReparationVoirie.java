package modele.cartes.lesCartes;

import modele.cartes.Carte;
import modele.cases.Propriete;
import modele.cases.lesCases.CaseTerrainConstructible;
import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

public class CarteReparationVoirie extends Carte{
	private int prixMaison, prixHotel;

	public CarteReparationVoirie(String nom) {
		super(nom);
		prixMaison = 40;
		prixHotel = 115;
	}

	@Override
	public void actionCarte() {
		Plateau plateau = Plateau.getInstance();
		Joueur joueur = plateau.getJoueurPresent();
		for(Propriete prop : joueur.getLesProprietes()) {
			if (prop.getClass().getName().equals("CaseTerrainConstructible")) {
				CaseTerrainConstructible terrain = (CaseTerrainConstructible)prop;
				if (terrain.getNbMaisons() >= 1 && terrain.getNbMaisons() <=4) {
					try {
						joueur.payer(prixMaison*terrain.getNbMaisons());
					} catch (MonopolyException e) {
						e.printStackTrace();
					}
				}
				else if (terrain.getNbMaisons() == 5)
					try {
						joueur.payer(prixHotel);
					} catch (MonopolyException e) {
						e.printStackTrace();
					}
			}
				
		}
			
	}

}
