package menu;

import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;

public class FinirTour extends Menu {

	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("5"))) {
			return false;
		}
		return true;
	}
	
	public boolean executerInteraction(IJoueur j) throws CloneNotSupportedException { // Execute l'action de l'interface
		try {
			Plateau.getInstance().finTour(j);
		}
		catch(HearthstoneException e){
			System.out.println(e);
		}
		return false;
	}
	
	public String getDescription() {
		return "Finir le tour";
	}
}
