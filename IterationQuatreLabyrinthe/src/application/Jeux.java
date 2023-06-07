/**
 * Jeux.java                       31 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import representation.Labyrinthe;
import representation.PileContigue;
import representation.Sommet;

/**
 * Gère la présence d'un joueur dans un labyrinthe
 * @author de Saint Palais François
 */
public class Jeux extends Labyrinthe {

    private static final String MOUVEMENT_IMPOSSIBLE 
    = """
              --------
         Mouvement impossible
              --------
      """;

    /** La position en X du joueur dans le labyrinthe */
    private int posXJoueur;
    
    /** La position en Y du joueur dans le labyrinthe */
    private int posYJoueur;
    
    public Jeux(int hauteur, int largeur) {
        super(hauteur, largeur);
        posXJoueur = this.getEntre().getPosX();
        posYJoueur = this.getEntre().getPosY();
    }

    /**
     * Constructeur utilisé lors du chargement d'un Json
     * @param hauteur La hauteur du labyrinthe
     * @param largeur La largeur du labyrinthe
     * @param listeSommet La liste des Sommet qui compose le labyrinthe
     * @param listeArrete La liste des arcs qui compose le labyrinthe
     * @param entree Le Sommet qui marque l'entrée du labyrinthe
     * @param sortie Le Sommet qui marque la sortie du labyrinthe
     * @param x La position en X du joueur
     * @param y La position en Y du joueur
     */
    public Jeux(int hauteur, int largeur, Sommet[][] listeSommet, 
                Sommet[][] listeArrete, Sommet entree,
                Sommet sortie, int x, int y) {
        super(hauteur,largeur,listeSommet, listeArrete, entree, sortie);
        posXJoueur = x;
        posYJoueur = y;
    }

    /**
     * @return la valeur de posXJoueur
     */
    public int getPosXJoueur() {
        return posXJoueur;
    }

    /**
     * @return la valeur de posYJoueur
     */
    public int getPosYJoueur() {
        return posYJoueur;
    }

    /**
     * @param x modifie la valeur de posXJoueur
     */
    public void setPosXJoueur(int x) {
        if (x < 0 || getHauteur() <= x) {
            throw new IllegalArgumentException("Erreur : La coordonnée saisie "
                    + "est en dehors du labyrinthe");
        }
        this.posXJoueur = x;
    }


    /**
     * @param y modifie la valeur de posYJoueur
     */
    public void setPosYJoueur(int y) {
        if (y < 0 || getHauteur() <= y) {
            throw new IllegalArgumentException("Erreur : La coordonnée saisie "
                    + "est en dehors du labyrinthe");
        }
        this.posYJoueur = y;
    }

    /**
     * Indique si le joueur est sorti du labyrinthe
     * @return true si le joueur est à la sorti false sinon
     */
    public boolean estSorti() {
        return    getSortie().getPosX() == posXJoueur 
               && getSortie().getPosY() == posYJoueur;
    }
    
    /**
     * Vérifie si le mouvement joueur par le joueur est valide
     * S'il y a un mur, c'est invalide.
     * @param mouvement un char qui definit le prochaine mouvenement du joueur
     * @return true si un deplacement est possible dans la direction mouvement,
     *         false sinon
     * @throws IllegalArgumentException si mouvement n'est pas un de ses quatre
     *         caractere : Z haut , S bas , Q gauche , D droite 
     * 
     */
    public boolean deplacementValide(char mouvement) {
        mouvement = Character.toUpperCase(mouvement);
        
        switch (mouvement) {
        case Main.HAUT: {
            return getListeSommet()[posYJoueur][posXJoueur].getVoisins()[0];
        }
        case Main.DROITE: {
            return getListeSommet()[posYJoueur][posXJoueur].getVoisins()[1];
        }
        case Main.BAS: {
            return getListeSommet()[posYJoueur][posXJoueur].getVoisins()[2];
        }
        case Main.GAUCHE: {
            return getListeSommet()[posYJoueur][posXJoueur].getVoisins()[3];
        }
        default:
            throw new IllegalArgumentException(String.
                      format("Erreur : %c n'est pas un mouvement valide",
                      mouvement));
        }
    }
    
    /**
     * Bouge le joueur dans la direction indiqué, d'une case
     * @param mouvement un char qui definit le prochaine mouvenement du joueur
     */
    public void bougerJoueur(char mouvement) {
        mouvement = Character.toUpperCase(mouvement);
        if (deplacementValide(mouvement)) {
            switch (mouvement) {
            case Main.HAUT: {
                setPosYJoueur(getPosYJoueur() - 1);
                break;
            }
            case Main.BAS: {
                setPosYJoueur(getPosYJoueur() + 1);
                break;
            }
            case Main.DROITE: {
                setPosXJoueur(getPosXJoueur() + 1);
                break;
            }
            case Main.GAUCHE: {
                setPosXJoueur(getPosXJoueur() - 1);
                break;
            }
            
            default : {
                System.err.println(mouvement + " lettre invalide");
                break;
            }
            }
        } else {
            System.out.println(MOUVEMENT_IMPOSSIBLE);
        }
    }
    
    /**
     * Met le joueur à l'entrée du labyrinthe    
     */
    public void joueurAuDebut() {
        posXJoueur = getEntre().getPosX();
        posYJoueur = getEntre().getPosY();
    }
    
