package capacite;

import carte.Serviteur;
import exception.HearthstoneException;

public class Provocation extends Capacite {

	public Provocation(String nom, String description) {
		super(nom, description);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if (cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		if (!(cible instanceof Serviteur))
			throw new HearthstoneException("Provocation ne peut cibler que un Serviteur");
		if (((Serviteur)cible).isProvoc())
			throw new HearthstoneException("La cible possede déjà le bonus Provocation");
		((Serviteur)cible).setProvoc(true);
		System.out.println(	((Serviteur)cible).getNom() + " provoque ses adversaires !");
		
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
