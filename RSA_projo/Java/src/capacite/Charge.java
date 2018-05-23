package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;

public class Charge extends Capacite{
	

	
	public Charge(String nom, String description) {	//Constructeur si la capacité Charge vise un Serviteur allié
		super(nom, description);
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
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
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof Serviteur))	//Si la cible n'appartient pas à la classe des Serviteurs
			throw new HearthstoneException("Cette capacité ne peut cibler que des serviteurs");
		if(((Serviteur)cible).isJouable())	//Si la cible est déjà jouable
			throw new HearthstoneException("La carte que vous ciblez est déjà jouable");
		((Serviteur)cible).setJouable(true);
		System.out.println(((Serviteur)cible).getNom() + " s'éveille plus tôt que prévu !");
			
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
