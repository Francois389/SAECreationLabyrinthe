/**
 * Jeux.java                       31 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package application;

import representation.Labyrinthe;
import representation.Sommet;

/**
 * Gère la présence d'un joueur dans un labyrinthe
 * @author de Saint Palais François
 */
public class Jeux extends Labyrinthe {

    /** La position en X du joueur dans le labyrinthe */
    private int posXJoueur;
    
    /** La position en Y du joueur dans le labyrinthe */
    private int posYJoueur;
    
    public Jeux(int hauteur, int largeur) {
        super(hauteur, largeur);
        // TODO Auto-generated constructor stub
        posXJoueur = this.getEntre().getPosX();
        posYJoueur = this.getEntre().getPosY();
    }

    /**
     * Constructeur utilisé lors du chargement d'un Json
     * @param hauteur
     * @param largeur
     * @param listeSommet
     * @param listeArrete
     * @param entree
     * @param sortie
     * @param x
     * @param y
     */
    public Jeux(int hauteur, int largeur, Sommet[][] listeSommet, Sommet[][] listeArrete, Sommet entree,
            Sommet sortie, int x, int y) {
        super(hauteur,largeur,listeSommet, listeArrete, entree, sortie);
        // TODO Auto-generated constructor stub
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
     * @param posXJoueur modifie la valeur de posXJoueur
     */
    public void setPosXJoueur(int posXJoueur) {
        this.posXJoueur = posXJoueur;
    }


    /**
     * @param posYJoueur modifie la valeur de posYJoueur
     */
    public void setPosYJoueur(int posYJoueur) {
        this.posYJoueur = posYJoueur;
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
     * @param mouvement Une lettre parrmi H, D, B et G
     * @return
     */
    public boolean deplacementValide(char mouvement) {
        if (!"HDBG".contains(mouvement + "")) {
            throw new IllegalArgumentException(
                    String.format("Erreur : %c n'est pas un mouvement valide", 
                                  mouvement)
            );
        }
        //TODO Écrire le corps
        return false;
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
                        //TODO ajouter le joueur
                        if (this.getListeSommet()[hauteur][j].equals(entree) && i == 1) {
                            affichage += "  E  ";
                        } else if (this.getListeSommet()[hauteur][j].equals(sortie) && i == 1) {
                            affichage += "  S  ";
                        } else {
                            if (i == 1) {
                                if (getListeSommet()[hauteur][j].getMarque() < 10) {
                                    affichage += String.format(CHAINE_VIDE_MARQUE_UNITE, getListeSommet()[hauteur][j].getMarque());
                                } else if (getListeSommet()[hauteur][j].getMarque() < 100) {
                                    affichage += String.format(CHAINE_VIDE_MARQUE_DIZAINE, getListeSommet()[hauteur][j].getMarque());
                                } else if (getListeSommet()[hauteur][j].getMarque() < 1000) {
                                    affichage += String.format(CHAINE_VIDE_MARQUE_CENTAINE, getListeSommet()[hauteur][j].getMarque());
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
}
