package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EventGererMaison implements EventHandler<ActionEvent> {

	private	Monopoly	monopoly;
	
	public EventGererMaison(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		if (monopoly.getTerrainSelectionne()==-1)
			monopoly.DialogAction("Tu n'as selectionne aucun terrain !", true);
		else
			monopoly.getFenetreTerrain().show();
	}

}
