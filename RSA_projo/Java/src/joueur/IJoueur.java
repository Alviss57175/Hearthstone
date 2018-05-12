package joueur;

import java.util.ArrayList;

import carte.ICarte;
import exception.HearthstoneException;

/**
 * Cette interface définit le comportement (normalement) minimal d'un joueur
 *
 */
public interface IJoueur {
	
		
        /**
         * Nombre max de mana que le joueur peut avoir (10 en principe)
         */
        public final static int MAX_MANA = 10;          // Nombre max de mana
        
        /**
         * Le nombre max de cartes qui peuvent composer un deck. 
         */
        public final static int TAILLE_DECK = 15;       // Nombre max de cartes dans le deck

        /**
         * @return C'est le héros choisi par le joueur
         */
        Heros getHeros();
        
        /**
         * @return C'est le pseudo du joueur
         */
        String getPseudo();

        /**
         * Le nombre de mana augmente de 1 �  chaque tour. Mais il ne pourra pas dépasser la constante MAX_MANA.
         * Certaines cartes peuvent augmenter ce nombre en cours de jeu (comme la carte Croissance sauvage)
         * @return nombre de manas potentiels du joueur au début du tour
         */
        int getMana();
        
        /**
         * Nombre de manas encore disponibles pendant le tour. Le stock est rempli �  chaque début de tour avec les mans potentiels.
         * @return nombre de mana encore en stock
         * @see getMana() 
         */
        int getStockMana();
        
        /**
         * La main est l'ensemble des cartes piochées par le joueur mais non encore mises en jeu.
         * @return La liste des cartes de la main
         */
        ArrayList<ICarte> getMain();
        
        /**
         * Le jeu d'un joueur est composé des cartes placées sur le board
         * @return La liste des cartes en jeu
         */
        ArrayList<ICarte> getJeu();
       
        /**
         * Permet de rechercher une carte en jeu par rapport �  un bout de son nom (grâce �  la fonction contains de la classe String).
         * @param nomCarte C'est une chaine de caractères qui correspond au nom ou �  un bout du nom de la carte que l'on souhaite
         * @return la première carte trouvée ou null si aucune carte ne correspond
         */
        ICarte getCarteEnJeu(String nomCarte);  
        
        
        /**
         * Permet de rechercher une carte de la main par rapport �  un bout de son nom (grâce �  la fonction contains de la classe String).
         * @param nomCarte C'est une chaine de caractères qui correspond au nom ou �  un bout du nom de la carte que l'on souhaite
         * @return la première carte trouvée ou null si aucune carte ne correspond
         */
        ICarte getCarteEnMain(String nomCarteMain);

        
        
        /**
         * Il se passe beaucoup de choses au moment où le joueur prend le tour. Son nombre de mana augmente,
         * son stock est réinitialisé en conséquence, les erviteurs en attente deviennent jouables, etc.
         * @throws HearthstoneException Si jamais le joueur tente de prendre le tour alors que ce n'est pas �  lui de le faire...
         */
        void prendreTour() throws HearthstoneException;

        /**
         * Il se passe beaucoup de choses au moment où le joueur finit le tour. 
         * @throws HearthstoneException Si jamais le joueur tente de finir le tour alors que ce n'est pas �  lui de le faire...
         */
        void finirTour() throws HearthstoneException;
        
        /**
         * Au début de chaque tour, une carte du deck est choisie aléatoirement et est placée dans la main du joueur. 
         * Elle est naturellement enlevée de son deck.
         * @throws HearthstoneException En cas de problème, une exception...
         */
        void piocher() throws HearthstoneException;
        
        /**
         * Pendant le tour, un joueur peut tenter de prendre une carte de sa main et de la placer sur le board. 
         * Si c'est possible, elle est retirée de la main et placée sur le board. Evidemment, il faut faire
         * attention aux effets de mise en jeu éventuels, aux contrôles, etc.
         * @param carte La carte jouée
         * @throws HearthstoneException En cas de problème, une exception...
         * @see ICarte
         */
        void jouerCarte(ICarte carte) throws HearthstoneException;
        
        /**
         * Pendant le tour, un joueur peut tenter de prendre une carte de sa main et de la placer sur le board. 
         * Si c'est possible, elle est retirée de la main et placée sur le board. Evidemment, il faut faire
         * attention aux effets de mise en jeu éventuels, aux contrôles, etc. C'est la même chose que la fonction
         * jouerCarte(carte), mais ici il faut préciser la cible obligatoirement
         * @param carte La carte...
         * @param cible Sa cible...
         * @throws HearthstoneException En cas de problème, une exception...
         */
        void jouerCarte(ICarte carte, Object cible) throws HearthstoneException;
        
        /**
         * Lorsque qu'une carte est en jeu, le joueur peut utiliser cette carte. Utiliser un serviteur
         * consistera �  attaque la cible (un autre serviteur ou le héros), �  utiliser un sort, etc. 
         * @param carte La carte
         * @param cible La cible
         * @throws HearthstoneException En cas de problème, une exception...
         */
        void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException;
        
        /**
         * Cette fonction utilise le pouvoir du héros du joueur pour faire quelque chose sur la cible...
         * @param cible La cible...
         * @throws HearthstoneException En cas de problème, une exception...
         */
        void utiliserPouvoir(Object cible) throws HearthstoneException;
        
        /**
         * Lorsqu'une carte a fini sa vie (un serviteur qui meut, un sort qui vient d'être utilisé, ...), il 
         * faut gérer le jeu (une carte en moins...), ainsi que les effets liés �  la disparition de la carte (Rale d'agonie par exemple).
         * @param carte La carte qui est perdue
         * @throws HearthstoneException En cas de problème, une exception...
         */
        void perdreCarte(ICarte carte) throws HearthstoneException;
        
        
        
}
