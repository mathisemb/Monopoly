package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CasePrison;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserPrison extends Parser {
	public ParserPrison(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("PRISON");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CasePrison(Integer.parseInt(tab[0]), tab[1]));
	}
}
