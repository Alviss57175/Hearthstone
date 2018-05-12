package carte;

import joueur.Joueur;

public abstract class Carte implements ICarte {
	
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
		return nom.equals(anObject);
	}

	public String toString() {
		return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
	}
	
	
	

}
