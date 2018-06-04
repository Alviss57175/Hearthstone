package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
/**
 * affichage de l'utilisation de l'effet de carte
 */
public class EffetCarte extends Menu{
	/**
	 * si le joueur saisit 2 dans le menu, l'interface d'effet de carte apparait
	 * @param choix
	 * le choix du joueur
	 * @return retourne vrai si c'est la bonne interaction
	 */
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("2"))) {
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
	public boolean executerInteraction(IJoueur j) throws IOException, HearthstoneException { // Execute l'action de l'interface
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Quel serviteur va utiliser son effet ?");
		ICarte select = j.getCarteEnJeu(br.readLine());
		if (select != null && select instanceof Serviteur) {
			try {    
				j.utiliserEffet(select, j);
				return true;
			}
			catch(HearthstoneException e) {
				return false;
			}
		}
		else {
			System.out.println("Le Serviteur que vous cherchez n'existe pas");
			return false;
		}
	}
	/**
	 * renvoie la description
	 * @return retourne la description
	 */
	public String getDescription() {
		return "Activer l'effet d'une carte";
	}
}
