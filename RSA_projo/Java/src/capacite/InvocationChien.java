package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.IJoueur;
import joueur.Joueur;
/**
 * Invocation des chiens et une capacit� propre � Rexxar, elle invoque autant de chiens +1/+1 avec Charge qu'il y'a de serviteurs
 */
public class InvocationChien extends InvocationServiteur{
	/**
	 * Constructeur du type InvocationChien
	 * @param nom
	 * nom de la capacit�
	 * @param description
	 * description 
	 * @param serviteur 
	 * serviteur invoqu� 
	 * @throws HearthstoneException en cas de probl�me
	 */
	
	public InvocationChien(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description, serviteur);
	}

	@Override
	/**
     * Invoque les chiens
     * @param cible 
     * joueur qui lance la capacit�
     * @throws HearthstoneException En cas de probl�me...
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		if (!(cible instanceof IJoueur))	//Si la cible n'appartient pas � la classe des Joueurs
			throw new HearthstoneException("Cette capacit� ne peut cibler que un Joueur");
		Serviteur servClone = null;
		for(int i = 1 ; i <= (Plateau.getInstance().getAdversaire((Joueur) cible)).getJeu().size(); i++) {
			servClone = (Serviteur)this.serviteur.clone();
			servClone.setProprietaire((Joueur)cible);
			((Joueur)cible).getJeu().add((ICarte) servClone);
			System.out.println(this.serviteur.getNom() + " r�pond � l'appel de " + this.getNom());
			servClone.executerEffetDebutMiseEnJeu(cible);
			}
		
	}


}
