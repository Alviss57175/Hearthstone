package carte;

import java.io.IOException;

import capacite.Capacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.Joueur;
/**
*Un serviteur est une créature
*/
public class Serviteur extends Carte {

	public int atk;	//Attaque du Serviteur
	public int def;	//Defense du Serviteur (Fais office de Points de Vie)
	public Capacite capacite;	//Capacite du serviteur (Null si le Serviteur n'a pas de capacité)
	public boolean jouable;		//Determine si le Serviteur peut attaquer ce tour ou non
	public boolean provoc;		//Determine si le Serviteur possède le bonus "Provocation" (Bonus != Capacite)
	public boolean usepouvoir;  //Determine si le Serviteur peut utiliser sont pouvoir durant le tour, ou s'il l'a déjà fait
	
	/**
	 * Constructeur dans le cas du serviteur qui possède une capacité
	 * @param nom Nom de la carte
	 * @param atk Point d'attaque de la carte
	 * @param def Point de defense de la carte
	 * @param cout Cout en mana de la carte
	 * @param capacite Capacite de la carte
	 * @param proprietaire Proprietaire de la carte
	 */
	public Serviteur(String nom, int atk, int def, int cout, Capacite capacite, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.capacite = capacite;
		this.proprietaire = proprietaire;
		this.jouable = false;
		this.provoc = false;
		this.usepouvoir = false;
	}
	/**
	 * Constructeur dans le cas du serviteur qui ne possède pas de capacité
	 * @param nom Nom de la carte
	 * @param atk Point d'attaque de la carte
	 * @param def Point de defense de la carte
	 * @param cout Cout en mana de la carte
	 * @param proprietaire Proprietaire de la carte
	 */
	public Serviteur(String nom, int atk, int def, int cout, Joueur proprietaire) {
		super(nom, cout, proprietaire);
		
		this.atk = atk;
		this.def = def;
		this.jouable = false;
		this.capacite = null;
		this.provoc = false;
		this.usepouvoir = false;
	}
	/**
	*Constructeur par clonage
	*@param c Carte que l'ont veut cloner;
	*/
	public Serviteur(Serviteur c) {
		super(c.getNom(), c.getCout(), c.getProprietaire());
		
		this.atk = c.getAtk();
		this.def = c.getDef();
		this.jouable = false;
		this.provoc = false;
		this.usepouvoir = false;
	}
	
