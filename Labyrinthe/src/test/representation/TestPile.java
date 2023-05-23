package test.representation;

import static org.junit.jupiter.api.Assertions.*;

import representation.Pile;
import representation.PileVideException;

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
		assertThrows(NullPointerException.class, ()->p.empiler(null));
	}

	@Test
	void testDepiler() {
		Pile p = new Pile();
		p = p.empiler(126).empiler(2);

		assertFalse(p.depiler().estVide());
		assertTrue(p.depiler().estVide());
	}

	@Test
	void testEstVide() {
		Pile p1 = new Pile();
		Pile p2 = new Pile().empiler("bonsoir");

		assertTrue(p1.estVide());
		assertFalse(p2.estVide());
	}

	@Test
	void testSommet() {
		Pile p = new Pile();
		p = p.empiler(1).empiler("2").empiler(true);

		assertEquals(p.sommet(), true);
		p.depiler();
		assertEquals(p.sommet(), "2");
		p.depiler();
		assertEquals(p.sommet(), 1);
		p.depiler();

		Pile temp = p;
		
		assertThrows(PileVideException.class, 
					 ()-> temp.sommet());

	}


}
