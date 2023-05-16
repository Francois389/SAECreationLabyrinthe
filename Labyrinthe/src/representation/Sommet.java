/* 
 * Sommet.java				18 avril 2023
 * Iut de Rodez , pas de copyright
 */

package representation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Denamiel Clément 
 * @author Descriaud Lucas
 */
public class Sommet {
    
    /** coordonnée X de la salle dans le labyrinthe */
    private int posX;

    /** coordonnée Y de la salle dans le labyrinthe */
    private int posY;



    /**
     * Constructeur de la classe sommet
     * Un sommet est valide si ses coordonnées sont dans les entier naturels
     * les paramètres x et y ne peuvent donc pas être négatifs
     * @param x position y du sommet
     * @param y position y du sommet
     * @throws IllegalArgumentException si les arguments sont invalides
     */
    public Sommet (int x, int y) {
        super();

        if (!coordonneesValides(x, y)) {
            throw new IllegalArgumentException();
        }

        posX = x;
        posY = y;
    }

    /**
     * verifie que les coordonnées sont valides (pas de coordonnées dans les négatifs)
     * @param x positio, coordonnées sont positives x du sommet
     * @param y position y du sommet
     * @return true si les parametres sont valides
     */
    private boolean coordonneesValides(int x, int y) {
        return 0 <= x && 0 <= y;
    }

    /**
     * 
     * @return la position x du sommet
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * 
     * @return la position y du sommet
     */    
    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return "(" + posX + "; " + posY + ")";
    }

    @Override
    public int hashCode() {
        return posX * 100 + posY; 
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
