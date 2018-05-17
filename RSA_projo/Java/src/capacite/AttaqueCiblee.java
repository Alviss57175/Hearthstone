package capacite;

import carte.Serviteur;
import exception.HearthstoneException;
import joueur.Joueur;

public class AttaqueCiblee extends Capacite {
	
	public int degats;
	
	public AttaqueCiblee(String nom, String description, int degats) {
		super(nom, description);
		this.degats = degats;
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		if(cible instanceof Joueur) {	//La cible est le joueur adverse
			((Joueur)cible).heros.PerdreVie(this.degats);
		}
		else {
			if (cible instanceof Serviteur) {	//La cible est un serviteur
				((Serviteur)cible).PerdreDef(this.degats);
			}
			else {	//La cible est un sort ou un autre type de carte (Me demandez pas comment ça peut arriver, j'suis juste prudent)
				throw new HearthstoneException("La cible n'est ni un joueur ni un serviteur");
			}
		}
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		
	}
	
	public String toString() {
		return "Nom Capacite [ " + this.nom + " ], Description [ " + this.description + " ], Dégâts [ " + this.degats + " ]";
	}
	
	
	

}
