package modele.parser.lesParsers;

import java.util.ArrayList;

import modele.cases.lesCases.CaseImpotRevenu;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserImpotRevenu extends Parser {
	public ParserImpotRevenu(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("IMPOT SUR LE REVENU");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		Plateau.getInstance().getLesCases().add(new CaseImpotRevenu(Integer.parseInt(tab[0]), tab[1], Integer.parseInt(tab[2])));
	}
}
