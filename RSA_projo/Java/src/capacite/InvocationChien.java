package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationChien extends InvocationServiteur{

	
	public InvocationChien(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description, serviteur);
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas à la classe des Joueurs
			throw new HearthstoneException("Cette capacité ne peut cibler que un Joueur");
		for(int i = 1 ; i <= (Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant())).getJeu().size(); i++) {
			((Joueur)cible).getJeu().add((ICarte) this.serviteur.clone());
			}
		System.out.println(((Joueur)cible).getJeu().size() + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}


}
