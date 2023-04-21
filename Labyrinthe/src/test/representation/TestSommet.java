/**
 * TestSommet.java                       18 avr. 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.representation;

import representation.Sommet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * //TODO Commenter la responsabilités de la classe TestSommet
 * @author Denamiel Clement 
 * @author Descriaud Lucas
 */
class TestSommet {
    
    public ArrayList<Sommet> sommetsValides;
    
    @BeforeEach
    public void genererSommetsValides() {
        sommetsValides = new ArrayList<>(10);
        
/*  0 */sommetsValides.add(new Sommet(0,0));
/*  1 */sommetsValides.add(new Sommet(1,0));
/*  2 */sommetsValides.add(new Sommet(2,0));
/*  3 */sommetsValides.add(new Sommet(3,0));
/*  4 */sommetsValides.add(new Sommet(4,0));
/*  5 */sommetsValides.add(new Sommet(0,1));
/*  6 */sommetsValides.add(new Sommet(0,2));
/*  7 */sommetsValides.add(new Sommet(0,3));
/*  8 */sommetsValides.add(new Sommet(0,4));
/*  9 */sommetsValides.add(new Sommet(0,0));
/* 10 */sommetsValides.add(new Sommet(1,1));
/* 11 */sommetsValides.add(new Sommet(2,2));
/* 12 */sommetsValides.add(new Sommet(3,3));
/* 13 */sommetsValides.add(new Sommet(4,4));

    }

    /**
     * Test method for {@link representation.Sommet#Sommet(int, int)}.
     */
    @Test
    void testConstructeurSommet() {
        assertThrows(IllegalArgumentException.class, ()-> new Sommet(-1, 0));
        assertThrows(IllegalArgumentException.class, ()-> new Sommet( 0,-1));
        assertThrows(IllegalArgumentException.class, ()-> new Sommet(Integer.MIN_VALUE, 0));
        assertThrows(IllegalArgumentException.class, ()-> new Sommet(0, Integer.MIN_VALUE));

        
        assertDoesNotThrow(()-> sommetsValides.get(0));
        assertDoesNotThrow(()-> sommetsValides.get(5));
        assertDoesNotThrow(()-> sommetsValides.get(2));
        assertDoesNotThrow(()-> sommetsValides.get(6));
        assertDoesNotThrow(()-> new Sommet(0,Integer.MAX_VALUE));
        assertDoesNotThrow(()-> new Sommet(Integer.MAX_VALUE,0));
        assertDoesNotThrow(()-> new Sommet(Integer.MAX_VALUE , Integer.MAX_VALUE));
    }
    
    @Test
    void testAjouterVoisin() {
        Sommet sommetTest = new Sommet(0,0);
        // TODO test entre egalité de deux arrayList 
        // TODO assertThrow
    }
    
    @Test 
    void testGetX() {
        assertEquals(sommetsValides.get(0).getPosX(),0);
        assertEquals(sommetsValides.get(1).getPosX(),1);
        assertEquals(sommetsValides.get(2).getPosX(),2);
        assertEquals(sommetsValides.get(3).getPosX(),3);
    
        
        assertNotEquals(sommetsValides.get(6).getPosX(),1);
        assertNotEquals(sommetsValides.get(7).getPosX(),2);
        assertNotEquals(sommetsValides.get(8).getPosX(),3);
        assertNotEquals(sommetsValides.get(9).getPosX(),4);
        
    }
    
    @Test 
    void testGetY() {
        assertEquals(sommetsValides.get(0).getPosY(),0);
        assertEquals(sommetsValides.get(5).getPosY(),1);
        assertEquals(sommetsValides.get(6).getPosY(),2);
        assertEquals(sommetsValides.get(7).getPosY(),3);
        assertEquals(sommetsValides.get(8).getPosY(),4);
    
        
        assertNotEquals(sommetsValides.get(1).getPosY(),1);
        assertNotEquals(sommetsValides.get(2).getPosY(),2);
        assertNotEquals(sommetsValides.get(3).getPosY(),3);
        assertNotEquals(sommetsValides.get(4).getPosY(),4);
        
    }
    
    @Test
    void testHashCode() {
        
    }
    

}
