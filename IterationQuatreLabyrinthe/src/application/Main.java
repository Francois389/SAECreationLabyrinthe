/**
 * Main.java                       25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import java.util.Scanner;

import representation.Labyrinthe;

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
        
        final int CHOIX_QUITTER = 4;
        final int CHOIX_DIMENSION = 1;
        final int CHOIX_BACKTRACKING = 3 ;
        final int CHOIX_CHAINE_ASCENDANTE = 2 ;
        
        final String MENU = """
                            +----------------------------+
                                1. Choix Dimension
                                2. Chaine Ascendante
                                3. Backtracking
                                4. Quitter
                            +----------------------------+
                            """;
        Scanner analyseurChoix = new Scanner(System.in);
        
        int choix;
        boolean quitter;
        
        int[] DimensionLabyrinthe = {0 , 0};
        
        choix = 0;
        quitter = false;
        do {
            System.out.println(MENU);
            System.out.println("Veuillez taper votre choix");
            if (analyseurChoix.hasNextInt()) {
                choix = analyseurChoix.nextInt();
                switch (choix) {
                case CHOIX_DIMENSION: {
                    DimensionLabyrinthe = choixDimensions();
                    System.out.println("Hauteur : " + DimensionLabyrinthe[0]  
                                       + " \nLargeur : " + DimensionLabyrinthe[1]);      
                    break;
                }
                case CHOIX_CHAINE_ASCENDANTE: {
                    creationChaineAscendante(DimensionLabyrinthe);
                    break;
                }
                case CHOIX_BACKTRACKING: {
                    creationBacktracking(DimensionLabyrinthe);
                    System.out.println("backtracking");
                }
                case CHOIX_QUITTER: {
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


    private static void creationBacktracking(int[] dimensionLabyrinthe) {
        try {
            Labyrinthe labyrintheJoueur = new Labyrinthe(dimensionLabyrinthe[0], 
                                                         dimensionLabyrinthe[1]);
            labyrintheJoueur.constructionBacktracking();
            System.out.println(labyrintheJoueur);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void creationChaineAscendante(int[] dimensionLabyrinthe) {
        try {
            Labyrinthe labyrintheJoueur = new Labyrinthe(dimensionLabyrinthe[0], 
                                                         dimensionLabyrinthe[1]);
            labyrintheJoueur.chaineAscendante();
            System.out.println(labyrintheJoueur);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static int[] choixDimensions() {

        boolean hauteurValide ,
                largeurValide;
        
        int [] DimensionChoisi = new int[2];
        Scanner analyseurDimension = new Scanner(System.in);
        hauteurValide = largeurValide = false ;
        do {
            System.out.print("Quelle hauteur souhaitez vous ? ");
            if (analyseurDimension.hasNextInt()) {
                DimensionChoisi[0] = analyseurDimension.nextInt();
                analyseurDimension.nextLine();
                hauteurValide = true ;
                System.out.println("Hauteur choisi : " + DimensionChoisi[0]);
            } else {
                System.out.println("Hauteur invalide !");
            }
            System.out.print("Quelle largeur souhaitez vous ? ");
            if (analyseurDimension.hasNextInt()) {
                DimensionChoisi[1] = analyseurDimension.nextInt();
                analyseurDimension.nextLine();
                largeurValide = true ;
                System.out.println("Largeur choisi : " + DimensionChoisi[1]);
            } else {
                System.out.println("Largeur invalide !");
            }
       } while (!hauteurValide && largeurValide);
       return DimensionChoisi;
    }

}
