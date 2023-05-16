/*
 * Pile.java            12/05/2023
 * IUT de Rodes
 */

package representation;

/**
 * classe permettant de représenter une pile
 * @author clement denamiel
 */
public class Pile {

    /** tableau avec tous les elements */
    private Object[] elementsEmpiles = {};

    /**
     * constructeur d'une nouvelle pile vide
     */
    public Pile() {}

    /**
     * Constructeur d'une nouvelle a partir d'un tableau d'objet
     */
    private Pile(Object[] pileExistante) {
        elementsEmpiles = pileExistante;
    }
    /**
     * permet de vérifier si la pile est vide
     * 
     * @return true si la pile est vide
     *         false sinon
     */
    public boolean estVide() {
        return elementsEmpiles.length == 0; // stub
    }

    /**
     * Ajoute un element de type object a une pile
     * @param element l'element a ajouter
     * @return la pile modifiee
     */
    public Pile empiler(Object element) {
        Object[] nouvellePile = new Object[elementsEmpiles.length + 1];
        nouvellePile[elementsEmpiles.length] = element;
 
        return new Pile(nouvellePile);
    }

    /**
     * retire le dernier element de la pile
     * @return la pile modifiee
     * @throws IllegalArgumentException
     */
    public Pile depiler() {
        if (estVide()) {
            throw new IllegalArgumentException(
                "on ne peut pas depiler une pile vide"
            );
        }

        Object[] nouvellePile = new Object[elementsEmpiles.length - 1];

        for (int i = 0; i < nouvellePile.length; i++) {
            nouvellePile[i] = elementsEmpiles[i];
        }
        return new Pile(nouvellePile);
    }
	
}
