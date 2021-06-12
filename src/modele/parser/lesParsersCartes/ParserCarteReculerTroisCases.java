package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteReculerTroisCases;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCarteReculerTroisCases extends Parser {
	public ParserCarteReculerTroisCases(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("Reculer de trois cases");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on r�cup�re chaque champ s�par� par ";"
		list.add(new CarteReculerTroisCases(tab[0]));
	}
}
