package menu;

import joueur.IJoueur;

public class EffetCarte extends Menu{
	
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("2"))) {
			return false;
		}
		return true;
	}
	
	public boolean executerInteraction(IJoueur j) { // Execute l'action de l'interface
		return false;
	}
	
	public String getDescription() {
		return "Activer l'effet d'une carte";
	}
}
