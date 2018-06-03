package capacite;

import java.util.ArrayList;
import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.Joueur;

public class EffetPermanent extends Capacite{
	
	public int buffatk; //Bonus d'attaque
	public int buffdef; //Bonus de defense
	public ArrayList<ICarte> serviteurbuff;	//Ce sera la liste des Serviteur beneficiaire de ce bonus, il sera initialisé au moment de l'activation du pouvoir
	
	public EffetPermanent(String nom, String description, int buffatk, int buffdef) {
		super(nom, description);
		this.buffatk = buffatk;
		this.buffdef = buffdef;
		this.serviteurbuff = null;
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
		throw new HearthstoneException("Cet effet ne fonctionne qu'à la mise en jeu de la carte");
		
	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if (cible == null) {
			throw new HearthstoneException("La cible n'existe pas");
		}
		for (ICarte c : ((Joueur)cible).getJeu()) {
			if (c instanceof Serviteur) {
				((Serviteur) c).setAtk(((Serviteur) c).getAtk() + buffatk);
				((Serviteur) c).setDef(((Serviteur) c).getDef() + buffdef);
				System.out.println(c.getNom() + " bénéficie du bonus de " + this.getNom());
			}
		}
		this.serviteurbuff = (ArrayList<ICarte>) ((Joueur) cible).getJeu().clone();
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if (cible == null) {
			throw new HearthstoneException("La cible n'existe pas");
		}
		if (!(cible instanceof Joueur)) {
			throw new HearthstoneException("Effet Permanent doit cibler un Joueur");
		}
		for (ICarte c : ((Joueur)cible).getJeu()) {
			if (c instanceof Serviteur) {
				for (ICarte d : this.serviteurbuff) {
					if(c.equals(d)) {
						((Serviteur) c).setAtk(((Serviteur) c).getAtk() - buffatk);
						((Serviteur) c).setDef(((Serviteur) c).getDef() - buffdef);
						System.out.println(c.getNom() + " perd le bonus accordé par " + this.getNom());
					}
				}
			}
		}
		this.serviteurbuff = null;
	}

}
