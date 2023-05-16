package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import representation.Graphe;
import representation.Sommet;


/**
 * Test Unitaire de la classe Graphe
 * @author Costes Quentin
 * @author de Saint Palais François
 * @author Denamiel Clement
 * @author Descriaud Lucas
 *
 */
class TestGraphe {

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
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        }  
        
        { /* graphe avec un sommet isolé */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}};
            assertDoesNotThrow(()-> new Graphe(tS, tA)); 
        }  
        
       
    }
    
    @Test
    @DisplayName("Test du constructeur avec valeur INvalide")
    void testGrapheNotOk() {
        {/* Il doit exister une liste de sommet et/ou d'arcs */
            Sommet[] tSommet = {new Sommet(1,1), new Sommet(1,2)};
            Sommet[][] tArcs = {{tSommet[0],tSommet[1]}};
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, null));
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, null));
            assertThrows(IllegalArgumentException.class, ()->new Graphe(null, tArcs));            
        }
        
        {/* Liste d'arcs non vide alors que liste sommet vide */
            Sommet[] tSommet = {};
            Sommet[][] tArcs = {{new Sommet(1,1),new Sommet(1,2)}};
            assertThrows(IllegalArgumentException.class, ()->new Graphe(tSommet, tArcs));
        }

        {/* Il existe une arrête entre deux sommet inexistant */
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet[][] tArcs = {{new Sommet(1,2),new Sommet(1,3)}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }
        
        {/* Un des sommets de l'arrête est inexistant*/
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet[][] tArcs = {{tSommets[0], new Sommet(3,3)}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }

        {/* Un des sommets de l'arrête est inexistant*/
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet [][] tArcs = {{new Sommet(1,3),tSommets[0]}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }

        {/* Il existe une boucle dans ce graphe */
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet[][] tArcs = {{tSommets[0],tSommets[0]}};
            assertThrows(IllegalArgumentException.class,()-> new Graphe(tSommets, tArcs));            
        }
        
        {/* Un graphe doit  avoir au moins une arrête */
            Sommet[] tSommets = {new Sommet(1,1), new Sommet(1,2), new Sommet(1,4)};
            Sommet[][] tArcs = {{}};
            assertThrows(IllegalArgumentException.class, ()-> new Graphe(tSommets, tArcs));
        }
        
        {/* Un graphe ne contient qu'une seule fois chaque sommet*/
            Sommet[] tS = {new Sommet(1,1),new Sommet(1,1),new Sommet(1,2)};
            Sommet[][] tA = {{tS[0],tS[2]}};
            assertThrows(IllegalArgumentException.class, ()-> new Graphe(tS,tA));
        }
        
    }

    @Test
    @DisplayName("Test du geter des nombre d'arretes")
    void testGetNbArretes() {
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,3), new Sommet(1,4) };
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(3, g.getNbArretes()); 
        }
        
        {
            Sommet [] tS = {new Sommet(2,1), new Sommet(2,2)};
            Sommet [][] tA = {{tS[0], tS[1]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(1, g.getNbArretes()); 
        }

        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1,3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}, {tS[0], tS[2]}, 
            			     {tS[2], tS[0]}, {tS[2], tS[1]}, {tS[1], tS[2]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(6, g.getNbArretes()); 
        }
        
        
        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,3), new Sommet(1,4)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(3, g.getNbArretes()); 
        }
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1,3)};
            Sommet[][] tA = {{tS[2], tS[1]}, {tS[1], tS[2]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(1, g.getNbArretes()); 
        }
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,3), new Sommet(1,4), 
                           new Sommet(1,5), new Sommet(1,6)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(5, g.getNbArretes()); 
        }
        {
            Sommet[] tS = {new Sommet(1,6), new Sommet(2,6)};
            Sommet[][] tA = {{tS[0],tS[1]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(2, g.getNbArretes()); 
        }
    }

    @Test
    @DisplayName("Test du getter des nombre de sommets")
    void testGetNbSommets() {
        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,3), new Sommet(1,4)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(3, g.getNbSommets()); 
        }
        
        {
            Sommet[] tS = {new Sommet(1, 2), new Sommet(1, 1)};
            Sommet[][] tA = {{tS[0], tS[1]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(2, g.getNbSommets()); 
        }
        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2)};
            Sommet[][] tA = {{tS[1], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertEquals(2, g.getNbSommets()); 
        }
        {
            Sommet[] tS = {new Sommet(1, 2), new Sommet(1, 1)};
            Sommet[][] tA = {{tS[1], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(tS.length + 1, g.getNbSommets()); 
        }
        {
            Sommet[] tS = {new Sommet(1, 1), new Sommet(1, 2)};
            Sommet[][] tA = {{tS[1], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertNotEquals(tS.length - 1, g.getNbSommets()); 
        }
    }
    
    @Test
    @DisplayName("Test de ma méthode sontRelier")
    void testSontRelier () {
        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2)};
            Sommet[][] tA = {{tS[1], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertTrue(g.sontRelies(tS[0], tS[1]));
            assertTrue(g.sontRelies(tS[1], tS[0]));  
        }
        
        {
            Sommet[] tS = {new Sommet(1, 1), new Sommet(1, 2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertTrue(g.sontRelies(tS[1], tS[0]));
            assertTrue(g.sontRelies(tS[1], tS[2]));
            assertTrue(g.sontRelies(tS[2], tS[0])); 
        }
        
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[1], tS[0]}};
            Graphe g = new Graphe(tS, tA);
            assertFalse(g.sontRelies(tS[0], tS[2]));
            assertFalse(g.sontRelies(tS[2], tS[0])); 
            assertTrue(g.sontRelies(tS[1], tS[0]));  
        }
       
        
    }
    

}
