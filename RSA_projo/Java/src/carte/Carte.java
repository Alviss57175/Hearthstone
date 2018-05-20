package carte;

import joueur.Joueur;

public abstract class Carte implements ICarte, Cloneable{
	
	public String nom;
	public int cout;
	public Joueur proprietaire;
	
	public Carte(String nom, int cout, Joueur proprietaire) {
		this.nom = nom;
		this.cout = cout;
		this.proprietaire = proprietaire;
	}

	public String getNom() {
		return nom;
	}

	public int getCout() {
		return cout;
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}

	public boolean equals(Object anObject) {
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire())) 
			return true;
		else
			return false;
	}

	public String toString() {
		return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	

}
