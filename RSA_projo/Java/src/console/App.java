package console;

import capacite.*;
import carte.Carte;
import carte.Serviteur;
import joueur.*;
import exception.*;

public class App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Console es = new Console();
		es.println("Bienvenue dans Hearthstone ! \nC'est en construction pour l'instant alors cassez vous et revenez plus tard svp ");
		//Try/Catch restent en commentaire pour l'instant, parce que l'initialisation de Jaina est pas pris en compte
		//try {
		Heros Jaina = new Heros("Jaina Portvaillant", new AttaqueCiblee("Boule de feu", "Inflige 1 point de dégats à l'adversaire ou l'un de ses serviteur", 1), 15);
		//}
		//catch(InvalidArgumentException e) {
		//	e.printStackTrace();
		//}
		Joueur Un = new Joueur("Akim le forgeron", Jaina);
		Carte GrosDragon = new Serviteur("Dragon Diabetique", 5, 15, 8, Un) ;
		Un.deck.add(GrosDragon);
		//es.println(GrosDragon);
		es.println(Un);
		
	}

	

}
