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
 * Marque du chasseur est une capacité qui réduit à 1 la défense d'un serviteur ennemi, peu importe sa défense initiale.
 */
public class MarqueChasseur extends Capacite {
/**
 * Constructeur du type MarqueChasseur
 * @param nom
 * nom de la capacité
 * @param description
 * description de la capacité
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
	 * le serviteur à qui on réduit les pv à 1.
	 * @throws HearthstoneException en cas de problème
	 * @throws IOException en cas de problème avec le buffer
	 */
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouvée
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
			System.out.println(((Serviteur)cible).getNom() + " est frappé par " + this.getNom() + " !\nIl ne lui reste plus qu'1 point de defense !");
		}
		
	}
		
	

	@Override
	/**
	 * Si la carte est un sort, on exécute le sort à la mise en jeu.
	 * @param Cible
     * le serviteur à qui on réduit les pv à 1.
     * @throws HearthstoneException En cas de problème...
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
