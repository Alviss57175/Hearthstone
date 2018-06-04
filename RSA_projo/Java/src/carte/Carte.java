package carte;

import joueur.Joueur;
/**
 * Une carte, est une... carte
 */
public abstract class Carte implements ICarte, Cloneable{

	public String nom; //Nom de la carte
	public int cout; //Cout de la carte
	public Joueur proprietaire; //Propietaire de la carte
	
	/**
	 * Constructeur du type Carte
	 * @param nom Nom de la carte
	 * @param cout Cout en Mana de la carte
	 * @param proprietaire Proprietaire de la carte
	 */
	
	public Carte(String nom, int cout, Joueur proprietaire) {
		this.nom = nom;
		this.cout = cout;
		this.proprietaire = proprietaire;
	}

	/**
	 * Renvoie le nom de la carte
	 * @return retourne le nom 
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Renvoie le cout en mana de la carte
	 * @return retourne le cout
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * Renvoie le proprietaire de la carte
	 * @return retourne le propriétaire
	 */
	public Joueur getProprietaire() {
		return proprietaire;
	}
	
	/**
	 * Etablie le proprietaire d'une carte (A sa création, le proprietaire d'une carte est généralement null)
	 * @param proprietaire Joueur auquel appartient la carte
	 */
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	/**
	 * Permet de determiner l'égalité entre cette carte et un autre objet
	 * @param anObject Objet auquel on compare la carte
	 * @return Renvoie vrai si les deux objets sont egaux, sinon, renvoie faux
	 */
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
	
	/**
	 * Traduit la classe en une chaine de caracteres
	 * @return La chaine en question
	 */
	public String toString() {
		return "Nom [ " + this.nom + " ], Cout [ " + this.cout + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
	}
	
	/**
	 * Clone la carte
	 * @throws CloneNotSupportedException Au cas où le clonage echoue
	 * @return Renvoie une nouvelle instance de cette carte 
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	

}
