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
        /* Il doit exister une liste de sommet et/ou d'arcs */
        assertThrows(IllegalArgumentException.class, ()->new Graphe(null, null));
        int[] tSommet = {1,2};
        int[][] tArcs = {{1,2}};
        assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, null));
        assertThrows(IllegalArgumentException.class, ()->new Graphe(null, tArcs));
        
        /* Liste d'arcs non vide alors que liste sommet vide */
        int[] tsommet = {};      //STUB Il faut que la classe Sommet soit écrite pour pouvoir l'utiliser
        int[][] tarcs = {{1,2}}; //STUB En attendant, nous utilisons des listes d'entiers
        assertThrows(IllegalArgumentException.class, ()->new Graphe(tsommet, tarcs));
        
        /* Il existe une arrête entre deux sommet inexistant */
        int[] tS = {1};
        int[][] tA = {{2, 3}};
        assertThrows(IllegalArgumentException.class,()-> new Graphe(tS, tA));
        
        /* Un des sommets de l'arrête est inexistant*/
        int[] tabS = {1};
        int[][] tabA = {{1, 3}};
        assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));
        
        /* Un des sommets de l'arrête est inexistant*/
        tabA[0][0] = 3; //{{3,3}}
        tabA[0][1] = 1; //{{3,1}}
        assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));
        
        /* Il existe une boucle dans ce graphe */
        tabA[0][0] = 1; //{{1,1}}
        assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));
        
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
