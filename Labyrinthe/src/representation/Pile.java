/*
 * Pile.java            12/05/2023
 * IUT de Rodez
 */

package representation;

/**
 * classe permettant de représenter une pile
 * @author Clement Denamiel
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
        elementsEmpiles = new Object[pileExistante.length];

        for (int i = 0; i < pileExistante.length; i++) {
            elementsEmpiles[i] = pileExistante[i];
        }
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
    	// System.out.println("element ajoute : " + element);
        Object[] nouvellePile = new Object[elementsEmpiles.length + 1];
        
        for (int i = 0; i < elementsEmpiles.length; i++) {
            nouvellePile[i] = elementsEmpiles[i];
        }
        nouvellePile[elementsEmpiles.length] = element;
        elementsEmpiles = nouvellePile;
 
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
        elementsEmpiles = nouvellePile;
        
        return new Pile(nouvellePile);
    }

    /**
     * méthode permettant de récupérer le sommet de la pile
     * si la pile est vide, 
     * @return le sommet de la pile
     * @throws IllagelArgumentException
     */
    public Object sommet() {
        if (estVide()) {
            throw new IllegalArgumentException(
                "on ne peut pas depiler une pile vide"
            );
        }
        return elementsEmpiles[elementsEmpiles.length-1];
    }
	
    @Override
    public String toString() {
        String resultat = "|               | <- haut de la pile\n";

        String ligne = "|%s\t\t|\n";

        for (int i = 0; i < elementsEmpiles.length; i++) {
            resultat += ligne.format(ligne, elementsEmpiles[i].toString());
        	// System.out.println(elementsEmpiles[i]);
        }

        resultat += "+---------------+";

        return resultat;
        
    }
}
