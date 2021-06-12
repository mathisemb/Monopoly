package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseGare;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserGare extends Parser {
	public ParserGare(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("GARE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseGare(Integer.parseInt(tab[0]), tab[1], tab[2]));
	}
}
