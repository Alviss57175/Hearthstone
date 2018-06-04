package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;
/**
 * L'invocation de serviteur est une capacit� qui invoque sur le terrain un ou plusieurs serviteurs.
 */
public class InvocationServiteur extends Capacite {

	public Serviteur serviteur; //Serviteur � invoquer 
	/**
	 * Constructeur du type serviteur
	 * @param nom
	 * nom de la capacit�
	 * @param description
	 * description de la capacit� 
	 * @param serviteur 
	 * Serviteur � invoquer
	 * @throws HearthstoneException en cas de probl�me
	 */
	public InvocationServiteur(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description);
		if(serviteur == null) {
			throw new HearthstoneException("Serviteur � invoquer invalide");
		}
		this.serviteur = serviteur;
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Renvoie une erreur si elle est utilis�e car il faut utiliser executerEffetMiseEnjeu
	 * @throws HearthstoneException en cas de probl�me
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'� la mise en jeu de la carte");
		
	}

	@Override
	/**
	 * Invoque le serviteur
	 * @param cible 
	 * le joueur cible
	 * @throws HearthstoneException en cas de probl�me
	 * @throws CloneNotSupportedException en cas de probl�me de clonage
	 * @throws IOException En cas de probleme avec le BufferedReader
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas � la classe des Joueurs
			throw new HearthstoneException("Cette capacit� ne peut cibler que un Joueur");
		Serviteur servClone = (Serviteur)this.serviteur.clone();
		servClone.setProprietaire((Joueur)cible);
		((Joueur)cible).getJeu().add((ICarte) servClone);
		System.out.println(this.serviteur.getNom() + " r�pond � l'appel de " + this.getNom());
		servClone.executerEffetDebutMiseEnJeu(cible);
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
