package modele.test;
import java.io.IOException;

import modele.cartes.Carte;
import modele.cases.Case;
import modele.cases.Propriete;
import modele.cases.lesCases.CaseCompagnie;
import modele.cases.lesCases.CaseGare;
import modele.cases.lesCases.CaseParkingGratuit;
import modele.cases.lesCases.CaseTerrainConstructible;
import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

import modele.cartes.lesCartes.CarteGain;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException, MonopolyException {
		
		//-------------------Tests pour la construction du plateau-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		
		if (plateau.getNbJoueurs() != 0)
			System.err.println("nb joueur != 0");
		if (!plateau.getLaCase(0).getNom().equals("CASE DEPART"))
			System.err.println("case 0 != CASE DEPART");
		if (!plateau.getLaCase(1).getNom().equals("BOULEVARD DE BELLEVILLE"))
			System.err.println("case 1 != BOULEVARD DE BELLEVILLE");
		if (!plateau.getLaCase(5).getNom().equals("GARE MONTPARNASSE"))
			System.err.println("case 5 != GARE MONTPARNASSE");
		if (!plateau.getLaCase(10).getNom().equals("SIMPLE VISITE"))
			System.err.println("case 10 != SIMPLE VISITE");
		if (!plateau.getLaCase(12).getNom().equals("COMPAGNIE D'ELECTRICITE"))
			System.err.println("case 12 != COMPAGNIE D'ELECTRICITE");
		if (!plateau.getLaCase(20).getNom().equals("PARKING GRATUIT"))
			System.err.println("case 20 != PARKING GRATUIT");

		if (plateau.getLaCase(20).getClass().equals(CaseParkingGratuit.class)) {
			CaseParkingGratuit parking = (CaseParkingGratuit)plateau.getLaCase(20);
			if (parking.getSommePosee() != 0)
				System.err.println("Somme posée sur le parking gratuit != 0");
		}
		
		if (!plateau.getLaCase(30).getNom().equals("ALLEZ EN PRISON"))
			System.err.println("case 30 != ALLEZ EN PRISON");
		if (!plateau.getLaCase(36).getNom().equals("CHANCE"))
			System.err.println("case 36 != CHANCE");
		if (!plateau.getLaCase(39).getNom().equals("RUE DE LA PAIX"))
			System.err.println("case 39 != RUE DE LA PAIX");
		if (!plateau.getLaCase(40).getNom().equals("PRISON"))
			System.err.println("case 40 != PRISON");
		
		for(Case pos : plateau.getLesCases()) {
			if (pos.getClass().equals(CaseTerrainConstructible.class) || pos.getClass().equals(CaseGare.class) || pos.getClass().equals(CaseCompagnie.class)) {
				Propriete prop = (Propriete)pos;
				if (prop.getProprietaire() != null)
					System.err.println("le proprietaire de la case : " + prop.getNom() + " n'est pas null et c'est : " + prop.getProprietaire().getPion());
			}
		}
		
		System.out.println("OK");
		*/
		
		
		//-------------------Tests pour la construction d’un joueur-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		
		Joueur joueur1 = new Joueur(null);
		plateau.ajouterJoueur(joueur1);
		if (joueur1.getArgent() != 1500)
			System.err.println("Le joueur ne possède pas 1500€");
		if (!joueur1.getLesProprietes().isEmpty())
			System.err.println("Le joueur possède un terrain");
		if (!joueur1.getPosition().equals(plateau.getLaCase(0)))
			System.err.println("Le joueur n'est pas sur la case départ");
		if (plateau.getNbJoueurs() != 1)
			System.err.println("Le plateau ne contient pas 1 joueur");
		Joueur joueur2 = new Joueur(null);
		plateau.ajouterJoueur(joueur2);
		if (plateau.getNbJoueurs() != 2)
			System.err.println("Le plateau ne contient pas 2 joueurs");
		*/
		
		
		//-------------------Tests pour le déplacement simple d’un joueur-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueur1 = new Joueur(null);
		plateau.ajouterJoueur(joueur1);
		
		joueur1.seDeplacerDe(5);
		if (!joueur1.getPosition().equals(plateau.getLaCase(5)))
			System.err.println("Le joueur n'est pas sur la case Gare Montparnasse");
		if (joueur1.getArgent() != 1500)
			System.err.println("Le joueur ne possède pas 1500€");
		if (!joueur1.getLesProprietes().isEmpty())
			System.err.println("Le joueur possède un terrain");
		joueur1.seDeplacerDe(5);
		if (!joueur1.getPosition().equals(plateau.getLaCase(10)))
			System.err.println("Le joueur n'est pas sur la case Simple Visite");
		if (joueur1.getArgent() != 1500)
			System.err.println("Le joueur ne possède pas 1500€");
		if (!joueur1.getLesProprietes().isEmpty())
			System.err.println("Le joueur possède un terrain");
		joueur1.seDeplacerVers(plateau.getLaCase(39));
		joueur1.seDeplacerDe(2);
		if (!joueur1.getPosition().equals(plateau.getLaCase(1)))
			System.err.println("Le joueur n'est pas sur la case Boulevard de Belleville");
		if (joueur1.getArgent() != 1700)
			System.err.println("Le joueur ne possède pas 1700€");
		//joueur1.seDeplacerDe(1);
		joueur1.seDeplacerDe(13);
		*/
		
		
		//-------------------Tests pour l’achat d’un terrain-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		
		joueurA.seDeplacerDe(6);
		if (!joueurA.getPosition().equals(plateau.getLaCase(6)))
			System.err.println("Le joueur n'est pas sur la case Rue de Vaugirard");
		Propriete prop = (Propriete)plateau.getLaCase(6);
		if (prop.getProprietaire() != null)
			System.err.println("le proprietaire de la case : " + prop.getNom() + " n'est pas null et c'est : " + prop.getProprietaire().getPion());
		joueurA.acheterPropriete(prop);
		if (prop.getProprietaire() != joueurA)
			System.err.println("le proprietaire de la case : " + prop.getNom() + " n'est pas " + joueurA + " mais : " + prop.getProprietaire().getPion());
		if (joueurA.getArgent() != 1400)
			System.err.println(joueurA + " ne possède pas 1400€ mais " + joueurA.getArgent());
		
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		plateau.setJoueurPresent(joueurB);
		joueurB.seDeplacerDe(6);
		if (!joueurB.getPosition().equals(plateau.getLaCase(6)))
			System.err.println("Le joueur n'est pas sur la case Rue de Vaugirard");
		if (prop.getProprietaire() == null)
			System.err.println("La case : " + prop.getNom() + " n'a pas de propriétaire");
		joueurB.acheterPropriete(prop);
		*/
		
		
		//-------------------Tests pour le déplacement d’un joueur sur un terrain constructible acheté-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		
		plateau.setJoueurPresent(joueurA);
		joueurA.seDeplacerDe(6);
		Propriete prop = (Propriete)plateau.getLaCase(6);
		joueurA.acheterPropriete(prop);
		if (joueurA.getArgent() != 1400)
			System.err.println(joueurA + " ne possède pas 1400€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurB);
		joueurB.seDeplacerDe(6);
		if (!joueurB.getPosition().equals(plateau.getLaCase(6)))
			System.err.println("Le joueurB n'est pas sur la case Rue de Vaugirard");
		if (joueurB.getArgent() != 1494)
			System.err.println(joueurB + " ne possède pas 1494€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1406)
			System.err.println(joueurA + " ne possède pas 1406€ mais " + joueurA.getArgent());
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(8));
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(9));
		if (joueurA.getArgent() != 1186)
			System.err.println(joueurA + " ne possède pas 1186€ mais " + joueurA.getArgent());
		joueurB.seDeplacerDe(3);
		if (!joueurB.getPosition().equals(plateau.getLaCase(9)))
			System.err.println("Le joueur n'est pas sur la case " + plateau.getLaCase(9));
		if (joueurB.getArgent() != 1478) // 1494 - 16
			System.err.println(joueurB + " ne possède pas 1478€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1202) // 1186 + 16
			System.err.println(joueurA + " ne possède pas 1202€ mais " + joueurA.getArgent());
		joueurB.setArgent(5);
		joueurB.setPosition(plateau.getLaCase(8));
		*/
		
		
		//-------------------Tests pour le déplacement d’un joueur sur une gare achetée-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(5)); // Gare Montparnasse
		plateau.setJoueurPresent(joueurB);
		joueurB.setPosition(plateau.getLaCase(5));
		if (joueurB.getArgent() != 1450)
			System.err.println(joueurB + " ne possède pas 1450€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1350) // 1500 - 200 (prix gare) + 50 (loyer)
			System.err.println(joueurA + " ne possède pas 1350€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(15)); // Gare de Lyon => 1350 - 200 = 1150
		plateau.setJoueurPresent(joueurB);
		joueurB.setPosition(plateau.getLaCase(15));
		if (joueurB.getArgent() != 1350) // 1450 - 2*50 (loyer = 50*2 car joueurA possède 2 gares)
			System.err.println(joueurB + " ne possède pas 1350€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1250) // 1150 + 2*50
			System.err.println(joueurA + " ne possède pas 1250€ mais " + joueurA.getArgent());
		joueurB.setPosition(plateau.getLaCase(25)); // gare du nord
		Propriete prop = (Propriete)plateau.getLaCase(25);
		if (prop.getProprietaire() != null)
			System.err.println("La case : " + prop.getNom() + " a un propriétaire et c'est " + prop.getProprietaire());
		if (joueurB.getArgent() != 1350) // pas de loyer à payer
			System.err.println(joueurB + " ne possède pas 1350€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1250) // pas de loyer à encaisser
			System.err.println(joueurA + " ne possède pas 1250€ mais " + joueurA.getArgent());
		*/
		
		
		//-------------------Tests pour le déplacement d’un joueur sur une compagnie achetée-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(12)); // Compagnie d’électricité
		if (joueurA.getArgent() != 1350) // 1500 - 150 (prix compagnie)
			System.err.println(joueurA + " ne possède pas 1350€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurB);
		plateau.setLesDes(12);
		joueurB.seDeplacerDe(plateau.getLesDes());
		if (!joueurB.getPosition().equals(plateau.getLaCase(12)))
			System.err.println("Le joueurB n'est pas sur la Compagnie d’électricité");
		if (joueurB.getArgent() != 1452) // 1500 - 12*4 (loyer) 1 compagnie => dés*4
			System.err.println(joueurB + " ne possède pas 1452€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1398) // 1350 + 12*4 (loyer recu de la compagnie)
			System.err.println(joueurA + " ne possède pas 1398€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(27)); // Compagnie des eaux
		if (joueurA.getArgent() != 1248) // 1398 - 150 (prix de la compagnie)
			System.err.println(joueurA + " ne possède pas 1248€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurB);
		joueurB.setPosition(plateau.getLaCase(23)); // Boulevard Malesherbes
		if (joueurB.getArgent() != 1452) // pas de loyer à payer
			System.err.println(joueurB + " ne possède pas 1452€ mais " + joueurB.getArgent());
		plateau.setLesDes(4);
		joueurB.seDeplacerDe(plateau.getLesDes());
		if (!joueurB.getPosition().equals(plateau.getLaCase(27)))
			System.err.println("Le joueurB n'est pas sur la Compagnie des eaux");
		if (joueurB.getArgent() != 1412) // 1452 - 4*10 (loyer) les 2 compagnies => dés*10
			System.err.println(joueurB + " ne possède pas 1412€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1288) // 1248 + 40 (loyer recu)
			System.err.println(joueurA + " ne possède pas 1288€ mais " + joueurA.getArgent());
		*/
		
		
		//-------------------Tests pour l’achat d’une maison-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(6)); // Rue de Vaugirard
		//joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(8)); // Rue de Courcelles
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(9)); // Avenue de la République
		
		if (joueurA.getArgent() != 1180) // 1500 - 100 - 100 - 120 (loyer recu)
			System.err.println(joueurA + " ne possède pas 1180€ mais " + joueurA.getArgent());
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		if (joueurA.getArgent() != 1130) // 1180 - 50 (la maison)
			System.err.println(joueurA + " ne possède pas 1130€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurB);
		joueurB.setPosition(plateau.getLaCase(6));
		if (joueurB.getArgent() != 1470) // 1500 - 30 (loyer1maison)
			System.err.println(joueurB + " ne possède pas 1470€ mais " + joueurB.getArgent());
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		*/
		
		
		//-------------------Tests pour l’achat d’un hôtel-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		Joueur joueurB = new Joueur(null);
		plateau.ajouterJoueur(joueurB);
		
		plateau.setJoueurPresent(joueurA);
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(6)); // Rue de Vaugirard
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(8)); // Rue de Courcelles
		joueurA.acheterPropriete((Propriete)plateau.getLaCase(9)); // Avenue de la République
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(8));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(8));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(8));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(8));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9));
		//joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9)); // acheter une 5ème maison <=> acheter un hotel
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(6));
		joueurA.acheterMaison((CaseTerrainConstructible)plateau.getLaCase(9)); // acheter une 5ème maison <=> acheter un hotel
		if (joueurA.getArgent() != 530) // 1500 - 320(les 3 terrains) - les 12 maisons - l'hôtel
			System.err.println(joueurA + " ne possède pas 530€ mais " + joueurA.getArgent());
		plateau.setJoueurPresent(joueurB);
		joueurB.setPosition(plateau.getLaCase(9));
		if (joueurB.getArgent() != 900) // 1500 - 600 (loyer)
			System.err.println(joueurB + " ne possède pas 900€ mais " + joueurB.getArgent());
		if (joueurA.getArgent() != 1130) // 530 + 600 (loyer recu)
			System.err.println(joueurA + " ne possède pas 1130€ mais " + joueurA.getArgent());
		*/
		
		
		//-------------------Tests pour le déplacement d’un joueur sur la case « Aller en Prison »-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		plateau.setJoueurPresent(joueurA);
		
		joueurA.setPosition(plateau.getLaCase(25)); // gare du nord
		plateau.setLesDes(5);
		joueurA.seDeplacerDe(plateau.getLesDes());
		if (!joueurA.getPosition().equals(plateau.getLaCase(40)))
			System.err.println("Le joueurA n'est pas sur la case Prison mais sur la case " + joueurA.getPosition().getNom());
		joueurA.payerPrison();
		if (joueurA.getArgent() != 1450) // 1500 - 50 (pour sortir de prison)
			System.err.println(joueurA + " ne possède pas 1450€ mais " + joueurA.getArgent());
		if (!joueurA.getPosition().equals(plateau.getLaCase(10))) // simple visite
			System.err.println("Le joueurA n'est pas sur la case simple visite mais sur la case " + joueurA.getPosition().getNom());
		*/
		
		
		//-------------------Tests pour les cartes « Chance » ou « Caisse de communauté »-------------------
		/*
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		plateau.setLesCartesChance("Parametre/CartesChance.csv");
		plateau.setLesCartesCommunaute("Parametre/CartesCommunaute.csv");
		Joueur joueurA = new Joueur(null);
		plateau.ajouterJoueur(joueurA);
		plateau.setJoueurPresent(joueurA);
		
		Carte carteMedecin = plateau.getLaCarteCommunaute("Payez la note du médecin");
		plateau.mettreAuSommetPileCommunaute(carteMedecin);
		plateau.setLesDes(2);
		joueurA.seDeplacerDe(plateau.getLesDes());
		if (joueurA.getArgent() != 1450) // 1500 - 50 (50€ du medecin)
			System.err.println(joueurA + " ne possède pas 1450€ mais " + joueurA.getArgent());
		if (!plateau.getLaCarteDuFondCommunaute().equals(carteMedecin))
			System.err.println("La carte medecin n'est pas au fond de la pile des cartes communaute");
		
		joueurA.setPosition(plateau.getLaCase(0));
		Carte carteHeritez = plateau.getLaCarteCommunaute("Vous héritez de 100€");
		plateau.mettreAuSommetPileCommunaute(carteHeritez);
		plateau.setLesDes(2);
		joueurA.seDeplacerDe(plateau.getLesDes());
		if (joueurA.getArgent() != 1550) // 1450 + 100 (heritage)
			System.err.println(joueurA + " ne possède pas 1600€ mais " + joueurA.getArgent());
		if (!plateau.getLaCarteDuFondCommunaute().equals(carteHeritez))
			System.err.println("La carte heritez n'est pas au fond de la pile des cartes communaute");
		
		joueurA.setPosition(plateau.getLaCase(0));
		Carte carteRetournez = plateau.getLaCarteCommunaute("Retournez à Belleville");
		plateau.mettreAuSommetPileCommunaute(carteRetournez);
		plateau.setLesDes(2);
		joueurA.seDeplacerDe(plateau.getLesDes());
		if (!joueurA.getPosition().equals(plateau.getLaCase(1))) // boulevard de Belleville
			System.err.println(joueurA + " n'est pas au boulevard de Belleville mais à " + joueurA.getPosition());
		if (!plateau.getLaCarteDuFondCommunaute().equals(carteRetournez))
			System.err.println("La carte retournez à Belleville n'est pas au fond de la pile des cartes communaute");
		System.out.println("OK");
		*/
	}
}
