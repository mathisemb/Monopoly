package modele.joueur;

import java.util.ArrayList;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.cases.Propriete;
import modele.cases.lesCases.CaseTerrainConstructible;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

import ui.Pion;

public class Joueur {
	private Pion pion;
	private int argent;
	private Case position;
	private ArrayList<Propriete> lesProprietes = new ArrayList<Propriete>();
	private ArrayList<Carte> lesCartes = new ArrayList<Carte>();
	
	
	public Joueur(Pion pion) throws MonopolyException {
		this.pion = pion;
		this.argent = 0;
		this.position = null;
	}
	
	public ArrayList<Propriete> getLesProprietes() {
		return lesProprietes;
	}

	public Pion getPion() {
		return pion;
	}
	public void setPion(Pion pion) {
		this.pion = pion;
	}

	public int getArgent() {
		return argent;
	}
	public void setArgent(int argent) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		this.argent = argent;
	}
	public void recoitArgent(int montant) throws MonopolyException {
		if (argent < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		argent = argent + montant;
	}
	
	public void payer(int montant, Joueur receveur) throws MonopolyException {
		if (montant < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		if (receveur == null)
			throw new MonopolyException("Erreur : receveur null dans payer()");
		this.argent -= montant;
		receveur.argent += montant;
	}
	
	public void payer(int montant) throws MonopolyException { // (� la banque)
		if (montant < 0)
			throw new MonopolyException("Erreur : argent n�gatif");
		this.argent -= montant;
	}
	
	
	public void garderCarte(Carte carte) {
		lesCartes.add(carte);
	}
	
	public ArrayList<Carte> getLesCartes() {
		return lesCartes;
	}

	
	public Case getPosition() {
		return position;
	}
	
	
	public void setPosition(Case destination) throws MonopolyException { // pour les t�l�portations et les tests
		// ne tient pas compte des 200 re�us � la case d�part
		if (destination == null)
			throw new MonopolyException("Erreur : destination");

		position = destination;
		
		destination.action();
	}
	
	public void seDeplacerVers(Case destination) throws MonopolyException {
		if (destination == null)
			throw new MonopolyException("Erreur : destination");
		if (getPosition() == null) // le joueur n'est pas encore dans le circuit
			position = destination;
		else {
			if (destination.getNumero() <= getPosition().getNumero()) // c�d qu'on passe forc�ment par la case d�part puisqu'on ne peut pas reculer
				recoitArgent(200);
			position = destination;
		}
		
		destination.action();
	}
	
	public void seDeplacerDe(int nbCases) throws MonopolyException {
		if (nbCases < 2 || nbCases > 12)
			throw new MonopolyException("Erreur : impossible de faire " + nbCases + " avec 2 d�s � 6 faces");
		seDeplacerVers(Plateau.getInstance().getLaCase((position.getNumero() + nbCases)%40));
	}
	
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
	
	public void payerPrison() {
		this.argent -= 50;
		try {
			this.setPosition(Plateau.getInstance().getLaCase(10)); // simple visite
		} catch (MonopolyException e) {
			e.printStackTrace();
		}
	}
	
	
	public Carte tirerCarte(ArrayList<Carte> list) throws MonopolyException {
		if (list.get(0) == null)
			throw new MonopolyException("Pas de carte � tirer");
		return list.get(0);
	}

	@Override
	public String toString() {
		return "joueur avec le pion " + pion;
	}
	

}
