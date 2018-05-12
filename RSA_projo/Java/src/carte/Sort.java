package carte;

import capacite.ICapacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.IJoueur;

public class Sort implements ICarte {

	public String nom;
	public int cout;
	public ICapacite capacite;
	public IJoueur proprietaire;
	
	
	
	public Sort(String nom, int cout, ICapacite capacite, IJoueur proprietaire) throws InvalidArgumentException {
		if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(capacite == null || proprietaire == null)
			throw new InvalidArgumentException();
		if(cout < 0)
			throw new InvalidArgumentException();
		
		this.nom = nom;
		this.cout = cout;
		this.capacite = capacite;
		this.proprietaire = proprietaire;
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
		// TODO Auto-generated method stub
		return super.clone();
	}

}
