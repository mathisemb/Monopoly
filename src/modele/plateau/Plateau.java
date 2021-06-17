package modele.plateau;

import modele.cases.Case;
import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.parser.Fichier;
import modele.parser.lesParsers.ParserAllezPrison;
import modele.parser.lesParsers.ParserCaisseCommunaute;
import modele.parser.lesParsers.ParserCaseDepart;
import modele.parser.lesParsers.ParserChance;
import modele.parser.lesParsers.ParserCompagnie;
import modele.parser.lesParsers.ParserGare;
import modele.parser.lesParsers.ParserImpotRevenu;
import modele.parser.lesParsers.ParserParkingGratuit;
import modele.parser.lesParsers.ParserPrison;
import modele.parser.lesParsers.ParserSimpleVisite;
import modele.parser.lesParsers.ParserTaxeLuxe;
import modele.parser.lesParsers.ParserTerrainConstructible;
import modele.parser.lesParsersCartes.ParserCarteDeplacement;
import modele.parser.lesParsersCartes.ParserCarteGain;
import modele.parser.lesParsersCartes.ParserCarteLiberePrison;
import modele.parser.lesParsersCartes.ParserCartePerte;
import modele.parser.lesParsersCartes.ParserCartePrison;
import modele.parser.lesParsersCartes.ParserCarteReculerTroisCases;
import modele.parser.lesParsersCartes.ParserCarteReparationMaison;
import modele.parser.lesParsersCartes.ParserCarteReparationVoirie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import modele.cartes.Carte;

public class Plateau {
	
	private int nbJoueurs;
	private ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private ArrayList<Case> lesCases = new ArrayList<Case>();
	private ArrayList<Carte> lesCartesCommunaute = new ArrayList<Carte>();
	private ArrayList<Carte> lesCartesChance = new ArrayList<Carte>();
	private int lesDes;
	private Joueur joueurPresent;
	
	public int lancerLesDes() {
		Random random = new Random();
		int nb;
		nb = 2+random.nextInt(10);
		return nb;
	}
	
	public Joueur getJoueurPresent() {
		return joueurPresent;
	}
	public void setJoueurPresent(Joueur joueurPresent) {
		this.joueurPresent = joueurPresent;
	}
	
	public int getLesDes() {
		return lesDes;
	}
	public void setLesDes(int lesDes) {
		this.lesDes = lesDes;
	}
	
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	
	public ArrayList<Joueur> getLesJoueurs() {
		return lesJoueurs;
	}

	public ArrayList<Case> getLesCases() {
		return lesCases;
	}
	
	public Carte getLaCarteChance(String nom) {
		for(Carte carte : this.lesCartesChance) {
			if (carte.getNom().equals(nom))
				return carte;
		}
		return null;
	}
	
	public Carte getLaCarteCommunaute(String nom) {
		for(Carte carte : this.lesCartesCommunaute) {
			if (carte.getNom().equals(nom))
				return carte;
		}
		return null;
	}
	
	public void mettreAuSommetPileCommunaute(Carte carte) {
		// on considère que le sommet de la pile est l'élément d'indice 0
		this.lesCartesCommunaute.remove(carte);
		this.lesCartesCommunaute.add(0, carte);
	}
	
	public void mettreAuSommetPileChance(Carte carte) {
		// on considère que le sommet de la pile est l'élément d'indice 0
		this.lesCartesChance.remove(carte);
		this.lesCartesChance.add(0, carte);
	}
	
	public void mettreAuFondPileCommunaute(Carte carte) {
		this.lesCartesCommunaute.remove(carte);
		lesCartesCommunaute.add(carte); // ajoute à la fin
	}
	
	public void mettreAuFondPileChance(Carte carte) {
		this.lesCartesChance.remove(carte);
		lesCartesChance.add(carte); // ajoute à la fin
	}
	
	public Carte getLaCarteDuFondCommunaute() throws MonopolyException {
		if (this.lesCartesCommunaute.get(lesCartesCommunaute.size()-1) == null)
			throw new MonopolyException("Pas de cartes dans le paquet des cartes communaute");
		return this.lesCartesCommunaute.get(lesCartesCommunaute.size()-1);
	}
	
	public Carte getLaCarteDuFondChance() throws MonopolyException {
		if (this.lesCartesChance.get(lesCartesChance.size()-1) == null)
			throw new MonopolyException("Pas de cartes dans le paquet des cartes chance");
		return this.lesCartesChance.get(lesCartesChance.size()-1);
	}
	
	
	
