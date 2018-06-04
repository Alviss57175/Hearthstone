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
/**
 * Une attaque ciblée est une capacité qui cible soit un héros, soit un serviteur. 
 */
public class AttaqueCiblee extends Capacite {
	
	public int degats;
	/**
	 * Constructeur du type attaque ciblée
	 * @param nom
	 * 	le nom de l'attaque
	 * @param description
	 * 	une description de l'attaque
	 * @param degats
	 * 	la valeur des dégats de l'attaque
	 */
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
	/**
	 * Execute une action
	 * @param Cible
     * On teste la nature de la cible (joueur ou serviteur, ou autre ?) et on applique les dégats.
     * @throws HearthstoneException En cas de problème de ciblage
     * @throws IOException en cas de problème d'entrée/sortie
     */
	public void executerAction(Object cible) throws HearthstoneException, IOException {
		if(cible == null) {	//Aucune cible trouvée
			throw new HearthstoneException("La cible n'existe pas");
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nbchoix = -1;
		while (nbchoix < 1 || nbchoix > 2) {
			System.out.println("");
			System.out.println("Qui voulez vous attaquer avec " + this.getNom() +" ?");
			System.out.println("\t1.Héros adverse");
			System.out.println("\t2.Serviteur adverse");
			try {
				nbchoix = Integer.parseInt(br.readLine());
			}
			catch(NumberFormatException e){
				nbchoix = -1;
			}
			if (nbchoix < 1 || nbchoix > 2)
				System.out.println("Choix Invalide");
		}
		if(nbchoix == 1) {	//La cible est le joueur adverse
			((Joueur)(Plateau.getInstance().getAdversaire((Joueur)cible))).heros.perdreVie(this.degats);
		}
		else {
			if (nbchoix == 2) {	//La cible est un serviteur
				System.out.println("Quel serviteur voulez vous attaquer ?");
				ICarte select = (Plateau.getInstance().getAdversaire((Joueur)cible)).getCarteEnJeu(br.readLine());
				if(select == null) {
					System.out.println("Le serviteur que vous voulez ciblez n'existe pas");
				}
				else {
					((Serviteur) select).PerdreDef(this.degats);
				}
			}
			else {	//La cible est un sort ou un autre type de carte (Me demandez pas comment ça peut arriver, j'suis juste prudent)
				throw new HearthstoneException("La cible n'est ni un joueur ni un serviteur");
			}
		}
		
	}

	@Override
	/**
	 * Execute l'action quand la carte est mise sur le terrain, comme un sort
	 * @param Cible
     * Si la carte est un sort, on exécute le sort à la mise en jeu.
     * @throws HearthstoneException En cas de problème de cible
     * @throws IOException en cas de problème d'entrée/sortie
     */
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
		
	}
	/**
	 * Traduit la classe en une chaine de caracteres
	 * @return La chaine en question
	 */
	public String toString() {
		return "Nom Capacite [ " + this.nom + " ], Description [ " + this.description + " ], Dégâts [ " + this.degats + " ]";
	}
	
	
	

}
