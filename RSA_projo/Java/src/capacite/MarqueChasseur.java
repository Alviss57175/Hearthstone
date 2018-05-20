package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.Joueur;

public class MarqueChasseur extends Capacite {

	public MarqueChasseur(String nom, String description) {
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
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}		
		if (!(cible instanceof Serviteur)) {	//La cible est un serviteur
			throw new HearthstoneException("Marque Du Chasseur ne peut viser que les serviteurs");
		}
		((Serviteur)cible).setDef(1);
		System.out.println(((Serviteur)cible).getNom() + " est frappé par " + this.getNom() + " !\nIl ne lui reste plus qu'1 point de defense !");
	}
		
	

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
