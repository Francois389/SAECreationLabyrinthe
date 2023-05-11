/**
 * TestSommet.java                       18 avr. 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.representation;

import representation.Sommet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * //TODO Commenter la responsabilités de la classe TestSommet
 * @author Denamiel Clement 
 * @author Descriaud Lucas
 */
class TestSommet {
    
    public ArrayList<Sommet> sommetsValides;
   	public ArrayList<Sommet> sommetsValidesSansDoublon;
   	
    @BeforeEach
    public void genererSommetsValidesSansDoublon() {
        sommetsValidesSansDoublon = new ArrayList<>(10);
        
/*  0 */sommetsValidesSansDoublon.add(new Sommet(0,0));
/*  1 */sommetsValidesSansDoublon.add(new Sommet(1,0));
/*  2 */sommetsValidesSansDoublon.add(new Sommet(2,0));
/*  3 */sommetsValidesSansDoublon.add(new Sommet(3,0));
/*  4 */sommetsValidesSansDoublon.add(new Sommet(4,0));
/*  5 */sommetsValidesSansDoublon.add(new Sommet(0,1));
/*  6 */sommetsValidesSansDoublon.add(new Sommet(0,2));
/*  7 */sommetsValidesSansDoublon.add(new Sommet(0,3));
/*  8 */sommetsValidesSansDoublon.add(new Sommet(0,4));
/*  9 */sommetsValidesSansDoublon.add(new Sommet(1,1));
/* 10 */sommetsValidesSansDoublon.add(new Sommet(2,2));
/* 11 */sommetsValidesSansDoublon.add(new Sommet(3,3));
/* 12 */sommetsValidesSansDoublon.add(new Sommet(4,4));
    }
    
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
/*  9 */sommetsValides.add(new Sommet(1,1));
/* 10 */sommetsValides.add(new Sommet(2,2));
/* 11 */sommetsValides.add(new Sommet(3,3));
/* 12 */sommetsValides.add(new Sommet(4,4));
/* 13 */sommetsValides.add(new Sommet(0,0));
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
        Sommet test = new Sommet(5,5);
        // Assure l'ajoutt de sommet
        for (int i = 0 ;  i < sommetsValidesSansDoublon.size() ; i++) {
            test.ajouterSommetVoisin(sommetsValidesSansDoublon.get(i));
            assertEquals(test.getListeDesVoisins().size(),i+1);
        }
        // Assure la levée d'exceptions si sommet deja existant
        for (int j = 0 ; j < sommetsValides.size()  ; j++) {
            Sommet aAjouter = sommetsValides.get(j);
            assertThrows(IllegalArgumentException.class, ()-> test.ajouterSommetVoisin(aAjouter));
        }
    }
    
    @DisplayName("Test supprimerVoisin sommet valide")
    @Test
    void testSupprimerVoisinValide() {
        Sommet avecVoisins = new Sommet(5,5);
        for (Sommet valide : sommetsValidesSansDoublon) {
            avecVoisins.ajouterSommetVoisin(valide);
        }
        
        Sommet aSupprimer = new Sommet(0,0);
        assertDoesNotThrow(()-> avecVoisins.supprimerSommetVoisin(aSupprimer));
        
    }
    
    @DisplayName("Test supprimerVoisin sommet invalide")
    @Test
    void testSupprimerVoisinInvalide() {
        Sommet avecVoisins = new Sommet(5,5);
        for (Sommet valide : sommetsValidesSansDoublon) {
            avecVoisins.ajouterSommetVoisin(valide);
        }
        
        Sommet aSupprimer = new Sommet(10,0);
        assertThrows(IllegalArgumentException.class, ()-> avecVoisins.supprimerSommetVoisin(aSupprimer));
        
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
    
    
    @DisplayName("Test toString")
    @Test
    void testToString() {
        String representation;
        Sommet sommet;
        
        representation = "";
        for (int i = 0; i < sommetsValidesSansDoublon.size(); i++) {
            sommet = sommetsValidesSansDoublon.get(i);
            representation = "(" + sommet.getPosX() + "; " + sommet.getPosY() + ")";
            assertEquals(representation, sommet.toString());
        } 
    }
    
    @DisplayName("Test hashcode")
    @Test
    void testHashCode() {
        int x,
        	y,
        	tailleVoisins,
        	res;
        
        x = y = tailleVoisins = 0;
        for (int i = 0; i < sommetsValides.size(); i++) {
            x = sommetsValides.get(i).getPosX();
            y = sommetsValides.get(i).getPosY();
            tailleVoisins = sommetsValides.get(i).getListeDesVoisins().size();
            res = x*100 + y * 10 + tailleVoisins;
            assertEquals(res, sommetsValides.get(i).hashCode());
        }
    
    }
    
    @DisplayName("Test equals")
    @Test
    void testEquals() {
        for (int i = 0; i < sommetsValidesSansDoublon.size(); i++) {
            assertEquals(sommetsValidesSansDoublon.get(i),sommetsValides.get(i));
        }
    }

}
