package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;


public class EventJouer implements EventHandler<ActionEvent> {
	
	private Monopoly monopoly;
	
	
	public EventJouer(Monopoly monopoly) {
		this.monopoly = monopoly;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		String tfDe1 = monopoly.getTfValeurDe1().getText();
		String tfDe2 = monopoly.getTfValeurDe2().getText();
		if (tfDe1.trim().isEmpty() || tfDe2.trim().isEmpty())
			monopoly.DialogAction("Remplissez correctement la valeur des dés", true);
		else {
			int de1 = Integer.parseInt(tfDe1);
			int de2 = Integer.parseInt(tfDe2);
			
			int nbCases = de1+de2;
			
			Plateau.getInstance().setLesDes(nbCases);
			
			if (de1==de2) {
				int nbDbl = monopoly.getNbDoubles();
				
				nbDbl++;
				monopoly.setNbDoubles(nbDbl);
				if (nbDbl==1)
					monopoly.getMessageFooter().setText("C'est ton premier double !");
				else if (nbDbl==2)
					monopoly.getMessageFooter().setText("C'est ton deuxieme double !! Encore un et c'est la taule...");
				else {
					monopoly.getMessageFooter().setText("Police, menottes, prison...");
					try {
						monopoly.setNbDoubles(0);			
					} catch (Exception e) {
						monopoly.DialogAction(e.getMessage(), true);
					}
				}
			} else {
				monopoly.setNbDoubles(0);
			}
			
			try {
				monopoly.getJoueurCourant().seDeplacerDe(nbCases);
			} catch (MonopolyException e) {
				monopoly.DialogAction(e.getMessage(), true);
			}
			
			monopoly.setInfosJoueurCourant();
			
			monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
			
		}
	}
}