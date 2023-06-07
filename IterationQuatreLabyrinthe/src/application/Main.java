    /**
 * Main.java                       25 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import java.util.Scanner;

import representation.Sommet;
import sauvegarde.LabyrintheJson;

/**
 * Menu principal à la console du jeu de labyrinthe
 * @author Descriaud Lucas
 * @author de Saint Palais François
 */

public class Main {
    
    /** Deplacement sur la droite du joueur*/
    public static final char DROITE = 'D';
    
    /** Deplacement vers le bas du joueur*/
    public static final char BAS = 'S';
    
    /** Deplacement vers la gauche du joueur*/
    public static final char GAUCHE = 'Q';
    
    /** Deplacement vers le haut du joueur*/
    public static final char HAUT = 'Z';
    
    /** Retour a l'entree */
    public static final char RESET = 'R';
    
    
    private static Scanner analyseurChoix;
   
    /* Lettre correspondante au choix du menu */
    private final static char CHOIX_BACKTRACKING = 'B' ;
    private final static char CHOIX_CHAINE_ASCENDANTE = 'C' ;
    private static final char CHOIX_CHARGER = 'L';
    private final static char CHOIX_DIMENSION = 'D';
    private final static char CHOIX_JOUER = 'J';
    private final static char CHOIX_QUITTER = 'Q';
    private static final char CHOIX_SAUVEGARDER = 'S';
    private static final char CHOIX_AFFICHER = 'A';
    private static final char CHOIX_REPONSE = 'R';
    private static final char CHOIX_ABANDONNER_PARTIE = 'F' ;
    private static final char CHOIX_POSITION_ENTREE_SORTIE = 'P';
    
    /** Labyrinthe utilisé par defaut si absence de dimension 
     *  ou absence de construction de la part de l'utilisateur
     */
    private static Jeux labyrintheParDefaut;
    
    
    private static final String INFO_JEUX 
    = """
            
      +------------------------------------------------------------------+
      
          Voici le labyrinthe.
          Vous êtes à l'emplacement indiquer par la lettre "J".
          La sorti ce situe à la lettre "S".
                Commandes :
                      - Z : vers le haut
                      - S : vers le bas 
                      - Q : vers la droite
                      - D : vers la gauche
                      - F : abandonner la partie
                      - R : retour à l'entrée
          
          Bonne chance !
      +------------------------------------------------------------------+
      """;
    
    private static final String MENU = 
            """
            
            +---------------------------------------------+
                D. Choix Dimension labyrinthe
                C. Construction par Chaîne ascendante
                B. Construction par Backtracking
                P. Positionnement de l'entree 
                   et de la sortie (optionnel)
                A. Afficher le labyrinthe
                
                J. Jouer
                
                R. Affichage de la reponse
                S. Sauvegarder labyrinthe
                L. Charger labyrinthe
                Q. Quitter
            +---------------------------------------------+
            """;
    

    private static final String ERREUR_LABYRINTHE_PAS_CONSTRUIT 
    = """
            
            +-------------------------------------------+
            
                 Aucun labyrinthe n'est construit
                 
            +-------------------------------------------+
      """;
    
    private static final String ABANDON_PARTIE 
    = "Vous avez quitter la partie en cours" ;

    private static final String ERREUR_COMMANDE
    = """           
      +------------------------------------------------------------------+
            Rappel des commandes :
              - %c : vers le haut
              - %c : vers le bas 
              - %c : vers la droite
              - %c : vers la gauche
              - %c : abandonner la partie
              - %c : retour a l'entree
              
      +------------------------------------------------------------------+
      """;
    
    private static final String MESSAGE_VICTOIRE_NOMBRE_COUP
    = """
     +------------------------------------------------+
         Félicitation vous avez atteint la sortie
            
                   Nombre de coup : %d
          Nous avons trouvé un chemin en %d coup
      
     +------------------------------------------------+   
     """;
    
    private static final String LABYRINTHE_CHARGE
    = """           
      +------------------------------------------------------------------+
            
            le labyrinthe est charge, il fait du %d par %d
              
      +------------------------------------------------------------------+
      """;
    
    private static final String ENTREE_INCORECTE
    = """           
      +------------------------------+
            
            Entree incorecte !
              
      +------------------------------+
      """;
    
    private static final String AFFICHEGE_LABY_DEFAULT
    = """           
      +--------------------------------------------------------+
            
            Pas de labyrinthe charge, voici celui par default
              
      +--------------------------------------------------------+
      """;
    
