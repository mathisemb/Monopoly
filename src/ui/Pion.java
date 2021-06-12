package ui;


/**
 * Cette classe existe peut-être dans votre projet. Si c'est le cas, n'utiliser pas la classe ci-dessous mais la vôtre
 * 
 * @author YL
 *
 */
public class Pion {
	private	String	nom = "rien";
	private	int		position = 0;

	
	public Pion(String nom) {
		setNom(nom);
	}

	public String getNom() {
		return nom;
	}
	
	private void setNom(String nom) {
		if (nom==null || nom.trim().equals(""))
			throw new IllegalArgumentException("Le nom du pion ne peut pas etre vide ou null");
		this.nom = nom;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		if (position < 0 || position > 40)
			throw new IllegalArgumentException("Le numero de la case est faux");

		this.position = position;
	}

	@Override
	public String toString() {
		return "Pion [nom=" + nom + "]";
	}
	
	
	

}
