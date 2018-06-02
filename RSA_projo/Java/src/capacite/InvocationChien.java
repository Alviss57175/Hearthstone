package capacite;

import java.io.IOException;

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
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas à la classe des Joueurs
			throw new HearthstoneException("Cette capacité ne peut cibler que un Joueur");
		Serviteur servClone = null;
		for(int i = 1 ; i <= (Plateau.getInstance().getAdversaire((Joueur) cible)).getJeu().size(); i++) {
			servClone = (Serviteur)this.serviteur.clone();
			servClone.setProprietaire((Joueur)cible);
			((Joueur)cible).getJeu().add((ICarte) servClone);
			System.out.println(this.serviteur.getNom() + " répond à l'appel de " + this.getNom());
			servClone.executerEffetDebutMiseEnJeu(cible);
			}
		
	}


}
