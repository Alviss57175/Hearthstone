package jeu;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;

public class Plateau implements IPlateau {
	
	static private Plateau plateau = null;
	public IJoueur joueurcourant;
	public IJoueur joueur;
	public boolean partiedemaree;
	
	private Plateau() {
		this.joueurcourant = null;
		this.partiedemaree = false;
		
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
		if (joueur == null)
			throw new HearthstoneException("Joueur n'existe pas");
		if (this.joueur != null && this.joueur.equals(joueur))
			throw new HearthstoneException("Joueur d�j� enregistr�");
		if (this.joueur !=null)
			setJoueurCourant(this.joueur);
		this.joueur = joueur;
		
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
		if(this.joueurcourant != null && this.joueurcourant.equals(joueur)) {
			throw new HearthstoneException("Joueur est d�j� le joueur courant");
		}
		if (this.joueurcourant == null)
			this.joueurcourant = joueur;
		else
			this.joueur = this.getJoueurCourant();
			this.joueurcourant = joueur;
	}

	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (joueur == null)
			throw new HearthstoneException("Joueur n'existe pas");
		if (this.joueur == null || this.joueurcourant == null)
			throw new HearthstoneException("Adversaire n'existe pas");
		if (! (this.joueur.equals(joueur)) && !(this.joueurcourant.equals(joueur)) )
			throw new HearthstoneException ("Le joueur n'est pas inscrit et n'a pas d'adversaire");
		if (this.joueur.equals(joueur))
			return this.joueurcourant;
		else
			return this.joueur;
		
		
		
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
		if (this.joueur == null || this.joueurcourant == null)
			throw new HearthstoneException("Des joueurs manquent � l'appel !");
		
		int randomIndex = (int)(Math.random());
		switch (randomIndex) {
			case 0 : 
				System.out.println(this.getJoueurCourant().getPseudo() + " commence la partie !\n");
				break;
			case 1 :
				setJoueurCourant(this.joueur);
				System.out.println(this.getJoueurCourant().getPseudo() + " commence la partie !\n");
				break;
			default :
				throw new HearthstoneException("Probleme avec la fonction random");
		}
		this.partiedemaree = true;
		System.out.println("C'est partie !\n");
	}

	@Override
	public boolean estDemarree() {
		return this.partiedemaree;
	}

	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException, CloneNotSupportedException {
		if (joueur == null)
			throw new HearthstoneException ("Ce joueur n'existe pas");
		if(!(this.joueurcourant.equals(joueur)))
			throw new HearthstoneException ("C'est le tour de " + this.joueurcourant.getPseudo() + ", vous ne pouvez pas finir son tour");
		getJoueurCourant().finirTour();
		setJoueurCourant(getAdversaire(joueur));
		getJoueurCourant().prendreTour();
		
	}

	@Override
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		if (joueur == null)
			throw new HearthstoneException ("Ce joueur n'existe pas");
		if (this.joueur == null || this.joueurcourant == null)
			throw new HearthstoneException("Adversaire n'existe pas");
		if (! (this.joueur.equals(joueur)) && !(this.joueurcourant.equals(joueur)) )
			throw new HearthstoneException ("Le joueur n'est pas inscrit et n'a pas d'adversaire");
		if(getAdversaire(joueur).getHeros().getVie() == 0)
			System.out.println(joueur.getPseudo() + " l'emporte !");
		
	}

}
