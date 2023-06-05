/**
 * TestOutilsTableau.java                       5 juin 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import outils.OutilsTableau;
import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe TestOutilsTableau
 * @author FranÃ§ois
 *
 */
class TestOutilsTableau {

    /**
     * Test method for {@link outils.OutilsTableau#contient(int[], int)}.
     */
    @Test
    void testContientIntArrayInt() {
        int[] liste = new int[10];
        for (int i = 0; i < liste.length; i++) {
            liste[i] = i;
        }
        for (int i = 0; i < liste.length; i++) {
            assertTrue(OutilsTableau.contient(liste, i));
            assertFalse(OutilsTableau.contient(liste, i+10));
        }
    }

    /**
     * Test method for {@link outils.OutilsTableau#contient(representation.Sommet[], representation.Sommet)}.
     */
    @Test
    void testContientSommetArraySommet() {
        Sommet[] listeSommet 
        = {new Sommet(0,0),
           new Sommet(1,0),
           new Sommet(0,1),
           new Sommet(1,1),
           new Sommet(Integer.MAX_VALUE-1,Integer.MAX_VALUE-1)};
        assertTrue(OutilsTableau.contient(listeSommet, new Sommet(0,0)));
        assertTrue(OutilsTableau.contient(listeSommet, new Sommet(1,0)));
        assertTrue(OutilsTableau.contient(listeSommet, new Sommet(0,1)));
        assertTrue(OutilsTableau.contient(listeSommet, new Sommet(1,1)));
        assertFalse(OutilsTableau.contient(listeSommet, new Sommet(2, 0)));
        assertFalse(OutilsTableau.contient(listeSommet, new Sommet(2, 2)));
        assertFalse(OutilsTableau.contient(listeSommet, new Sommet(2, 1)));
        assertFalse(OutilsTableau.contient(listeSommet, new Sommet(1000, 1000)));
        assertFalse(OutilsTableau.contient(listeSommet, new Sommet(Integer.MAX_VALUE, Integer.MAX_VALUE)));
    }

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
