package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseChance;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserChance extends Parser {
	public ParserChance(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("CHANCE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseChance(Integer.parseInt(tab[0]), tab[1]));
	}
}
