package menu;

import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;
/**
 *Affichage de l'action Fin de tour 
 */
  public class FinirTour extends Menu {
	/**
	 * si le joueur saisit 5 dans le menu, l'interface de fin de tour
	 * @param choix
	 * le choix du joueur
	 * @return vrai si c'est la bonne interaction
	 */
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("5"))) {
			return false;
		}
		return true;
	}/**
	 * exécute l'action
	 * @param j
	 * le joueur
	 * @throws CloneNotSupportedException en cas de problème de clonage
	 */
	
	public boolean executerInteraction(IJoueur j) throws CloneNotSupportedException { // Execute l'action de l'interface
		try {
			Plateau.getInstance().finTour(j);
		}
		catch(HearthstoneException e){
			System.out.println(e);
		}
		return false;
	}
	/**
	 * renvoie la description
	 * @return retourne la description
	 */
	public String getDescription() {
		return "Finir le tour";
	}
}
