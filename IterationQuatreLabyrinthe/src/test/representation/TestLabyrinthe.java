package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
            Labyrinthe aAfficher = new Labyrinthe(5,7 , true);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][0], aAfficher.listeSommet[0][1]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][1], aAfficher.listeSommet[0][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][2], aAfficher.listeSommet[0][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][2], aAfficher.listeSommet[1][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][3], aAfficher.listeSommet[0][4]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][5], aAfficher.listeSommet[0][6]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[0][5], aAfficher.listeSommet[1][5]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][0], aAfficher.listeSommet[1][1]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][1], aAfficher.listeSommet[1][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][3], aAfficher.listeSommet[1][4]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][5], aAfficher.listeSommet[1][6]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][0], aAfficher.listeSommet[2][0]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][2], aAfficher.listeSommet[2][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[2][1], aAfficher.listeSommet[3][1]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][4], aAfficher.listeSommet[2][4]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][3], aAfficher.listeSommet[2][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[3][1], aAfficher.listeSommet[3][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[3][2], aAfficher.listeSommet[3][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[2][0], aAfficher.listeSommet[3][0]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[2][3], aAfficher.listeSommet[3][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][5], aAfficher.listeSommet[2][5]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[1][6], aAfficher.listeSommet[2][6]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[2][4], aAfficher.listeSommet[3][4]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][0], aAfficher.listeSommet[3][0]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][0], aAfficher.listeSommet[4][1]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][1], aAfficher.listeSommet[4][2]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][2], aAfficher.listeSommet[4][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][3], aAfficher.listeSommet[3][3]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][4], aAfficher.listeSommet[4][5]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][4], aAfficher.listeSommet[3][4]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][5], aAfficher.listeSommet[4][6]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[4][6], aAfficher.listeSommet[3][6]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[3][4], aAfficher.listeSommet[3][5]);
			aAfficher.ajouterArrete(aAfficher.listeSommet[2][5], aAfficher.listeSommet[3][5]);
			aAfficher.listeSommet[0][0].setVoisin(true, 3);
			aAfficher.listeSommet[2][6].setVoisin(true, 1);
//            aAfficher.constructionBacktracking();
        	System.out.print(aAfficher);
        	System.out.println("fini ");
        }     
        {
            Labyrinthe aAfficher = new Labyrinthe(5,7 , true);
            aAfficher.chaineAscendante();
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
    
    @Test
    void testOntTousLaMemeMarque() {
        Labyrinthe g = new Labyrinthe(2,2);
        System.out.println("remier appele");
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
        Sommet s = new Sommet(3,3,123);
        for (Sommet[] sommets : g.getListeSommet()) {
            for (Sommet sTest : sommets) {
                System.out.print("Avant: "+sTest.getMarque());
                g.fusionnerMarques(s,sTest);
                System.out.println("Apres " + sTest.getMarque()+"\n");
            }
        }
        assertTrue(g.ontTousLaMemeMarque());
    }
}
