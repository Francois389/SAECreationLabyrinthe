/*
 * TestOutilsListe.java                                   11 mai 2023
 * IUT de Rodez, pas de copyright, ni "copyleft"
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsListe;

import representation.Sommet;

/**
 * Test unitaire de la classe OutilsListe
 * @author Costes Quentin
 * @author de Saint Palais François
 * @author Denamiel Clément
 * @author Descriaud Lucas
 */
class TestOutilsListe {

    @Test
    void testContient () {
        {
            int[] liste = {1,2,3,4,5};
            assertFalse(OutilsListe.contient(liste,6));            
            assertFalse(OutilsListe.contient(liste,0));            
            assertTrue(OutilsListe.contient(liste,5));            
            assertTrue(OutilsListe.contient(liste,1));            
            assertTrue(OutilsListe.contient(liste,3));            
        }
        {
            int[] liste = {1};
            assertFalse(OutilsListe.contient(liste,3));
            assertFalse(OutilsListe.contient(liste,3));
            assertTrue(OutilsListe.contient(liste,1));
        }
        {
            int[] liste = {};
            assertFalse(OutilsListe.contient(liste, 0));
        }
        assertFalse(OutilsListe.contient(null, 0));
    }
    
    @Test
    void testContientSommet() {
        {
            Sommet[] liste = 
            {new Sommet(0,0), new Sommet(1,0), new Sommet(2,0), new Sommet(3,0),
             new Sommet(4,0), new Sommet(0,1), new Sommet(0,2), new Sommet(0,3),
             new Sommet(0,4), new Sommet(1,1), new Sommet(2,2), new Sommet(3,3),
             new Sommet(4,4) };
            assertFalse(OutilsListe.contient(liste,new Sommet(10,3)));
            assertTrue(OutilsListe.contient(liste,new Sommet(1,0)));
            assertTrue(OutilsListe.contient(liste,new Sommet(0,3)));
            assertTrue(OutilsListe.contient(liste,new Sommet(3,3)));
        }
        {
            Sommet[] liste = {new Sommet(2,0)};
            assertFalse(OutilsListe.contient(liste,new Sommet(2,2)));
            assertTrue(OutilsListe.contient(liste,new Sommet(2,0)));
        }
        {
            Sommet[] liste = {};
            assertFalse(OutilsListe.contient(liste, new Sommet(2,0)));
        }
        assertFalse(OutilsListe.contient(null, new Sommet(2,0)));
    }

    @Test
    void testTabVersString() {
        Integer[] liste = {1,2,3,4,5};
        String attendu = "[1, 2, 3, 4, 5]";
        assertEquals(attendu, OutilsListe.tabVersString(liste));
    }

    @Test
    void testTabVersStringBoolean() {
        boolean[] liste = {true,false,true,false};
        String attendu = "[true, false, true, false]";
        assertEquals(attendu, OutilsListe.tabVersString(liste));
    }
}
