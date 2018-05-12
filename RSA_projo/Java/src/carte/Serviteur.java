package carte;

import capacite.ICapacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.IJoueur;

public abstract class Serviteur implements ICarte {

	public String nom;
	public int atk;
	public int def;
	public int cout;
	public ICapacite capacite;
	public IJoueur proprietaire;
	public boolean jouable;
	
	
	
	public Serviteur(String nom, int atk, int def, int cout, ICapacite capacite, IJoueur proprietaire) throws InvalidArgumentException {
		if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(capacite == null || proprietaire == null)
			throw new InvalidArgumentException();
		if(atk < 0 || def < 0 || cout < 0)
			throw new InvalidArgumentException();
		
		this.nom = nom;
		this.atk = atk;
		this.def = def;
		this.cout = cout;
		this.capacite = capacite;
		this.proprietaire = proprietaire;
		this.jouable = false;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	public int getAtk() {
		return atk;
	}

	

	public boolean isJouable() {
		return jouable;
	}


	public void setJouable(boolean jouable) {
		this.jouable = jouable;
	}


	public int getDef() {
		return def;
	}


	public ICapacite getCapacite() {
		return capacite;
	}


	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getCout() {
		return this.cout;
	}

	@Override
	public IJoueur getProprietaire() {
		return this.proprietaire;
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean disparait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
