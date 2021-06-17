package modele.cases.lesCases;

import modele.cases.Propriete;
import modele.exceptions.MonopolyException;

public class CaseTerrainConstructible extends Propriete {
	private String typeCase = "terrain constructible";
	private String couleur;
	int prixMaison, loyerSansMaison, loyer1Maison, loyer2Maison, loyer3Maison, loyer4Maison, loyerHotel;
	int nbMaisons;
	public CaseTerrainConstructible(int numero, String typeCase, String couleur, String nom, 
			int prixAchat, int prixMaison, int loyerSansMaison, int loyer1Maison, int loyer2Maison, int loyer3Maison, int loyer4Maison, int loyerHotel) throws MonopolyException {
		
		super(numero, nom, prixAchat); // à la construction, le loyer est celui sans maison
		this.prixMaison = prixMaison;
		this.loyerSansMaison = loyerSansMaison;
		this.loyer1Maison = loyer1Maison;
		this.loyer2Maison = loyer2Maison;
		this.loyer3Maison = loyer3Maison;
		this.loyer4Maison = loyer4Maison;
		this.loyerHotel = loyerHotel;
		this.couleur = couleur;
		
		this.nbMaisons = 0;
	}
	
	public String getCouleur() {
		return couleur;
	}

	public String getTypeCase() {
		return typeCase;
	}
	
	public int getNbMaisons() {
		return nbMaisons;
	}
	
	public void ajouterMaison() {
		this.nbMaisons++;
	}
	
	public int getPrixMaison() {
		return prixMaison;
	}

	
	@Override
	public int getLoyer() {
		int i = 0;
		int coeff;
		for(Propriete prop : this.getProprietaire().getLesProprietes()) {
			if (prop.getClass().getName().equals(CaseTerrainConstructible.class.getName())) { // la propriété est un terrain constructible
				CaseTerrainConstructible terrain = (CaseTerrainConstructible)prop;
				if (terrain.getCouleur().equals(this.getCouleur())) // on incrémente le compteur des propriétés de cette couleur
					i++;
			}		
		}
		
		if (this.getCouleur().equals("MARRON") || this.getCouleur().equals("BLEU FONCE")) {
			if (i==2)
				coeff = 2;
			else
				coeff = 1;
		}
		else {
			if (i==3)
				coeff = 2;
			else
				coeff = 1;
		}

			
		switch (nbMaisons) {
			case 0 : return coeff*loyerSansMaison;
			case 1 : return loyer1Maison;
			case 2 : return loyer2Maison;
			case 3 : return loyer3Maison;
			case 4 : return loyer4Maison;
			case 5 : return loyerHotel; // 5 maisons = 1 hôtel
			default : return coeff*loyerSansMaison;
		}
	}
	
}
