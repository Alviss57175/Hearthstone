package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.*;
import exception.HearthstoneException;
import joueur.*;

public class JouerCarte extends Menu {
	
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("1"))) {
			return false;
		}
		return true;
	}
	
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
	
	public String getDescription() {
		return "Jouer une carte";
	}
	
}
