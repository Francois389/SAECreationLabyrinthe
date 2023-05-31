/**
 * TestOutilsTableau.java                       30 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsTableau;
import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe TestOutilsTableau
 * @author francois
 *
 */
class TestOutilsTableau {

    /**
     * Test method for {@link outils.OutilsTableau#copieSaufNull(representation.Sommet[])}.
     */
    @Test
    void testCopieSaufNull() {
        Sommet[] tab1 = {null,new Sommet(0, 0),new Sommet(1, 0),null};
        Sommet[] attendue1 = {new Sommet(0, 0),new Sommet(1, 0)};
        assertArrayEquals(attendue1, OutilsTableau.copieSaufNull(tab1));
        Sommet[] tab2 = {null,null};
        Sommet[] attendue2 = {};
        assertArrayEquals(attendue2, OutilsTableau.copieSaufNull(tab2));
        
    }

}
