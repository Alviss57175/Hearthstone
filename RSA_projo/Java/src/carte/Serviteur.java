package carte;

import capacite.Capacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.Joueur;

public class Serviteur extends Carte {
	
	public int atk;
	public int def;
	public Capacite capacite;
	public boolean jouable;
	
	
	
	public Serviteur(String nom, int atk, int def, int cout, Capacite capacite, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.capacite = capacite;
		this.proprietaire = proprietaire;
		this.jouable = false;
	}
	
	public Serviteur(String nom, int atk, int def, int cout, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.proprietaire = proprietaire;
		this.jouable = false;
	}
	
	
	@Override
	public boolean equals(Object obj) { // A completer
		// TODO Auto-generated method stub
		return super.equals(obj);
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


	public Capacite getCapacite() {
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
	public Joueur getProprietaire() {
		return this.proprietaire;
	}

	public void PerdreDef(int degats) {
		this.def = this.def - degats;
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
	
	public String toString() {
		if(!(this.capacite == null)) {
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
		}
		else {
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
		}
	}
	

}
