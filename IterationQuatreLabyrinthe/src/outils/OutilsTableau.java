/**
 * OutilsListe.java                       11 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package outils;

import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe OutilsListe
 * @author de Saint Palais François
 *
 */
public class OutilsTableau {

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
    
    
    /**
     * Retourne la représentation humaine du tableau passer en paramètre.
     * @param liste Le tableau dont ont veux obtenir la représention
     * @return
     */
    public static String tabVersString(Object[] liste) {
        String resulat = "[";
        for (int i = 0; i < liste.length - 1; i++) {
            resulat += liste[i] + ", ";
        }
        return resulat + liste[liste.length - 1] + "]";
    }
    
    /**
     * Retourne la représentation humaine du tableau passer en paramètre.
     * @param liste Le tableau dont ont veux obtenir la représention
     * @return
     */
    public static String tabVersString(boolean[] liste) {
        String resulat = "[";
        for (int i = 0; i < liste.length - 1; i++) {
            resulat += liste[i] + ", ";
        }
        return resulat + liste[liste.length - 1] + "]";
    }
    
    /**
     * Renvoie un nouveau tableau en aillant retirer 
     * l'élement à l'inice non voulue
     * @param tab
     * @return
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

