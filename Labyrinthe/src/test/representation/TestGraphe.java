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
        {/* Un graphe normal */
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 3}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));            
        }
             
        { /* graphe symétrique */
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {2, 3}, {3, 2}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        } 
        
        { /* graphe transitif */
            int[] tS = {1, 3, 4};
            int[][] tA = {{1, 3}, {3, 4}, {4, 1}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        }  
        
        { /* graphe avec un sommet isolé */
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 1}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        }  
        
       
    }
    
    @Test
    @DisplayName("Test du constructeur avec valeur INvalide")
    void testGrapheNotOk() {
        {/* Il doit exister une liste de sommet et/ou d'arcs */
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, null));
            int[] tSommet = {1,2};
            int[][] tArcs = {{1,2}};
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, null));
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, tArcs));            
        }
        
        {/* Liste d'arcs non vide alors que liste sommet vide */
            int[] tsommet = {};      //STUB Il faut que la classe Sommet soit écrite pour pouvoir l'utiliser
            int[][] tarcs = {{1,2}}; //STUB En attendant, nous utilisons des listes d'entiers
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tsommet, tarcs));
        }

        {/* Il existe une arrête entre deux sommet inexistant */
            int[] tS = {1};
            int[][] tA = {{2, 3}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tS, tA));            
        }
        
        {/* Un des sommets de l'arrête est inexistant*/
            int[] tabS = {1};
            int[][] tabA = {{1, 3}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));            
        }

        {/* Un des sommets de l'arrête est inexistant*/
            int[] tabS = {1};
            int [][] tabA = {{3,1}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));            
        }

        {/* Il existe une boucle dans ce graphe */
            int[] tabS = {1};
            int [][] tabA = {{3,1}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tabS, tabA));            
        }
        
        {/* Un graphe doit  avoir au moins une arrête */
            int[] tS = {1, 2, 4};
            int[][] tA = {{}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));
        }
        
    }

    @Test
    void testGetNbArretes() {
        //fail("Not yet implemented");
        
    }

    @Test
    void testGetNbSommets() {
        
        {
            int[] tS = {1, 3, 4};
            int[][] tA = {{1, 3}, {3, 4}, {4, 1}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(3, g.getNbSommets()); 
        }
    }

}
