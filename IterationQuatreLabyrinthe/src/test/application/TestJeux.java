/**
 * TestJeux.java                       3 juin 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Jeux;
import representation.Labyrinthe;
import representation.Sommet;

/**
 * //TODO Commenter la responsabilités de la classe TestJeux
 * @author de
 *
 */
class TestJeux {

    /**
     * Test method for {@link application.Jeux#getPosXJoueur()}.
     */
    @Test
    void testGetPosXJoueur() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link application.Jeux#getPosYJoueur()}.
     */
    @Test
    void testGetPosYJoueur() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link application.Jeux#setPosXJoueur(int)}.
     */
    @Test
    void testSetPosXJoueur() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link application.Jeux#setPosYJoueur(int)}.
     */
    @Test
    void testSetPosYJoueur() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link application.Jeux#estSorti()}.
     */
    @Test
    void testEstSorti() {
        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for {@link application.Jeux#deplacementValide(char)}.
     */
    @Test
    void testDeplacementValide() {
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
        Jeux labyrinthe 
        = new Jeux(hauteur, largeur, listeSommet, listeArrete, 
                   entree, sortie, 0, 0);
        System.out.println(labyrinthe);

        assertTrue(labyrinthe.deplacementValide('B'));
        assertTrue(labyrinthe.deplacementValide('b'));
        assertFalse(labyrinthe.deplacementValide('H'));
        assertFalse(labyrinthe.deplacementValide('h'));
        assertFalse(labyrinthe.deplacementValide('D'));
        assertFalse(labyrinthe.deplacementValide('d'));
        assertFalse(labyrinthe.deplacementValide('G'));
        assertFalse(labyrinthe.deplacementValide('g'));
        assertThrows(IllegalArgumentException.class,()->labyrinthe.deplacementValide('a'));
    }

}
