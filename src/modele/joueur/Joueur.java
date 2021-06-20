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
	 * Son argent est initialis� � 0 (mis � 1500 seulement lors de l'ajout du joueur sur le plateau)
	 * Et sa position est initialis�e � null (puisqu'il n'est pas encore sur un plateau)
	 * @param pion le pion qui repr�sente le joueur sur le plateau de Monopoly
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
	 * Retourne le pion qui repr�sente le joueur
	 * @return le pion du joueur
	 */
	public Pion getPion() {
		return pion;
	}

	/**
	 * Retourne l'argent que poss�de le joueur
	 * @return l'argent du joueur
	 */
	public int getArgent() {
		return argent;
	}
	
	/**
	 * Met � jour l'argent du joueur
	 * @param la nouvelle somme d'argent que le joueur poss�de
	 * @throws MonopolyException si jamais l'argent est n�gatif
	 */
	public void setArgent(int argent) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		this.argent = argent;
	}
	
	/**
	 * Ajoute de l'argent au compte en banque du joueur
	 * @param montant le montant que le joueur recoit
	 * @throws MonopolyException si jamais le montant est n�gatif
	 */
	public void recoitArgent(int montant) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		argent = argent + montant;
	}
	
	/**
	 * Retire de l'argent au compte en banque du joueur et le donne � un autre joueur
	 * @param montant le montant que le joueur paie
	 * @throws MonopolyException si jamais le montant est n�gatif ou que le joueur qui recoit l'argent est null
	 */
	public void payer(int montant, Joueur receveur) throws MonopolyException {
		if (montant < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		if (receveur == null)
			throw new MonopolyException("Erreur : receveur null dans payer()");
		this.argent -= montant;
		receveur.argent += montant;
	}
	
	/**
	 * Retire de l'argent au compte en banque du joueur (le joueur paie la banque)
	 * @param montant le montant que le joueur paie
	 * @throws MonopolyException si jamais le montant est n�gatif
	 */
	public void payer(int montant) throws MonopolyException { // (� la banque)
		if (montant < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
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
	 * Retourne la liste (ArrayList) des cartes que le joueur poss�de
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
	 * Modifie la position du joueur sans aucune v�rification, pour les t�l�portations
	 * @param destination la case sur laquelle va le joueur
	 * @throws MonopolyException si jamais la destination est null
	 */
	public void setPosition(Case destination) throws MonopolyException { // pour les t�l�portations et les tests
		// ne tient pas compte des 200 re�us � la case d�part
		if (destination == null)
			throw new MonopolyException("Erreur : destination");

		position = destination;
		
		destination.action();
	}
	
	/**
	 * D�place le joueur en tenant compte du passage par la case d�part et en appliquant l'action de la case de destination
	 * @param destination la case sur laquelle va le joueur
	 * @throws MonopolyException si jamais la destination est null
	 */
	public void seDeplacerVers(Case destination) throws MonopolyException {
		if (destination == null)
			throw new MonopolyException("Erreur : destination");
		if (this.getPosition() != null) // alors il est d�j� sur le plateau
			if (destination.getNumero() <= getPosition().getNumero()) // c�d qu'on passe forc�ment par la case d�part puisqu'on ne peut pas reculer
				recoitArgent(200);
		position = destination;
		
		destination.action();
	}
	
	/**
	 * D�place le joueur d'un certain nombre de cases
	 * C'est la m�thode utilis�e apr�s avoir lanc� les d�s
	 * @param nbCases le nombre de case que doit parcourir le joueur
	 * @throws MonopolyException si jamais le nombre de cases est impossible � avoir avec 2 d�s
	 */
	public void seDeplacerDe(int nbCases) throws MonopolyException {
		if (nbCases < 2 || nbCases > 12)
			throw new MonopolyException("Erreur : impossible de faire " + nbCases + " avec 2 d�s � 6 faces");
		seDeplacerVers(Plateau.getInstance().getLaCase((position.getNumero() + nbCases)%40));
	}
	
	/**
	 * Le joueur ach�te une propriete
	 * @param prop la propriete que veut acheter le joueur
	 * @throws MonopolyException si jamais la propriete a deja un proprietaire
	 */
	public void acheterPropriete(Propriete prop) throws MonopolyException {
		if (prop.getProprietaire() != null) {
			if (prop.getProprietaire().equals(this))
				throw new MonopolyException("Vous etes deja proprietaire de cette case...");
			else
				throw new MonopolyException("Erreur : la propri�t� " + prop.getNom() + " a d�j� un propri�taire (" + prop.getProprietaire().toString() + ")");
		}
		argent = argent - prop.getPrixAchat();
		this.lesProprietes.add(prop);
		prop.setProprietaire(this);
	}
	
	/**
	 * Le joueur achete une maison sur un certain terrain
	 * @param terrain le terrain sur lequel le joueur veut construire une maison
	 * @throws MonopolyException si jamais on ne peut pas construire de maison sur ce terrain selon les r�gles du Monopoly (2 maisons d'�carts et terrains de
	 * la m�me couleur)
	 */
	public void acheterMaison(CaseTerrainConstructible terrain) throws MonopolyException {
		if (!this.equals(terrain.getProprietaire())) // on v�rifie que le joueur poss�de le terrain
			throw new MonopolyException("Erreur : on ne peut pas acheter une maison sur un terrain que l'on ne poss�de pas");
		
		int i = 0;
		int nb = terrain.getNbMaisons();
		boolean ecart2Maisons = false;
		for(Propriete prop : this.getLesProprietes()) {
			if (prop.getClass().getName().equals(CaseTerrainConstructible.class.getName())) { // la propri�t� est un terrain constructible
				CaseTerrainConstructible iterator = (CaseTerrainConstructible)prop;
				if (iterator.getNbMaisons() != nb && iterator.getNbMaisons() != nb+1)
					ecart2Maisons = true;
				if (iterator.getCouleur().equals(terrain.getCouleur())) // on incr�mente le compteur des propri�t�s de cette couleur
					i++;
			}		
		}
		
		if (ecart2Maisons)
			throw new MonopolyException("Erreur : on ne peut pas avoir 2 maisons d��cart entre les terrains d�une m�me famille");
		
		if (terrain.getCouleur().equals("MARRON") || terrain.getCouleur().equals("BLEU FONCE")) {
			if (i==2) {
				argent -= terrain.getPrixMaison();
				terrain.ajouterMaison(); // (nbMaisons++)
			}
			else
				throw new MonopolyException("Erreur : on ne peut pas acheter une maison si on n'a pas les 2 terrains de la m�me couleur");
		}
		else {
			if (i==3) {
				argent -= terrain.getPrixMaison();
				terrain.ajouterMaison(); // (nbMaisons++)
			}
			else
				throw new MonopolyException("Erreur : on ne peut pas acheter une maison si on n'a pas les 3 terrains de la m�me couleur");
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
	 * @return la carte tir�e
	 * @throws MonopolyException si jamais le paquet est vide
	 */
	public Carte tirerCarte(ArrayList<Carte> list) throws MonopolyException {
		if (list.get(0) == null)
			throw new MonopolyException("Pas de carte � tirer");
		return list.get(0);
	}

	/**
	 * Retourne la chaine de caract�re qui repr�sente le joueur (destinee � etre affichee lors de messages d'erreur ou autre)
	 * @return une chaine de caract�re permettant de comprendre de quel joueur il s'agit
	 */
	@Override
	public String toString() {
		return "joueur avec le pion " + pion;
	}
	
}
