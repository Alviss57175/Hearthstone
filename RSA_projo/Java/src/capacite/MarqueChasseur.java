package capacite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import jeu.Plateau;
import joueur.Joueur;

public class MarqueChasseur extends Capacite {

	public MarqueChasseur(String nom, String description) {
		super(nom, description);
		// TODO Auto-generated constructor stub
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
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Quel serviteur voulez vous marquer ?");
		ICarte select = (Plateau.getInstance().getAdversaire((Joueur)cible)).getCarteEnJeu(br.readLine());
		if(select == null) {
			System.out.println("Le serviteur que vous voulez ciblez n'existe pas");
		}
		else {
			((Serviteur)cible).setDef(1);
			System.out.println(((Serviteur)cible).getNom() + " est frappé par " + this.getNom() + " !\nIl ne lui reste plus qu'1 point de defense !");
		}
		
	}
		
	

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		if (((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1) instanceof Sort) {
			executerAction(cible);
		}
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
