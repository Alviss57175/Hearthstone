package carte;

import capacite.Capacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.Joueur;

public class Serviteur extends Carte {
	
	public int atk;	//Attaque du Serviteur
	public int def;	//Defense du Serviteur (Fais office de Points de Vie)
	public Capacite capacite;	//Capacite du serviteur (Null si le Serviteur n'a pas de capacité)
	public boolean jouable;		//Determine si le Serviteur peut attaquer ce tour ou non
	public boolean provoc;		//Determine si le Serviteur possède le bonus "Provocation" (Bonus != Capacite)
	
	
	
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
		this.jouable = false;
		this.capacite = null;
	}
	
	public Serviteur(Serviteur c) {
		super(c.getNom(), c.getCout(), c.getProprietaire());
		
		this.atk = c.getAtk();
		this.def = c.getDef();
		this.jouable = false;
	}
	
	
	@Override
	public boolean equals(Object anObject) { // A completer
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire()) && this.getAtk() == ((Serviteur) anObject).getAtk() && this.getDef() == ((Serviteur) anObject).getDef() && this.getCapacite().equals(((Serviteur) anObject).getCapacite()) && this.isJouable() == (((Serviteur) anObject).isJouable())) 
			return true;
		else
			return false;
		
	}


	public int getAtk() {
		return atk;
	}


	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public boolean isProvoc() {
		return provoc;
	}

	public void setProvoc(boolean provoc) {
		this.provoc = provoc;
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
		System.out.println(degats + " point(s) de dégâts infligé(s) à " + this.getNom());
		this.def = this.def - degats;
	}
	
	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException, CloneNotSupportedException {
		this.getCapacite().executerEffetDebutTour();
		
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetFinTour();
		
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
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
		if(this.getDef() <= 0)
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
		if( this.capacite != null && this.proprietaire != null) {
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
		}
		else {
			if (this.capacite == null && this.proprietaire == null) {	
				return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Jouable [ " + this.jouable + " ]";
			}
			else {
				if(this.capacite == null)
					return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
				else
					return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Capacite [ " + this.capacite.getNom() + " ], Jouable [ " + this.jouable + " ]";
			}
		}
	}
	

}
