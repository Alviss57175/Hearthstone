package joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import console.*;
import java.util.Random;
import java.util.Scanner;

import capacite.*;
import carte.Carte;
import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import jeu.IPlateau;
import jeu.Plateau;

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
		this.mana = 5;
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
	
	public void afficherMain() {
		String lsmain = "";
		for (ICarte c : this.main) {
			if (c instanceof Serviteur) {
				if(((Serviteur)c).getCapacite()== null) {
					lsmain = lsmain + "\n [" + c.getNom() + ", Cout : " + c.getCout() + ", " + ((Serviteur)c).getAtk() + "/" + ((Serviteur)c).getDef() + "]";
				}
				else {
					lsmain = lsmain + "\n [" + c.getNom() + ", Cout : " + c.getCout() + ", " + ((Serviteur)c).getAtk() + "/" + ((Serviteur)c).getDef() + ", Capacité : " + ((Serviteur)c).getCapacite().getNom() + "]";
				}
			}
			else {
				lsmain = lsmain + "\n [" + c.getNom() + ", Cout : " + c.getCout() + ", Capacite : " + ((Sort)c).getCapacite() + "]";
			}
		}
		System.out.println(lsmain);
	}

	@Override
	public ArrayList<ICarte> getJeu() {
		return this.jeu;
	}
	
	public void afficherJeu() {
		String lsjeu = "";
		for (ICarte c : this.jeu) {
			if (c instanceof Serviteur) {
				if(((Serviteur)c).getCapacite()== null) {
					lsjeu = lsjeu + "\n [" + c.getNom() + ", " + ((Serviteur)c).getAtk() + "/" + ((Serviteur)c).getDef() + "]";
				}
				else {
					lsjeu = lsjeu+ "\n [" + c.getNom() + ", " + ((Serviteur)c).getAtk() + "/" + ((Serviteur)c).getDef() + ", Capacité : " + ((Serviteur)c).getCapacite().getNom() + "]";
				}
			}
			else {
				lsjeu = lsjeu + "\n [" + c.getNom() + ", Capacite : " + ((Sort)c).getCapacite() + "]";
			}
		}
		System.out.println(lsjeu);
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
	public void prendreTour() throws HearthstoneException, CloneNotSupportedException {	//Appellée au début du tour d'un joueur : Augmente la mana si elle n'est pas à son seuil, restore les stocks, et rend les monstres endormis jouables 
		System.out.println(this.getPseudo() + " commence son tour !");
		if(this.mana < MAX_MANA){
			this.mana = this.mana + 1;
			System.out.println("Sa mana augmente !");
		}
		this.stockMana = this.mana;
		for(ICarte c : this.jeu){
			if (!((Serviteur) c).isJouable()){
				((Serviteur) c).setJouable(true);
				System.out.print(c.getNom() + " de " + this.getPseudo() + " s'éveille !");
			}
			if (!(((Serviteur)c).getCapacite() == null))
				c.executerEffetDebutTour(null);
		}
		this.getHeros().setUsePouvoir(true);
		try {
			piocher();
		}
		catch(HearthstoneException e){
			System.out.println("Le deck est vide...");
			Plateau.getInstance().gagnePartie(Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()));
		}
		
		
	}

	@Override
	public void finirTour() throws HearthstoneException {
		if(!(this.equals(Plateau.getInstance().getJoueurCourant())))
			throw new HearthstoneException ("C'est le tour de " + Plateau.getInstance().getJoueurCourant() + ", vous ne pouvez pas finir son tour");
		System.out.println(this.getPseudo() + " met fin à son tour");
		for(ICarte c : this.jeu){
			if (!(((Serviteur)c).getCapacite() == null))
				c.executerEffetFinTour(null);
		}
		
		
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
	    System.out.println(this.getPseudo() + " pioche une carte !");
	    
		
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if(carte == null || !this.main.contains(carte)) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas dans votre main");
		if(this.stockMana >= carte.getCout()){	//Si le joueur à un stock de mana suffisant
			this.stockMana = this.stockMana - carte.getCout(); 	//On retire le mana necessaire a l'invocation
			this.jeu.add(carte);	//On ajoute la carte au jeu
			this.main.remove(carte);	//On retire la carte de la main
			System.out.println(this.getPseudo() + " invoque " + carte.getNom());
		}
		else{
			throw new HearthstoneException("Mana insufisant");
		}
		
	}

	@Override
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if(carte == null || !this.main.contains(carte)) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas dans votre main");
		if(this.stockMana >= carte.getCout()){	//Si le joueur à un stock de mana suffisant
			this.stockMana = this.stockMana - carte.getCout(); 	//On retire le mana necessaire a l'invocation
			this.jeu.add(carte);	//On ajoute la carte au jeu
			this.main.remove(carte);	//On retire la carte de la main
			System.out.println(this.getPseudo() + " invoque " + carte.getNom());
			if(carte instanceof Sort || ((Serviteur) carte).getCapacite() != null) {
				carte.executerEffetDebutMiseEnJeu(cible);
			}
			if(carte instanceof Sort) {
				this.perdreCarte(carte);
			}
			
		}
		else{
			throw new HearthstoneException("Mana insufisant");
		}
		
		
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		if(carte == null || this.getCarteEnJeu(carte.getNom()) == null) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas en jeu");
		if(!(carte instanceof Serviteur))
			throw new HearthstoneException("Seul un Serviteur peut attaquer");
		if(cible == null || (!(cible instanceof Joueur) && !(cible instanceof Serviteur)) )
			throw new HearthstoneException("La cible n'existe pas");
		if(!((Serviteur)carte).isJouable())
			throw new HearthstoneException(carte.getNom() + " ne peut pas attaquer");
		boolean verifprovoc = false;
		for(ICarte c : Plateau.getInstance().getAdversaire(this).getJeu())
			if(((Serviteur)c).isProvoc())
				verifprovoc = true;
		if(verifprovoc)
			System.out.println("Un ou plusieurs Serviteur adverse vous provoc");
			for(ICarte c : Plateau.getInstance().getAdversaire(this).getJeu())
				if(((Serviteur)c).isProvoc() && cible.equals(c))
					verifprovoc = false;
			if(verifprovoc)
				throw new HearthstoneException("L'attaque échoue, vous devez ciblé le/les serviteurs qui vous provoquent");
		if(cible instanceof Serviteur) {
			System.out.println(carte.getNom() + " attaque " + ((Serviteur)cible).getNom() + " de " + ((Serviteur)cible).getProprietaire().getPseudo());
			((Serviteur)cible).PerdreDef(((Serviteur)carte).getAtk());
			((Serviteur)carte).setJouable(false);
		}
		else {
			System.out.println(carte.getNom() + " attaque " + ((Joueur)cible).getPseudo());
			((Joueur)cible).getHeros().perdreVie(((Serviteur)carte).getAtk());
			((Serviteur)carte).setJouable(false);
		}
			
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException, IOException {
		if (cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		if(this.getHeros().getUsePouvoir()){
			System.out.println(this.getPseudo() + " utilise le pouvoir de " + this.getHeros().getNom() + " : " + this.getHeros().getPouvoir().getNom() + "\n" + this.getHeros().getPouvoir().getDescription());
			this.getHeros().getPouvoir().executerAction(cible);
			this.getHeros().setUsePouvoir(false);
		}
		else {
			throw new HearthstoneException("Vous avez déjà utiliser votre pouvoir pour ce tour");
		}
		
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(carte == null || this.getCarteEnJeu(carte.getNom()) == null) //Si la carte demandée n'est pas initialisée ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas en jeu");
		if(!carte.disparait())
			throw new HearthstoneException("Cette carte n'est pas encore détruite" + carte.toString());
	
		if(carte instanceof Serviteur && ((Serviteur)carte).getCapacite() != null) {
			((Serviteur) carte).getCapacite().executerEffetDisparition(this);
		}
		
		this.jeu.remove(carte);
		System.out.println(carte.getNom() + " est détruite !");
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