    /**                                                  
     * La boucle de jeux.                                
     * Demande au joueur quel déplacement veut-il réaliser.
     * Propose également au joueur de quitter le jeux pour retourner au menu.
     * @param partie partie en cours
     */
    private static void boucleJeux(Jeux partie) {
        int nombreCoup ;
        boolean sortiAtteinte;
        boolean quitter;
        boolean retourAuDebut;
        String choix;
        sortiAtteinte = quitter = false;

        System.out.println(INFO_JEUX);
        System.out.println(partie);
        nombreCoup = 0;
        
        do {
            
            System.out.print("Entrez votre déplacement : ");
            choix = analyseurChoix.next();
//            analyseurChoix.nextLine();
            
            //TODO Faire mieux
            quitter = (choix.toUpperCase().contains(CHOIX_ABANDONNER_PARTIE+""));
            retourAuDebut = choix.toUpperCase().contains(RESET+"");
            
            if (!quitter) {
                if (!retourAuDebut) {                
                    for (int i = 0; i < choix.length(); i++) {
                        try {
                            partie.bougerJoueur(choix.charAt(i));
                            nombreCoup ++ ;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println(String.format(ERREUR_COMMANDE, 
                                    Main.HAUT,Main.BAS,Main.DROITE,Main.GAUCHE, 
                                    Main.CHOIX_ABANDONNER_PARTIE, Main.RESET));
                        }
                    }
                } else {
                    partie.joueurAuDebut();
                }
            }
            
            sortiAtteinte = partie.estSorti();
            System.out.println(partie);
            
        } while (!sortiAtteinte && !quitter);
        
        if (sortiAtteinte) {
            System.out.printf(MESSAGE_VICTOIRE_NOMBRE_COUP, nombreCoup,
                              partie.parcoursProfondeur().length()-1);
        } else {
            System.out.println(ABANDON_PARTIE);
        }
    }
    
