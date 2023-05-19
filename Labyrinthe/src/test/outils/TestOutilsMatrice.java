/**
 * TestOutilsMatrice.java                       18 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.outils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import outils.OutilsMatrice;

/**
 * Test unitaire de la classe OutilsMatrice
 * @author de Saint Palais François
 *
 */
/**
 * //TODO Commenter la responsabilités de la classe TestOutilsMatrice
 * @author francois
 *
 */
class TestOutilsMatrice {

    /**
     * Test method for {@link outils.OutilsMatrice#colonneVide(boolean[][], int)}.
     */
    @Test
    void testColonneVideBooleanArrayArrayInt() {
        boolean[][] matrice = {{false,true,false},{false,false,false},{true,true,false}};
        boolean[][] matriceNull = null;
        boolean[][] matriceVide = {{}};
        
        assertTrue(OutilsMatrice.colonneVide(matrice, 2));
        assertFalse(OutilsMatrice.colonneVide(matrice, 0));
        assertFalse(OutilsMatrice.colonneVide(matrice, 1));
        
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceNull, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceVide, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matrice, Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, Integer.MIN_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, 3));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, -1));
    }

    /**
     * Test method for {@link outils.OutilsMatrice#colonneVide(int[][], int)}.
     */
    @Test
    void testColonneVideIntArrayArrayInt() {
        int[][] matrice = {{0,2,0},{0,0,0},{1,2,0}};
        int[][] matriceNull = null;
        int[][] matriceVide = {{}};
        
        assertTrue(OutilsMatrice.colonneVide(matrice, 2));
        assertFalse(OutilsMatrice.colonneVide(matrice, 0));
        assertFalse(OutilsMatrice.colonneVide(matrice, 1));
        
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceNull, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceVide, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matrice, Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, Integer.MIN_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, 3));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, -1));
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
     * Test method for {@link outils.OutilsMatrice#ligneVide(boolean[][], int)}.
     */
    @Test
    void testLigneVideBooleanArrayArrayInt() {
        boolean[][] matrice = {{false,true,false},{false,false,false},{true,true,false}};
        boolean[][] matriceNull = null;
        boolean[][] matriceVide = {{}};
        
        assertTrue(OutilsMatrice.colonneVide(matrice, 1));
        assertFalse(OutilsMatrice.colonneVide(matrice, 0));
        assertFalse(OutilsMatrice.colonneVide(matrice, 2));
        
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceNull, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matriceVide, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.colonneVide(matrice, Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, Integer.MIN_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, 3));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.colonneVide(matrice, -1));
    }

    /**
     * Test method for {@link outils.OutilsMatrice#ligneVide(int[][], int)}.
     */
    @Test
    void testLigneVideIntArrayArrayInt() {
        int[][] matrice = {{0,2,0},{0,0,0},{1,2,0}};
        int[][] matriceNull = null;
        int[][] matriceVide = {{}};
        
        assertTrue(OutilsMatrice.ligneVide(matrice, 1));
        assertFalse(OutilsMatrice.ligneVide(matrice, 0));
        assertFalse(OutilsMatrice.ligneVide(matrice, 2));
        
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.ligneVide(matriceNull, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.ligneVide(matriceVide, 2));
        assertThrows(IllegalArgumentException.class,
                     () -> OutilsMatrice.ligneVide(matrice, Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.ligneVide(matrice, Integer.MIN_VALUE));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.ligneVide(matrice, 3));
        assertThrows(IllegalArgumentException.class,
                () -> OutilsMatrice.ligneVide(matrice, -1));
    }

    /**
     * Test method for {@link outils.OutilsMatrice#supLigneColonne(boolean[][], int)}.
     */
    @Test
    void testSupLigneColonneBooleanArrayArrayInt() {
        {
            boolean[][] matrice = {{true, false, true},{false, false, false},{true,false,true}};
            boolean[][] matriceAttendu = {{true, true},{true, true}};
            Integer indiceNull = null;
            
            assertArrayEquals(matriceAttendu, OutilsMatrice.supLigneColonne(matrice, 1));
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, Integer.MAX_VALUE));
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, indiceNull));
        }
        {/* matrice est vide */
            boolean[][] matrice = {{}};
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, 0));
        }
        {/* matrice est null */
            boolean[][] matrice = null;
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, 0));
        }
    }

    /**
     * Test method for {@link outils.OutilsMatrice#supLigneColonne(int[][], int)}.
     */
    @Test
    void testSupLigneColonneIntArrayArrayInt() {
        {
            int[][] matrice = {{1,2,3},{2,2,2},{1,2,3}};
            int[][] matriceAttendu = {{1,3},{1,3}};
            Integer indiceNull = null;
            
            assertArrayEquals(matriceAttendu, OutilsMatrice.supLigneColonne(matrice, 1));
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, Integer.MAX_VALUE));
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, indiceNull));
        }
        {
            int[][] matrice = {{}};
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, 0));
        }
        {
            int[][] matrice = null;
            assertThrows(IllegalArgumentException.class,
                    () -> OutilsMatrice.supLigneColonne(matrice, 0));
            
        }
        
    }
    
    @Test
    void testMatriceToStringInt() {
        Integer[][] matrice = {{1,2,3},{2,2,2},{1,2,3}};
        String attendu = 
          "[[1, 2, 3],\n"
        + " [2, 2, 2],\n"
        + " [1, 2, 3]]";
		
		assertEquals(attendu, OutilsMatrice.matriceToString(matrice));
    }
    @Test
    void testMatriceToStringBoolean() {
        boolean[][] matrice = {{true, false, true},{false, false, false},{true,false,true}};
        String attendu = 
          "[[true, false, true],\n"
        + " [false, false, false],\n"
        + " [true, false, true]]";
		
		assertEquals(attendu, OutilsMatrice.matriceToString(matrice));
    }

}
