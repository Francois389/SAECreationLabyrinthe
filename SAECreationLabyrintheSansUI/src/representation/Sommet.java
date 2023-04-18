package representation;

public class Sommet {
    
    /** coordonnée X de la salle dans le labyrinthe */
    private int posX;

    /** coordonnée Y de la salle dans le labyrinthe */
    private int posY;

    /** liste des voisins du sommet */
    private int[][] listeDesVoisins;

    public Sommet (int x, int y) {
        super();

        if (!coordonneesValides(x, y)) {
            throw new IllegalArgumentException();
        }

        posX = x;
        posY = y;
    }

    private boolean coordonneesValides(int x, int y) {
        return 0 >= x && 0 >= y;
    }
    
}
