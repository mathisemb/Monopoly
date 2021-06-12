package modele.parser.lesParsersCartes;

import java.util.ArrayList;

import modele.cartes.lesCartes.CarteGain;
import modele.parser.Parser;
import modele.plateau.Plateau;

public class ParserCarteGain extends Parser {
	public ParserCarteGain(Parser suivant) {
		super(suivant);
	}
	
	public boolean saitParser(String ligne) {
		return ligne.contains("Recevez")
				|| ligne.contains("héritez")
				|| ligne.contains("doit vous donner")
				|| ligne.contains("vous remboursent")
				|| ligne.contains("vous rapporte")
				|| ligne.contains("Vous devez toucher")
				|| ligne.contains("vous verse");
	}
	
	public void parser(String ligne, ArrayList list) throws Exception {
		String[] tab = ligne.split(";"); // on récupère chaque champ séparé par ";"
		list.add(new CarteGain(tab[0], Integer.parseInt(tab[1])));
	}
}
