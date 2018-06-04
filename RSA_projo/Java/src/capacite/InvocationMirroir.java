package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;
/**
* c'est	aussi une capacit� de la famille des invocations de	serviteurs.	Elle	
* est propre	� Jaina. La	carte poss�dant	cette capacit� invoque automatiquement,aud�but	
* du tour,deux serviteurs 0/+2 avec la capacit� "Provocation".
*/
public class InvocationMirroir extends InvocationServiteur {
	/**
	 * @param nom
	 * nom de la capacit�
	 * @param description
	 * description
	 * @param serviteur 
	 * serviteur invoqu�
	 * @throws HearthstoneException en cas de probl�me
	 */

	public InvocationMirroir(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description, serviteur);
	}


	@Override
	/**
     * Invoque les miroirs
     * @param cible 
     * joueur qui lance la capacit�
     * @throws HearthstoneException En cas de probl�me...
     * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
     * @throws IOException En cas de probleme avec le BufferedReader
     */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if(cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		if(!(cible instanceof Joueur))
			throw new HearthstoneException("La cible doit etre une joueur");
		Serviteur servClone = null;
		for (int i = 0 ; i < 2 ; i++) {
			servClone = (Serviteur)this.serviteur.clone();
			servClone.setProprietaire((Joueur)cible);
			((Joueur)cible).getJeu().add((ICarte) servClone);
			System.out.println(this.serviteur.getNom() + " r�pond � l'appel de " + this.getNom());
			servClone.executerEffetDebutMiseEnJeu(cible);
		}
		System.out.println("Deux " + this.serviteur.getNom() + " r�pondent � l'appel de " + this.getNom());
	}
	


	

}
