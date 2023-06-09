package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import modele.joueur.Joueur;

public class EventChoixJoueur implements EventHandler<ActionEvent> {

	private Monopoly monopoly;
	
	
	public EventChoixJoueur(Monopoly monopoly) {
		this.monopoly = monopoly;
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof ToggleButton) {
			ToggleButton b = (ToggleButton)e.getSource();
			Joueur j = (Joueur) b.getUserData();
			
			monopoly.setJoueurCourant(j);
			System.out.println(monopoly.getJoueurCourant()+" doit jouer");
			
			monopoly.getZoneProprietes().getItems().clear();
		}
	}

}
