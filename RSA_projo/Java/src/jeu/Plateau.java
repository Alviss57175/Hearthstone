package jeu;

import java.util.ArrayList;

import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;
/**
 * Implémente IPlateau
 */
public class Plateau implements IPlateau {
	
	static private Plateau plateau = null;
	public IJoueur joueurcourant;
	public IJoueur joueur;
	public boolean partiedemaree;
	/**
	 * Constructeur du type plateau
	 */
	private Plateau() {
		this.joueurcourant = null;
		this.partiedemaree = false;
		
	}
	/**
	 * Créer un plateau si il n'existe pas
	 * @return retourne le plateau existant, ou un nouveau plateau dans le cas échéant
	 */
	static public Plateau getInstance() {
		if (!(plateau == null))
				return plateau;
		else
			plateau = new Plateau();
			return plateau;
	}
	
	@Override
	 /**
     * Ajoute un joueur Ã  la partie. 
     * @param joueur Le joueur Ã  ajouter
     * @throws HearthstoneException si l'on essaie d'ajouter un 3e joueur par exemple, si le joueur est null, etc...
     */
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (joueur == null)
			throw new HearthstoneException("Joueur n'existe pas");
		if (this.joueur != null && this.joueur.equals(joueur))
			throw new HearthstoneException("Joueur déjà enregistré");
		if (this.joueur !=null)
			setJoueurCourant(this.joueur);
		this.joueur = joueur;
		
	}
	

	@Override
	/**
     * Renvoie le joueur courant, c'est-Ã -dire celui qui a le tour et qui doit jouer
      * @return le joueur courant, ou null si la partie n'est pas dÃ©marÃ©e
      */
	public IJoueur getJoueurCourant() {
		return this.joueurcourant;
	}

	@Override
	/**
     * Le setter qui va avec le getter
     * @param joueur Le nouveau joueur courant
     * @throws HearthstoneException si le nouveau joueur est null, etc. (faut tout tester naturellement...)
     */
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if(joueur == null) {
			throw new HearthstoneException("Joueur n'existe pas");
		}
		if(this.joueurcourant != null && this.joueurcourant.equals(joueur)) {
			throw new HearthstoneException("Joueur est déjà le joueur courant");
		}
		if (this.joueurcourant == null)
			this.joueurcourant = joueur;
		else
			this.joueur = this.getJoueurCourant();
			this.joueurcourant = joueur;
	}

	@Override
	/**
     * Renvoie....l'adversaire
     * @param joueur dont on veut l'adversaire
     * @return le joueur adverse
     * @throws HearthstoneException si le param est null ou si le param ne correspond Ã  aucun des 2 joueurs de la partie, etc.
     */
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
	/**
     * Ca démarre la partie. Il faut déterminer aléatoirement le joueur qui commence, etc.
     * @throws HearthstoneException si 2 joueurs ne sont pas ajoutés, etc....
     * @throws CloneNotSupportedException si problème de clonage
     */
	public void demarrerPartie() throws HearthstoneException, CloneNotSupportedException {
		if (this.joueur == null || this.joueurcourant == null)
			throw new HearthstoneException("Des joueurs manquent à l'appel !");
		
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
		
		getJoueurCourant().prendreTour();
	}

	@Override
	/**
     * Ã  votre avis ?
     * @return vrai si la partie a démarée, faux sinon
     */
	public boolean estDemarree() {
		return this.partiedemaree;
	}

	@Override
	/**
     * Le joueur passé en paramètre vient de décider de finir son tour. Du coup, le plateau
     * doit gérer le changement de joueur courant (entre autres)
     * @param joueur le joueur qui a fini son tour et qui passe la main
     * @throws HearthstoneException si le joueur qui passe son tour, n'avait pas le tour, alors
     * c'est qu'il fait n'importe quoi !
     * @throws CloneNotSupportedException 
     */
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
	/**
     * Le héros du joueur adverse du joueur passé en paramètre est mort. Donc le joueur a gagné !
     * @param joueur celui qui a gagné
     * @throws HearthstoneException au cas on souhaite...tricher !
     */
	public void gagnePartie(IJoueur joueur) throws HearthstoneException {
		if (joueur == null)
			throw new HearthstoneException ("Ce joueur n'existe pas");
		
		System.out.println(joueur.getPseudo() + " l'emporte !");
		this.partiedemaree = false;
	
	}
	/**
	 * Affiche les informations du plateau : Joueurs, PV,Main etc...
	 * @throws HearthstoneException en cas de problème
	 */
	public void afficherPlateau() throws HearthstoneException {
		System.out.println("\nJoueur : " + this.getAdversaire(this.getJoueurCourant()).getPseudo() + "\t Héros : " + Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getHeros().getNom());
		System.out.println("Points de vie : " + this.getAdversaire(this.getJoueurCourant()).getHeros().getVie() + "\t Mana : " + Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getMana());
		((Joueur) this.getAdversaire(this.getJoueurCourant())).afficherJeu();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		((Joueur) this.getJoueurCourant()).afficherJeu();
		System.out.println("Main :");
		((Joueur) this.getJoueurCourant()).afficherMain();
		System.out.println("Points de vie : " + this.getJoueurCourant().getHeros().getVie() + ", Mana : " + this.getJoueurCourant().getStockMana());
		System.out.println("Joueur : " + this.getJoueurCourant().getPseudo() + "\n");
		System.out.println("");
	}

}
