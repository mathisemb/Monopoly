package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CartePerte;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCartePerte extends Parser {
	public ParserCartePerte(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("Payez") || ligne.contains("Amende");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CartePerte(tab[0], Integer.parseInt(tab[1])));
	}
}
