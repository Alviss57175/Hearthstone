package joueur;

import capacite.Capacite;
import capacite.ICapacite;
import exception.InvalidArgumentException;

public class Heros{

	public String nom;
	public int vie;
	public Capacite pouvoir;

	public Heros(String nom, Capacite pouvoir, int vie) /*throws InvalidArgumentException*/ {
		/*if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(pouvoir == null)
			throw new InvalidArgumentException();*/
		setNom(nom);
		setPouvoir(pouvoir);
		setVie(vie);
	}
	
	public String getNom() {
		return nom;
	}
	
	
	private void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getVie() {
		return vie;
	}
	
	private void setVie(int vie) {
		this.vie = vie;
	}
	
	public ICapacite getPouvoir() {
		return pouvoir;
	}
	
	private void setPouvoir(Capacite pouvoir) {
		this.pouvoir = pouvoir;
	}
	
	public void PerdreVie(int degats) {
		System.out.println(degats + " point(s) de dégâts infligé(s) au héros !");
		this.vie =this.vie - degats;
	}
	
	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Heros) || anObject == null)
			return false;
		if((Heros) anObject == this)
			return true;
		if(this.getNom().equals(((Heros) anObject).getNom()) && this.getPouvoir().equals(((Heros) anObject).getPouvoir()) && this.getVie() == ((Heros)anObject).getVie())
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		
		return "Nom[ "+this.getNom()+" ], Vie[ "+this.getVie()+" ], Pouvoir[ "+this.getPouvoir().getNom()+" ]";
	
	}
	
	
	
	
	
}
