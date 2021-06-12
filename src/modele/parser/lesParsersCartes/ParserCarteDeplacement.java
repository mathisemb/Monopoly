package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteDeplacement;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCarteDeplacement extends Parser {
	public ParserCarteDeplacement(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("Avancez")
				|| ligne.contains("Retournez")
				|| ligne.contains("Rendez-vous")
				|| ligne.contains("Allez");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		Plateau plateau = Plateau.getInstance();
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CarteDeplacement(tab[0], plateau.getLaCase(tab[1])));
	}
}