    @Override
    public String toString() {
        String affichage;
        affichage = "";
        this.setMarqueSommet();
        for (int hauteur = 0 ; hauteur < this.getHauteur() ; hauteur++){ 
            for (int j = 0 ; j < this.getLargeur() ; j++ ) {
                if (this.getListeSommet()[hauteur][j].getVoisins()[HAUT]) {
                    affichage += HAUT_CASE_VIDE; 
                }
                else {
                    affichage += HAUT_CASE;
                }  
                if (j == this.getLargeur() -1 ) {
                    affichage += "+";
                }
            }    
            for (int i = 0; i < HAUTEUR_CASE ; i++) {
                affichage += "\n";        
                   for (int j = 0; j < this.getLargeur(); j++) {
                        if (this.getListeSommet()[hauteur][j].getVoisins()[GAUCHE]) {
                            affichage += BORD_VIDE; 
                        } else {
                            affichage += BORD_CASE;
                        }
                        
                        /* Affichage de l'entrée, de la sortie 
                         * ou de la marque du sommet */
                        if (   this.getListeSommet()[hauteur][j].getPosX()
                        		== posXJoueur 
                            && this.getListeSommet()[hauteur][j].getPosY()
                                == posYJoueur && i == 1) {
                         affichage += "  J  ";
                        } else if (this.getListeSommet()[hauteur][j]
                        		       .equals(entree) && i == 1) {
                            affichage += "  E  ";
                        } else if (this.getListeSommet()[hauteur][j]
                        		       .equals(sortie) && i == 1) {
                            affichage += "  S  ";
                        } else {
                            if (i == 1) {
                                if (getListeSommet()[hauteur][j]
                                    .getMarque() < 10) {
                                    affichage += String.format(
                                    		     CHAINE_VIDE_MARQUE_UNITE
                                    		     , getListeSommet()[hauteur][j]
                                    		       .getMarque());
                                    
                                } else if (getListeSommet()[hauteur][j]
                                		   .getMarque() < 100) {
                                    affichage += String.format(
                                    		     CHAINE_VIDE_MARQUE_DIZAINE
                                    		     , getListeSommet()[hauteur][j]
                                    		       .getMarque());
                                    
                                } else if (getListeSommet()[hauteur][j]
                                		   .getMarque() < 1000) {
                                    affichage += String.format(
                                    		     CHAINE_VIDE_MARQUE_CENTAINE
                                    		     , getListeSommet()[hauteur][j]
                                    		       .getMarque());
                                } else {
                                    affichage += CHAINE_VIDE;
                                }
                            } else {
                                affichage += CHAINE_VIDE;
                            }
                            
                        }
                        
                        if (j == this.getLargeur() -1 ) {
                            affichage.substring(0, affichage.length() - 1);
                            affichage += BORD_CASE;
                        }
                }
            }
            affichage += "\n";
            if (hauteur == this.getHauteur() -1 ) {
                for (int j = 0 ; j < this.getLargeur() ; j++ ) {
                    affichage += HAUT_CASE;
                    if (j == this.getLargeur() -1 ) {
                        affichage += "+";
                    }
                }
            }
            
            
            
        }
        return affichage + "\n";
    }
    
    
    public String toStringSolution() {
        String affichage;
        affichage = "";
        
        PileContigue pileSolution = this.parcoursProfondeur();
        
        Sommet[] solution = new Sommet[pileSolution.length()];
        System.out.println(pileSolution.length());
        int indice = 0;
        do {
			solution[indice] = (Sommet) pileSolution.sommet();
			pileSolution.depiler();
			indice ++;
		} while (!pileSolution.estVide());
        
        
        for (int i = 0 ; i < this.getListeSommet().length ; i++){ 
            for (int j = 0 ; j < this.getListeSommet()[i].length ; j++ ) {
            	this.getListeSommet()[i][j].setMarque(0);    		
            	for (int k = 0; k < solution.length; k++) {
                    if (this.getListeSommet()[i][j].equals(solution[k])) {
                    	this.getListeSommet()[i][j].setMarque(1);
                    }
                }
            }
       }
        
        for (int hauteur = 0 ; hauteur < this.getHauteur() ; hauteur++){ 
            for (int j = 0 ; j < this.getLargeur() ; j++ ) {
                if (this.getListeSommet()[hauteur][j].getVoisins()[HAUT]) {
                    affichage += HAUT_CASE_VIDE; 
                }
                else {
                    affichage += HAUT_CASE;
                }  
                if (j == this.getLargeur() -1 ) {
                    affichage += "+";
                }
            }    
            for (int i = 0; i < HAUTEUR_CASE ; i++) {
                affichage += "\n";        
                   for (int j = 0; j < this.getLargeur(); j++) {
                        if (this.getListeSommet()[hauteur][j]
                        		.getVoisins()[GAUCHE]) {
                            affichage += BORD_VIDE; 
                        } else {
                            affichage += BORD_CASE;
                        }
                        
                        if (this.getListeSommet()[hauteur][j].equals(entree) 
                            && i == 1) {
                            affichage += "  E  ";
                        } else if (this.getListeSommet()[hauteur][j]
                        		       .equals(sortie) && i == 1) {
                            affichage += "  S  ";
                        } else {
                            if (i == 1) {
                                if (getListeSommet()[hauteur][j]
                                    .getMarque() == 0) {
                                    affichage += "     ";
                                } else if (getListeSommet()[hauteur][j]
                                		   .getMarque() < 100) {
                                    affichage += "  X  ";
                                }
                            } else {
                                affichage += CHAINE_VIDE;
                            }
                            
                        }
                        
                        if (j == this.getLargeur() -1 ) {
                            affichage.substring(0, affichage.length() - 1);
                            affichage += BORD_CASE;
                        }
                }
            }
            affichage += "\n";
            if (hauteur == this.getHauteur() -1 ) {
                for (int j = 0 ; j < this.getLargeur() ; j++ ) {
                    affichage += HAUT_CASE;
                    if (j == this.getLargeur() -1 ) {
                        affichage += "+";
                    }
                }
            }
            
            
            
        }
        return affichage + "\n";
    }
}
