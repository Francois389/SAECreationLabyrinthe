/**
 * TestLabyrintheJson.java                       31 mai 2023
 * IUT de Rodez, pas de copyleft, pas de copyright
 */
package test.sauvegarde;

import representation.Labyrinthe;
import sauvegarde.LabyrintheJson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * //TODO Commenter la responsabilités de la classe TestLabyrintheJson
 * @author francois
 *
 */
class TestLabyrintheJson {

    /**
     * Test method for {@link sauvegarde.LabyrintheJson#enregistrerLabyrinthe(representation.Labyrinthe)}.
     */
    @Test
    void testEnregistrerLabyrinthe() {
        Labyrinthe test = new Labyrinthe(10, 10);
        test.constructionBacktracking();
        LabyrintheJson.enregistrerLabyrinthe(test);
    }

    /**
     * Test method for {@link sauvegarde.LabyrintheJson#chargerLabyrinthe()}.
     */
    @Test
    void testChargerLabyrinthe() {
		Labyrinthe test = LabyrintheJson.chargerLabyrinthe();
		System.out.println(test);
    }

}
