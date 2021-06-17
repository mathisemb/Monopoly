package application.event;

import java.util.ArrayList;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import modele.joueur.Joueur;
import modele.plateau.Plateau;

public class EventPasser implements EventHandler<ActionEvent> {
	
	private	Monopoly monopoly;
	
	public EventPasser(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		
		// TODO Verifier lorsqu'il y a eu double, ou prison
		
		monopoly.getZoneProprietes().getItems().clear(); // on commence par vider la liste des proprietes
		
		ArrayList<Joueur> lesJoueurs = monopoly.getListeJoueurs();
		Joueur jc = monopoly.getJoueurCourant();
		int i = lesJoueurs.indexOf(jc);
		int suivant = (i+1) % lesJoueurs.size();

		Plateau.getInstance().setJoueurPresent(lesJoueurs.get(suivant)); // on met à jour le joueur courant de la partie métier (une seule ligne à rajouter plutot
																		 // que de le mettre en commun)
		
		ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
		button.fire();
		
		monopoly.setInfosJoueurCourant();
	}

}
