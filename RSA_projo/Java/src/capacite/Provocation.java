package capacite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import carte.ICarte;
import carte.Serviteur;
import carte.Sort;
import exception.HearthstoneException;
import joueur.Joueur;

public class Provocation extends Capacite {

	public Provocation(String nom, String description) {
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
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'� la mise en jeu de la carte");
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException {
		if (cible == null)
			throw new HearthstoneException("La cible n'existe pas");
		//Si le Pouvoir est activ� par un sort, alors on selectionne le serviteur qui en beneficie
				if (((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1) instanceof Sort) {
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Quel serviteur voulez vous donner Provoc ?");
					ICarte select = ((Joueur)cible).getCarteEnJeu(br.readLine());
					if(select != null && select instanceof Serviteur) {
						((Serviteur)select).setProvoc(true);
						System.out.println(select.getNom() + " provoque ses adversaires !");
					}
					else {
						System.out.println("Carte introuvable : Sort d�truit (D�sol�...)");
					}
				}
				else {
					//Sinon c'est la carte invoqu� qui en b�n�ficie (Donc la derniere carte dans la liste des cartes jou�es)
					((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).setProvoc(true);
					System.out.println(	((Serviteur) ((Joueur)cible).getJeu().get(((Joueur)cible).getJeu().size() - 1)).getNom() + " provoque ses adversaires !");
				}
		
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
