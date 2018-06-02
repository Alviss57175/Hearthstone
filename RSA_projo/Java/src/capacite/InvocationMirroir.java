package capacite;

import java.io.IOException;

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
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if(cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		if(!(cible instanceof Joueur))
			throw new HearthstoneException("La cible doit etre une joueur");
		Serviteur servClone = null;
		for (int i = 0 ; i < 2 ; i++) {
			servClone = (Serviteur)this.serviteur.clone();
			servClone.setProprietaire((Joueur)cible);
			((Joueur)cible).getJeu().add((ICarte) servClone);
			System.out.println(this.serviteur.getNom() + " répond à l'appel de " + this.getNom());
			servClone.executerEffetDebutMiseEnJeu(cible);
		}
		System.out.println("Deux " + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}
	

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		
	}
	
	

}
