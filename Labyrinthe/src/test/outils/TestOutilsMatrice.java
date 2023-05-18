/**
 * TestOutilsMatrice.java                       18 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsMatrice;

/**
 * //TODO Commenter la responsabilités de la classe TestOutilsMatrice
 * @author francois
 *
 */
class TestOutilsMatrice {

    /**
     * Test method for {@link outils.OutilsMatrice#estNulle(int[][])}.
     */
    @Test
    void testEstNulleInt() {
        int[][] matriceNul = {{0,0,0},{0,0,0},{0,0,0}};
        int[][] matriceNonNul = {{0,0,0},{0,0,0},{0,1,0}};
        assertTrue(OutilsMatrice.estNulle(matriceNul));
        assertFalse(OutilsMatrice.estNulle(matriceNonNul));
    }

    /**
     * Test method for {@link outils.OutilsMatrice#supLigneColonne(int[][], int)}.
     */
    @Test
    void testSupLigneColonneIntArrayArrayInt() {
        fail("Not yet implemented");
        
    }

    /**
     * Test method for {@link outils.OutilsMatrice#supLigneColonne(boolean[][], int)}.
     */
    @Test
    void testSupLigneColonneBooleanArrayArrayInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link outils.OutilsMatrice#colonneVide(int[][], int)}.
     */
    @Test
    void testColonneVideIntArrayArrayInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link outils.OutilsMatrice#colonneVide(boolean[][], int)}.
     */
    @Test
    void testColonneVideBooleanArrayArrayInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link outils.OutilsMatrice#ligneVide(int[][], int)}.
     */
    @Test
    void testLigneVideIntArrayArrayInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link outils.OutilsMatrice#ligneVide(boolean[][], int)}.
     */
    @Test
    void testLigneVideBooleanArrayArrayInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link outils.OutilsMatrice#estNul(boolean[][])}.
     */
    @Test
    void testEstNulBoolean() {
        boolean[][] matriceFalse = {{false,false,false},{false,false,false},{false,false,false}};
        boolean[][] matriceMixte = {{false,true,false},{false,false,false},{false,false,false}};
        assertTrue(OutilsMatrice.estNul(matriceFalse));
        assertFalse(OutilsMatrice.estNul(matriceMixte));
    }

}
