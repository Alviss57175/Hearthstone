package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;

public class InvocationMirroir extends InvocationServiteur {


	public InvocationMirroir(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description, serviteur);
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException, CloneNotSupportedException {
		
	}
	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if(cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		if(!(cible instanceof Joueur))
			throw new HearthstoneException("La cible doit etre une joueur");
		for (int i = 0 ; i < 2 ; i++) {
			((Joueur)cible).getJeu().add((ICarte) this.serviteur.clone());
		}
		System.out.println("Deux " + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}
	

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		
	}
	
	

}
