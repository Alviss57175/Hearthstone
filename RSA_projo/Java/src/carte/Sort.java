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
	
	public Sort(Sort c) {
		super(c.getNom(), c.getCout(), c.getProprietaire());
		this.capacite = c.getCapacite();
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
		this.getCapacite().executerEffetDebutTour();
		
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetFinTour();
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetMiseEnJeu(cible);
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetDisparition(cible);
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		this.getCapacite().executerAction(cible);
	}

	@Override
	public boolean disparait() {
		return true;
	}

	@Override
	public boolean equals(Object anObject) { // A completer
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire()) && this.getCapacite().equals(((Serviteur) anObject).getCapacite())) 
			return true;
		else
			return false;
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	public String toString() {
		return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ],  Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
	}
	
}
