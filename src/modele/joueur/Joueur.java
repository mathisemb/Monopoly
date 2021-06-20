package modele.joueur;

import java.util.ArrayList;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.cases.Propriete;
import modele.cases.lesCases.CaseTerrainConstructible;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

import ui.Pion;

/**
 * Classe representant un joueur au Monopoly
 * @author Mathis
 * @version 1.0
 */

public class Joueur {
	private Pion pion;
	private int argent;
	private Case position;
	private ArrayList<Propriete> lesProprietes = new ArrayList<Propriete>();
	private ArrayList<Carte> lesCartes = new ArrayList<Carte>();
	
	/**
	 * On construit un joueur seulement avec son pion
	 * Son argent est initialisé à 0 (mis à 1500 seulement lors de l'ajout du joueur sur le plateau)
	 * Et sa position est initialisée à null (puisqu'il n'est pas encore sur un plateau)
	 * @param pion le pion qui représente le joueur sur le plateau de Monopoly
	 */
	public Joueur(Pion pion) {
		this.pion = pion;
		this.argent = 0;
		this.position = null;
	}
	
	/**
	 * Retourne la liste (ArrayList) des proprietes appartenant au joueur
	 * @return les proprietes du joueur
	 */
	public ArrayList<Propriete> getLesProprietes() {
		return lesProprietes;
	}

	/**
	 * Retourne le pion qui représente le joueur
	 * @return le pion du joueur
	 */
	public Pion getPion() {
		return pion;
	}

	/**
	 * Retourne l'argent que possède le joueur
	 * @return l'argent du joueur
	 */
	public int getArgent() {
		return argent;
	}
	
