package application.event;

import application.FenetreTerrain;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.cases.lesCases.CaseTerrainConstructible;


public class EventGererMaison implements EventHandler<ActionEvent> {

	private	Monopoly	monopoly;
	
	public EventGererMaison(Monopoly monopoly) {
		this.monopoly = monopoly;
	}

	@Override
	public void handle(ActionEvent e) {
		if (monopoly.getTerrainSelectionne()==-1)
			monopoly.DialogAction("Tu n'as selectionne aucun terrain !", true);
		else {
			if (!(monopoly.getZoneProprietes().getItems().get(monopoly.getTerrainSelectionne()) instanceof CaseTerrainConstructible))
				monopoly.DialogAction("Tu ne peux pas gérer de maison sur ce type de propriété !", true);
			else {
				CaseTerrainConstructible terrain = (CaseTerrainConstructible)(monopoly.getZoneProprietes().getItems().get(monopoly.getTerrainSelectionne()));
				new FenetreTerrain(terrain);
				//monopoly.getFenetreTerrain().show();
			}
		}
	}

}
