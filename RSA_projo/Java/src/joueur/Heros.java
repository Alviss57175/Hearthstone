package joueur;

import capacite.Capacite;
import capacite.ICapacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import jeu.Plateau;

public class Heros implements Cloneable{

	public String nom;
	public int vie;
	public Capacite pouvoir;
	public boolean usepouvoir; //Permet de determiner si le héros à déjà utiliser son pouvoir ce tour-ci
	public IJoueur proprietaire; //Permet de determiner le vainqueur si les pdv du héros tombent à 0

	public Heros(String nom, Capacite pouvoir, int vie) throws InvalidArgumentException {
		if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(pouvoir == null)
			throw new InvalidArgumentException();
		setNom(nom);
		setPouvoir(pouvoir);
		setVie(vie);
		this.proprietaire = null;
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
	
	public IJoueur getProprietaire() {
		return proprietaire;
	}
	
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public boolean getUsePouvoir() {
		return usepouvoir;
	}
	
	public void setUsePouvoir(boolean b) {
		this.usepouvoir = b;
	}
	
	public void perdreVie(int degats) throws HearthstoneException {
		System.out.println(degats + " point(s) de dégâts infligé(s) au héros !");
		this.vie =this.vie - degats;
		if (this.vie <= 0) {
			System.out.println(this.getProprietaire().getPseudo() + " n'a plus de point de vie...");
			Plateau.getInstance().gagnePartie((Plateau.getInstance().getAdversaire(this.proprietaire)));
		}
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	
	
	
}
