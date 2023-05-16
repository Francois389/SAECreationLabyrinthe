/**
 * TestLabyrinthe.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import representation.Graphe;
import representation.Sommet;
import representation.Labyrinthe;

/**
 * 
 * @author costes quentin
 * @author descriaud lucas
 *
 */
class TestLabyrinthe {

    @Test
    void testConstructeur() {
        {/* Liste d'arcs non vide alors que liste sommet vide */
            Sommet[] tS = {};
            Sommet[][] tA = {{new Sommet(1,1),new Sommet(1,2)}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tS, tA, tS[1], tS[0]));
        }
    }

}
