package joueur;

import capacite.Capacite;
import capacite.ICapacite;
import exception.HearthstoneException;
import exception.InvalidArgumentException;
import jeu.Plateau;
/**
 * le héros est le personnage qu'on incarne
 *
 */
public class Heros implements Cloneable{

	public String nom;
	public int vie;
	public Capacite pouvoir;
	public boolean usepouvoir; //Permet de determiner si le héros à déjà utiliser son pouvoir ce tour-ci
	public IJoueur proprietaire; //Permet de determiner le vainqueur si les pdv du héros tombent à 0
/**
 * Constructeur du type Heros
 * @param
 * nom du héros
 * @param pouvoir
 * pouvoir du héros
 * @param vie
 * vie du héros
 * @throws InvalidArgumentException si problème d'arguments
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
	 * set le nom du héros 
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
	 * renvoie le pouvoir du héros
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
	 * Renvoie le propriétaire le carte
	 * @return retourne le propriétaire
	 */
	public IJoueur getProprietaire() {
		return proprietaire;
	}
	/**
	 * set le propriétaire
	 * @param proprietaire
	 * applique le nouveau propriétaire
	 */
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	/**
	 * Determine si le Serviteur peut utiliser son pouvoir durant le tour, ou s'il l'a déjà fait
	 * @return true si il peut l'utiliser, false si il l'a utilisé
	 */
	public boolean getUsePouvoir() {
		return usepouvoir;
	}
	/**
	 * set l'utilisation du pouvoir 
	 * @param b
	 * Établit l'utilisation du pouvoir
	 */
	public void setUsePouvoir(boolean b) {
		this.usepouvoir = b;
	}
	/**
	 * Inflige les dégats au héros, si sa défense est negative ou nulle, la partie est terminée
	 * @param degats
	 * dégats infligés
	 * @throws HearthstoneException en cas de problème
	 */
	public void perdreVie(int degats) throws HearthstoneException {
		System.out.println(degats + " point(s) de dégâts infligé(s) au héros !");
		this.vie =this.vie - degats;
		if (this.vie <= 0) {
			System.out.println(this.getProprietaire().getPseudo() + " n'a plus de point de vie...");
			Plateau.getInstance().gagnePartie((Plateau.getInstance().getAdversaire(this.proprietaire)));
		}
	}
	
	@Override
	/**
	 * Permet de determiner l'égalité entre ce héros et un autre objet
	 * @param anObject Objet auquel on compare le héros
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
     * @throws CloneNotSupportedException Dans l'éventualité où la fonction echoue
     * @return Retourne un clone de la même carte (Avec une ID differente)
     */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	
	
	
}
