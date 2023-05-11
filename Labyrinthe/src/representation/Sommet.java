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

    /** liste des voisins du sommet */
    private List<Sommet> listeDesVoisins;


    /**
     * Constructeur de la classe sommet
     * //TODO Expliquer la validité d'un Sommet
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
        listeDesVoisins = new ArrayList<Sommet>(10);
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
     * permet d'ajouter un sommet dans la liste des voisins du sommet courant
     * @param nouveauVoisin le voisin à ajouter dans la liste des voisins
     * @throws IllegalArgumentException si le sommmet est deja présent dans la liste
     * @return true si l'element a bien ete ajoute dans la liste des voisins
     */
    public void ajouterSommetVoisin(Sommet nouveauVoisin) {
        for (int i = 0; i < listeDesVoisins.size(); i++) {
            if (nouveauVoisin.equals(listeDesVoisins.get(i))) {
                throw new IllegalArgumentException("Le sommet existe deja dans la liste");
            }
        }
        listeDesVoisins.add(nouveauVoisin);
    }
    
    /**
     * permet de supprimer un sommet dans la liste des voisins du sommet courant
     * @param voisinASupprimer le voisin à supprimer dans la liste des voisins
     */    
    public void supprimerSommetVoisin(Sommet voisinASupprimer) {
        boolean sommetTrouve;
        
        sommetTrouve = false;
        for (int i = 0; i < listeDesVoisins.size() && !sommetTrouve; i++) {
            if (voisinASupprimer.equals(listeDesVoisins.get(i))) {
                sommetTrouve = true;
                listeDesVoisins.remove(i);
            }
        }
        
        if (!sommetTrouve) {
            throw new IllegalArgumentException(
                "le sommet a supprimer n'est pas dans les voisins'");
        }
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
    
    /**
     * 
     * @return la liste des voisins de ce sommet
     */
    public List<Sommet> getListeDesVoisins() {
        return listeDesVoisins;
    }

    @Override
    public String toString() {
        return "(" + posX + "; " + posY + ")";
    }

    @Override
    public int hashCode() {
        return posX * 100 + posY * 10 + listeDesVoisins.size(); 
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
