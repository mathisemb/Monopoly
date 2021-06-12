package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseCompagnie;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCompagnie extends Parser {
	public ParserCompagnie(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("COMPAGNIE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseCompagnie(Integer.parseInt(tab[0]), tab[1], tab[2], Integer.parseInt(tab[3])));
	}
}
