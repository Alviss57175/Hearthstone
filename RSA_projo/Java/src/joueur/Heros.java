package joueur;

import capacite.Capacite;
import capacite.ICapacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import jeu.Plateau;
/**
 * le h�ros est le personnage qu'on incarne
 *
 */
public class Heros implements Cloneable{

	public String nom;
	public int vie;
	public Capacite pouvoir;
	public boolean usepouvoir; //Permet de determiner si le h�ros � d�j� utiliser son pouvoir ce tour-ci
	public IJoueur proprietaire; //Permet de determiner le vainqueur si les pdv du h�ros tombent � 0
/**
 * Constructeur du type Heros
 * @param
 * nom du h�ros
 * @param pouvoir
 * pouvoir du h�ros
 * @param vie
 * vie du h�ros
 * @throws InvalidArgumentException si probl�me d'arguments
 */
  	public Heros(String nom, Capacite pouvoir, int vie) throws InvalidArgumentException {
 
		if(nom == null || nom.equals(""))
			throw new InvalidArgumentException();
		if(pouvoir == null)
			throw new InvalidArgumentException();
		setNom(nom);
		setPouvoir(pouvoir);
		setVie(vie);
		this.proprietaire = null;
	}
  	/**
	 * Renvoie le nom de la carte
	 * @return retourne le nom 
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * set le nom du h�ros 
	 * @param nom 
	 * Applique le nouveau nom
	 */
	private void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Renvoie les pv 
	 * @return retourne les pv
	 */
	public int getVie() {
		return vie;
	}
	/**
	 * set les pv 
	 * @param vie
	 * applique les nouveaux points de vie 
	 */
	private void setVie(int vie) {
		this.vie = vie;
	}
	/**
	 * renvoie le pouvoir du h�ros
	 * @return retourne le pouvoir 
	 */
	public ICapacite getPouvoir() {
		return pouvoir;
	}
	/**
	 * set le pouvoir
	 * @param pouvoir
	 * applique le nouveau pouvoir
	 */
	private void setPouvoir(Capacite pouvoir) {
		this.pouvoir = pouvoir;
	}
	/**
	 * Renvoie le propri�taire le carte
	 * @return retourne le propri�taire
	 */
	public IJoueur getProprietaire() {
		return proprietaire;
	}
	/**
	 * set le propri�taire
	 * @param proprietaire
	 * applique le nouveau propri�taire
	 */
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	/**
	 * Determine si le Serviteur peut utiliser son pouvoir durant le tour, ou s'il l'a d�j� fait
	 * @return true si il peut l'utiliser, false si il l'a utilis�
	 */
	public boolean getUsePouvoir() {
		return usepouvoir;
	}
	/**
	 * set l'utilisation du pouvoir 
	 * @param b
	 * �tablit l'utilisation du pouvoir
	 */
	public void setUsePouvoir(boolean b) {
		this.usepouvoir = b;
	}
	/**
	 * Inflige les d�gats au h�ros, si sa d�fense est negative ou nulle, la partie est termin�e
	 * @param degats
	 * d�gats inflig�s
	 * @throws HearthstoneException en cas de probl�me
	 */
	public void perdreVie(int degats) throws HearthstoneException {
		System.out.println(degats + " point(s) de d�g�ts inflig�(s) au h�ros !");
		this.vie =this.vie - degats;
		if (this.vie <= 0) {
			System.out.println(this.getProprietaire().getPseudo() + " n'a plus de point de vie...");
			Plateau.getInstance().gagnePartie((Plateau.getInstance().getAdversaire(this.proprietaire)));
		}
	}
	
	@Override
	/**
	 * Permet de determiner l'�galit� entre ce h�ros et un autre objet
	 * @param anObject Objet auquel on compare le h�ros
	 * @return Renvoie vrai si les deux objets sont egaux, sinon, renvoie faux
	 */
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Heros) || anObject == null)
			return false;
		if((Heros) anObject == this)
			return true;
		if(this.getNom().equals(((Heros) anObject).getNom()) && this.getPouvoir().equals(((Heros) anObject).getPouvoir()) && this.getVie() == ((Heros)anObject).getVie())
			return true;
		else
			return false;
	}
	@Override
	/**
	 * Traduit la classe en une chaine de caracteres
	 * @return La chaine en question
	 */
	public String toString() {
		
		return "Nom[ "+this.getNom()+" ], Vie[ "+this.getVie()+" ], Pouvoir[ "+this.getPouvoir().getNom()+" ]";
	
	}
	
	@Override
	/**
     * Fonction qui clonera les cartes
     * @throws CloneNotSupportedException Dans l'�ventualit� o� la fonction echoue
     * @return Retourne un clone de la m�me carte (Avec une ID differente)
     */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	
	
	
}
