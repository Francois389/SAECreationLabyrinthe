/**
 * Jeux.java                       31 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package representation;

/**
 * Gère la présence d'un joueur dans un labyrinthe
 * @author de Saint Palais François
 */
public class Jeux extends Labyrinthe {

    //TODO position joueur
    /** La position en X du joueur dans le labyrinthe */
    private int posXJoueur;
    
    /** La position en Y du joueur dans le labyrinthe */
    private int posYJoueur;
    
    public Jeux(int hauteur, int largeur) {
        super(hauteur, largeur);
        // TODO Auto-generated constructor stub
        setPosXJoueur(this.getEntre().getPosX());
        setPosYJoueur(this.getEntre().getPosY());
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
}