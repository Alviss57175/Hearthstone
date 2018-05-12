package capacite;

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
		return description.equals(anObject);
	}

	public String toString() {
        return "Nom Capacite[ "+this.getNom()+" ], Description[ "+this.getDescription()+" ]";
	}
	
	

}
