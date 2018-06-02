package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.Joueur;

public class AttaqueTotale extends Capacite {

	public int degats;
	
	public AttaqueTotale(String nom, String description, int degats) {
		super(nom, description);
		this.degats = degats;
		
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
		if(!(cible instanceof Joueur)) {	//La cible n'est pas le joueur adverse
			throw new HearthstoneException("Vous devez cibler un joueur pour activer cette capacité");
		}
		if((Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().size() <= 0) {	//Si il n'y a aucune carte sur le jeu du joueur adverse
			throw new HearthstoneException("Le jeu adverse est vide, impossible d'utiliser cette attaque");
		}
		for(ICarte c : (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu()) {
			if(c instanceof Serviteur) {
				((Serviteur) c).PerdreDef(degats);
			}
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
