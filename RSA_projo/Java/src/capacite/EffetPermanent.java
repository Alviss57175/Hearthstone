package capacite;

import java.util.ArrayList;
import carte.ICarte;
import carte.Serviteur;
import exception.HearthstoneException;
import joueur.Joueur;
/**
 * Un effet permanent est un buff(ou un bonus pour les noobs) en attaque et/ou en d�fense. Il est permanent tant que le serviteur qui l'applique est vivant 
 */
public class EffetPermanent extends Capacite{

	public int buffatk; //Bonus d'attaque
	public int buffdef; //Bonus de defense
	public ArrayList<ICarte> serviteurbuff;	//Ce sera la liste des Serviteur beneficiaire de ce bonus, il sera initialis� au moment de l'activation du pouvoir
	/**
	 * Constructeur du type EffetPermanent
	 * @param nom
	 * nom de l'effet
	 * @param description
	 * description de l'effet
	 * @param buffatk
	 * 	la valeur du buff d'attaque
	 * @param buffdef
	 * 	la valeur du buff de d�fense
	 */
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
	/**
	 * Renvoie une erreur si elle est utilis�e car il faut utiliser executerEffetMiseEnjeu
	 * @throws HearthstoneException en cas de probl�me
	 */
	public void executerAction(Object cible) throws HearthstoneException {
		throw new HearthstoneException("Cet effet ne fonctionne qu'� la mise en jeu de la carte");
		
	}

	@Override
	/**
	 * execute l'effet permanent
	 * @param Cible
	 * on choisit le joueur, et on applique � tout son jeu les buffs.
	 * @throws HearthstoneException En cas de probl�me...
	 * @throws CloneNotSupportedException En cas d'un probl�me avec le clonage d'un objet
	 */
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException {
		if (cible == null) {
			throw new HearthstoneException("La cible n'existe pas");
		}
		for (ICarte c : ((Joueur)cible).getJeu()) {
			if (c instanceof Serviteur) {
				((Serviteur) c).setAtk(((Serviteur) c).getAtk() + buffatk);
				((Serviteur) c).setDef(((Serviteur) c).getDef() + buffdef);
				System.out.println(c.getNom() + " b�n�ficie du bonus de " + this.getNom());
			}
		}
		this.serviteurbuff = (ArrayList<ICarte>) ((Joueur) cible).getJeu().clone();
	}

	@Override
	/**
	 * execute la dispariton d'une carte
	 * @param cible
	 * On cible le joueur � qui on enl�ve tous ses buffs quand le serviteur est d�truit
	 * @throws HearthstoneException en cas de probl�me
	 */
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
						System.out.println(c.getNom() + " perd le bonus accord� par " + this.getNom());
					}
				}
			}
		}
		this.serviteurbuff = null;
	}

}