	public Case getLaCase(String nom) {
		for (Case pos : this.getLesCases()) {
			if (pos.getNom().equals(nom))
				return pos;
		}
		return null;
	}
	
	
	public void setLesCases(String fileName) throws NumberFormatException, IOException {
		ParserPrison parser12 = new ParserPrison(null);
		ParserTaxeLuxe parser11 = new ParserTaxeLuxe(parser12);
		ParserAllezPrison parser10 = new ParserAllezPrison(parser11);
		ParserCompagnie parser9 = new ParserCompagnie(parser10);
		ParserTerrainConstructible parser8 = new ParserTerrainConstructible(parser9);
		ParserSimpleVisite parser7 = new ParserSimpleVisite(parser8);
		ParserParkingGratuit parser6 = new ParserParkingGratuit(parser7);
		ParserImpotRevenu parser5 = new ParserImpotRevenu(parser6);
		ParserChance parser4 = new ParserChance(parser5);
		ParserCaisseCommunaute parser3 = new ParserCaisseCommunaute(parser4);
		ParserGare parser2 = new ParserGare(parser3);
		ParserCaseDepart parser1 = new ParserCaseDepart(parser2);
		
		Fichier.lire(fileName, parser1, this.lesCases);
	}
	
	public void setLesCartesChance(String fileName) {
		ParserCarteReparationVoirie parser8 = new ParserCarteReparationVoirie(null);
		ParserCarteReparationMaison parser7 = new ParserCarteReparationMaison(parser8);
		ParserCarteReculerTroisCases parser6 = new ParserCarteReculerTroisCases(parser7);
		ParserCartePrison parser5 = new ParserCartePrison(parser6);
		ParserCartePerte parser4 = new ParserCartePerte(parser5);
		ParserCarteLiberePrison parser3 = new ParserCarteLiberePrison(parser4);
		ParserCarteGain parser2 = new ParserCarteGain(parser3);
		ParserCarteDeplacement parser1 = new ParserCarteDeplacement(parser2);
		
		Fichier.lire(fileName, parser1, this.lesCartesChance);
	}
	
	public void setLesCartesCommunaute(String fileName) {
		ParserCarteReparationVoirie parser8 = new ParserCarteReparationVoirie(null);
		ParserCarteReparationMaison parser7 = new ParserCarteReparationMaison(parser8);
		ParserCarteReculerTroisCases parser6 = new ParserCarteReculerTroisCases(parser7);
		ParserCartePrison parser5 = new ParserCartePrison(parser6);
		ParserCartePerte parser4 = new ParserCartePerte(parser5);
		ParserCarteLiberePrison parser3 = new ParserCarteLiberePrison(parser4);
		ParserCarteGain parser2 = new ParserCarteGain(parser3);
		ParserCarteDeplacement parser1 = new ParserCarteDeplacement(parser2);
		
		Fichier.lire(fileName, parser1, this.lesCartesCommunaute);
	}

	
	public ArrayList<Carte> getLesCartesCommunaute() {
		return lesCartesCommunaute;
	}



	public ArrayList<Carte> getLesCartesChance() {
		return lesCartesChance;
	}

	
	
	public Case getLaCase(int num) {
		return this.getLesCases().get(num);
	}
	
	
	public void ajouterJoueur(Joueur joueur) throws MonopolyException {
		if (joueur == null)
			throw new MonopolyException("Erreur : joueur inconnu");
		if (lesJoueurs.contains(joueur))
			throw new MonopolyException("Erreur : joueur déjà présent");
		joueur.seDeplacerVers(getLaCase(0)); // on le met sur la case départ
		joueur.setArgent(1500);
		lesJoueurs.add(joueur);
		nbJoueurs++;
	}
	
	//-----------------------Partie singleton-----------------------

	// constructeur private pour qu'on ne puisse pas en créer plusieurs (même pas un pour l'instant)
	private Plateau() {
	}
	
	
	// l'unique instance
	private static Plateau instance = null;
	
	
	// l'accesseur à l'instance (donc en public maintenant)
	public static Plateau getInstance() {
		if (instance==null) // c'est pour ça qu'il fallait l'initialiser à null
			instance = new Plateau();
		
		return instance; // on est sûr que l'instance existe 
	}

}
