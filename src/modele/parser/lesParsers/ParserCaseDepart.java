package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseDepart;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCaseDepart extends Parser {
	public ParserCaseDepart(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("CASE DEPART");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseDepart(Integer.parseInt(tab[0]), tab[1], Integer.parseInt(tab[2])));
	}
}
