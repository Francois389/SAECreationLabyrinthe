/**
 * OutilsListe.java                       11 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;

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
        for (int i = 0; i < liste.length; i++) {
            if (liste[i] == element) {
                return true;
            }
        }
        return false;
    }
}
