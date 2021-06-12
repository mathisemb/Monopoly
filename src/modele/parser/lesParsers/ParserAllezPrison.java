package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseAllezPrison;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserAllezPrison extends Parser {
	public ParserAllezPrison(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("ALLEZ EN PRISON");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseAllezPrison(Integer.parseInt(tab[0]), tab[1]));
	}
}
