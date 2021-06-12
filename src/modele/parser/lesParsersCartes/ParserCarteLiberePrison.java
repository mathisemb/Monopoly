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
		return ligne.contains("lib�r� de prison");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on r�cup�re chaque champ s�par� par ";"
		list.add(new CarteLiberePrison(tab[0]));
	}
}
