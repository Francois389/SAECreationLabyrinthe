package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsListe;

class TestOutilListe {

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
    }

}
