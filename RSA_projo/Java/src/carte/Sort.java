package carte;

import capacite.Capacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.Joueur;

public class Sort extends Carte {

	public Capacite capacite;
	
	
	
	
	public Sort(String nom, int cout, Capacite capacite, Joueur proprietaire) throws InvalidArgumentException {
		super(nom, cout, proprietaire);
		this.capacite = capacite;
		
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
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	
	public Capacite getCapacite() {
		return this.capacite;
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

	public String toString() {
		return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ],  Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
	}
	
}
