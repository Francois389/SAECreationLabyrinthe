/**
 * OutilsListe.java                       11 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;

import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe OutilsListe
 * @author francois
 *
 */
public class OutilsListe {

    /**
     * Renvoie true si element est dans liste.
     * False sinon.
     * @param liste
     * @param element
     * @return
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
     * Renvoie true si element est dans liste.
     * False sinon.
     * @param liste
     * @param element
     * @return
     */
    public static boolean contient(Sommet[] liste, Sommet element) {
        if (liste == null) {
            return false;
        }
        for (int i = 0; i < liste.length; i++) {
            if (liste[i].equals(element) ) {
                return true;
            }
        }
        return false;
    }
    
    
    public static String versString(Object[] liste) {
        String resulat = "[";
        for (int i = 0; i < liste.length - 1; i++) {
            resulat += liste[i] + ", ";
        }
        return resulat + liste[liste.length - 1] + "]";
    }
}
