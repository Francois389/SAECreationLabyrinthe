/**
 * TestLabyrinthe.java                       5 juin 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import representation.Labyrinthe;
import representation.PileContigue;
import representation.Sommet;

/**
 * Test unitaire de la classe 
 * @author de Saint Palais François
 *
 */
class TestLabyrinthe {
    
private ArrayList<Labyrinthe> labyrintheCorrecte;
    
    public boolean existeArcEntre(Sommet sommet1, Sommet sommet2, Sommet[][] listeArcs) {
        for (int i = 0; i < listeArcs.length; i++) {
            if (listeArcs[i][0].equals(sommet1) && listeArcs[i][1].equals(sommet2)) {
                return true;
            }  
        }
        return false;
    }
    
    @BeforeEach
    void genererLabyrintheValide() {
        labyrintheCorrecte = new ArrayList<>(2);
        { /* petit labyrinthe */
            labyrintheCorrecte.add(new Labyrinthe(5, 5));            
        }
             
        { /* grand labyrinthe */
            labyrintheCorrecte.add(new Labyrinthe(30, 30)); 
        }
        
        { /* grand labyrinthe */
            labyrintheCorrecte.add(new Labyrinthe(10, 20)); 
        }
        Sommet[][] listeSommet = {
                {new Sommet(0,0),
                 new Sommet(1,0)},
                {new Sommet(0,1),
                 new Sommet(1,1)}
        };
        listeSommet[0][0].setVoisin(true, 1);
        listeSommet[0][1].setVoisin(new boolean[] {false,false,true,true});
        listeSommet[1][1].setVoisin(new boolean[] {true, false, false, true});
        listeSommet[1][0].setVoisin(true, 1);
        Sommet[][] listeArcs = {
                {listeSommet[0][0],listeSommet[1][1]},
                {listeSommet[0][1],listeSommet[0][0]},
                {listeSommet[0][1],listeSommet[1][1]},
                {listeSommet[1][1],listeSommet[0][1]},
                {listeSommet[1][1],listeSommet[1][0]},
                {listeSommet[1][0],listeSommet[1][1]}
        };
        Sommet entree = listeSommet[0][0];
        Sommet sorti = listeSommet[1][0];
        Labyrinthe laby = new Labyrinthe(2, 2, listeSommet, listeArcs, entree, sorti);
        
        labyrintheCorrecte.add(laby);
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
        
    /**
     * Test method for {@link representation.Labyrinthe#Labyrinthe(int, int)}.
     */
    @Test
    void testLabyrintheOKIntInt() {
        int hauteur = 5;
        int largeur = 6;
        
        assertDoesNotThrow(() -> new Labyrinthe(hauteur, largeur));
        Labyrinthe l = new Labyrinthe(hauteur, largeur);
        assertEquals(hauteur, l.getHauteur());
        assertEquals(largeur, l.getLargeur());
        assertEquals(0, l.getListeArcs().length);
        assertEquals(hauteur, l.getListeSommet().length);
        assertEquals(largeur, l.getListeSommet()[0].length);
        assertEquals(l.getListeSommet()[0][0], l.getEntre());
        assertEquals(l.getListeSommet()[hauteur-1][largeur-1], l.getSortie());
    }
    /**
     * Test method for {@link representation.Labyrinthe#Labyrinthe(int, int)}.
     */
    @Test
    @DisplayName("Labyrinthe Not Ok")
    void testLabyrintheNokIntInt() {
        assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(0, 0));
        assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(1, 1));
        assertThrows(IllegalArgumentException.class, ()->new Labyrinthe(-1, -1));
    }

    /**
     * Test method for {@link representation.Labyrinthe#Labyrinthe(int, int,
     *  Sommet[][], Sommet[][], Sommet, Sommet)}.
     */
    @Test
    void testLabyrintheIntIntSommetSommetSommetSommet() {
        int hauteur = 3;
        int largeur = 7;
        Sommet[][] listeSommet = {
                {new Sommet(0,0),
                 new Sommet(1,0),
                 new Sommet(2,0),
                 new Sommet(3,0),
                 new Sommet(4,0),
                 new Sommet(5,0),
                 new Sommet(6,0)
                 },
                {new Sommet(0,1),
                 new Sommet(1,1),
                 new Sommet(2,1),
                 new Sommet(3,1),
                 new Sommet(4,1),
                 new Sommet(5,1),
                 new Sommet(6,1)
                 },                
                {new Sommet(0,2),
                 new Sommet(1,2),
                 new Sommet(2,2),
                 new Sommet(3,2),
                 new Sommet(4,2),
                 new Sommet(5,2),
                 new Sommet(6,2)}
                
        };
        Sommet[][] listeArcs = {
                {listeSommet[0][0],listeSommet[0][1]}
        };
        Labyrinthe laby 
        = new Labyrinthe(hauteur, largeur, listeSommet, listeArcs, 
                         listeSommet[0][0], listeSommet[hauteur-1][largeur-1]);
        assertEquals(hauteur, laby.getHauteur());
        assertEquals(largeur, laby.getLargeur());
        assertEquals(1, laby.getListeArcs().length);
        assertEquals(2, laby.getListeArcs()[0].length);
        assertEquals(hauteur, laby.getListeSommet().length);
        assertEquals(largeur, laby.getListeSommet()[0].length);
        assertEquals(laby.getListeSommet()[0][0], laby.getEntre());
        assertEquals(laby.getListeSommet()[hauteur-1][largeur-1], laby.getSortie());
    }
    
    @Test
    void testBacktracking() {
        Labyrinthe test = new Labyrinthe(20, 20);
        test.constructionBacktracking();
    }
    
    /**
     * Test method for {@link representation.Labyrinthe#chaineAscendante()}.
     */
    @Test
    void testChaineAscendante() {
        assertDoesNotThrow(()->labyrintheCorrecte.get(0).chaineAscendante());
        /** Un graphe correcte a le nombre de sommet moins une porte (arcs)*/
        assertEquals(labyrintheCorrecte.get(0).getHauteur()*labyrintheCorrecte.get(0).getLargeur()-1,
                     labyrintheCorrecte.get(0).getListeArcs().length);
    }

    
    /**
     * Test method for {@link representation.Labyrinthe#constructionBacktracking()}.
     */
    @Test
    void testConstructionBacktracking() {
        assertDoesNotThrow(()->labyrintheCorrecte.get(0).constructionBacktracking());
        /** Un graphe correcte a le nombre de sommet moins une porte (arcs)*/
        assertEquals(labyrintheCorrecte.get(0).getHauteur()*labyrintheCorrecte.get(0).getLargeur()-1,
                labyrintheCorrecte.get(0).getListeArcs().length);
    }
    
    /**
     * Test method for {@link representation.Labyrinthe#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsObject() {
        Labyrinthe l0 = new Labyrinthe(5, 5);
        Labyrinthe l1 = new Labyrinthe(30, 30);
        Labyrinthe l2 = new Labyrinthe(10, 20);
        assertEquals(l0, labyrintheCorrecte.get(0));
        assertEquals(l1, labyrintheCorrecte.get(1));
        assertEquals(l2, labyrintheCorrecte.get(2));
        
        Sommet[][] listeSommet1 = {
                {new Sommet(0,0),
                 new Sommet(1,0)},
                {new Sommet(0,1),
                 new Sommet(1,1)}
        };
        listeSommet1[0][0].setVoisin(true, 1);
        listeSommet1[0][1].setVoisin(new boolean[] {false,false,true,true});
        listeSommet1[1][1].setVoisin(new boolean[] {true, false, false, true});
        listeSommet1[1][0].setVoisin(true, 1);
        Sommet[][] listeArcs1 = {
                {listeSommet1[0][0],listeSommet1[1][1]},
                {listeSommet1[0][1],listeSommet1[0][0]},
                {listeSommet1[0][1],listeSommet1[1][1]},
                {listeSommet1[1][1],listeSommet1[0][1]},
                {listeSommet1[1][1],listeSommet1[1][0]},
                {listeSommet1[1][0],listeSommet1[1][1]}
        };
        Sommet entree1 = listeSommet1[0][0];
        Sommet sorti1 = listeSommet1[1][0];

        Sommet[][] listeSommet2 = {
                {new Sommet(0,0),
                 new Sommet(1,0)},
                {new Sommet(0,1),
                 new Sommet(1,1)}
        };
        listeSommet2[0][0].setVoisin(true, 1);
        listeSommet2[0][1].setVoisin(new boolean[] {false,false,true,true});
        listeSommet2[1][1].setVoisin(new boolean[] {true, false, false, true});
        listeSommet2[1][0].setVoisin(true, 1);
        Sommet[][] listeArcs2 = {
                {listeSommet2[0][0],listeSommet2[1][1]},
                {listeSommet2[0][1],listeSommet2[0][0]},
                {listeSommet2[0][1],listeSommet2[1][1]},
                {listeSommet2[1][1],listeSommet2[0][1]},
                {listeSommet2[1][1],listeSommet2[1][0]},
                {listeSommet2[1][0],listeSommet2[1][1]}
        };
        Sommet entree2 = listeSommet2[0][0];
        Sommet sorti2 = listeSommet2[1][0];
        Labyrinthe laby1 = new Labyrinthe(2, 2, listeSommet1, listeArcs1, entree1, sorti1);
        Labyrinthe laby2 = new Labyrinthe(2, 2, listeSommet2, listeArcs2, entree2, sorti2);
        
        assertEquals(laby1, laby2);
    }

    @Test
    void testEstPresent(){
        Labyrinthe g = new Labyrinthe(2,2);
        Sommet s1 = new Sommet(3,1);
        assertFalse(g.estPresent(s1));
        assertTrue(g.estPresent(g.getListeSommet()[0][0]));
    }
    
    @Test
    void testFusionnerMarques() {
        Labyrinthe g = new Labyrinthe(2,2);
        Sommet s = new Sommet(3,3);
        for (Sommet[] sommets : g.getListeSommet()) {
            for (Sommet sTest : sommets) {
                g.fusionnerMarques(s,sTest);
            }
        }
        assertTrue(g.ontTousLaMemeMarque());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getEntre()}.
     */
    @Test
    void testGetEntre() {
        Sommet origine = new Sommet(0,0);
        assertEquals(origine, labyrintheCorrecte.get(0).getEntre());
        assertEquals(origine, labyrintheCorrecte.get(1).getEntre());
        assertEquals(origine, labyrintheCorrecte.get(2).getEntre());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getHauteur()}.
     */
    @Test
    void testGetHauteur() {
        assertEquals(5 , labyrintheCorrecte.get(0).getHauteur());
        assertEquals(30 , labyrintheCorrecte.get(1).getHauteur());
        assertEquals(10 , labyrintheCorrecte.get(2).getHauteur());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getLargeur()}.
     */
    @Test
    void testGetLargeur() {
        assertEquals(5 , labyrintheCorrecte.get(0).getLargeur());
        assertEquals(30 , labyrintheCorrecte.get(1).getLargeur());
        assertEquals(20 , labyrintheCorrecte.get(2).getLargeur());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getListeArcs()}.
     */
    @Test
    void testGetListeArcs() {
        Sommet[][] listeSommet = {
                {new Sommet(0,0),
                 new Sommet(1,0)},
                {new Sommet(0,1),
                 new Sommet(1,1)}
        };
        listeSommet[0][0].setVoisin(true, 1);
        listeSommet[0][1].setVoisin(new boolean[] {false,false,true,true});
        listeSommet[1][1].setVoisin(new boolean[] {true, false, false, true});
        listeSommet[1][0].setVoisin(true, 1);
        Sommet[][] listeArcs = {
                {listeSommet[0][0],listeSommet[1][1]},
                {listeSommet[0][1],listeSommet[0][0]},
                {listeSommet[0][1],listeSommet[1][1]},
                {listeSommet[1][1],listeSommet[0][1]},
                {listeSommet[1][1],listeSommet[1][0]},
                {listeSommet[1][0],listeSommet[1][1]}
        };
        
        assertArrayEquals(listeArcs, labyrintheCorrecte.get(3).getListeArcs());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getListeSommet()}.
     */
    @Test
    void testGetListeSommet() {
        Sommet[][] listeSommet = {
                {new Sommet(0,0),
                 new Sommet(1,0)},
                {new Sommet(0,1),
                 new Sommet(1,1)}
        };
        assertArrayEquals(listeSommet, labyrintheCorrecte.get(3).getListeSommet());
    }

    /**
     * Test method for {@link representation.Labyrinthe#getSortie()}.
     */
    @Test
    void testGetSortie() {
        assertEquals(new Sommet(4,4), labyrintheCorrecte.get(0).getSortie());
        assertEquals(new Sommet(29,29), labyrintheCorrecte.get(1).getSortie());
        assertEquals(new Sommet(19,9), labyrintheCorrecte.get(2).getSortie());
        assertEquals(new Sommet(0,1), labyrintheCorrecte.get(3).getSortie());
    }

    

    @Test
    void testOntTousLaMemeMarque() {
        Labyrinthe g = new Labyrinthe(2,2);
        int compteur = 0;
        assertTrue(g.ontTousLaMemeMarque());
        for (Sommet[] sommets : g.getListeSommet()) {
            for (Sommet sTest : sommets) {
                sTest.setMarque(compteur);
                compteur++;
            }
        }
        assertFalse(g.ontTousLaMemeMarque());
    }

    /**
     * Test method for {@link representation.Labyrinthe#parcoursProfondeur()}.
     */
    @Test
    void testParcoursProfondeur() {
		int hauteur, largeur;
        hauteur = largeur = 3;
        Sommet[][] listeSommet = {
                {new Sommet(0, 0),
                 new Sommet(1, 0),
                 new Sommet(2, 0)},
                
                {new Sommet(0, 1),
                 new Sommet(1, 1),
                 new Sommet(2, 1)},
                
                {new Sommet(0, 2),
                 new Sommet(1, 2),
                 new Sommet(2, 2)}
        };
        //                                         haut  droite  Bas  Gauche
        listeSommet[0][0].setVoisin(new boolean[]{false, false, true, false});
        listeSommet[0][1].setVoisin(new boolean[]{false, true, false, false});
        listeSommet[0][2].setVoisin(new boolean[]{false, false, true, true});

        listeSommet[1][0].setVoisin(new boolean[]{true, true, true, false});
        listeSommet[1][1].setVoisin(new boolean[]{false, true, false, true});
        listeSommet[1][2].setVoisin(new boolean[]{true, false, true, true});
        
        listeSommet[2][0].setVoisin(new boolean[]{true, true, false, false});
        listeSommet[2][1].setVoisin(new boolean[]{false, false, false, true});
        listeSommet[2][2].setVoisin(new boolean[]{true, false, false, false});
        
        Sommet[][] listeArrete = {
                {listeSommet[0][0],listeSommet[1][0]},
                {listeSommet[1][0],listeSommet[0][0]},

                {listeSommet[1][0],listeSommet[1][1]},
                {listeSommet[1][1],listeSommet[1][0]},

                {listeSommet[1][0],listeSommet[2][0]},
                {listeSommet[2][0],listeSommet[1][0]},
                
                {listeSommet[2][0],listeSommet[2][1]},
                {listeSommet[2][1],listeSommet[2][0]},
                
                {listeSommet[1][1],listeSommet[1][2]},
                {listeSommet[1][2],listeSommet[1][1]},
                
                {listeSommet[0][2],listeSommet[1][2]},
                {listeSommet[1][2],listeSommet[0][2]},
                
                {listeSommet[1][2],listeSommet[2][2]},
                {listeSommet[2][2],listeSommet[1][2]},

                {listeSommet[0][2],listeSommet[0][1]},
                {listeSommet[0][1],listeSommet[0][2]},
                
        };
        Sommet entree = listeSommet[0][0];
        Sommet sortie = listeSommet[2][2];
        
        Labyrinthe aParcourir = new Labyrinthe
        (hauteur, largeur, listeSommet, listeArrete, entree, sortie);
        
        
        PileContigue parcoursCorrect = new PileContigue();
        parcoursCorrect.empiler(listeSommet[0][0]);
        parcoursCorrect.empiler(listeSommet[1][0]);
        parcoursCorrect.empiler(listeSommet[1][1]);
        parcoursCorrect.empiler(listeSommet[1][2]);
        parcoursCorrect.empiler(listeSommet[2][2]);
  
  		assertEquals(aParcourir.parcoursProfondeur(), parcoursCorrect);
  
    }


    /**
     * Test method for {@link representation.Labyrinthe#setEntre(representation.Sommet)}.
     */
    @Test
    void testSetEntre() {
        labyrintheCorrecte.get(0).setEntre(labyrintheCorrecte.get(0).getListeSommet()[1][3]);
        assertEquals(labyrintheCorrecte.get(0).getListeSommet()[1][3], 
                     labyrintheCorrecte.get(0).getEntre());
    }

    /**
     * Test method for {@link representation.Labyrinthe#setMarqueSommet()}.
     */
    @Test
    void testSetMarqueSommet() {
    	Labyrinthe lab = labyrintheCorrecte.get(0);
    	
    	int marque = 0;
    	for (Sommet[] listeSommets : lab.getListeSommet()) {
            for (Sommet s : listeSommets) {
                s.setMarque(marque);
                assertEquals(marque, s.getMarque());
                marque--;
            }
        }
    }

    /**
     * Test method for {@link representation.Labyrinthe#setSortie(representation.Sommet)}.
     */
    @Test
    void testSetSortie() {
        labyrintheCorrecte.get(0).setSortie(labyrintheCorrecte.get(0).getListeSommet()[1][3]);
        assertEquals(labyrintheCorrecte.get(0).getListeSommet()[1][3], 
                     labyrintheCorrecte.get(0).getSortie());
    }

    @Test
    @DisplayName("Test du toString")
    void testToString() {
        {
            Labyrinthe aAfficher = new Labyrinthe(12,15);
            aAfficher.chaineAscendante();
        }
    }
    
    @Test
    void testGetCoordoneeFromMarque() {
        Labyrinthe laby = labyrintheCorrecte.get(3);
        laby.setMarqueSommet();
        assertArrayEquals(new int[] {0,0}, laby.getCoordoneeFromMarque(1));
        assertArrayEquals(new int[] {0,1}, laby.getCoordoneeFromMarque(2));
        assertArrayEquals(new int[] {1,0}, laby.getCoordoneeFromMarque(3));
        assertArrayEquals(new int[] {1,1}, laby.getCoordoneeFromMarque(4));
        assertThrows(IllegalArgumentException.class,
                ()-> laby.getCoordoneeFromMarque(0));
        assertThrows(IllegalArgumentException.class,
                ()-> laby.getCoordoneeFromMarque(-1));
        assertThrows(IllegalArgumentException.class,
                ()-> laby.getCoordoneeFromMarque(5));
        assertThrows(IllegalArgumentException.class,
                ()-> laby.getCoordoneeFromMarque(Integer.MAX_VALUE));
    	
    }

}
