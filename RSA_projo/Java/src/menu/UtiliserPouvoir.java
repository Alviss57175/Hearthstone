package menu;

import java.io.IOException;

import exception.HearthstoneException;
import joueur.IJoueur;

public class UtiliserPouvoir extends Menu {

	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("3"))) {
			return false;
		}
		return true;
	}
	
	public boolean executerInteraction(IJoueur j) throws HearthstoneException, IOException { // Execute l'action de l'interface
		try {
			j.utiliserPouvoir(j);
		}
		catch(HearthstoneException e) {
			return false;
		}
		return true;
	}
	
	public String getDescription() {
		return "Utilise le pouvoir du héros";
	}
}
