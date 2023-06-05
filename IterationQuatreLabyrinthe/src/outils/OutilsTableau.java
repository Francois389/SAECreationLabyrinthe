/**
 * OutilsListe.java                       11 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;

import representation.Sommet;

/**
 * Méthode utilitaire sur les tableau 
 * @author de Saint Palais François
 * @author Costes Quentin
 * @author Descriaud Luas
 *
 */
public class OutilsTableau {

    /**
     * Verifie l'appartenance d'un entier dans un tableau
     * @param liste la liste a parcourir
     * @param element l'entier recherché
     * @return True si element est dans liste.
     *         False sinon.
     */
    public static boolean contient(int[] liste, int element) {
        if (liste == null) {
            return false;
        }
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == element) {
                return true;
            }
        }
        return false;
    }
    /**
     * Verifie l'appartenance d'un sommet dans un tableau
     * @param liste liste la liste a parcourir
     * @param element le Sommet recherché
     * @return True si element est dans liste.
     *         False sinon.
     */
    public static boolean contient(Sommet[] liste, Sommet element) {
        if (liste == null) {
            return false;
        }
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == null) {
                return false;
            } else if (liste[i].equals(element) ) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Creer une String avec les element d'un tableau 
     * @param liste Le tableau dont ont veux obtenir la représention
     *              Le tableau peut contenir n'importe quel objet
     * @return la représentation humaine du tableau passer en paramètre.
     */
    public static String tabVersString(Object[] liste) {
        String resulat = "[";
        for (int i = 0; i < liste.length - 1; i++) {
            resulat += liste[i] + ", ";
        }
        return resulat + liste[liste.length - 1] + "]";
    }
    
    /**
     * Creer une String avec les element d'un tableau 
     * @param liste Le tableau dont ont veux obtenir la représention
     *              Le tableau ne contient que des boolean
     * @return la représentation humaine du tableau passer en paramètre.
     */
    public static String tabVersString(boolean[] liste) {
        String resulat = "[";
        for (int i = 0; i < liste.length - 1; i++) {
            resulat += liste[i] + ", ";
        }
        return resulat + liste[liste.length - 1] + "]";
    }
    
    /**
     * Retire les element nulles dans un tableau
     * @param tab le tableau dont on souhaite retirer les element null
     * @return une copie de tab sans les element null
     */
    public static Sommet[] copieSaufNull(Sommet[] tab) {
       
       	
       	int taille = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                taille++;
            }
        }
        
        Sommet[] nouveauTableau = new Sommet[taille];
        int pointeur = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                nouveauTableau[pointeur] = tab[i];
                pointeur++;
            }
        }
        return nouveauTableau;
    }
}

