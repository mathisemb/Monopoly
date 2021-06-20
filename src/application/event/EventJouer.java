package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.cases.Case;
import modele.cases.lesCases.CaseCaisseCommunaute;
import modele.cases.lesCases.CaseChance;
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
			monopoly.DialogAction("Remplissez correctement la valeur des d�s", true);
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

			
			
			// On affiche une boite de dialogue avec le contenu de la carte (avant le d�placement car il y a certaines cartes qui provoquent des d�placement)
			Case futureCase = Plateau.getInstance().getLaCase(monopoly.getJoueurCourant().getPosition().getNumero()+nbCases);
			if (futureCase instanceof CaseCaisseCommunaute || futureCase instanceof CaseChance) {
				try {
					monopoly.afficheCarte(futureCase);
				} catch (MonopolyException e1) {
					e1.printStackTrace();
				}
			}		

			// Le joueur se d�place
			try {
				monopoly.getJoueurCourant().seDeplacerDe(nbCases);
			} catch (MonopolyException e) {
				monopoly.DialogAction(e.getMessage(), true);
			}
			
			// On m�j le panneau de droite
			monopoly.setInfosJoueurCourant();
			
			// On met enfin � jour l'ui du plateau
			monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
			
		}
	}
}
