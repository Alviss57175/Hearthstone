package joueur;

import java.util.ArrayList;
import console.*;
import java.util.Random;

import capacite.Capacite;
import capacite.ICapacite;
import carte.Carte;
import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import jeu.IPlateau;

public class Joueur implements IJoueur {
	
	public String pseudo; //Nom du joueur
	public Heros heros; //Classe du héros
	public int mana; //Mana maximum du personnage
	public int stockMana; //Stock de mana durant le tour
	public ArrayList<ICarte> jeu = new ArrayList<ICarte>(); //Carte posée sur le plateau du joueur
	public ArrayList<ICarte> main = new ArrayList<ICarte>();
	public ArrayList<ICarte> deck = new ArrayList<ICarte>() ; //Deck du joueur

	
	
	public Joueur(String pseudo, Heros heros) /*throws InvalidArgumentException*/ {
		//			Controles
		/*if(pseudo == null || pseudo.equals(""))	
			throw new InvalidArgumentException();
		if(heros == null)
			throw new InvalidArgumentException();*/
		//				Set
		this.heros = heros;
		this.pseudo = pseudo;
		this.mana = 0;
		this.stockMana = 0;
	}

	@Override
	public Heros getHeros() {
		return this.heros;
	}

	@Override
	public String getPseudo() {
		return this.pseudo;
	}

	@Override
	public int getMana() {
		return this.mana;
	}
	


	public ArrayList<ICarte> getDeck() {
		return deck;
	}

	@Override
	public int getStockMana() {
		return this.stockMana;
	}

	@Override
	public ArrayList<ICarte> getMain() {
		return this.main;
	}

	@Override
	public ArrayList<ICarte> getJeu() {
		return this.jeu;
	}

	@Override
	public ICarte getCarteEnJeu(String nomCarte) { //Renvoie la premiere carte dans le jeu du joueur qui contient nomCarte
		for(ICarte c : this.jeu){
			if(c.getNom().contains(nomCarte)){
				return c;
			}
		}
		return null;	//Si il n'y a pas de carte : On renvoie null
	}

	@Override
	public ICarte getCarteEnMain(String nomCarteMain) {	//Même principe dans la main du joueur
		for(ICarte c : this.main){
			if(c.getNom().contains(nomCarteMain)){
				return c;
			}
		}
		return null;
	}

	@Override
	public void prendreTour() throws HearthstoneException {	//Appellée au début du tour d'un joueur : Augmente la mana si elle n'est pas à son seuil, restore les stocks, et rend les monstres endormis jouables 
		if(this.mana < MAX_MANA){
			this.mana = this.mana + 1;
		}
		this.stockMana = this.mana;
		for(ICarte c : this.jeu){
			if (!((Serviteur) c).isJouable()){
				((Serviteur) c).setJouable(true);
			}
		}
		
	}

	@Override
	public void finirTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void piocher() throws HearthstoneException {
		if(this.deck.size() == 0){	//Si le deck est vide on appelle l'exception
			throw new HearthstoneException("Le deck est vide");
		}
		int randomIndex = (int)(Math.random() * this.deck.size());
	    ICarte randomCarte = this.deck.get(randomIndex);
		this.deck.remove(randomIndex);	//On retire la carte du deck
	    this.main.add(randomCarte);	//On la rajoute sur le terrain
	    
		
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if(carte == null || !this.main.contains(carte)) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas dans votre main");
		if(this.stockMana >= carte.getCout()){	//Si le joueur à un stock de mana suffisant
			this.stockMana = this.stockMana - carte.getCout(); 	//On retire le mana necessaire a l'invocation
			this.jeu.add(carte);	//On ajoute la carte au jeu
			this.main.remove(carte);	//On retire la carte de la main
		}
		else{
			throw new HearthstoneException("Mana insufisant");
		}
		
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		
		
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		this.getHeros().getPouvoir().executerAction(cible);
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(carte == null || this.getCarteEnJeu(carte.getNom()) == null) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas en jeu");
		if(!carte.disparait())
			throw new HearthstoneException("Cette carte n'est pas encore détruite" + carte.toString());
		System.out.println(this.jeu);
		this.jeu.remove(carte);
		System.out.println(this.jeu);

		
			
	}

	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof IJoueur) || anObject == null)
			return false;
		if((Joueur) anObject == this)
			return true;
		if(this.getPseudo().equals(((Joueur) anObject).getPseudo()) && this.getHeros().equals(((Joueur) anObject).getHeros()) && this.getMana() == ((Joueur)anObject).getMana() && this.getStockMana() == ((Joueur)anObject).getStockMana() && this.getJeu().equals(((Joueur)anObject).getJeu()) && this.getDeck().equals(((Joueur)anObject).getDeck()) && this.getMain().equals(((Joueur)anObject).getMain()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Pseudo [ " + this.pseudo + " ]\n Heros [ " + this.heros.getNom() + " ]\n Mana [ " + this.mana + " ], StockMana [ " + this.stockMana + " ]\n Jeu [ " + this.jeu + " ]\n Main [ " + this.main + " ]\n Deck [ " + this.deck + " ]";
	}
	
	

}
