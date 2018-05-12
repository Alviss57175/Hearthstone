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
		this.vie =this.vie - degats;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	public String toString() {
		
		return "Nom[ "+this.getNom()+" ], Vie[ "+this.getVie()+" ], Pouvoir[ "+this.getPouvoir().getNom()+" ]";
	
	}
	
	
	
	
	
}
