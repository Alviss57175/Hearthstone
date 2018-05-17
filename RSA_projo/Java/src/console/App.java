package console;

import capacite.*;
import carte.Carte;
import carte.ICarte;
import carte.Serviteur;
import joueur.*;
import exception.*;
import jeu.*;

public class App {
	public static void main(String[] args) throws HearthstoneException, CloneNotSupportedException {
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
		Joueur Deux = new Joueur("Mortimer la planteur d'olive", Jaina);
		Joueur Trois = new Joueur("Gerard L'inscruste", Jaina);
		//ICarte GrosDragon = new Serviteur("Dragon Diabetique", 5, 1, 8, Un) ;
		//Un.deck.add((Carte) GrosDragon.clone());
		Plateau.getInstance();
		Plateau.getInstance().ajouterJoueur(Un);
		Plateau.getInstance().ajouterJoueur(Deux);
		Plateau.getInstance().ajouterJoueur(Trois);
				
		
	}

	

}
