package menu;

import java.io.IOException;

import exception.HearthstoneException;
import joueur.IJoueur;
/**
 * affichage de l'action utiliser pouvoir
 */
public class UtiliserPouvoir extends Menu {
	/**
	 * si le joueur saisit 3 dans le menu, l'interface d'utilisation de pouvoir apparait
	 * @param choix
	 * le choix du joueur
	 */
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("3"))) {
			return false;
		}
		return true;
	}
	/**
	 * exécute l'action
	 * @param j
	 * le joueur
	 * @throws IOException en cas de problème de buffer
	 * @throws HearthstoneException en cas de problème
	 */
	public boolean executerInteraction(IJoueur j) throws HearthstoneException, IOException { // Execute l'action de l'interface
		try {
			j.utiliserPouvoir(j);
		}
		catch(HearthstoneException e) {
			return false;
		}
		return true;
	}
	/**
	 * renvoie la description
	 * @return retourne la description
	 */
	public String getDescription() {
		return "Utilise le pouvoir du héros";
	}
}
