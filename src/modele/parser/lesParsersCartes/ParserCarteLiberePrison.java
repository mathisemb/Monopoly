package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteLiberePrison;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCarteLiberePrison extends Parser {
	public ParserCarteLiberePrison(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("libéré de prison");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CarteLiberePrison(tab[0]));
	}
}
