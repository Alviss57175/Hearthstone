package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationChien extends InvocationServiteur{

	public IJoueur joueur; //Joueur adverse : Permet de determiner le nombre de créature à invoquer en fonction de son jeu
	
	public InvocationChien(String nom, String description, Serviteur serviteur, IJoueur joueur) throws HearthstoneException {
		super(nom, description, serviteur);
		this.joueur = joueur;
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas à la classe des Joueurs
			throw new HearthstoneException("Cette capacité ne peut cibler que un Joueur");
		if (!(Plateau.getInstance().getAdversaire((Joueur)cible).equals(this.joueur))) //Si le joueur en parametre n'est pas l'adversaire
			throw new HearthstoneException("Parametre de l'adversaire erroné");
		for(int i = 1 ; i <= this.joueur.getJeu().size(); i++) {
			((Joueur)cible).getJeu().add((ICarte) this.serviteur.clone());
			}
		System.out.println(this.joueur.getJeu().size() + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}


}
