package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteReparationMaison;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCarteReparationMaison extends Parser {
	public ParserCarteReparationMaison(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("réparations dans toutes vos maisons");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CarteReparationMaison(tab[0]));
	}
}
