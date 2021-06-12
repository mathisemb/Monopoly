package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseSimpleVisite;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserSimpleVisite extends Parser {
	public ParserSimpleVisite(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("SIMPLE VISITE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseSimpleVisite(Integer.parseInt(tab[0]), tab[1]));
	}
}
