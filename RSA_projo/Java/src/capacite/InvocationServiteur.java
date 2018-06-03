package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationServiteur extends Capacite {

	public Serviteur serviteur; //Serviteur � invoquer 
	
	public InvocationServiteur(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description);
		if(serviteur == null) {
			throw new HearthstoneException("Serviteur � invoquer invalide");
		}
		this.serviteur = serviteur;
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'� la mise en jeu de la carte");
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas � la classe des Joueurs
			throw new HearthstoneException("Cette capacit� ne peut cibler que un Joueur");
		Serviteur servClone = (Serviteur)this.serviteur.clone();
		servClone.setProprietaire((Joueur)cible);
		((Joueur)cible).getJeu().add((ICarte) servClone);
		System.out.println(this.serviteur.getNom() + " r�pond � l'appel de " + this.getNom());
		servClone.executerEffetDebutMiseEnJeu(cible);
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
