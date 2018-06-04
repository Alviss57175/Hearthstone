package carte;

import java.io.IOException;

import capacite.Capacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import joueur.Joueur;
/**
 * Un sort est une capacit� qui est execut�e � la mise en jeu, la carte disparait imm�diatement
 */
public class Sort extends Carte {

	public Capacite capacite;
	
	/**
	 * Constructeur du type sort
	 * @param nom
	 * nom de la carte
	 * @param cout
	 * cout du sort
	 * @param capacite
	 * capacite du sort
	 * cout de la carte en mana
	 * @param proprietaire
	 * joueur propri�taire de la carte
	 * @throws InvalidArgumentException en cas de probl�me
	 */
	
	
	public Sort(String nom, int cout, Capacite capacite, Joueur proprietaire) throws InvalidArgumentException {
		super(nom, cout, proprietaire);
		this.capacite = capacite;
		
	}
	/**
	*Constructeur par clonage
	*@param c Carte que l'on veut cloner
	*/
	public Sort(Sort c) {
		super(c.getNom(), c.getCout(), c.getProprietaire());
		this.capacite = c.getCapacite();
	}

	@Override
	/**
	 * Renvoie le nom de la carte
	 * @return retourne le nom
	 */
	public String getNom() {
		return this.nom;
	}

	@Override
	/**
	 * Renvoie le cout en mana de la carte
	 * @return retourne le cout
	 */
	public int getCout() {
		return this.cout;
	}

	@Override
	/**
	 * Renvoie le propri�taire le carte
	 * @return retourne le propri�taire
	 */
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	/**
	 * Retourne la capacit� du serviteur
	 * @return retourne la capacit�
	 */
	public Capacite getCapacite() {
		return this.capacite;
	}
	

	@Override
	/**
     * Une carte peut avoir un effet au d�but de chaque tour o� elle est en jeu
     * @param cible En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     */
	public void executerEffetDebutTour(Object cible) throws HearthstoneException, CloneNotSupportedException {
		this.getCapacite().executerEffetDebutTour();
		
	}

	@Override
	/**
     * Une carte peut avoir un effet �  la fin d'un chaque tour o� elle est en jeu
     * @param cible En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     */
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetFinTour();
	}

	@Override
	/**
     * Une carte peut avoir un effet au d�but de sa mise en jeu 
     * @param cible En general pointe sur le joueur    
     * @throws HearthstoneException En cas de probleme
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		this.getCapacite().executerEffetMiseEnJeu(cible);
	}

	@Override
	/**
     * Une carte peut avoir un effet au moment de sa disparition du jeu 
     * @param cible  En general pointe sur le joueur 
     * @throws HearthstoneException En cas de probleme
     */
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		this.getCapacite().executerEffetDisparition(cible);
	}

	@Override
	/**
     * Une carte peut avoir une action qui se commande �  n'importe quel moment du tour lorsqu'elle est en jeu 
     * @param cible En general pointe sur le joueur
     * @throws HearthstoneException En cas de Probleme
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		this.getCapacite().executerAction(cible);
	}

	@Override
	/**
     * Fonction qui teste si les conditions pour que la carte soit encore présente au tour suivant. Si la fonction
     * renvoie vrai, il faut sûrement la retirer du board...
     * @return true si la carte est foutu (un serviteur tué, un sort lancé, etc.)
    */
	public boolean disparait() {
		return true;
	}

	@Override
	/**
	 * Permet de determiner l'�galit� entre cette carte et un autre objet
	 * @param anObject Objet auquel on compare la carte
	 * @return Renvoie vrai si les deux objets sont egaux, sinon, renvoie faux
	 */
	public boolean equals(Object anObject) { // A completer
		if (!(anObject instanceof ICarte) || anObject == null)
			return false;
		if((Carte) anObject == this)
			return true;
		if(this.getNom().equals(((Carte) anObject).getNom()) && this.getCout() == ((Carte) anObject).getCout() && this.getProprietaire().equals(((Carte) anObject).getProprietaire()) && this.getCapacite().equals(((Serviteur) anObject).getCapacite())) 
			return true;
		else
			return false;
		
	}
	@Override
	/**
     * Fonction qui clonera les cartes
     * @throws CloneNotSupportedException Dans l'�ventualit� o� la fonction echoue
     * @return Retourne un clone de la m�me carte (Avec une ID differente)
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
		if(this.proprietaire != null)
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ],  Capacite [ " + this.capacite.getNom() + " ], Proprietaire [ " + this.proprietaire.getPseudo() + " ]";
		else
			return "Nom Carte [ " + this.nom + " ], Cout [ " + this.cout + " ],  Capacite [ " + this.capacite.getNom() + " ]";
	}
	
}