    /**
     * Demande à l'utilisateur les dimensions du labyrinthe qu'il veut.
     * @return un tableau de deux entier dont le premier est la hauteur
     *         et le deuxieme la largeur 
     * 
     */
    private static int[] choixDimensions() {

        boolean hauteurValide ,
                largeurValide;
        
        int [] dimensionChoisi;
        analyseurChoix = new Scanner(System.in);
        hauteurValide = largeurValide = false ;
        do {
            System.out.println();
            dimensionChoisi = new int[2];
            
            //TODO Extraire méthode
            System.out.print("Entrez la hauteur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                dimensionChoisi[0] = analyseurChoix.nextInt();
                hauteurValide = 1 < dimensionChoisi[0];
            } else {
                hauteurValide = false;
            }
            if (hauteurValide) {
                System.out.println("Hauteur choisi : " + dimensionChoisi[0]);                
            } else {
                System.out.println("Erreur : Hauteur invalide !");
            }
            
            analyseurChoix.nextLine();
            
            System.out.print("Entrez la largeur souhaité : ");
            if (analyseurChoix.hasNextInt()) {
                dimensionChoisi[1] = analyseurChoix.nextInt();
                largeurValide = 1 < dimensionChoisi[1];
                System.out.println("Largeur choisi : " + dimensionChoisi[1]);
            } else {
                System.out.println("Erreur : Largeur invalide !");
                largeurValide = false;
            }
            analyseurChoix.nextLine();
            
        } while (!hauteurValide || !largeurValide);
        System.out.println("Choix fait");
        return dimensionChoisi;                          
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
        //                                         haut  droite  Bas  Gauche
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
     * Lancement du menu
     * @param non utilisé      
     */
    public static void main(String[] args) {
        char choix;
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
            if (!analyseurChoix.hasNext()) {
                analyseurChoix.nextLine();
            } else {
                choix = Character.toUpperCase(analyseurChoix.next().charAt(0));
                
                switch (choix) {
                case CHOIX_AFFICHER: {
                    if (labyrintheConstruit) {
                        System.out.println(partie);
                    } else {
                    	
                    	System.out.println(AFFICHEGE_LABY_DEFAULT);
                        System.out.println(labyrintheParDefaut);
                    }
                    break;
                }
                case CHOIX_DIMENSION: {
                    dimensionLabyrinthe = choixDimensions();
                    System.out.println(  "Hauteur : " + dimensionLabyrinthe[0]  
                                       + "\nLargeur : " + dimensionLabyrinthe[1]);
                    partie = null;
                    labyrintheConstruit = false;
                    break;
                }
                case CHOIX_CHAINE_ASCENDANTE: {
                    partie = new Jeux(dimensionLabyrinthe[0]
                    		          , dimensionLabyrinthe[1]);
                    partie.chaineAscendante();
                    labyrintheConstruit = true;
                    break;
                }
                
                case CHOIX_BACKTRACKING: {
                    partie = new Jeux(dimensionLabyrinthe[0]
                    		          , dimensionLabyrinthe[1]);
                    partie.constructionBacktracking();
                    labyrintheConstruit = true;
                    break;
                }
                case CHOIX_POSITION_ENTREE_SORTIE: {
                    definitionEntreSortie(dimensionLabyrinthe, partie);
                    
                    System.out.println("l'entree et la sortie ont bien ete modifiee");
                    break;
                }
                case CHOIX_JOUER: {
                    if (!labyrintheConstruit) {
                        System.out.println(  "Aucun graphe n'a été construit."
                        		           + " Nous prenons celui par défaut");
                        partie = labyrintheParDefaut;
                        boucleJeux(partie);
                    } else {
                        boucleJeux(partie);
                    }
                    break;
                }
                case CHOIX_REPONSE: {
                    System.out.println("Affichage Reponse");
                    if (!labyrintheConstruit) {
                        System.out.println(  "Aucun graphe n'a été construit."
                        		          + " Nous prenons celui par défaut");
                        partie = labyrintheParDefaut;
                        System.out.println(partie.parcoursProfondeur());
                        System.out.println(partie.toStringSolution());
                    } else {
                        System.out.println(  "Voici le parcours a faire "
                        		           + "pour trouver la sortie");
                        System.out.println(partie.parcoursProfondeur());
                        System.out.println(partie.toStringSolution());
                    }
                    break;
                }
                
                case CHOIX_SAUVEGARDER: {
                    if (labyrintheConstruit) {
                        LabyrintheJson.enregistrerLabyrinthe(partie);
                    } else {
                        System.err.println(ERREUR_LABYRINTHE_PAS_CONSTRUIT);
                    }
                    break;
                }
                case CHOIX_CHARGER: {
                    partie = LabyrintheJson.chargerLabyrinthe();
                    labyrintheConstruit = true;
                    System.out.printf(LABYRINTHE_CHARGE, partie.getHauteur(), partie.getLargeur());

                    break;
                }
                

                case CHOIX_QUITTER: {
                    quitter = true ;
                    break;
                }
                default:
                    System.err.println(ENTREE_INCORECTE);
                    break;
                }
            }
        } while (!quitter);
        analyseurChoix.close();
    }

    /**
     * @param dimensionLabyrinthe
     * @param partie
     */
    private static void definitionEntreSortie(int[] dimensionLabyrinthe, Jeux partie) {
        Sommet nouvelleEntree = null;
        Sommet nouvelleSortie = null;
        
        do {
            System.out.println("Entrez la marque de l'entrée souhaitee ");
            if (analyseurChoix.hasNextInt()) {
                int[] coordonnee 
                = partie.getCoordoneeFromMarque(analyseurChoix.nextInt());
                
                nouvelleEntree 
                = partie.getListeSommet()[coordonnee[0]][coordonnee[1]];
                System.out.println(nouvelleEntree);
            } else {
               System.err.println("Veuillez entree un nombre !");
               analyseurChoix.nextLine();
            }
            System.out.println("Entrez la marque de la sortie souhaitee ");
            if (analyseurChoix.hasNextInt()) {
                int[] coordonnee 
                = partie.getCoordoneeFromMarque(analyseurChoix.nextInt());
                System.out.println(nouvelleSortie);
                nouvelleSortie 
                = partie.getListeSommet()[coordonnee[0]][coordonnee[1]];
            } else {
                System.err.println("Veuillez entree un nombre !");
                analyseurChoix.nextLine();
            }
            if (nouvelleEntree.equals(nouvelleSortie)) {
                System.out.println(  "l'entree et la sortie ne" 
                                   + "peuvent pas etre confondues ");
            }
        } while (nouvelleEntree.equals(nouvelleSortie));
        
        partie.setEntre(nouvelleEntree);
        partie.setSortie(nouvelleSortie);
        partie.joueurAuDebut();
    }
}
