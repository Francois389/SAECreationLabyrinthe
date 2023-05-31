/**
 * TestLabyrintheJson.java                       31 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.sauvegarde;

import representation.Labyrinthe;
import sauvegarde.LabyrintheJson;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * //TODO Commenter la responsabilités de la classe TestLabyrintheJson
 * @author francois
 *
 */
class TestLabyrintheJson {
    
    private static ArrayList<Labyrinthe> labyrinthe;

    @BeforeAll
    void creerJeuxTest() {
        labyrinthe = new ArrayList<>();
        Labyrinthe test = new Labyrinthe(10, 10);
        test.constructionBacktracking();
        labyrinthe.add(test);
    }
    
    /**
     * Test method for {@link sauvegarde.LabyrintheJson#enregistrerLabyrinthe(representation.Labyrinthe)}.
     */
    @Test
    void testEnregistrerLabyrinthe() {
        LabyrintheJson.enregistrerLabyrinthe(labyrinthe.get(0));
    }

    /**
     * Test method for {@link sauvegarde.LabyrintheJson#chargerLabyrinthe()}.
     */
    @Test
    void testChargerLabyrinthe() {
		Labyrinthe test = LabyrintheJson.chargerLabyrinthe();
		assertEquals(labyrinthe.get(0), test);
		System.out.println(test);
    }

}
