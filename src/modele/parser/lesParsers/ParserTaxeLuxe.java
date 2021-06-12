package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseTaxeLuxe;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserTaxeLuxe extends Parser {
	public ParserTaxeLuxe(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("TAXE DE LUXE");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseTaxeLuxe(Integer.parseInt(tab[0]), tab[1]));
	}
}
