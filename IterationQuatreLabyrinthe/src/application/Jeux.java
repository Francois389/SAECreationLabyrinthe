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
}
