package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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
class TestLabyrinthe {
    
    private ArrayList<Labyrinthe> grapheCorrecte;
    
    @BeforeEach
    void genererGrapheValide() {
        grapheCorrecte = new ArrayList<>(2);
        { /* petit labyrinthe */
            grapheCorrecte.add(new Labyrinthe(5, 5));            
        }
             
        { /* grand labyrinthe */
            grapheCorrecte.add(new Labyrinthe(30, 30)); 
        }
        
        { /* grand labyrinthe */
            grapheCorrecte.add(new Labyrinthe(10, 20)); 
        }
    }
    
    /**
     * Permet de verifier la prÃ©sence d'un arc entre deux sommets
     * dans les deux sens.
     * @param sommet1
     * @param sommet2
     * @return true si un arc existe entre les deux sommets, false sinon
     */
    public boolean sontRelies (Sommet sommet1, Sommet sommet2, Sommet[][] listeArcs) {
        return    existeArcEntre(sommet1, sommet2, listeArcs) 
               || existeArcEntre(sommet2, sommet1, listeArcs);
    }
    
    public boolean existeArcEntre(Sommet sommet1, Sommet sommet2, Sommet[][] listeArcs) {
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0].equals(sommet1) && listeArcs[i][1].equals(sommet2)) {
                return true;
            }  
        }
        return false;
    }
        
    
    @Test
    void testA() {
        Labyrinthe a = new Labyrinthe(3,4);
    	//a.fusionnerMarques();
    }
    
    @Test
    @DisplayName("Test du toString")
    void testToString() {
        {
            Labyrinthe aAfficher = new Labyrinthe(10,10);
            aAfficher.
        	System.out.print(aAfficher);
        	System.out.println("fini ");
        }
        
    }
    
    @Test
    void testEstPresent(){
        Labyrinthe g = new Labyrinthe(2,2);
        Sommet s1 = new Sommet(3,1);
        assertFalse(g.estPresent(s1));
        assertTrue(g.estPresent(g.getListeSommet()[0][0]));
    }
}
