package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.*;
import exception.HearthstoneException;
import joueur.*;
/**
 * affichage de l'action jouer carte
 *
 */
public class JouerCarte extends Menu {
	/**
	 * si le joueur saisit 1 dans le menu, l'interface de jouer la carte apparait
	 * @param choix
	 * le choix du joueur
	 */	
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("1"))) {
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
	public boolean executerInteraction(IJoueur j) throws IOException, HearthstoneException, CloneNotSupportedException { // Execute l'action de l'interface
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Quelle carte voulez vous invoquer ?");
		ICarte select = j.getCarteEnMain(br.readLine());
		if (select != null) {
			if( select instanceof Serviteur && ((Serviteur)select).getCapacite() == null) {
				try {
				j.jouerCarte(select);
				return true;
				}
				catch(HearthstoneException e) {
				}
				return false;
			}
			try {
				j.jouerCarte(select, j);
				return true;
				}
				catch(HearthstoneException e) {
				}
				return false;
		}
		System.out.println("Carte introuvable");
		return false;
	}
	/**
	 * renvoie la description
	 * @return retourne la description
	 */
	public String getDescription() {
		return "Jouer une carte";
	}
	
}
