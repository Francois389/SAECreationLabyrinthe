package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import representation.Graphe;


/**
 * Test Unitaire de la classe Graphe
 * @author Costes Quentin
 * @author François de Saint Palais
 * 
 *
 */
class TestGraphe {

    @Test
    @DisplayName("Test du constructeur avec valeur Valide")
    void testGrapheOK() {
        {
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 3}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));            
        }
        
        {
            int[] tS = {1};
            int[][] tA = {{1, 1}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));
        }
        
        {   
            int[] tS = {};
            int[][] tA = {{}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));
        }
    }
    
    @Test
    @DisplayName("Test du constructeur avec valeur INvalide")
    void testGrapheNotOk() {
        /* Il doit exister une liste de sommet et d'arcs */
        assertThrows(IllegalArgumentException.class, ()->new Graphe(null, null));
        
        /* Liste d'arcs non vide alors que liste sommet vide */
        int[] tsommet = {};
        int[][] tarcs = {{1,2}};
        assertThrows(IllegalArgumentException.class, ()->new Graphe(tsommet, tarcs));
        
        int[] tS = {1};
        int[][] tA = {{2, 3}};
        assertThrows(IllegalArgumentException.class,()-> new Graphe(tS, tA));
    }

    @Test
    void testGetNbArretes() {
        //fail("Not yet implemented");
        
    }

    @Test
    void testGetNbSommets() {
        // fail 
    }

}
