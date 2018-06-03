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

public class Charge extends Capacite{
	

	
	public Charge(String nom, String description) {	//Constructeur si la capacit� Charge vise un Serviteur alli�
		super(nom, description);
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'� la mise en jeu de la carte");
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, IOException {
		if (cible == null)	//Si la cible n'existe pas
			throw new HearthstoneException("Cible n'existe pas");
		//Si le Pouvoir est activ� par un sort, alors on selectionne le serviteur qui en beneficie
		if (((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1) instanceof Sort) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Quelle carte voulez eveiller ?");
			ICarte select = ((Joueur)cible).getCarteEnJeu(br.readLine());
			if(select != null && select instanceof Serviteur) {
				((Serviteur)select).setJouable(true);
				System.out.println(select.getNom() + " s'eveille");
			}
			else {
				System.out.println("Carte introuvable : Sort d�truit (D�sol�...)");
			}
		}
		else {
			//Sinon c'est la carte invoqu� qui en b�n�ficie (Donc la derniere carte dans la liste des cartes jou�es)
			((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).setJouable(true);
			System.out.println(((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).getNom() + " s'�veille");
		}
		
			
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
