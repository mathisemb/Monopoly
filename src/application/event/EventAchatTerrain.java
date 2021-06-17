package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.cases.Propriete;
import modele.exceptions.MonopolyException;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

	private	Monopoly	monopoly;
	
	public EventAchatTerrain(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		if (!(monopoly.getJoueurCourant().getPosition() instanceof Propriete))
			monopoly.DialogAction("Vous ne pouvez pas acheter cette case...", true);
		else {
			Propriete prop = (Propriete)(monopoly.getJoueurCourant().getPosition());
			try {
				monopoly.getJoueurCourant().acheterPropriete(prop);
				monopoly.setInfosJoueurCourant();
				monopoly.DialogInfo("Achat effectué !");
			} catch (MonopolyException e1) {
				monopoly.DialogAction(e1.getMessage(), true);
			}
		}
	}

}