	/**
	 * Met à jour l'argent du joueur
	 * @param la nouvelle somme d'argent que le joueur possède
	 * @throws MonopolyException si jamais l'argent est négatif
	 */
	public void setArgent(int argent) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent négatif");
		this.argent = argent;
	}
	
	/**
	 * Ajoute de l'argent au compte en banque du joueur
	 * @param montant le montant que le joueur recoit
	 * @throws MonopolyException si jamais le montant est négatif
	 */
	public void recoitArgent(int montant) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent négatif");
		argent = argent + montant;
	}
	
	/**
	 * Retire de l'argent au compte en banque du joueur et le donne à un autre joueur
	 * @param montant le montant que le joueur paie
	 * @throws MonopolyException si jamais le montant est négatif ou que le joueur qui recoit l'argent est null
	 */
	public void payer(int montant, Joueur receveur) throws MonopolyException {
		if (montant < 0)
			throw new MonopolyException("Erreur : argent négatif");
		if (receveur == null)
			throw new MonopolyException("Erreur : receveur null dans payer()");
		this.argent -= montant;
		receveur.argent += montant;
	}
	
	/**
	 * Retire de l'argent au compte en banque du joueur (le joueur paie la banque)
	 * @param montant le montant que le joueur paie
	 * @throws MonopolyException si jamais le montant est négatif
	 */
	public void payer(int montant) throws MonopolyException { // (à la banque)
		if (montant < 0)
			throw new MonopolyException("Erreur : argent négatif");
		this.argent -= montant;
	}
	
	/**
	 * Ajoute la carte au paquet de cartes que le joueur garde
	 * @param carte la carte que le joueur veut conserver
	 */
	public void garderCarte(Carte carte) {
		lesCartes.add(carte);
	}
	
	/**
	 * Retourne la liste (ArrayList) des cartes que le joueur possède
	 * @return la liste des cartes du joueur
	 */
	public ArrayList<Carte> getLesCartes() {
		return lesCartes;
	}

	/**
	 * Retourne la position du joueur
	 * @return la case sur laquelle le joueur se situe
	 */
	public Case getPosition() {
		return position;
	}
	
	/**
	 * Modifie la position du joueur sans aucune vérification, pour les téléportations
	 * @param destination la case sur laquelle va le joueur
	 * @throws MonopolyException si jamais la destination est null
	 */
	public void setPosition(Case destination) throws MonopolyException { // pour les téléportations et les tests
		// ne tient pas compte des 200 reçus à la case départ
		if (destination == null)
			throw new MonopolyException("Erreur : destination");

		position = destination;
		
		destination.action();
	}
	
	/**
	 * Déplace le joueur en tenant compte du passage par la case départ et en appliquant l'action de la case de destination
	 * @param destination la case sur laquelle va le joueur
	 * @throws MonopolyException si jamais la destination est null
	 */
	public void seDeplacerVers(Case destination) throws MonopolyException {
		if (destination == null)
			throw new MonopolyException("Erreur : destination");
		if (this.getPosition() != null) // alors il est déjà sur le plateau
			if (destination.getNumero() <= getPosition().getNumero()) // càd qu'on passe forcément par la case départ puisqu'on ne peut pas reculer
				recoitArgent(200);
		position = destination;
		
		destination.action();
	}
	
	/**
	 * Déplace le joueur d'un certain nombre de cases
	 * C'est la méthode utilisée après avoir lancé les dés
	 * @param nbCases le nombre de case que doit parcourir le joueur
	 * @throws MonopolyException si jamais le nombre de cases est impossible à avoir avec 2 dés
	 */
	public void seDeplacerDe(int nbCases) throws MonopolyException {
		if (nbCases < 2 || nbCases > 12)
			throw new MonopolyException("Erreur : impossible de faire " + nbCases + " avec 2 dés à 6 faces");
		seDeplacerVers(Plateau.getInstance().getLaCase((position.getNumero() + nbCases)%40));
	}
	
	/**
	 * Le joueur achète une propriete
	 * @param prop la propriete que veut acheter le joueur
	 * @throws MonopolyException si jamais la propriete a deja un proprietaire
	 */
	public void acheterPropriete(Propriete prop) throws MonopolyException {
		if (prop.getProprietaire() != null) {
			if (prop.getProprietaire().equals(this))
				throw new MonopolyException("Vous etes deja proprietaire de cette case...");
			else
				throw new MonopolyException("Erreur : la propriété " + prop.getNom() + " a déjà un propriétaire (" + prop.getProprietaire().toString() + ")");
		}
		argent = argent - prop.getPrixAchat();
		this.lesProprietes.add(prop);
		prop.setProprietaire(this);
	}
	
	/**
	 * Le joueur achete une maison sur un certain terrain
	 * @param terrain le terrain sur lequel le joueur veut construire une maison
	 * @throws MonopolyException si jamais on ne peut pas construire de maison sur ce terrain selon les règles du Monopoly (2 maisons d'écarts et terrains de
	 * la même couleur)
	 */
	public void acheterMaison(CaseTerrainConstructible terrain) throws MonopolyException {
		if (!this.equals(terrain.getProprietaire())) // on vérifie que le joueur possède le terrain
			throw new MonopolyException("Erreur : on ne peut pas acheter une maison sur un terrain que l'on ne possède pas");
		
		int i = 0;
		int nb = terrain.getNbMaisons();
		boolean ecart2Maisons = false;
		for(Propriete prop : this.getLesProprietes()) {
			if (prop.getClass().getName().equals(CaseTerrainConstructible.class.getName())) { // la propriété est un terrain constructible
				CaseTerrainConstructible iterator = (CaseTerrainConstructible)prop;
				if (iterator.getNbMaisons() != nb && iterator.getNbMaisons() != nb+1)
					ecart2Maisons = true;
				if (iterator.getCouleur().equals(terrain.getCouleur())) // on incrémente le compteur des propriétés de cette couleur
					i++;
			}		
		}
		
		if (ecart2Maisons)
			throw new MonopolyException("Erreur : on ne peut pas avoir 2 maisons d’écart entre les terrains d’une même famille");
		
		if (terrain.getCouleur().equals("MARRON") || terrain.getCouleur().equals("BLEU FONCE")) {
			if (i==2) {
				argent -= terrain.getPrixMaison();
				terrain.ajouterMaison(); // (nbMaisons++)
			}
			else
				throw new MonopolyException("Erreur : on ne peut pas acheter une maison si on n'a pas les 2 terrains de la même couleur");
		}
		else {
			if (i==3) {
				argent -= terrain.getPrixMaison();
				terrain.ajouterMaison(); // (nbMaisons++)
			}
			else
				throw new MonopolyException("Erreur : on ne peut pas acheter une maison si on n'a pas les 3 terrains de la même couleur");
		}
	}
	
	/**
	 * Le joueur paie 50 pour sortir de prison
	 */
	public void payerPrison() {
		try {
			this.payer(50);
			this.setPosition(Plateau.getInstance().getLaCase(10)); // simple visite
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Le joueur tire une carte dans un paquet de cartes (ArrayList)
	 * @param list la liste des cartes dans laquelle le joueur tire
	 * @return la carte tirée
	 * @throws MonopolyException si jamais le paquet est vide
	 */
	public Carte tirerCarte(ArrayList<Carte> list) throws MonopolyException {
		if (list.get(0) == null)
			throw new MonopolyException("Pas de carte à tirer");
		return list.get(0);
	}

	/**
	 * Retourne la chaine de caractère qui représente le joueur (destinee à etre affichee lors de messages d'erreur ou autre)
	 * @return une chaine de caractère permettant de comprendre de quel joueur il s'agit
	 */
	@Override
	public String toString() {
		return "joueur avec le pion " + pion;
	}
	
}
