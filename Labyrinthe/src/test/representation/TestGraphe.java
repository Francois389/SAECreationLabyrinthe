package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import representation.Graphe;
import representation.Sommet;


/**
 * Test Unitaire de la classe Graphe
 * @author Costes Quentin
 * @author François de Saint Palais
 * 
 *
 */
class TestGraphe {

    //TODO Transformé les tableau d'ntier en tableau de Sommet
    @Test
    @DisplayName("Test du constructeur avec valeur Valide")
    void testGrapheOK() {
        {/* Un graphe normal */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            assertDoesNotThrow(()-> new Graphe(tS, tA));            
        }
             
        { /* graphe symétrique */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}, {tS[0], tS[2]}, {tS[2], tS[0]}, {tS[1], tS[2]}, {tS[2], tS[1]}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        }
        
        { /* graphe transitif */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 4)};
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
            int[] tSommet = {1,2};   //STUB Il faut que la classe Sommet soit écrite pour pouvoir l'utiliser
            int[][] tArcs = {{1,2}}; //STUB En attendant, nous utilisons des listes d'entiers
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, null));
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, null));
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, tArcs));            
        }
        
        {/* Liste d'arcs non vide alors que liste sommet vide */
            int[] tSommet = {};
            int[][] tArcs = {{1,2}};
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, tArcs));
        }

        {/* Il existe une arrête entre deux sommet inexistant */
            int[] tSommets = {1};
            int[][] tArcs = {{2, 3}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }
        
        {/* Un des sommets de l'arrête est inexistant*/
            int[] tSommets = {1};
            int[][] tArcs = {{1, 3}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }

        {/* Un des sommets de l'arrête est inexistant*/
            int[] tSommets = {1};
            int [][] tArcs = {{3,1}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }

        {/* Il existe une boucle dans ce graphe */
            int[] tSommets = {1};
            int [][] tArcs = {{1,1}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }
        
        {/* Un graphe doit  avoir au moins une arrête */
            int[] tSommets = {1, 2, 4};
            int[][] tArcs = {{}};
            assertThrows(IllegalArgumentException.class, ()-> new Graphe(tSommets, tArcs));
        }
        
    }

    @Test
    @DisplayName("Test du geter des nombre d'arretes")
    void testGetNbArretes() {
        {
            int[] tS = {1, 3, 4};
            int[][] tA = {{1, 3}, {3, 4}, {4, 1}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(3, g.getNbArretes()); 
        }
        
        {
            int[] tS = {2, 1};
            int[][] tA = {{1, 2}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(1, g.getNbArretes()); 
        }

        
        {
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {3, 2}, {2, 3}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(6, g.getNbArretes()); 
        }
        
        
        
        {
            int[] tS = {1, 3, 4};
            int[][] tA = {{1, 3}, {3, 4}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(3, g.getNbArretes()); 
        }
        {
            int[] tS = {1, 2, 3};
            int[][] tA = {{3, 2}, {2, 3}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(1, g.getNbArretes()); 
        }
        {
            int[] tS = {1, 3, 4, 5, 6};
            int[][] tA = {{1, 3}, {3, 4}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(5, g.getNbArretes()); 
        }
        {
            int[] tS = {1, 2};
            int[][] tA = {{1, 2}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(2, g.getNbArretes()); 
        }
    }

    @Test
    @DisplayName("Test du getter des nombre de sommets")
    void testGetNbSommets() {
        
        {
            int[] tS = {1, 3, 4};
            int[][] tA = {{1, 3}, {3, 4}, {4, 1}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(3, g.getNbSommets()); 
        }
        
        {
            int[] tS = {2, 1};
            int[][] tA = {{1, 2}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(2, g.getNbSommets()); 
        }
        
        {
            int[] tS = {2, 1};
            int[][] tA = {{2, 1}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(2, g.getNbSommets()); 
        }
        {
            int[] tS = {2, 1};
            int[][] tA = {{2, 1}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(tS.length + 1, g.getNbSommets()); 
        }
        {
            int[] tS = {2, 1};
            int[][] tA = {{2, 1}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(tS.length - 1, g.getNbSommets()); 
        }
    }
    
    @Test
    @DisplayName("Test de ma méthode sontRelier")
    void testSontRelier () {
        
        {
            int[] tS = {2, 1};
            int[][] tA = {{2, 1}};
            Graphe g = new Graphe(tS, tA);
            assertTrue(g.sontRelier(1, 2));
            assertTrue(g.sontRelier(2, 1));  
        }
        
        {
            int[] tS = {1, 2, 3};
            int[][] tA = {{1, 2}, {2, 3}, {3, 1}};
            Graphe g = new Graphe(tS, tA);
            assertTrue(g.sontRelier(2, 1));
            assertTrue(g.sontRelier(2, 3));
            assertTrue(g.sontRelier(3, 1)); 
        }
        
    }
    

}
