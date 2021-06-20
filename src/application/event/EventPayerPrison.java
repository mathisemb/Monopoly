package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class EventPayerPrison implements EventHandler<ActionEvent> {

	private	Monopoly monopoly;
	
	public EventPayerPrison(Monopoly monopoly) {
		this.monopoly = monopoly;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if ( !(Plateau.getInstance().getJoueurPresent().getPosition().equals(Plateau.getInstance().getLaCase("PRISON"))) ) {
			monopoly.DialogAction("Vous n'êtes pas en prison !", true);
		}
		else {
			Plateau.getInstance().getJoueurPresent().payerPrison();
			monopoly.DialogInfo("Félicitations, vous êtes libéré de prison");
			monopoly.setInfosJoueurCourant();
			monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
			try {
				Plateau.getInstance().getJoueurPresent().seDeplacerVers(Plateau.getInstance().getLaCase(10)); // simple visite
			} catch (MonopolyException e) {
				e.printStackTrace();
			}
		}
	}

}
