/*
 * PileContigue.java            12/05/2023
 * IUT de Rodes
 */

package representation;

/**
 * classe permettant de représenter une pile
 * @author clement denamiel
 */
public class PileContigue {

    /** tableau avec tous les elements */
    private Object[] elementsEmpiles = new Object[10];
    
    /** nombre d'elements dans la pile */
    private int nbElements = 0;
    
    
    /**
     * constructeur d'une nouvelle pile vide
     */
    public PileContigue() {}


    /**
     * permet de vérifier si la pile est vide
     * 
     * @return true si la pile est vide
     *         false sinon
     */
    public boolean estVide() {
        return nbElements == 0;
    }

    
    /**
     * transvase les elements empiles dans un 
     * nouveau tableau d'objet de la taille en parametre
     * @param taille la taille du nouveau tableau
     * @return nouveauTableau le tableau avec les anciens 
     * 		   elements et de bonne taille
     */
    private Object[] augmenterTableau(int taille) {
		Object[] nouveauTableau = new Object[taille];
		
		for (int i = 0; i < elementsEmpiles.length; i++) {
			nouveauTableau[i] = elementsEmpiles[i];
		}
		
		return nouveauTableau;
	}
    
    /**
     * permet de copier les elements actuels dans un tableau de 
     * taille inferieure
     * @param taille la nouvelle taille du tableau
     * @return le tableau de taille reduite
     */
    private Object[] reduireTableau(int taille) {
    	Object[] nouveauTableau = new Object[taille];
    	
    	for (int i = 0; i < taille; i++) {
			nouveauTableau[i] = elementsEmpiles[i];
		}
    	
    	return nouveauTableau;
    }
    
    /**
     * permet de verifier si une pile est pleine
     * comme la pile contient des valeurs null par defaut
     * et qu'on ne peut pas empiler null, si le dernier element
     * du tableau n'est pas null alors la pile est pleine
     * @return
     */
    private boolean estPleine() {
    	return elementsEmpiles.length == nbElements;
    }
    
    /**
     * Ajoute un element de type object a une pile
     * l'element ne doit pas etre null
     * @param element l'element a ajouter
     * @return la pile modifiee
     * @throws NullPointerException
     */
    public PileContigue empiler(Object element) {
    	if (element == null) {
    		throw new NullPointerException(
    			"Un element null ne peut etre empile"
			);
    	}
    	
    	if (estPleine()) {
        	elementsEmpiles = augmenterTableau(elementsEmpiles.length + nbElements);
        }
        
    	elementsEmpiles[nbElements] = element;
    	
        nbElements++;
        
        return this;
    }

    
    /**
     * retire le dernier element de la pile
     * @return la pile modifiée
     * @throws PileVideException si la pile est vide
     */
    public PileContigue depiler() {
        if (estVide()) {
            throw new PileVideException(
                "On ne peut pas depiler une pile vide"
            );
        }
        
        elementsEmpiles[nbElements - 1] = null;
        
        nbElements--;

        if (elementsEmpiles.length - nbElements >= elementsEmpiles.length / 2) {
        	elementsEmpiles = reduireTableau(nbElements);
        }
        
        return this;
    }

    /**
     * méthode permettant de récupérer le sommet de la pile
     * si la pile est vide, 
     * @return le sommet de la pile
     * @throws PileVideException si la pile est vide
     */
    public Object sommet() {
        if (estVide()) {
            throw new PileVideException(
                "Une pile vide n'a pas de sommet"
            );
        }
        return elementsEmpiles[nbElements-1];
    }
	
    
    @Override
    public String toString() {
        String resultat = "+-----------------------+ <- haut de la pile\n";

        String ligne = "|%s\t|\n";

        for (int i = 0; i < elementsEmpiles.length && elementsEmpiles[i] != null; i++) {
            resultat += String.format(ligne, elementsEmpiles[i].toString());
        }
      
        resultat += "+-----------------------+";

        return resultat;
        
    }
}
