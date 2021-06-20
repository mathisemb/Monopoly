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

/**
 * Classe representant le plateau du Monopoly
 * @author Mathis
 * @version 1.0
 */

public class Plateau {
	private int nbJoueurs;
	private ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
	private ArrayList<Case> lesCases = new ArrayList<Case>();
	private ArrayList<Carte> lesCartesCommunaute = new ArrayList<Carte>();
	private ArrayList<Carte> lesCartesChance = new ArrayList<Carte>();
	private int lesDes;
	private Joueur joueurPresent;
	
	/**
	 * Retourne un entier aléatoire entre 2 et 12
	 * @return un entier aléatoire entre 2 et 12
	 */
	public int lancerLesDes() {
		Random random = new Random();
		int nb;
		nb = 2+random.nextInt(10);
		return nb;
	}
	
	/**
	 * Retourne le joueur qui est en train de jouer
	 * @return le joueur présent
	 */
	public Joueur getJoueurPresent() {
		return joueurPresent;
	}
	
	/**
	 * Met à jour le joueur qui est en train de joueur
	 * @param joueurPresent le joueur à qui ca va etre le tour
	 */
	public void setJoueurPresent(Joueur joueurPresent) {
		this.joueurPresent = joueurPresent;
	}
	
	/**
	 * Retourne le dernier résulat des dés
	 * @return
	 */
	public int getLesDes() {
		return lesDes;
	}
	
	/**
	 * Modifie la valeur des dés (utilisé principalement pour les tests)
	 * @param lesDes la nouvelle valeur des dés
	 */
	public void setLesDes(int lesDes) {
		this.lesDes = lesDes;
	}
	
	/**
	 * Retourne le nombre de joueurs qui ont été ajouté au plateau
	 * @return le nombre de joueurs
	 */
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	
	/**
	 * Modifie le nombre de joueurs
	 * @param nbJoueurs le nouveau nombre de joueurs
	 */
	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	/**
	 * Retourne la liste (ArrayList) des joueurs
	 * @return la liste des joueurs
	 */
	public ArrayList<Joueur> getLesJoueurs() {
		return lesJoueurs;
	}

	/**
	 * Retourne la liste (ArrayList) des cases du plateau
	 * @return la liste des cases
	 */
	public ArrayList<Case> getLesCases() {
		return lesCases;
	}
	
	/**
	 * Retourne la carte chance ayant un certain nom
	 * @param nom le nom de la carte qu'on cherche
	 * @return la carte possedant ce nom
	 */
	public Carte getLaCarteChance(String nom) {
		for(Carte carte : this.lesCartesChance) {
			if (carte.getNom().equals(nom))
				return carte;
		}
		return null;
	}
	
	/**
	 * Retourne la carte communaute ayant un certain nom
	 * @param nom le nom de la carte qu'on cherche
	 * @return la carte possedant ce nom
	 */
	public Carte getLaCarteCommunaute(String nom) {
		for(Carte carte : this.lesCartesCommunaute) {
			if (carte.getNom().equals(nom))
				return carte;
		}
		return null;
	}
	
	/**
	 * Met une carte au sommet de la pile des communaute, càd à l'index 0 de la liste
	 * @param carte la carte qu'on veut mettre au sommet
	 */
	public void mettreAuSommetPileCommunaute(Carte carte) {
		// on considère que le sommet de la pile est l'élément d'indice 0
		this.lesCartesCommunaute.remove(carte);
		this.lesCartesCommunaute.add(0, carte);
	}
	
	/**
	 * Met une carte au sommet de la pile des chance, càd à l'index 0 de la liste
	 * @param carte la carte qu'on veut mettre au sommet
	 */
	public void mettreAuSommetPileChance(Carte carte) {
		// on considère que le sommet de la pile est l'élément d'indice 0
		this.lesCartesChance.remove(carte);
		this.lesCartesChance.add(0, carte);
	}
	
	/**
	 * Met une carte au fond de la pile des communaute, càd à la fin de la liste
	 * @param carte la carte qu'on veut mettre au fond
	 */
	public void mettreAuFondPileCommunaute(Carte carte) {
		this.lesCartesCommunaute.remove(carte);
		lesCartesCommunaute.add(carte); // ajoute à la fin
	}
	
