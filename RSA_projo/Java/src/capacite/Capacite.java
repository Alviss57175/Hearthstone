package capacite;

import carte.Carte;
import carte.ICarte;
import carte.Serviteur;

public abstract class Capacite implements ICapacite {
	
	public String nom;
	public String description;
	
	public Capacite(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

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

	public String toString() {
        return "Nom Capacite[ "+this.getNom()+" ], Description[ "+this.getDescription()+" ]";
	}
	
	

}
