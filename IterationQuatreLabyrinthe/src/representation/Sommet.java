/* 
 * Sommet.java				18 avril 2023
 * Iut de Rodez , pas de copyright
 */

package representation;

/**
 * Représentation d'un sommet d'un graphe
 * Un sommet a une position en x et y et des marques
 * @author Denamiel Clément 
 * @author Descriaud Lucas
 */
public class Sommet {
    
    /** coordonnée X de la salle dans le labyrinthe */
    private int posX;

    /** coordonnée Y de la salle dans le labyrinthe */
    private int posY;

    /** Contient les possibles marques du Sommet.  */
    private char[] marques;


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
        marques = new char[10];
    }

    /**
     * Vérifie que les coordonnées sont valides (pas de coordonnées dans les négatifs)
     * @param x position coordonnées sont positives x du sommet
     * @param y position y du sommet
     * @return true si les paramètres sont valides
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
        if (obj == null) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    public char[] getMarques() {
        return marques;
    }

    public void setMarques(char[] argMarques) throws IllegalArgumentException{
        if (argMarques.length != 10) {
            throw new IllegalArgumentException("Erreur : "
                    + "La liste doit être de longueur 10");
        }
        this.marques = argMarques;
    }

    public void setMarques(char marque, int indice) {
        if (indice < 0 || 9 < indice) {
            throw new IllegalArgumentException("Erreur : l'indice doit être compris entre 0 et 10");
        }
        this.marques[indice] = marque;
    }
}
