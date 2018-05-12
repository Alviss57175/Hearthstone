package joueur;

import java.util.ArrayList;
import java.util.Random;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import exception.InvalidArgumentException;

public class Joueur implements IJoueur {
	
	public String pseudo; //Nom du joueur
	public Heros heros; //Classe du héros
	public int mana; //Mana maximum du personnage
	public int stockMana; //Stock de mana durant le tour
	public ArrayList<ICarte> main; //Main du joueur
	public ArrayList<ICarte> jeu; //Carte posée sur le plateau du joueur
	public ArrayList<ICarte> deck; //Deck du joueur

	
	
	public Joueur(String pseudo, Heros heros, int mana, int stockMana, ArrayList<ICarte> deck) throws InvalidArgumentException {
		//			Controles
		if(pseudo == null || pseudo.equals(""))	
			throw new InvalidArgumentException();
		if(heros == null)
			throw new InvalidArgumentException();
		if(main == null || jeu == null || deck == null)
			throw new InvalidArgumentException();
		if(mana < 0 || stockMana < 0 )
			throw new InvalidArgumentException();
		//				Set
		this.heros = heros;
		this.pseudo = pseudo;
		this.mana = mana;
		this.stockMana = stockMana;
		this.main = new ArrayList<ICarte>(); 
		this.jeu = new ArrayList<ICarte>(); 
		this.deck = deck;
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
			throw new HearthstoneException();
		}
		Random rand = new Random();	//Mise en place du randomizer
	    int randomIndex = rand.nextInt(this.deck.size()-1);  //Génération d'un nombre pas plus grand que la taille du deck
	    ICarte randomCarte = this.deck.get(randomIndex);	//Selection de la carte à l'index généré
	    this.deck.remove(randomIndex);	//On retire la carte du deck
	    this.main.add(randomCarte);	//On la rajoute sur le terrain
	    
		
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if(this.stockMana >= carte.getCout()){	//Si le joueur à un stock de mana suffisant
			this.stockMana = this.stockMana - carte.getCout(); 	//On retire le mana necessaire a l'invocation
			this.jeu.add(carte);	//On ajoute la carte au jeu
			this.main.remove(this.main.indexOf(carte));	//On retire la carte de la main
		}
		else{
			throw new HearthstoneException();
		}
		
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	

}
