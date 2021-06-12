package modele.cases;

import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;
import ui.UICase;

public abstract class Case {
	private int numero;
	private String nom;
	private UICase ui;
	
	
	
	public Case(int numero, String nom) throws MonopolyException {
		this.numero = numero;
		if (nom == null || nom.trim().equals(""))
			throw new MonopolyException("Erreur : nom case");
		this.nom = nom;
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}
	
	public UICase getUi() {
		return ui;
	}
	
	
	public Case getSuivant() {
		return Plateau.getInstance().getLesCases().get(numero+1);
	}
	
	public abstract void action() throws MonopolyException;
	
	public abstract String getTypeCase();

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	
}
