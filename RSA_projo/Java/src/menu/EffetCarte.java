package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;

public class EffetCarte extends Menu{
	
	public boolean saitInteragir(String choix) { //Parcours les interfaces et determine laquelle utiliser
		if(!(choix.equals("2"))) {
			return false;
		}
		return true;
	}
	
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
	
	public String getDescription() {
		return "Activer l'effet d'une carte";
	}
}