	/**
	 * Met une carte au fond de la pile des chance, càd à la fin de la liste
	 * @param carte la carte qu'on veut mettre au fond
	 */
	public void mettreAuFondPileChance(Carte carte) {
		this.lesCartesChance.remove(carte);
		lesCartesChance.add(carte); // ajoute à la fin
	}
	
	/**
	 * Retourne la carte au fond de la pile des communaute (utilisé principalement pour les tests)
	 * @return la carte du fond
	 * @throws MonopolyException si jamais la pile est vide
	 */
	public Carte getLaCarteDuFondCommunaute() throws MonopolyException {
		if (this.lesCartesCommunaute.get(lesCartesCommunaute.size()-1) == null)
			throw new MonopolyException("Pas de cartes dans le paquet des cartes communaute");
		return this.lesCartesCommunaute.get(lesCartesCommunaute.size()-1);
	}
	
	/**
	 * Retourne la carte au fond de la pile des chance (utilisé principalement pour les tests)
	 * @return la carte du fond
	 * @throws MonopolyException si jamais la pile est vide
	 */
	public Carte getLaCarteDuFondChance() throws MonopolyException {
		if (this.lesCartesChance.get(lesCartesChance.size()-1) == null)
			throw new MonopolyException("Pas de cartes dans le paquet des cartes chance");
		return this.lesCartesChance.get(lesCartesChance.size()-1);
	}
	
	/**
	 * Retourne la case ayant un certain nom
	 * @param nom le nom de la case qu'on cherche
	 * @return la case portant ce nom
	 */
	public Case getLaCase(String nom) {
		for (Case pos : this.getLesCases()) {
			if (pos.getNom().equals(nom))
				return pos;
		}
		return null;
	}
	
	/**
	 * Initialise toutes les cases du plateau à partir d'un fichier texte
	 * @param fileName le fichier texte contenant les cases
	 * @throws NumberFormatException si jamais le format du fichier n'est pas correct
	 * @throws IOException pour tout autre problème
	 */
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
	
	/**
	 * Initialise toutes les cartes chance du plateau à partir d'un fichier texte
	 * @param fileName le fichier texte contenant les cartes
	 */
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
	
	/**
	 * Initialise toutes les cartes communaute du plateau à partir d'un fichier texte
	 * @param fileName le fichier texte contenant les cartes
	 */
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

	/**
	 * Retourne la liste (ArrayList) des cartes communaute
	 * @return la liste des cartes
	 */
	public ArrayList<Carte> getLesCartesCommunaute() {
		return lesCartesCommunaute;
	}

	/**
	 * Retourne la liste (ArrayList) des cartes chance
	 * @return la liste des cartes
	 */
	public ArrayList<Carte> getLesCartesChance() {
		return lesCartesChance;
	}

	/**
	 * Retourne la case à un certain indice
	 * @param num le numero de la case qu'on cherche
	 * @return la case ayant le numero
	 */
	public Case getLaCase(int num) {
		if (num <= 40)
			return this.getLesCases().get(num);
		else
			return this.getLesCases().get(num%40);
	}
	
	/**
	 * Ajoute un joueur au plateau en mettant à jour ses champs
	 * @param joueur le joueur qu'on ajoute
	 * @throws MonopolyException si jamais le joueur est null ou déjà présent sur le plateau
	 */
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

	/**
	 * Partie singleton
	 * Constructeur private pour qu'on ne puisse pas en créer plusieurs (même pas un pour l'instant)
	 */
	private Plateau() {
	}
	
	/**
	 * Partie singleton
	 * L'unique instance de la classe Plateau
	 */
	private static Plateau instance = null;
	
	/**
	 * Partie singleton
	 * L'accesseur à l'instance (donc en public maintenant)
	 * Retourne l'unique instance de la classe Plateau
	 * @return le plateau
	 */
	public static Plateau getInstance() {
		if (instance==null) // c'est pour ça qu'il fallait l'initialiser à null
			instance = new Plateau();
		
		return instance; // on est sûr que l'instance existe 
	}

}
