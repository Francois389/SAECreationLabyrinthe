/**
 * TestLabyrinthe.java                       16 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
        {/* graphe réflexif */
            Sommet[] tS = {new Sommet(1, 1)};
            Sommet[][] tA = {{tS[0], tS[0]}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tS, tA, tS[0], tS[0] ));
        }
        
        {/* graphe réflexif */
            Sommet[] tS = {new Sommet(1, 1), new Sommet(1, 2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[0]}, {tS[1], tS[1]}, {tS[2], tS[2]},
            				 {tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[1]}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tS, tA, tS[0], tS[0] ));
        }
        
        {/* graphe avec boucle(s) */
            Sommet[] tS = {new Sommet(1, 1), new Sommet(1, 2)};
            Sommet[][] tA = {{tS[0], tS[0]}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tS, tA, tS[0], tS[0] ));
        }
        
        
        {/* graphe avec circuit(s) */
            Sommet[] tS = {new Sommet(1, 1), new Sommet(1, 2), new Sommet(1, 3)};
            Sommet[][] tA = {{tS[0], tS[1]}, {tS[1], tS[2]}, {tS[2], tS[0]}};
            assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(tS, tA, tS[0], tS[0] ));
        }
    }

}

