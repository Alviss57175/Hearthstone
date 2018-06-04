package carte;

import java.io.IOException;

import exception.HearthstoneException;
import joueur.IJoueur;

/**
 * Une carte, ben c'est une carte du jeu quoi ! Une carte peut √™tre
 * une carte de sort ou une carte serviteur (on ne s'occupe pas des autres sortes de cartes).
 * @see IJoueur
 */
public interface ICarte extends Cloneable {
        

        String getNom();                                // Une carte doit avoir un nom
        int getCout();                          // Une carte co√ªte un crtain nombre de mana
        IJoueur getProprietaire();      // Une carte appartient √  un joueur
        
        /**
         * Une carte peut avoir un effet au d√©but de chaque tour o√π elle est en jeu
         * @param cible En general pointe sur le joueur 
         * @throws HearthstoneException En cas de probleme
         * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
         */
        void executerEffetDebutTour(Object cible) throws HearthstoneException, CloneNotSupportedException;

        /**
         * Une carte peut avoir un effet √  la fin d'un chaque tour o√π elle est en jeu
         * @param cible En general pointe sur le joueur 
         * @throws HearthstoneException En cas de probleme
         */
       void executerEffetFinTour(Object cible) throws HearthstoneException;
        
        /**
         * Une carte peut avoir un effet au d√©but de sa mise en jeu 
         * @param cible En general pointe sur le joueur    
         * @throws HearthstoneException En cas de probleme
         * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
         * @throws IOException En cas de probleme avec le BufferedReader
         */
        void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException;
        
        /**
         * Une carte peut avoir un effet au moment de sa disparition du jeu 
         * @param cible  En general pointe sur le joueur 
         * @throws HearthstoneException En cas de probleme
         */
        void executerEffetDisparition(Object cible) throws HearthstoneException;
        
        /**
         * Une carte peut avoir une action qui se commande √  n'importe quel moment du tour lorsqu'elle est en jeu 
         * @param cible En general pointe sur le joueur
         * @throws HearthstoneException En cas de Probleme
         * @throws IOException En cas de probleme avec le BufferedReader
         */
        void executerAction(Object cible) throws HearthstoneException, IOException;
        
        /**
         * Fonction qui teste si les conditions pour que la carte soit encore pr√©sente au tour suivant. Si la fonction
         * renvoie vrai, il faut s√ªrement la retirer du board...
         * @return true si la carte est foutu (un serviteur tu√©, un sort lanc√©, etc.)
        */
        boolean disparait();
        
        /**
         * Fonction qui clonera les cartes
         * @throws CloneNotSupportedException Dans l'ÈventualitÈ o˘ la fonction echoue
         * @return Retourne un clone de la mÍme carte (Avec une ID differente)
         */
		Object clone() throws CloneNotSupportedException;
        
        
		
}
