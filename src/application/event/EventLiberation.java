package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modele.cartes.Carte;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;

public class EventLiberation implements EventHandler<ActionEvent> {

	private	Monopoly monopoly;
	
	public EventLiberation(Monopoly monopoly) {
		this.monopoly = monopoly;
	}
	
	@Override
	public void handle(ActionEvent event) {
		if ( !(Plateau.getInstance().getJoueurPresent().getPosition().equals(Plateau.getInstance().getLaCase("PRISON"))) ) {
			monopoly.DialogAction("Vous n'êtes pas en prison !", true);
		}
		else {
			// Il y a 2 cartes libéré de prison
			Carte libere1 = Plateau.getInstance().getLaCarteChance("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou vendue.");
			Carte libere2 = Plateau.getInstance().getLaCarteChance("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée ou vendue.");
			if (monopoly.getJoueurCourant().getLesCartes().contains(libere1) || monopoly.getJoueurCourant().getLesCartes().contains(libere2)) {
				try {
					Plateau.getInstance().getJoueurPresent().seDeplacerVers(Plateau.getInstance().getLaCase(10)); // simple visite
					monopoly.setInfosJoueurCourant();
					monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
				} catch (MonopolyException e) {
					e.printStackTrace();
				}
			}
			else
				monopoly.DialogAction("Vous ne possédez pas de carte de libération, vous devez rester en priosn !", true);
		}
	}

}
