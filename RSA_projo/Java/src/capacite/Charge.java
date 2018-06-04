package capacite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.Joueur;
/**
 * Charge est une capacité qui permet au serviteur d'attaquer immédiatement après avoir été invoqué.
 */
public class Charge extends Capacite{
	/**
	 * Constructeur du type charge
	 * @param nom
	 * 	le nom de la capacité
	 * @param description
	 * 	une description de la capacité
	 */

	
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
	/**
	 * Renvoie une erreur si elle est utilisée car il faut utiliser executerEffetMiseEnjeu
	 * @throws HearthstoneException en cas de problème
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'à la mise en jeu de la carte");
		
	}

	@Override
	/**
	 * execute la capacité charge
	 * @param Cible
     * Serviteur à rendre jouable immédiatement (si il ne l'est pas déjà)
     * @throws HearthstoneException En cas de problème...
     * @throws IOException en cas de problème avec le buffer
     */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		//Si le Pouvoir est activé par un sort, alors on selectionne le serviteur qui en beneficie
		if (((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1) instanceof Sort) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Quelle carte voulez eveiller ?");
			ICarte select = ((Joueur)cible).getCarteEnJeu(br.readLine());
			if(select != null && select instanceof Serviteur) {
				((Serviteur)select).setJouable(true);
				System.out.println(select.getNom() + " s'eveille");
			}
			else {
				System.out.println("Carte introuvable : Sort détruit (Désolé...)");
			}
		}
		else {
			//Sinon c'est la carte invoqué qui en bénéficie (Donc la derniere carte dans la liste des cartes jouées)
			((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).setJouable(true);
			System.out.println(((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).getNom() + " s'éveille");
		}
		
			
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
