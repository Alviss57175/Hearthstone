package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationMirroir extends InvocationServiteur {
	
	public IJoueur joueur; //Joueur auquel appartient le jeu où seront invoquer les serviteur

	public InvocationMirroir(String nom, String description, Serviteur serviteur, IJoueur joueur) throws HearthstoneException {
		super(nom, description, serviteur);
		if(this.joueur == null) {
			throw new HearthstoneException("Joueur en parametre invalide");
		}
		this.joueur = joueur;
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException, CloneNotSupportedException {
		for (int i = 0 ; i < 2 ; i++) {
			(this.joueur).getJeu().add((ICarte) this.serviteur.clone());
		}
		System.out.println("Deux " + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		
	}
	
	

}
