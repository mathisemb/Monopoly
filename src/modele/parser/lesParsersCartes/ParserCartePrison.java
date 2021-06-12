package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CartePrison;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCartePrison extends Parser {
	public ParserCartePrison(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("PRISON");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on r�cup�re chaque champ s�par� par ";"
		list.add(new CartePrison(tab[0]));
	}
}
