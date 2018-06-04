package capacite;

import java.io.IOException;

import exception.HearthstoneException;

/**
 * Une capacité, c'est l'abstraction générique pour décrire, le pouvoir du héros, le sort d'une carte Sort, la capacité d'un serviteur, etc.
 */
public interface ICapacite {
        /**
         * Le nom de la capacité ("Boule de feu")
         * @return Une chaine de caractères...
         */
        String getNom();
        
        /**
         * La description de la capacité ("Inflige +1 points de dégat au héros visé"
         * @return Une chaine de caractères
         */
        String getDescription();

        /**
         * Certaines capacité agissent en début de tour (J'ai pas d'exemple mais on ne sait jamais)
         * @throws HearthstoneException En cas de problème...
         * @throws CloneNotSupportedException En cas de probleme avec le clonnage d'un objet
         */
        void executerEffetDebutTour() throws HearthstoneException, CloneNotSupportedException;
        
        /**
         * Certaines capacité agissent en fin de tour (J'ai pas d'exemple mais on ne sait jamais)
         * @throws HearthstoneException En cas de problème...
         */
        void executerEffetFinTour() throws HearthstoneException;
        
        /**
         * Certaines capacité agissent quand on le demande, et éventuellement sur une cible...
         * @param cible Cible de l'action (En general le joueur qui lance la capacite)
         * @throws HearthstoneException En cas de problème...
         * @throws IOException En cas de problem avec le BufferedReader
         */
        void executerAction(Object cible) throws HearthstoneException, IOException;

        /**
         * Certaines capacité agissent en début de mise en jeu. C'est souvent le cas des sorts.
         * @param cible Cible de l'action (En general le joueur qui lance la capacite)
         * @throws HearthstoneException En cas de problème...
         * @throws CloneNotSupportedException En cas de probleme avec le clonage d'un objet
         * @throws IOException En cas de problem avec le BufferedReader
         */
        void executerEffetMiseEnJeu(Object cible) throws HearthstoneException, CloneNotSupportedException, IOException;
        
        /**
         * Certaines capacité agissent lorsque la carte disparaît du jeu (comme le râle d'agonie...). 
         * @param cible Cible de l'action (En general le joueur qui lance la capacite)
         * @throws HearthstoneException En cas de problème...
         */
        void executerEffetDisparition(Object cible) throws HearthstoneException;
}
