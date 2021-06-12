package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseParkingGratuit;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserParkingGratuit extends Parser {
	public ParserParkingGratuit(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("PARKING GRATUIT");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseParkingGratuit(Integer.parseInt(tab[0]), tab[1]));
	}
}
