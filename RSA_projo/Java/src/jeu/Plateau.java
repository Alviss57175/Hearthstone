package jeu;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Plateau implements IPlateau {
	
	static private Plateau plateau = null;
	public IJoueur joueurcourant;
	public IJoueur joueuradverse;
	public ArrayList<IJoueur> participants;
	public boolean partiedemaree;
	
	private Plateau() {
		this.joueurcourant = null;
		this.joueuradverse = null;
		this.partiedemaree = false;
		this.participants = new ArrayList<IJoueur>();
		
	}
	
	static public Plateau getInstance() {
		if (!(plateau == null))
				return plateau;
		else
			plateau = new Plateau();
			return plateau;
	}
	
	@Override
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if(joueur == null)
			throw new HearthstoneException("Joueur n'existe pas");
		if(this.participants.size() >= 2)
			throw new HearthstoneException("Deux joueurs déjà enregistrés");
		if(participants.size() == 1 && joueur.equals(this.participants.get(0)))
			throw new HearthstoneException("Joueur déjà enregistré");
		this.participants.add(joueur);
	}

	@Override
	public IJoueur getJoueurCourant() {
		return this.joueurcourant;
	}

	@Override
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if(joueur == null) {
			throw new HearthstoneException("Joueur n'existe pas");
		}
		if(!(this.participants.contains(joueur))) {
			throw new HearthstoneException("Joueur n'est pas inscrit");
		}
		if(this.joueurcourant.equals(joueur)) {
			throw new HearthstoneException("Joueur est déjà le joueur courant");
		}
		if(this.participants.indexOf(joueur) == 0) {
			this.joueurcourant = this.participants.get(0);
			this.joueuradverse = this.participants.get(1);
		}
		else {
			this.joueurcourant = this.participants.get(1);
			this.joueuradverse = this.participants.get(0);
		}
	}

	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		return this.joueuradverse;
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estDemarree() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
