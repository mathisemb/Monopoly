package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseCaisseCommunaute;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCaisseCommunaute extends Parser {
	public ParserCaisseCommunaute(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("CAISSE COMMUNAUTE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseCaisseCommunaute(Integer.parseInt(tab[0]), tab[1]));
	}
}
