/**
 * Main.java                       25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import java.util.Scanner;

import representation.Labyrinthe;
import representation.Sommet;

/**
 * Menu principal à la console du jeu de labyrinthe
 * @author Descriaud Lucas
 * @author de Saint Palais François
 */

public class Main {
    
    private final static int CHOIX_DIMENSION = 1;
    private final static int CHOIX_CHAINE_ASCENDANTE = 2 ;
    private final static int CHOIX_BACKTRACKING = 3 ;
    private final static int CHOIX_JOUER = 4;
    private final static int CHOIX_QUITTER = 0;
    private static final int CHOIX_SAUVEGARDER = 5;
    private static final int CHOIX_CHARGER = 6;
    
    private static final String MENU = 
            """
            +---------------------------------------------+
                1. Choix dimension labyrinthe
                2. Construction par chaîne Ascendante
                3. Construction par backtracking
                
                4. Jouer

                5. Sauvegarder graphe
                6. Charger graphe
                0. Quitter
            +---------------------------------------------+
            """;
    private static final String INFO_JEUX 
    = """
      +------------------------------------------------------------------+
      
          Voici le labyrinthe.
          Vous êtes à l'emplacement indiquer par la lettre "J".
          La sorti ce situe à la lettre "S".
          Bonne chance !
          Entrez Q pour quitter
          
      +------------------------------------------------------------------+
      """;
    
    private static final String MESSAGE_VICTOIRE 
    = """
            +------------------------------------------+
                                                       
              Félicitation vous avez atteint la sortie 
                                                       
            +------------------------------------------+
      """;
    
    private static final char HAUT = 'H';
    private static final char DROITE = 'D';
    private static final char BAS = 'B';
    private static final char GAUCHE = 'G';
    
    private static Scanner analyseurChoix;
    
    private static Jeux labyrintheParDefaut;

