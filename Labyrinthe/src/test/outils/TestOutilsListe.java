/*
 * TestOutilsListe.java                                   11 mai 2023
 * IUT de Rodez, pas de copyright, ni "copyleft"
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsListe;

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
        }
        {
            int[] liste = {};
            assertFalse(OutilsListe.contient(liste, 0));
        }
        assertFalse(OutilsListe.contient(null, 0));
    }
    
    @Test
    void testTabVersString() {
        Integer[] liste = {1,2,3,4,5};
        String attendu = "[1, 2, 3, 4, 5]";
        assertEquals(attendu, OutilsListe.tabVersString(liste));
    }

}
