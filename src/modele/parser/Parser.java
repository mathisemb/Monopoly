package modele.parser;

import java.util.ArrayList;

import modele.exceptions.ParserManquantException;

public abstract class Parser {

	private Parser suivant = null;
	
	public Parser(Parser suivant) {
		this.suivant = suivant;
	}

	public void traiter(String ligne, ArrayList list) throws Exception {
		if (saitParser(ligne))
			// Si le parser sait parser la ligne, il la parse
			parser(ligne, list);
		else if (aUnSuivant()) 
			// S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
			getSuivant().traiter(ligne, list);
		else
			// Sinon, on est arrivé au bout sans trouver un parser
			// et on lance une exception ! Que le prog appelant se débrouille avec cette ligne !
			throw new ParserManquantException();
		
	}

	private Parser getSuivant() {
		return suivant;
	}

	private boolean aUnSuivant() {
		return suivant != null;
	}


	public abstract void parser(String ligne, ArrayList list) throws Exception;
	

	public abstract boolean saitParser(String ligne);

}