    /**
     * Lancement du menu
     * @param non utilisé      
     */
    public static void main(String[] args) {
        int choix;
        boolean quitter;
        int[] dimensionLabyrinthe = {5,5};
        Jeux partie = null;
        boolean labyrintheConstruit = false;
        
        genererLabyrintheParDefaut();
        
        
        analyseurChoix = new Scanner(System.in);
        choix = 0;
        quitter = false;

        do {
            System.out.println(MENU);
            System.out.print("Entrez votre choix : ");
            if (!analyseurChoix.hasNextInt()) {
                analyseurChoix.nextLine();
            } else {
                choix = analyseurChoix.nextInt();
                switch (choix) {
                case CHOIX_DIMENSION: {
                    dimensionLabyrinthe = choixDimensions();
                    System.out.println(  "Hauteur : " + dimensionLabyrinthe[0]  
                                       + "\nLargeur : " + dimensionLabyrinthe[1]);
                    partie = null;
                    labyrintheConstruit = false;
                    break;
                }
                case CHOIX_CHAINE_ASCENDANTE: {
                    System.out.println("Chaine ascendante");
                    //TODO Créer un graphe par chaîne ascendante avec les dimension donné
                    partie = new Jeux(dimensionLabyrinthe[0], dimensionLabyrinthe[1]);
                    partie.chaineAscendante();
                    labyrintheConstruit = true;
                    break;
                }
                case CHOIX_BACKTRACKING: {
                    System.out.println("backtracking");
                    //TODO Créer un graphe par backtracking avec les dimension donné
                    partie = new Jeux(dimensionLabyrinthe[0], dimensionLabyrinthe[1]);
                    partie.constructionBacktracking();
                    labyrintheConstruit = true;
                    break;
                }
                case CHOIX_JOUER: {
                    System.out.println("Jouer");
                    //TODO créer une nouvelle partie avec le graphe créer 
                    //ou le graphe par défaut si n'y a pas de graphe créer
                    if (!labyrintheConstruit) {
                        System.out.println("Aucun graphe n'a été construit. Nous prenons celui par défaut");
                        partie = labyrintheParDefaut;
                        System.out.println(partie.parcoursProfondeur());
                        boucleJeux(partie);
                    } else {
                        boucleJeux(partie);
                    }
                    break;
                }
                case CHOIX_SAUVEGARDER: {
                    System.out.println("Sauvegarde");
                    //TODO Sauvegarde le graphe s'il a été construit
                    break;
                }
                case CHOIX_CHARGER: {
                    System.out.println("Charger");
                    //TODO Charger le graphe mis en sauvegarde
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
            }
        } while (!quitter);
        analyseurChoix.close();
    }
    /**
     * Créer un labyrinthe par défaut
     */
    private static void genererLabyrintheParDefaut() {
        /* Doit représenter ce labyrinthe :
         *   X  0     1     2
         * Y +-----+-----+-----+
         *   |     |           |
         * 0 |  E  |  2     3  |
         *   |     |           |
         *   +     +-----+     +
         *   |                 |
         * 1 |  4     5     6  |
         *   |                 |
         *   +     +-----+     +
         *   |           |     |
         * 2 |  7     8  |  S  |
         *   |           |     |
         *   +-----+-----+-----+
         */
        int hauteur, largeur;
        hauteur = largeur = 3;
        Sommet[][] listeSommet = {
                {new Sommet(0, 0),
                 new Sommet(1, 0),
                 new Sommet(2, 0)},
                
                {new Sommet(0, 1),
                 new Sommet(1, 1),
                 new Sommet(2, 1)},
                
                {new Sommet(0, 2),
                 new Sommet(1, 2),
                 new Sommet(2, 2)}
        };
        //                                          H      D     B      G
        listeSommet[0][0].setVoisin(new boolean[]{false, false, true, false});
        listeSommet[0][1].setVoisin(new boolean[]{false, true, false, false});
        listeSommet[0][2].setVoisin(new boolean[]{false, false, true, true});

        listeSommet[1][0].setVoisin(new boolean[]{true, true, true, false});
        listeSommet[1][1].setVoisin(new boolean[]{false, true, false, true});
        listeSommet[1][2].setVoisin(new boolean[]{true, false, true, true});
        
        listeSommet[2][0].setVoisin(new boolean[]{true, true, false, false});
        listeSommet[2][1].setVoisin(new boolean[]{false, false, false, true});
        listeSommet[2][2].setVoisin(new boolean[]{true, false, false, false});
        
        Sommet[][] listeArrete = {
                {listeSommet[0][0],listeSommet[1][0]},
                {listeSommet[1][0],listeSommet[0][0]},

                {listeSommet[1][0],listeSommet[1][1]},
                {listeSommet[1][1],listeSommet[1][0]},

                {listeSommet[1][0],listeSommet[2][0]},
                {listeSommet[2][0],listeSommet[1][0]},
                
                {listeSommet[2][0],listeSommet[2][1]},
                {listeSommet[2][1],listeSommet[2][0]},
                
                {listeSommet[1][1],listeSommet[1][2]},
                {listeSommet[1][2],listeSommet[1][1]},
                
                {listeSommet[0][2],listeSommet[1][2]},
                {listeSommet[1][2],listeSommet[0][2]},
                
                {listeSommet[1][2],listeSommet[2][2]},
                {listeSommet[2][2],listeSommet[1][2]},

                {listeSommet[0][2],listeSommet[0][1]},
                {listeSommet[0][1],listeSommet[0][2]},
                
        };
        Sommet entree = listeSommet[0][0];
        Sommet sortie = listeSommet[2][2];
        labyrintheParDefaut 
        = new Jeux(hauteur, largeur, listeSommet, listeArrete, 
                   entree, sortie, 0, 0);
    }

    /**
     * 
     * @return
     */
    private static int[] choixDimensions() {

        boolean hauteurValide ,
                largeurValide;
        
        int [] DimensionChoisi;
        analyseurChoix = new Scanner(System.in);
        hauteurValide = largeurValide = false ;
        do {
            System.out.println();
            DimensionChoisi = new int[2];
            
            System.out.print("Entrez la hauteur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                DimensionChoisi[0] = analyseurChoix.nextInt();
                hauteurValide = true;
                System.out.println("Hauteur choisi : " + DimensionChoisi[0]);
            } else {
                System.out.println("Erreur : Hauteur invalide !");
                hauteurValide = false;
            }
            analyseurChoix.nextLine();
            
            System.out.print("Entrez la largeur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                DimensionChoisi[1] = analyseurChoix.nextInt();
                largeurValide = true;
                System.out.println("Largeur choisi : " + DimensionChoisi[1]);
            } else {
                System.out.println("Erreur : Largeur invalide !");
                largeurValide = false;
            }
            analyseurChoix.nextLine();
            
        } while (!hauteurValide || !largeurValide);
        System.out.println("Choix fait");
        return DimensionChoisi;                          
    }                                                    
                                                         
    /**                                                  
     * La boucle de jeux.                                
     * Demande au joueur quel déplacement veut-il réaliser.
     * Propose également au joueur de quitter le jeux pour retourner au menu.
     * @param partie
     */
    private static void boucleJeux(Jeux partie) {
        //TODO Écrire le corps
        boolean sortiAtteinte;
        boolean quitter;
        char choix;
        sortiAtteinte = quitter = false;

        partie.joueurAuDebut();
        
        System.out.println(INFO_JEUX);
        
        System.out.println(partie);
        do {
            
            System.out.print("Entrez votre déplacement : ");
            choix = Character.toUpperCase(analyseurChoix.next().charAt(0));
            analyseurChoix.nextLine();
            
            quitter = (choix - 'Q') == CHOIX_QUITTER;
            if (!quitter) {
                try {
                    partie.bougerJoueur(choix);
                } catch (Exception e) {
                    System.out.println("Attention : Vous devez choisir parmi H, D, B et G");
                }
            }
            
            sortiAtteinte = partie.estSorti();
            System.out.println(partie);
            
        } while (!sortiAtteinte && !quitter);
        
        if (sortiAtteinte) {
            System.out.println(MESSAGE_VICTOIRE);
        } else {
            //TODO créer une constante
            System.out.println("Vous avez quitter la partie en cours");
        }
    }

}
