package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;
/**
 * affichage de l'action attaquer
 */
public class AttaquerCarte extends Menu{
/**
 * si le joueur saisit 4 dans le menu, l'interface d'attaque apparait
 * @param choix
 * le choix du joueur
 */
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("4"))) {
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
		System.out.println("Quel serviteur va attaquer ?");
		ICarte select = j.getCarteEnJeu(br.readLine());
		if (select != null && select instanceof Serviteur) {
			if(((Serviteur)select).isJouable()) {
				int nbchoix = -1;
				while (nbchoix < 1 || nbchoix > 2) {
					System.out.println("");
					System.out.println("Qui voulez vous attaquer avec " + select.getNom() +" ?");
					System.out.println("\t1.Héros adverse");
					System.out.println("\t2.Serviteur adverse");
					try {
						nbchoix = Integer.parseInt(br.readLine());
					}
					catch(NumberFormatException e){
						nbchoix = -1;
					}
					if (nbchoix < 1 || nbchoix > 2)
						System.out.println("Choix Invalide");
				}
				if(nbchoix == 1) {	//La cible est le joueur adverse
					try {
						j.utiliserCarte(select, Plateau.getInstance().getAdversaire(j));
						return true;
					}
					catch(HearthstoneException e) {
						return false;
					}
				}
				else {
					if (nbchoix == 2) {	//La cible est un serviteur
						System.out.println("Quel serviteur voulez vous attaquer ?");
						ICarte selectcible = (Plateau.getInstance().getAdversaire((Joueur)j)).getCarteEnJeu(br.readLine());
						if(select == null) {
							System.out.println("Le serviteur que vous voulez ciblez n'existe pas");
							return false;
						}
						else {
							try {
								j.utiliserCarte(select, selectcible);
								return true;
							}
							catch(HearthstoneException e) {
								return false;
							}
						}
					}
				}
			}
			else {
				System.out.println(((Serviteur)select).getNom() + " ne peut pas attaquer");
				return false;
			}
		}
		System.out.println("Le Serviteur que vous cherchez n'existe pas");
		return false;
	}
	/**
	 * renvoie la description
	 * @return retourne la description
	 */
	public String getDescription() {
		return "Attaquer avec un serviteur";
	}
}
