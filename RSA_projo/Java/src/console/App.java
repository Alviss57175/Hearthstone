package console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import capacite.*;
import carte.*;
import joueur.*;
import exception.*;
import jeu.*;
import menu.*;

public class App {
	public static void main(String[] args) throws HearthstoneException, CloneNotSupportedException, InvalidArgumentException, IOException {
		// TODO Auto-generated method stub
		Console es = new Console();
		
		//Création des héros
	
		Heros Jaina = new Heros("Jaina Portvaillant", new AttaqueCiblee("Boule de feu", "Inflige 1 point de dégats à l'adversaire ou l'un de ses serviteur", 1), 15);
	
		Heros Rexxar = new Heros("Rexxar", new AttaqueHeros("Tir assuré", "Inflige 2 point de dégats au héro adverse", 2), 15);
		
		es.println("Génération des héros réussites");
		
		//Generation Menu
		
		
		Menu jouercarte = new JouerCarte();
		Menu effetcarte = new EffetCarte();
		Menu utiliserpouvoir = new UtiliserPouvoir();
		Menu attaquercarte = new AttaquerCarte();
		Menu finirtour = new FinirTour();
		
		
		jouercarte.setSuivant(effetcarte);
		effetcarte.setSuivant(utiliserpouvoir);
		utiliserpouvoir.setSuivant(attaquercarte);
		attaquercarte.setSuivant(finirtour);
		finirtour.setSuivant(null);
		
		//Création des cartes
		ArrayList<ICarte> neutre = new ArrayList<ICarte>();
		neutre.add(new Serviteur("Chasse-marée Murloc", 2, 1, 2, new InvocationServiteur("Cri de Guerre", "Invocation d'un Eclaireur Murloc", new Serviteur("Eclaireur Murloc", 1, 1, 0, null, null)), null));
		neutre.add(new Sort("Charge", 1, new Charge("Charge", "Reveil un serviteur endormi"), null));
		neutre.add(new Sort("Attaque Mentale", 2, new AttaqueHeros("Attaque Mentale", "Inflige 5 points de dégâts au héros adverse", 5), null));
		neutre.add(new Serviteur("Champion de Hurlevent", 6, 6, 7, new EffetPermanent("Bonus de Hurlevent", "Octroie au serviteurs alliés un bonus +1/+1", 1, 1), null));
		neutre.add(new Serviteur("Chef de raid", 2, 2, 3, new EffetPermanent("Bonus du Chef de raid", "Octroie au serviteurs alliés un bonus +1/0", 1, 0), null));
		neutre.add(new Serviteur("Garde de Baie-du-butin", 5, 4, 5, new Provocation("Provocation", "L'adversaire est obligé de viser ce Serviteur"), null));
		neutre.add(new Serviteur("La missilière téméraire", 5, 2, 6, new Charge("Charge", "Reveil un serviteur endormi"), null));
		neutre.add(new Serviteur("L'ogre-magi", 4, 4, 4, new Provocation("Provocation", "L'adversaire est obligé de viser ce Serviteur"), null));
		neutre.add(new Serviteur("Archimage", 4, 7, 6, new Provocation("Provocation", "L'adversaire est obligé de viser ce Serviteur"), null));
		neutre.add(new Serviteur("Gnôme Lepreux", 1, 1, 1, new AttaqueHeros("Attaque du lépreux", "Catapulte son bras en faisant un bras d'honneur et inflige 2 points de dégâts au héros adverse", 2), null));
		neutre.add(new Serviteur("Golem des moissons", 2, 3, 3, new InvocationServiteur("Golémisation", "Invocation d'un Golem Endomagé", new Serviteur("Golem endomagé", 2, 1, 0, null, null)), null));
		
		ArrayList<ICarte> exclujaina = new ArrayList<ICarte>();
		exclujaina.add(new Sort("Choc de Flamme", 7 ,new AttaqueTotale("Choc de Flame", "Inflige 4 points de dégâts à tout les serviteurs adverses", 4), null));
		exclujaina.add(new Sort("Eclair de givre", 2, new AttaqueCiblee("Eclaire de givre", "Inflige 3 points de dégâts à la cible", 4), null));
		exclujaina.add(new Sort("Intelligence des arcanes", 2, new Pioche("Intelligence des arcanes", "Permet au joueur qui l'a jouer de piocher 2 cartes", 2), null));
		exclujaina.add(new Sort("Image mirroir", 1, new InvocationMirroir("Image mirroir", "Invoque deux serviteurs de Jaina ayant Provocation", new Serviteur("Serviteur Jaina", 0, 2 , 0, new Provocation("Provoc", "Provoc les serviteurs adverses"),null)), null));
		exclujaina.add(new Sort("Explosion pyrotechnique", 10, new AttaqueCiblee("Explosion pyrotechnique", "Inflige 10 points de dégâts à la cible", 10), null));
		
		ArrayList<ICarte> exclurexxar = new ArrayList<ICarte>();
		exclurexxar.add(new Serviteur("Busard Affamé", 3, 2, 5, new Pioche("Pioche du piaf", "Piocher 1 carte", 1), null));
		exclurexxar.add(new Sort("Marque du Chasseur", 1, new MarqueChasseur("Marque du Chasseur", "Réduit à 1 les points de vie d'un serviteur"), null));
		exclurexxar.add(new Sort("Tir des Arcanes", 1, new AttaqueHeros("Tir des Arcanes", "Inflige 2 points de dégâts au héros adverse", 2), null));
		exclurexxar.add(new Sort("Lachez les chiens", 3, new InvocationChien("Lachez les chiens", "Who let the dogs out ? Invoque autant de chiens que de Serviteur sur le terrain adverse", new Serviteur("Rexx Chien Flic", 1, 1, 0, new Charge("Charge", "Reveil un serviteur endormi"), null)), null));
		exclurexxar.add(new Sort("Ordre de tuer", 3, new AttaqueCiblee("Ordre de tuer", "Inflige 3 points de dégâts à la cible", 3), null));
	
		es.println("Génération des cartes réussites");
		
		//Creation du plateau
		Plateau.getInstance();
		
		es.println("Génération du plateau");
		
		es.println("Bienvenue sur Hearthstone");
		
		//Creation Joueur 1
		String pseudo = null;
		while (pseudo == null) {
			es.println("Joueur 1, entrez votre nom");
			pseudo = es.readLine();
			if (pseudo == null)
				es.println("Pseudo Invalide");
		}
			
		
		int choixheros = 0;
		while (choixheros < 1 || choixheros > 2) {
			es.println("Selectionnez votre héros (Tapez le numero correspondant)");
			es.println("\t1.Jaina Portvaillant");
			es.println("\t2.Rexxar, Champion de la horde");
			try {
				choixheros = Integer.parseInt(es.readLine());
			}
			catch(NumberFormatException e){
				choixheros = -1;
			}
			if (choixheros < 1 || choixheros > 2)
				es.println("Choix Invalide");
			
		}
			
		Joueur Un = null;
			
		switch (choixheros) //Selon la réponse on initialise le héros du joueur, et on ajoute les cartes exclusives au héros dans son Deck 
		{
			case 1 : Un = new Joueur(pseudo, Jaina);
				Un.getDeck().addAll((Collection<? extends ICarte>) exclujaina.clone());	//Note : On prend soin de cloner les cartes des Listes et pas de les ajouter directement
				break;
				
			case 2 : Un = new Joueur(pseudo, Rexxar);
			Un.getDeck().addAll((Collection<? extends ICarte>) exclurexxar.clone());
			break;
			
			default : throw new InvalidArgumentException();
		}
	
		Un.getDeck().addAll((Collection<? extends ICarte>) neutre.clone());//On ajoute ensuite les cartes neutre dans le deck
		for(ICarte c : Un.getDeck())	//Et on ajoute le proprietaire des cartes à chaque carte du deck
			((Carte)c).setProprietaire(Un);
		
		Plateau.getInstance().ajouterJoueur(Un);
		es.println(Un.getPseudo() + " est ajouté à la partie"); 
		
		//Creation Joueur 2
		pseudo = null;
		while (pseudo == null) {
			es.println("Joueur 2, entrez votre nom");
			pseudo = es.readLine();
			if (pseudo == null)
				es.println("Pseudo Invalide");
		}
			
		
		choixheros = 0;
		while (choixheros < 1 || choixheros > 2) {
			es.println("Selectionnez votre héros (Tapez le numero correspondant)");
			es.println("\t1.Jaina Portvaillant");
			es.println("\t2.Rexxar, Champion de la horde");
			try {
				choixheros = Integer.parseInt(es.readLine());
			}
			catch(NumberFormatException e){
				choixheros = -1;
			}
			if (choixheros < 1 || choixheros > 2)
				es.println("Choix Invalide");
			
		}
			
		Joueur Deux = null;
			
		switch (choixheros) //Selon la réponse on initialise le héros du joueur, et on ajoute les cartes exclusives au héros dans son Deck 
		{
			case 1 : Deux = new Joueur(pseudo, Jaina);
				Deux.getDeck().addAll((Collection<? extends ICarte>) exclujaina.clone());	//Note : On prend soin de cloner les cartes des Listes et pas de les ajouter directement
				break;
				
			case 2 : Deux = new Joueur(pseudo, Rexxar);
			Deux.getDeck().addAll((Collection<? extends ICarte>) exclurexxar.clone());
			break;
			
			default : throw new InvalidArgumentException();
		}
	
		Deux.getDeck().addAll((Collection<? extends ICarte>) neutre.clone());//On ajoute ensuite les cartes neutre dans le deck
		for(ICarte c : Deux.getDeck())	//Et on ajoute le proprietaire des cartes à chaque carte du deck
			((Carte)c).setProprietaire(Deux);
		
		Plateau.getInstance().ajouterJoueur(Deux);
		es.println(Deux.getPseudo() + " est ajouté à la partie"); 
		
		
		
		Plateau.getInstance().getJoueurCourant().piocher();
		Plateau.getInstance().getJoueurCourant().piocher();
		Plateau.getInstance().getJoueurCourant().piocher();
		
		Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).piocher();
		Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).piocher();
		Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).piocher();
		
		Plateau.getInstance().demarrerPartie();
		
		int nbchoix = 0;	//Variable ou sera stocker le choix d'action du joueur
		
		while(Plateau.getInstance().estDemarree()){
			Plateau.getInstance().afficherPlateau();
			while (nbchoix < 1 || nbchoix > 5) {
				es.println("");
				es.println("Que voulez vous faire ?");
				es.println("\t1.Poser une carte");
				es.println("\t2.Activer effet carte");
				es.println("\t3.Utiliser pouvoir héros");
				es.println("\t4.Attaquer carte");
				es.println("\t5.Finir tour");
				try {
					nbchoix = Integer.parseInt(es.readLine());
				}
				catch(NumberFormatException e){
					nbchoix = -1;
				}
				if (nbchoix < 1 || nbchoix > 5)
					es.println("Choix Invalide");
			}
			
			jouercarte.interagir(String.valueOf(nbchoix), Plateau.getInstance().getJoueurCourant());
			
			nbchoix = 0;
			
			Plateau.getInstance().gagnePartie(Plateau.getInstance().getJoueurCourant());
			
			
		}
		
		
	}

	

}
