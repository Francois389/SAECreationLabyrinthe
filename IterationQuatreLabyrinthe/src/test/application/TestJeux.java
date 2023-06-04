/**
 * TestJeux.java                       3 juin 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.Jeux;
import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe TestJeux
 * @author de
 *
 */
class TestJeux {
    
    private Jeux labyrinthe;

    @BeforeEach
    void genererJeuxTest() {
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
        listeSommet[0][0].setVoisin(true,2);
        listeSommet[1][0].setVoisin(new boolean[]{true, true, true, false});
        listeSommet[2][0].setVoisin(true,0);
        listeSommet[2][0].setVoisin(true,1);
        listeSommet[0][1].setVoisin(true,1);
        listeSommet[1][1].setVoisin(true,1);
        listeSommet[1][1].setVoisin(true,3);
        listeSommet[2][1].setVoisin(true,3);
        listeSommet[0][2].setVoisin(true,2);
        listeSommet[0][2].setVoisin(true,3);
        listeSommet[1][2].setVoisin(new boolean[] {true, false, true, true});
        listeSommet[2][2].setVoisin(true,0);
        
        Sommet[][] listeArrete = {
                {listeSommet[0][0],listeSommet[0][1]},
                {listeSommet[0][1],listeSommet[1][1]},
                {listeSommet[0][1],listeSommet[0][2]},
                {listeSommet[1][1],listeSommet[2][1]},
                {listeSommet[0][2],listeSommet[1][2]},
                {listeSommet[2][1],listeSommet[2][0]},
                {listeSommet[2][1],listeSommet[2][2]},
                {listeSommet[2][0],listeSommet[1][0]},
        };
        Sommet entree = listeSommet[0][0];
        Sommet sortie = listeSommet[2][2];
        labyrinthe 
        = new Jeux(hauteur, largeur, listeSommet, listeArrete, 
                   entree, sortie, 0, 0);
    }

    /**
     * Test method for {@link application.Jeux#getPosXJoueur()}.
     */
    @Test
    void testGetPosXJoueur() {
        assertEquals(0, labyrinthe.getPosXJoueur());
    }

    /**
     * Test method for {@link application.Jeux#getPosYJoueur()}.
     */
    @Test
    void testGetPosYJoueur() {
        assertEquals(0, labyrinthe.getPosYJoueur());
    }

    /**
     * Test method for {@link application.Jeux#setPosXJoueur(int)}.
     */
    @Test
    void testSetPosXJoueur() {
        assertDoesNotThrow(()->labyrinthe.setPosXJoueur(1));
        assertEquals(1, labyrinthe.getPosXJoueur());
        
        /* Il n'existe pas de pièce aux coordonnée 3 */
        assertThrows(IllegalArgumentException.class,()-> labyrinthe.setPosXJoueur(3));

        /* Il n'existe pas de pièce aux coordonnée -1 */
        assertThrows(IllegalArgumentException.class,()-> labyrinthe.setPosXJoueur(-1));
    }

    /**
     * Test method for {@link application.Jeux#setPosYJoueur(int)}.
     */
    @Test
    void testSetPosYJoueur() {
        assertDoesNotThrow(()->labyrinthe.setPosYJoueur(1));
        assertEquals(1, labyrinthe.getPosYJoueur());
        
        /* Il n'existe pas de pièce aux coordonnée 3 */
        assertThrows(IllegalArgumentException.class,()-> labyrinthe.setPosYJoueur(3));

        /* Il n'existe pas de pièce aux coordonnée -1 */
        assertThrows(IllegalArgumentException.class,()-> labyrinthe.setPosYJoueur(-1));
    }

    /**
     * Test method for {@link application.Jeux#estSorti()}.
     */
    @Test
    void testEstSorti() {
        assertFalse(labyrinthe.estSorti());
        
        labyrinthe.setPosXJoueur(2);
        labyrinthe.setPosYJoueur(2);
        assertTrue(labyrinthe.estSorti());
    }

    /**
     * Test method for {@link application.Jeux#deplacementValide(char)}.
     */
    @Test
    void testDeplacementValide() {
        System.out.println(labyrinthe);
        assertTrue(labyrinthe.deplacementValide('B'));
        assertTrue(labyrinthe.deplacementValide('b'));
        assertFalse(labyrinthe.deplacementValide('H'));
        assertFalse(labyrinthe.deplacementValide('h'));
        assertFalse(labyrinthe.deplacementValide('D'));
        assertFalse(labyrinthe.deplacementValide('d'));
        assertFalse(labyrinthe.deplacementValide('G'));
        assertFalse(labyrinthe.deplacementValide('g'));
        
        labyrinthe.setPosXJoueur(1);
        labyrinthe.setPosYJoueur(1);
        System.out.println(labyrinthe);
        assertThrows(IllegalArgumentException.class,()->labyrinthe.deplacementValide('a'));
    }

}
