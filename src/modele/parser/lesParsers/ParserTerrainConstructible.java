package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseTerrainConstructible;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserTerrainConstructible extends Parser {
	public ParserTerrainConstructible(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("TERRAIN CONSTRUCTIBLE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseTerrainConstructible(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], 
				Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), Integer.parseInt(tab[6]), Integer.parseInt(tab[7]), Integer.parseInt(tab[8]), Integer.parseInt(tab[9]),
				Integer.parseInt(tab[10]), Integer.parseInt(tab[11])));
	}
}
