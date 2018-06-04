package capacite;

import carte.Carte;
import carte.ICarte;
import carte.Serviteur;
/**
 * implémente ICapacite
 */
public abstract class Capacite implements ICapacite {
	
	public String nom;
	public String description;
	/**
	 * Constructeur du type Capacite
	 * @param nom
	 * nom de la capacité
	 * @param description
	 * description de la capacité 
	 */
	public Capacite(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}
	/**
	 * Renvoie le nom de la carte
	 */
	public String getNom() {
		return this.nom;
	}
	/**
	 * Renvoie la description de la carte
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Permet de determiner l'égalité entre cette carte et un autre objet
	 * @param anObject Objet auquel on compare la carte
	 * @return Renvoie vrai si les deux objets sont egaux, sinon, renvoie faux
	 */
	public boolean equals(Object anObject) { //A faire plus précisement
		if (!(anObject instanceof ICapacite) || anObject == null)
			return false;
		if((Capacite) anObject == this)
			return true;
		if(this.getNom().equals(((Capacite) anObject).getNom()) && this.getDescription().equals(((Capacite) anObject).getDescription()))
			return true;
		else
			return false;
		
	}
	/**
	 * Traduit la classe en une chaine de caracteres
	 * @return La chaine en question
	 */
	public String toString() {
        return "Capacite[ "+this.getNom()+" ], Description[ "+this.getDescription()+" ]";
	}
	
	

}
