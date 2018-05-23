package joueur;

import java.util.ArrayList;
import console.*;
import java.util.Random;

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
	public Heros heros; //Classe du h�ros
	public int mana; //Mana maximum du personnage
	public int stockMana; //Stock de mana durant le tour
	public ArrayList<ICarte> jeu = new ArrayList<ICarte>(); //Carte pos�e sur le plateau du joueur
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
	public ICarte getCarteEnMain(String nomCarteMain) {	//M�me principe dans la main du joueur
		for(ICarte c : this.main){
			if(c.getNom().contains(nomCarteMain)){
				return c;
			}
		}
		return null;
	}

	@Override
	public void prendreTour() throws HearthstoneException, CloneNotSupportedException {	//Appell�e au d�but du tour d'un joueur : Augmente la mana si elle n'est pas � son seuil, restore les stocks, et rend les monstres endormis jouables 
		System.out.println(this.getPseudo() + " commence son tour !");
		if(this.mana < MAX_MANA){
			this.mana = this.mana + 1;
			System.out.println("Sa mana augmente !");
		}
		this.stockMana = this.mana;
		for(ICarte c : this.jeu){
			if (!((Serviteur) c).isJouable()){
				((Serviteur) c).setJouable(true);
				System.out.print(c.getNom() + " de " + this.getPseudo() + " s'�veille !");
			}
			if (!(((Serviteur)c).getCapacite() == null))
				c.executerEffetDebutTour(null);
		}
		piocher();
		
		
	}

	@Override
	public void finirTour() throws HearthstoneException {
		if(!(this.equals(Plateau.getInstance().getJoueurCourant())))
			throw new HearthstoneException ("C'est le tour de " + Plateau.getInstance().getJoueurCourant() + ", vous ne pouvez pas finir son tour");
		System.out.println(this.getPseudo() + " met fin � son tour");
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
		if(carte == null || !this.main.contains(carte)) //Si la carte demand�e n'est pas initialis�e ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas dans votre main");
		if(this.stockMana >= carte.getCout()){	//Si le joueur � un stock de mana suffisant
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
	public void jouerCarte(ICarte carte, Object cible) throws HearthstoneException, CloneNotSupportedException {
		if(carte == null || !this.main.contains(carte)) //Si la carte demand�e n'est pas initialis�e ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas dans votre main");
		if(this.stockMana >= carte.getCout()){	//Si le joueur � un stock de mana suffisant
			this.stockMana = this.stockMana - carte.getCout(); 	//On retire le mana necessaire a l'invocation
			this.jeu.add(carte);	//On ajoute la carte au jeu
			this.main.remove(carte);	//On retire la carte de la main
			System.out.println(this.getPseudo() + " invoque " + carte.getNom());
			if(((Serviteur) carte).getCapacite() != null || carte instanceof Sort)
				carte.executerEffetDebutMiseEnJeu(cible);
		}
		else{
			throw new HearthstoneException("Mana insufisant");
		}
		
		
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		if(carte == null || this.getCarteEnJeu(carte.getNom()) == null) //Si la carte demand�e n'est pas initialis�e ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas en jeu");
		if(!(carte instanceof Serviteur))
			throw new HearthstoneException("Seul un Serviteur peut attaquer");
		if(!(((Serviteur)carte).jouable))
			throw new HearthstoneException("Cette carte ne peut pas attaquer, ou a d�j� attaqu�");
		if(cible == null || (!(cible instanceof Joueur) && !(cible instanceof Serviteur)) )
			throw new HearthstoneException("La cible n'existe pas");
		if(cible instanceof Serviteur && Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getCarteEnJeu(((Carte) cible).getNom()) == null )
			throw new HearthstoneException("La cible n'est pas pr�sente sur le jeu de l'adversaire");
		if(cible instanceof Joueur && cible != Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()))
			throw new HearthstoneException("La cible n'est pas le bon joueur");
		boolean verifprovoc = false;
		for(ICarte c : Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getJeu())
			if(((Serviteur)c).isProvoc())
				verifprovoc = true;
		if(verifprovoc)
			System.out.println("Un ou plusieurs Serviteur adverse vous provoc");
			for(ICarte c : Plateau.getInstance().getAdversaire(Plateau.getInstance().getJoueurCourant()).getJeu())
				if(((Serviteur)c).isProvoc() && cible.equals(c))
					verifprovoc = false;
			if(verifprovoc)
				throw new HearthstoneException("L'attaque �choue, vous devez cibl� le/les serviteurs qui vous provoquent");
		if(cible instanceof Serviteur) {
			System.out.println(carte.getNom() + " attaque " + ((Serviteur)cible).getNom() + " de " + ((Serviteur)cible).getProprietaire());
			((Serviteur)cible).PerdreDef(((Serviteur)carte).getAtk());
		}
		else {
			System.out.println(carte.getNom() + " attaque " + ((Joueur)cible).getPseudo());
			((Joueur)cible).getHeros().perdreVie(((Serviteur)carte).getAtk());
		}
			
	}

	@Override
	public void utiliserPouvoir(Object cible) throws HearthstoneException {
		System.out.println(this.getPseudo() + " utilise le pouvoir de " + this.getHeros().getNom() + " : " + this.getHeros().getPouvoir().getNom() + "\n" + this.getHeros().getPouvoir().getDescription());
		this.getHeros().getPouvoir().executerAction(cible);
	}

	@Override
	public void perdreCarte(ICarte carte) throws HearthstoneException {
		if(carte == null || this.getCarteEnJeu(carte.getNom()) == null) //Si la carte demand�e n'est pas initialis�e ou ne fais pas partie des cartes en mains
			throw new HearthstoneException("Cette carte n'est pas en jeu");
		if(!carte.disparait())
			throw new HearthstoneException("Cette carte n'est pas encore d�truite" + carte.toString());
		if(((Serviteur) carte).getCapacite() instanceof EffetPermanent) {
			((Serviteur) carte).getCapacite().executerEffetDisparition(this);
		}
		else {
			((Serviteur) carte).getCapacite().executerEffetDisparition(null);
		}
		this.jeu.remove(carte);
		System.out.println(carte.getNom() + " est d�truite !");
		

		
			
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
