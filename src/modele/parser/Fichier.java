package modele.parser;

import modele.exceptions.ParserManquantException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modele.parser.Parser;

/**
 * La classe Fichier correspond au demandeur. Elle contient qu'une seule méthode, statique, appelée 'lire'. 
 * Tout se passe dans cette méthode
 */
public class Fichier {
	

	/**
	 * Cette méthode ouvre le fichier (en faisant tous les contrôles nécessaires). Puis, elle boucle sur
	 * chaque ligne et confie au parser le traitement de la ligne. S'il n'y a pas de parser,
	 * la ligne est tout simplement affichée dans la console
	 * @param nomFichier nom du fichier à lire et à parser
	 * @param parser c'est le premier parser de la liste
	 */
	public static void lire(String nomFichier, Parser parser, ArrayList list) {
		if (nomFichier == null)
			throw new IllegalArgumentException("Erreur : nom fichier");
		
		File fichier = new File(nomFichier);
		
		if (! fichier.isFile())
			throw new IllegalArgumentException("Erreur : nom fichier");
		
		BufferedReader reader = null;
		String ligne;

		try {
			reader = new BufferedReader(new FileReader(fichier));
				
			while ((ligne = reader.readLine()) != null) {
				if (parser==null)
					// Si y a pas de parser, alors on ne sais vraiment pas quoi faire avec et on l'affiche...
					System.out.println("Ligne : " + ligne);
				else
					// Puisqu'on a un parser, on l'utilise. C'est lui qui traitera la ligne
					try {
						parser.traiter(ligne, list); // on regrade les parser grace au suivant et quand on trouve le bon on utilise son implementation de parser()
					}
					catch (ParserManquantException e) {
						System.err.println("Aucun parser n'existe pour la ligne " + ligne);
					}
					catch (Exception e) {
						// Je ne me foule pas dans le traitement de l'exception
						e.printStackTrace();
					}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
