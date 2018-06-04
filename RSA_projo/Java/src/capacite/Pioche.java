package capacite;

import java.io.IOException;

import carte.Sort;
import exception.HearthstoneException;
import joueur.Joueur;
/**
 * Pioche est une capacit� qui fait...piocher, merci captain obvious.
 */
public class Pioche extends Capacite {

	public int nbCarte; //Nombre de carte � piocher
	/**
	 * Constructeur du type Pioche
	 * @param nom
	 * nom de la capacit�
	 * @param description
	 * description de la capacit�
	 * @param nbCarte
	 * le nombre de cartes
	 */
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
	/**
	 * Execute la pioche
	 * @param cible 
	 * le joueur qui doit piocher
	 * @throws HearthstoneException en cas de probl�me
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null) {
			throw new HearthstoneException("La cible n'existe pas");
		}
		if(!(cible instanceof Joueur)) {
			throw new HearthstoneException("Cette capacit� doit cibler le joueur qui doit piocher");
		}
		for(int i = 0; i < this.nbCarte ; i++) {
			((Joueur)cible).piocher();
		}
	}

	@Override
	/**
	 *  Si la carte est un sort, on ex�cute le sort � la mise en jeu.
	 * @param Cible
     * le joueur qui doit piocher
     * @throws HearthstoneException En cas de probl�me...
     * @throws IOException en cas de probl�me avec le buffer
     */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouv�e
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
