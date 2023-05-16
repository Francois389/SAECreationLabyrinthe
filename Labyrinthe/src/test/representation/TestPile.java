package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import representation.Pile;

import org.junit.jupiter.api.Test;

class TestPile {

	@Test
	void testConstructeur() {
		assertDoesNotThrow(()-> new Pile());
		
	}

	
	@Test
	void testEmpiler() {
		Pile p = new Pile();
		assertDoesNotThrow(()->p.empiler("bonjour"));
		assertDoesNotThrow(()->p.empiler(true));
		assertDoesNotThrow(()->p.empiler(1));
	}

	@Test
	void testDepiler() {
		Pile p = new Pile();
		Pile p1 = p.empiler("1er");
		Pile p2 = p1.empiler(2);

		assertTrue(p2.depiler().depiler().estVide());

		p1 = p1.depiler();
		assertTrue(p1.estVide());
		
		p2 = p2.depiler();
		assertFalse(p2.estVide());
		p2 = p2.depiler();
		assertTrue(p2.estVide());
	}

	@Test
	void testEstVide() {
		Pile p1 = new Pile();
		Pile p2 = new Pile().empiler("bonsoir");

		assertTrue(p1.estVide());
		assertFalse(p2.estVide());
	}

}
