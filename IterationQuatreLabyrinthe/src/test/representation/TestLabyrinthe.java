package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import outils.OutilsTableau;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import representation.Labyrinthe;
import representation.PileContigue;
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
    @DisplayName("Test du toString")
    void testToString() {
 
        {
            Labyrinthe aAfficher = new Labyrinthe(12,15);
            aAfficher.chaineAscendante();
            //System.out.print(aAfficher);
        }
    }
    
    @Test
    void testEstPresent(){
        Labyrinthe g = new Labyrinthe(2,2);
        Sommet s1 = new Sommet(3,1);
        assertFalse(g.estPresent(s1));
        assertTrue(g.estPresent(g.getListeSommet()[0][0]));
    }
    
    @Test
    void testOntTousLaMemeMarque() {
        Labyrinthe g = new Labyrinthe(2,2);

        assertFalse(g.ontTousLaMemeMarque());
        for (Sommet[] sommets : g.getListeSommet()) {
            for (Sommet sTest : sommets) {
                sTest.setMarque(1);
            }
        }
        assertTrue(g.ontTousLaMemeMarque());
    }

    
    @Test
    void testFusionnerMarques() {
        Labyrinthe g = new Labyrinthe(2,2);
        Sommet s = new Sommet(3,3);
        for (Sommet[] sommets : g.getListeSommet()) {
            for (Sommet sTest : sommets) {
                System.out.print("Avant: "+sTest.getMarque());
                g.fusionnerMarques(s,sTest);
                System.out.println("Apres " + sTest.getMarque()+"\n");
            }
        }
        assertTrue(g.ontTousLaMemeMarque());
    }
    


    @Test
    void testBacktracking() {
        System.out.println("Debut");
        Labyrinthe test = new Labyrinthe(20, 20);
        test.constructionBacktracking();
//        System.out.println(test);
        
        
    }

    @Test
    void testProfondeur() {
        Labyrinthe test = new Labyrinthe(5, 5);
        test.constructionBacktracking();
        System.out.println(test);
        PileContigue parcours = test.parcoursProfondeur();
        System.out.println(parcours);
    }
    
}
