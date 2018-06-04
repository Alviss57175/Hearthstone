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
 * Marque du chasseur est une capacit� qui r�duit � 1 la d�fense d'un serviteur ennemi, peu importe sa d�fense initiale.
 */
public class MarqueChasseur extends Capacite {
/**
 * Constructeur du type MarqueChasseur
 * @param nom
 * nom de la capacit�
 * @param description
 * description de la capacit�
 */
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
	/**
	 * Execute la marque du chasseur
	 * @param cible 
	 * le serviteur � qui on r�duit les pv � 1.
	 * @throws HearthstoneException en cas de probl�me
	 * @throws IOException en cas de probl�me avec le buffer
	 */
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouv�e
			throw new HearthstoneException("La cible n'existe pas");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Quel serviteur voulez vous marquer ?");
		ICarte select = (Plateau.getInstance().getAdversaire((Joueur)cible)).getCarteEnJeu(br.readLine());
		if(select == null) {
			System.out.println("Le serviteur que vous voulez ciblez n'existe pas");
		}
		else {
			((Serviteur)cible).setDef(1);
			System.out.println(((Serviteur)cible).getNom() + " est frapp� par " + this.getNom() + " !\nIl ne lui reste plus qu'1 point de defense !");
		}
		
	}
		
	

	@Override
	/**
	 * Si la carte est un sort, on ex�cute le sort � la mise en jeu.
	 * @param Cible
     * le serviteur � qui on r�duit les pv � 1.
     * @throws HearthstoneException En cas de probl�me...
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
