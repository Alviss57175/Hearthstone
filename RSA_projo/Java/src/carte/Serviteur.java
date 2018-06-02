package carte;

import java.io.IOException;

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
	public boolean usepouvoir;  //Determine si le Serviteur peut utiliser sont pouvoir durant le tour, ou s'il l'a déjà fait
	
	
	public Serviteur(String nom, int atk, int def, int cout, Capacite capacite, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.capacite = capacite;
		this.proprietaire = proprietaire;
		this.jouable = false;
		this.provoc = false;
		this.usepouvoir = false;
	}
	
	public Serviteur(String nom, int atk, int def, int cout, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.jouable = false;
		this.capacite = null;
		this.provoc = false;
		this.usepouvoir = false;
	}
	
	public Serviteur(Serviteur c) {
		super(c.getNom(), c.getCout(), c.getProprietaire());
		
		this.atk = c.getAtk();
		this.def = c.getDef();
		this.jouable = false;
		this.provoc = false;
		this.usepouvoir = false;
	}
	
	
	@Override
	public boolean equals(Object anObject) { // A completer
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire()) && this.getAtk() == ((Serviteur) anObject).getAtk() && this.getDef() == ((Serviteur) anObject).getDef() && this.getCapacite().equals(((Serviteur) anObject).getCapacite()) && this.isJouable() == (((Serviteur) anObject).isJouable()) && this.isProvoc() == (((Serviteur) anObject).isProvoc()) && this.isUsePouvoir() == (((Serviteur) anObject).isUsePouvoir())) 
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
	
	public boolean isUsePouvoir() {
		return usepouvoir;
	}

	public void setUsePouvoir(boolean usepouvoir) {
		this.usepouvoir = usepouvoir;
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

	public void PerdreDef(int degats) throws HearthstoneException {
		System.out.println(degats + " point(s) de dégâts infligé(s) à " + this.getNom());
		this.def = this.def - degats;
		if(this.disparait()) {
			this.proprietaire.perdreCarte(this);
		}
	}
	
	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if(!(this.capacite == null)) {
		this.getCapacite().executerEffetDebutTour();
		}
		
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetFinTour();
		}
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetMiseEnJeu(cible);
		}
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetDisparition(cible);
		}
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerAction(cible);
		}
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
