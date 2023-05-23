package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import outils.OutilsMatrice;

import org.junit.jupiter.api.BeforeEach;


import representation.Labyrinthe;
import representation.Sommet;


/**
 * Test Unitaire de la classe Labyrinthe
 * @author Costes Quentin
 * @author de Saint Palais François
 * @author Denamiel Clément
 * @author Descriaud Lucas
 *
 */
class TestGraphe {
    
    private ArrayList<Labyrinthe> grapheCorrecte;
    
    @BeforeEach
    void genererGrapheValide() {
        grapheCorrecte = new ArrayList<>(10);
        {/* Un graphe normal */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            grapheCorrecte.add(new Labyrinthe(tS, tA));            
        }
             
        { /* graphe symétrique */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}, {tS[0], tS[2]}, 
            				 {tS[2], tS[0]}, {tS[1], tS[2]}, {tS[2], tS[1]}};
            grapheCorrecte.add(new Labyrinthe(tS, tA)); 
        }
        
        { /* graphe transitif */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            grapheCorrecte.add(new Labyrinthe(tS, tA)); 
        }  
        
        { /* graphe avec un sommet isolé */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}};
            grapheCorrecte.add(new Labyrinthe(tS, tA)); 
        } 
        {
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1,3),
            			   new Sommet(2,1), new Sommet(2,2), new Sommet(2,3)};
            Sommet[][]tA = {{tS[0],tS[3]},{tS[2],tS[5]}};
            grapheCorrecte.add(new Labyrinthe(tS, tA));
        }
    }

    @Test
    @DisplayName("Test du constructeur avec valeur Valide")
    void testGrapheOK() {
        {/* Un graphe normal */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            assertDoesNotThrow(()-> new Labyrinthe(tS, tA));            
        }
             
        { /* graphe symétrique */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}, {tS[0], tS[2]}, {tS[2], tS[0]}, {tS[1], tS[2]}, {tS[2], tS[1]}};
            assertDoesNotThrow(()-> new Labyrinthe(tS, tA)); 
        }
        
        { /* graphe transitif */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            assertDoesNotThrow(()-> new Labyrinthe(tS, tA)); 
        }  
        
        { /* graphe avec un sommet isolé */
            Sommet[] tS = {new Sommet(1,1), new Sommet(1,2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[0]}};
            assertDoesNotThrow(()-> new Labyrinthe(tS, tA)); 
        }  
        
       
    }
    
    @Test
    @DisplayName("Test du constructeur avec valeur Invalide")
    void testGrapheNotOk() {
        {/* Il doit exister une liste de sommet et/ou d'arcs */
            Sommet[] tSommet = {new Sommet(1,1), new Sommet(1,2)};
            Sommet[][] tArcs = {{tSommet[0],tSommet[1]}};
            Sommet[] tSommetVide = {};
            Sommet[][] tArcsVide = {{}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(null, null));
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tSommet, null));
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(null, tArcs));            
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tSommetVide, tArcsVide));            
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tSommet, tArcsVide));
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tSommetVide, tArcs));
        }
        {
            
        }
        
        {/* Liste d'arcs non vide alors que liste sommet vide */
            Sommet[] tSommet = {};
            Sommet[][] tArcs = {{new Sommet(1,1),new Sommet(1,2)}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tSommet, tArcs));
        }

        {/* Il existe une arrête entre deux sommet inexistant */
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet[][] tArcs = {{new Sommet(1,2),new Sommet(1,3)}};
            assertThrows(IllegalArgumentException.class,()-> new Labyrinthe(tSommets, tArcs));            
        }
        
        {/* Un des sommets de l'arrête est inexistant*/
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet[][] tArcs = {{tSommets[0], new Sommet(3,3)}};
            assertThrows(IllegalArgumentException.class,()-> new Labyrinthe(tSommets, tArcs));            
        }

        {/* Un des sommets de l'arrête est inexistant*/
            Sommet[] tSommets = {new Sommet(1,1)};
            Sommet [][] tArcs = {{new Sommet(1,3),tSommets[0]}};
            assertThrows(IllegalArgumentException.class,()-> new Labyrinthe(tSommets, tArcs));            
        }


        
        {/* Un graphe doit  avoir au moins une arrête */
            Sommet[] tSommets = {new Sommet(1,1), new Sommet(1,2), new Sommet(1,4)};
            Sommet[][] tArcs = {{}};
            assertThrows(IllegalArgumentException.class, ()-> new Labyrinthe(tSommets, tArcs));
        }
        
        {/* Un graphe ne contient qu'une seule fois chaque sommet*/
            Sommet[] tS = {new Sommet(1,1),new Sommet(1,1),new Sommet(1,2)};
            Sommet[][] tA = {{tS[0],tS[2]}};
            assertThrows(IllegalArgumentException.class, ()-> new Labyrinthe(tS,tA));
        }
        
    }

    @Test
    void testAjoutetArrete() {
        {
            Sommet s1 = new Sommet(1,1);
            Sommet s2 = new Sommet(1,2);
            Sommet s3 = new Sommet(1,3);
            Sommet[] tS = {s1, s2, s3};
            Sommet[][] tA = {{tS[1], tS[2]}};
            Labyrinthe g = new Labyrinthe(tS, tA);
            assertFalse(g.sontRelies(tS[0], tS[1]));           
            g.ajouterArrete(s1, s2);
            assertTrue(g.sontRelies(s1, s2));                        
        }
        
        {
            Sommet s1 = new Sommet(1,1);
            Sommet s2 = new Sommet(1,2);
            Sommet s3 = new Sommet(1,3);
            Sommet[] tS = {s1, s2, s3};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            Labyrinthe g = new Labyrinthe(tS, tA);
            
            assertFalse(g.sontRelies(tS[0], tS[2]));
            
            g.ajouterArrete(tS[0], tS[2]);
            
            assertTrue(g.sontRelies(tS[0], tS[2]));
            assertTrue(g.sontRelies(tS[1], tS[2]));

        }
        
        {
            Sommet s1 = new Sommet(1,1);
            Sommet s2 = new Sommet(1,2);
            Sommet s3 = new Sommet(1,3);
            Sommet[] tS = {s1, s2};
            Sommet[][] tA = {{tS[0], tS[1]}};
            Labyrinthe g = new Labyrinthe(tS, tA);
            
            // le sommet n'existe pas dans le graphe
            assertThrows(IllegalArgumentException.class, ()-> g.ajouterArrete(tS[1], s3));  
        }
        // TODO tester le fait que ça throw si l'arrete existe deja
        {
            Sommet s1 = new Sommet(1,1);
            Sommet s2 = new Sommet(1,2);
            Sommet s3 = new Sommet(1,3);
            Sommet[] tS = {s1, s2, s3};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}};
            Labyrinthe g = new Labyrinthe(tS, tA);
        }
    }
}
