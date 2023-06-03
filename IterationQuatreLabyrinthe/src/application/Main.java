/**
 * Main.java                       25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import java.util.Scanner;

/**
 * Menu principal à la console du jeu de labyrinthe
 * @author Descriaud Lucas
 * @author de Saint Palais François
 */

public class Main {
    
    final static int CHOIX_DIMENSION = 1;
    final static int CHOIX_CHAINE_ASCENDANTE = 2 ;
    final static int CHOIX_BACKTRACKING = 3 ;
    final static int CHOIX_JOUER = 4;
    final static int CHOIX_QUITTER = 5;
    
    static Scanner analyseurChoix;

    /**
     * Lancement du menu
     * @param non utilisé      
     */
    public static void main(String[] args) {
        

        
        final String MENU = """
                            +----------------------------+
                                1. Choix dimension labyrinthe
                                2. Construction par chaîne Ascendante
                                3. Construction par backtracking
                                
                                4. Jouer
                                
                                5. Quitter
                            +----------------------------+
                            """;
        analyseurChoix = new Scanner(System.in);
        
        int choix;
        boolean quitter;
        
        int[] DimensionLabyrinthe;
        
        choix = 0;
        quitter = false;
        do {
            System.out.println(MENU);
            System.out.println("Entrez votre choix : ");
            if (analyseurChoix.hasNextInt()) {
                choix = analyseurChoix.nextInt();
                switch (choix) {
                case CHOIX_DIMENSION: {
                    DimensionLabyrinthe = choixDimensions();
                    System.out.println(  "Hauteur : " + DimensionLabyrinthe[0]  
                                       + "\nLargeur : " + DimensionLabyrinthe[1]);      
                    break;
                }
                case CHOIX_CHAINE_ASCENDANTE: {
                    System.out.println("Chaine ascendante");
                    //TODO Créer un graphe par chaîne ascendante avec les dimension donné
                    break;
                }
                case CHOIX_BACKTRACKING: {
                    System.out.println("backtracking");
                    //TODO Créer un graphe par backtracking avec les dimension donné
                    break;
                }
                case CHOIX_JOUER: {
                    System.out.println("Jouer");
                    //TODO créer une nouvelle partie avec le graphe créer 
                    //ou le graphe par défaut si n'y a pas de graphe créer
                    break;
                }
                case CHOIX_QUITTER: {
                    quitter = true ;
                    break;
                }
                default:
                    System.out.println("Choix incorrect");
                    //TODO meilleur affichage d'erreur
                    //TODO demander confirmation
                    break;
                }
            } else {
                analyseurChoix.nextLine();
            }
        } while (! quitter);
        analyseurChoix.close();
    }

    /**
     * 
     * @return
     */
    private static int[] choixDimensions() {

        boolean hauteurValide ,
                largeurValide;
        
        int [] DimensionChoisi = new int[2];
        analyseurChoix = new Scanner(System.in);
        hauteurValide = largeurValide = false ;
        do {
            System.out.print("Entrez la hauteur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                DimensionChoisi[0] = analyseurChoix.nextInt();
                analyseurChoix.nextLine();
                hauteurValide = true ;
                System.out.println("Hauteur choisi : " + DimensionChoisi[0]);
            } else {
                System.out.println("Hauteur invalide !");
            }
            System.out.print("Entrez la largeur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                DimensionChoisi[1] = analyseurChoix.nextInt();
                analyseurChoix.nextLine();
                largeurValide = true ;
                System.out.println("Largeur choisi : " + DimensionChoisi[1]);
            } else {
                System.out.println("Largeur invalide !");
            }
        } while (!hauteurValide || !largeurValide);
        System.out.println("Choix fais");
//        analyseurChoix.nextLine();
//        analyseurChoix.close();
        return DimensionChoisi;
    }

}