	/**
	 * Verifie si l'égalité entre un Serviteur et un objet
	 * @param anObject Objet auquel on le compare
	 * @return Retourne vrai s'ils sont egaux, sinon retourne faux
	 */
	@Override
	public boolean equals(Object anObject) { // A completer
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire()) && this.getAtk() == ((Serviteur) anObject).getAtk() && this.getDef() == ((Serviteur) anObject).getDef() && this.getCapacite().equals(((Serviteur) anObject).getCapacite()) && this.isJouable() == (((Serviteur) anObject).isJouable()) && this.isProvoc() == (((Serviteur) anObject).isProvoc()) && this.isUsePouvoir() == (((Serviteur) anObject).isUsePouvoir())) 
			return true;
		else
			return false;
		
	}

	/**
	 * Retourne les points d'Attaque de la carte
	 * @return Retourne l'attaque
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * set la valeur de l'attaque
	 * @param atk
	 * la valeur de l'attaque à appliquer
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}
	/**
	 * set la valeur de la défense
	 * @param def
	 * la valeur de la défense à appliquer
	 */
	public void setDef(int def) {
		this.def = def;
	}
	/**
	 * test si le serviteur a provocation
	 * @return si le serviteur a provocation
	 */
	public boolean isProvoc() {
		return provoc;
	}
	/**
	 * set la provocation
	 * @param provoc
	 * l'état de provocation à appliquer
	 */
	public void setProvoc(boolean provoc) {
		this.provoc = provoc;
	}
	/**
	 * Retourne si le serviteur est jouable 
	 * @return retourne la jouabilité du serviteur
	 */
	public boolean isJouable() {
		return jouable;
	}

	/**
	 * set la jouabilité
	 * @param jouable
	 * l'état de jouabilité à appliquer
	 */
	public void setJouable(boolean jouable) {
		this.jouable = jouable;
	}
	/**
	 * Determine si le Serviteur peut utiliser son pouvoir durant le tour, ou s'il l'a déjà fait
	 * @return true si il peut l'utiliser, false si il l'a utilisé
	 */
	public boolean isUsePouvoir() {
		return usepouvoir;
	}
	/**
	 * set l'utilisation du pouvoir 
	 * @param usepouvoir
	 * Établit l'utilisation du pouvoir
	 */
	public void setUsePouvoir(boolean usepouvoir) {
		this.usepouvoir = usepouvoir;
	}

	/**
	 * Retourne les points de défense de la carte
	 * @return retourne la défense
	 */
	public int getDef() {
		return def;
	}

	/**
	 * Retourne la capacité du serviteur
	 * @return retourne la capacité
	 */
	public Capacite getCapacite() {
		return capacite;
	}


	@Override
	/**
	 * Renvoie le nom de la carte
	 * @return retourne le nom
	 */
	public String getNom() {
		return nom;
	}
	@Override
	/**
	 * Renvoie le cout en mana de la carte
	 * @return retourne le cout
	 */
	public int getCout() {
		return cout;
	}
	@Override
	/**
	 * Renvoie le propriétaire le carte
	 * @return retourne le propriétaire
	 */
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	/**
	 * Inflige les dégats au serviteur, si sa défense est negative ou nulle, la carte disparait
	 * @param degats
	 * dégats infligés
	 * @throws HearthstoneException en cas de problème
	 */
	public void PerdreDef(int degats) throws HearthstoneException {
		System.out.println(degats + " point(s) de dégâts infligé(s) à " + this.getNom());
		this.def = this.def - degats;
		if(this.disparait()) {
			this.proprietaire.perdreCarte(this);
		}
	}
	
	@Override
	/**
     * Une carte peut avoir un effet au début de chaque tour où elle est en jeu
     * @param cible En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     */
	public void executerEffetDebutTour(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if(!(this.capacite == null)) {
		this.getCapacite().executerEffetDebutTour();
		}
		
	}

	@Override
	 /**
     * Une carte peut avoir un effet à  la fin d'un chaque tour où elle est en jeu
     * @param cible En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     */
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetFinTour();
		}
	}

	@Override
	  
    /**
     * Une carte peut avoir un effet au début de sa mise en jeu 
     * @param cible En general pointe sur le joueur    
     * @throws HearthstoneException En cas de probleme
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetMiseEnJeu(cible);
		}
	}

	@Override
	 /**
     * Une carte peut avoir un effet au moment de sa disparition du jeu 
     * @param cible  En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     */
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerEffetDisparition(cible);
		}
	}

	@Override
	/**
     * Une carte peut avoir une action qui se commande Ã  n'importe quel moment du tour lorsqu'elle est en jeu 
     * @param cible En general pointe sur le joueur
     * @throws HearthstoneException En cas de Probleme
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(!(this.capacite == null)) {
			this.getCapacite().executerAction(cible);
		}
	}

	@Override
	/**
     * Fonction qui teste si les conditions pour que la carte soit encore prÃ©sente au tour suivant. Si la fonction
     * renvoie vrai, il faut sÃ»rement la retirer du board...
     * @return true si la carte est foutu (un serviteur tuÃ©, un sort lancÃ©, etc.)
    */
	public boolean disparait() {
		if(this.getDef() <= 0)
			return true;
		else
			return false;
	}

	
	
	@Override
	/**
     * Fonction qui clonera les cartes
     * @throws CloneNotSupportedException Dans l'éventualité où la fonction echoue
     * @return Retourne un clone de la même carte (Avec une ID differente)
     */
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	/**
	 * Traduit la classe en une chaine de caracteres
	 * @return La chaine en question
	 */
	public String toString() {
		if( this.capacite != null && this.proprietaire != null) {
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
		}
		else {
			if (this.capacite == null && this.proprietaire == null) {	
				return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Jouable [ " + this.jouable + " ]";
			}
			else {
				if(this.capacite == null)
					return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ], Jouable [ " + this.jouable + " ]";
				else
					return "Nom Carte [ " + this.nom + " ], Cout [ " + this.getCout() + " ], Attaque [ " + this.getAtk() + " ], Defense [ " + this.getDef() + " ], Capacite [ " + this.capacite.getNom() + " ], Jouable [ " + this.jouable + " ]";
			}
		}
	}
	

}
