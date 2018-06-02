package capacite;

import java.io.IOException;

import carte.Sort;
import exception.HearthstoneException;
import joueur.Joueur;

public class Pioche extends Capacite {

	public int nbCarte; //Nombre de carte à piocher
	
	public Pioche(String nom, String description, int nbCarte) {
		super(nom, description);
		this.nbCarte = nbCarte;
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
		if(cible == null) {
			throw new HearthstoneException("La cible n'existe pas");
		}
		if(!(cible instanceof Joueur)) {
			throw new HearthstoneException("Cette capacité doit cibler le joueur qui doit piocher");
		}
		for(int i = 0; i < this.nbCarte ; i++) {
			((Joueur)cible).piocher();
		}
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		if (((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1) instanceof Sort) {
			executerAction(cible);
		}
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
