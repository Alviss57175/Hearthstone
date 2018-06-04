package capacite;

import java.io.IOException;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.IJoueur;
import joueur.Joueur;
/**
* c'est	aussi une capacité de la famille des invocations de	serviteurs.	Elle	
* est propre	à Jaina. La	carte possédant	cette capacité invoque automatiquement,audébut	
* du tour,deux serviteurs 0/+2 avec la capacité "Provocation".
*/
public class InvocationMirroir extends InvocationServiteur {
	/**
	 * @param nom
	 * nom de la capacité
	 * @param description
	 * description
	 * @param serviteur 
	 * serviteur invoqué
	 * @throws HearthstoneException en cas de problème
	 */

	public InvocationMirroir(String nom, String description, Serviteur serviteur) throws HearthstoneException {
		super(nom, description, serviteur);
	}


	@Override
	/**
     * Invoque les miroirs
     * @param cible 
     * joueur qui lance la capacité
     * @throws HearthstoneException En cas de problème...
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
			System.out.println(this.serviteur.getNom() + " répond à l'appel de " + this.getNom());
			servClone.executerEffetDebutMiseEnJeu(cible);
		}
		System.out.println("Deux " + this.serviteur.getNom() + " répondent à l'appel de " + this.getNom());
	}
	


	

}
