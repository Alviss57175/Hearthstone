package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.Joueur;
/**
 * l'attaque du héros est une attaque ciblée qui permet d'attaquer immédiatement le héros, elle passe outre la provocation
 */
public class AttaqueHeros extends AttaqueCiblee{
	/**
	 * Constructeur du type AttaqueHeros
	 * @param nom
	 * 	le nom de l'attaque
	 * @param description
	 * 	une description de l'attaque
	 * @param degats
	 * 	la valeur des dégats de l'attaque
	 */
	public AttaqueHeros(String nom, String description, int degats) {
		super(nom, description, degats);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	/**
	 * Execute l'action
	 * @param cible 
	 *le héros à attaquer
	 * @throws  HearthstoneException En cas de problème de cible
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}

		for (ICarte c : (Plateau.getInstance().getAdversaire((Joueur)cible)).getJeu()) {
			if(((Serviteur)c).isProvoc()) {
				System.out.println("L'attaque passe outre la provocation de " + c.getNom() + " !");
			}
		}
		(Plateau.getInstance().getAdversaire((Joueur)cible)).getHeros().perdreVie(this.degats);
		
		
	}
	
	

}
