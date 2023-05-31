/**
 * Main.java                       25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import java.util.Scanner;

/**
 * Menu principal du jeu de labyrinthe
 * @author Descriaud Lucas
 *
 */

public class Main {

    /**
     * Lancement du menu
     * @param non utilisé      
     */
    public static void main(String[] args) {
        
        final int CHOIX_QUITTER = 3;
        final int CHOIX_CHAINE_ASCENDANTE = 1;
        final int CHOIX_BACKTRACKING = 2 ;
        
        final String MENU = """
                            +----------------------------+
                                1. Chaine ascendante
                                2. Backtracking
                                3. Quitter
                            +----------------------------+
                            """;
        Scanner analyseurChoix = new Scanner(System.in);
        
        int choix;
        boolean quitter;
        
        choix = 0;
        quitter = false;
        do {
            System.out.println(MENU);
            System.out.println("Quelle type de constrution souhaitez vous ?");
            if (analyseurChoix.hasNextInt()) {
                choix = analyseurChoix.nextInt();
                switch (choix) {
                case 1: {
                    System.out.println("chaine ascendante");
                    break;
                }
                case 2: {
                    System.out.println("backtracking");
                    break;
                }
                case 3: {
                    quitter = true ;
                    break;
                }
                default:
                    System.out.println("Choix incorrect");
                    break;
                }
            }
            analyseurChoix.nextLine();
        } while (! quitter);
    }

}
