package capacite;

import java.io.IOException;
import java.util.Iterator;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.Joueur;
/**
 * Une attaque totale est une attaque qui cible tous les serviteurs de l'adversaire.
 */
public class AttaqueTotale extends Capacite {

	public int degats;
	/**
	 * Constructeur du type AttaqueTotale 
	 * @param nom
	 * 	le nom de l'attaque
	 * @param description
	 * 	une description de l'attaque
	 * @param degats
	 * 	la valeur des dégats de l'attaque
	 */
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
		/**
	 * Quand on cible un ennemi, l'attaque cible tous les ennemis et inflige les dégats à tout les ennemis, si il y'en a
	 * @param cible
	 * le joueur à cibler
	 * @throws HearthstoneException En cas de problème de cible
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		if(!(cible instanceof Joueur)) {	//La cible n'est pas le joueur adverse
			throw new HearthstoneException("Vous devez cibler un joueur pour activer cette capacité");
		}
		if((Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().size() <= 0) {	//Si il n'y a aucune carte sur le jeu du joueur adverse
			throw new HearthstoneException("Le jeu adverse est vide, impossible d'utiliser cette attaque");
		}
		//On evite ConcurrentModificationException en utilisant un while au lieu de parcourir le jeu avec For
		int i = 0;
		ICarte c = (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().get(0);
		
		while (i <= (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().size()-1) {
			if(c instanceof Serviteur) {
				((Serviteur) c).PerdreDef(degats);
				if((Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().size() != 0 && (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().get(i).equals(c)) {
					i++;
				}
			}
			if(!(i > (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().size()-1)){
				c = (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu().get(i);
			}
			
		}
	}

	@Override
	/**
	 * la même chose que executerAction, mais dans le cas d'un sort
	 * @param cible
	 * le joueur à cibler
	 * @throws HearthstoneException En cas de problème de cible
	 * @throws IOException en cas de problème d'entrée sortie
	 */
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
