package joueur;

import capacite.ICapacite;
import exception.InvalidArgumentException;

public abstract class Heros implements IJoueur{

	public String nom;
	public ICapacite pouvoir;

	public Heros(String nom, ICapacite pouvoir, int mana, int stockmana) throws InvalidArgumentException {
		if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(pouvoir == null)
			throw new InvalidArgumentException();
		setNom(nom);
		setPouvoir(pouvoir);	
	}
	
	public String getNom() {
		return nom;
	}
	private void setNom(String nom) {
		this.nom = nom;
	}
	
	public ICapacite getPouvoir() {
		return pouvoir;
	}
	private void setPouvoir(ICapacite pouvoir) {
		this.pouvoir = pouvoir;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
	
}
