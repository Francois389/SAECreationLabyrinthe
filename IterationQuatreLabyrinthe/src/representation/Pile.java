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
     * @throws NullPointerException
     * @return la pile modifiee
     */
    public Pile empiler(Object element) {
    	// System.out.println("element ajoute : " + element);
        if (element == null) {
            throw new NullPointerException("Erreur  : "
                    + "On ne peut pas empiler un element null");
        }
        Object[] nouvellePile = new Object[elementsEmpiles.length + 1];
        
        for (int i = 0; i < elementsEmpiles.length; i++) {
            nouvellePile[i] = elementsEmpiles[i];
        }
        nouvellePile[elementsEmpiles.length] = element;
        elementsEmpiles = nouvellePile;
 
        return this;
    }

    /**
     * retire le dernier element de la pile
     * @return la pile modifiee
     * @throws PileVideException
     */
    public Pile depiler() {
        if (estVide()) {
            throw new PileVideException(
                "on ne peut pas depiler une pile vide"
            );
        }

        Object[] nouvellePile = new Object[elementsEmpiles.length - 1];

        for (int i = 0; i < nouvellePile.length; i++) {
            nouvellePile[i] = elementsEmpiles[i];
        }
        elementsEmpiles = nouvellePile;
        
        return this;
    }

    /**
     * méthode permettant de récupérer le sommet de la pile
     * si la pile est vide, 
     * @return le sommet de la pile
     * @throws PileVideException
     */
    public Object sommet() {
        if (estVide()) {
            throw new PileVideException(
                "une pile vide n'a pas de sommet'"
            );
        }
        return elementsEmpiles[elementsEmpiles.length-1];
    }
	
    @Override
    public String toString() {
        String resultat = "|               | <- haut de la pile\n";

        String ligne = "|%s\t\t|\n";

        for (int i = 0; i < elementsEmpiles.length; i++) {
            resultat += String.format(ligne, elementsEmpiles[i].toString());
        }

        resultat += "+---------------+";

        return resultat;
        
    }
}
