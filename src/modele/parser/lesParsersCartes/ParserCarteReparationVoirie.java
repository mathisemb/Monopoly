package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteReparationVoirie;
import modele.parser.Parser;

public class ParserCarteReparationVoirie extends Parser {
	public ParserCarteReparationVoirie(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("réparations de voirie");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CarteReparationVoirie(tab[0]));
	}
}
