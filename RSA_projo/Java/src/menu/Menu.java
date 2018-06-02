package menu;

import java.io.IOException;

import exception.HearthstoneException;
import joueur.*;

public abstract class Menu {
	
	private Menu suivant = null;
	
	public Menu() {
		this.suivant = null;
	}
	
	public void setSuivant(Menu suivant) {
		this.suivant = suivant;
	}
	
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		return false;
	}
	
	public boolean executerInteraction(IJoueur j) throws CloneNotSupportedException, IOException, HearthstoneException, CloneNotSupportedException { // Execute l'action de l'interface
		return false;
	}
	
	
	public void interagir(String choix ,IJoueur j) throws CloneNotSupportedException, IOException, HearthstoneException, CloneNotSupportedException { //Fais "tourner le menu"
		if(saitInteragir(choix)) {
			executerInteraction(j);
		}
		if(suivant != null) {
			suivant.interagir(choix, j);
		}
	}
	
	public String getDescription() {
		return "Menu principal";
	}
	
}