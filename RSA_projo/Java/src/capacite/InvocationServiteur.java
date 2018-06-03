package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationServiteur extends Capacite {

	public Serviteur serviteur; //Serviteur à invoquer 
	
	public InvocationServiteur(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description);
		if(serviteur == null) {
			throw new HearthstoneException("Serviteur à invoquer invalide");
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
		throw new HearthstoneException("Cet effet ne fonctionne qu'à la mise en jeu de la carte");
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas à la classe des Joueurs
			throw new HearthstoneException("Cette capacité ne peut cibler que un Joueur");
		Serviteur servClone = (Serviteur)this.serviteur.clone();
		servClone.setProprietaire((Joueur)cible);
		((Joueur)cible).getJeu().add((ICarte) servClone);
		System.out.println(this.serviteur.getNom() + " répond à l'appel de " + this.getNom());
		servClone.executerEffetDebutMiseEnJeu(cible);
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
