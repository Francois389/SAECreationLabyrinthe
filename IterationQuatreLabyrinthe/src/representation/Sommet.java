/* 
 * Sommet.java				18 avril 2023
 * Iut de Rodez , pas de copyright
 */

package representation;

/**
 * Représentation d'un sommet d'un graphe
 * Un sommet a une position en x et y et des marque
 * @author Denamiel Clément 
 * @author Descriaud Lucas
 */
/**
 * //TODO Commenter la responsabilités de la classe Sommet
 * @author francois
 *
 */
public class Sommet {
    
    /** coordonnée X de la salle dans le labyrinthe */
    private int posX;

    /** coordonnée Y de la salle dans le labyrinthe */
    private int posY;

    /** Contient les possibles marque du Sommet.  */
    private int marque;
    
    /** 
     * Indique les voisins du sommet. 
     * Commence par le voisin du haut puis continue dans le sens horaire
     */
    private boolean[] voisins;


    /**
     * Constructeur de la classe sommet
     * Un sommet est valide si ses coordonnées sont dans les entier naturels
     * les paramètres x et y ne peuvent donc pas être négatifs
     * @param x position y du sommet
     * @param y position y du sommet
     * @throws IllegalArgumentException si les arguments sont invalides
     */
    public Sommet (int x, int y, int m) {
        super();
        if (!coordonneesValides(x, y)) {
            throw new IllegalArgumentException();
        }
        posX = x;
        posY = y;
        marque = m;
        voisins = new boolean[4]; // par defaut a false
    }
    
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
        voisins = new boolean[4]; // par defaut a false
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
     * @return la position x du sommet
     */
    public int getPosX() {
        return posX;
    }
    
    /**  
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
	
	/**
	 * @return la marque du sommet
	 */
    public int getMarque() {
        return marque;
    }
	
	/**
	 * attribut a un sommet une marque
	 */
    public void setMarque(int argMarque) {
        this.marque = argMarque;
    }

	/**
	 * getter de l'atrbut voisins
	 * @return liste des voisins
	 */
	public boolean[] getVoisins() {
        return this.voisins;
    }
    
    
    /**
     * @param voisins la nouvelle liste a attribuer
     */
    public void setVoisin(boolean[] voisins) {
        this.voisins = voisins ; 
    }

	/**
	 *
	 */
    public void setVoisin(boolean voisin, int indice) {
        if (indice < 0 || voisins.length <= indice) {
            throw new IllegalArgumentException("Erreur : Indice ");
        }
        this.voisins[indice] = voisin ; 
    }
}
