package capacite;

import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.Joueur;

public class AttaqueHeros extends AttaqueCiblee{

	public AttaqueHeros(String nom, String description, int degats) {
		super(nom, description, degats);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		if(!(cible instanceof Joueur)) {	//La cible est le joueur adverse
			throw new HearthstoneException("L'Attaque du héros ne peut viser que un Héros");
		}
		for (ICarte c : ((Joueur)cible).getJeu()) {
			if(((Serviteur)c).isProvoc()) {
				System.out.println("L'attaque passe outre la provocation de " + c.getNom() + " !");
			}
		}
		((Joueur)cible).getHeros().perdreVie(this.degats);
		
		
	}

}
