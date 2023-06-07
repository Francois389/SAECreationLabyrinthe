package test.representation;

import representation.PileContigue;
import exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
class TestPileContigue {

	@Test
	void testConstructeur() {
		assertDoesNotThrow(()-> new PileContigue());
	}

	@Test
	void testEstVide() {
		PileContigue p1 = new PileContigue();
		PileContigue p2 = new PileContigue().empiler("bonsoir");

		assertTrue(p1.estVide());
		assertFalse(p2.estVide());
	}
	
	
	@Test
	void testEmpiler() {
		PileContigue p = new PileContigue();
		assertDoesNotThrow(()->p.empiler("bonjour"));
		assertDoesNotThrow(()->p.empiler(true));
		assertDoesNotThrow(()->p.empiler(1));
		
		assertThrows(NullPointerException.class, ()->p.empiler(null));
	}

	@Test
	void testDepiler() {
		PileContigue p = new PileContigue();
		p = p.empiler(126).empiler(2);

		assertFalse(p.depiler().estVide());
		assertTrue(p.depiler().estVide());
		
		PileContigue vide = new PileContigue();
		assertThrows(PileVideException.class, ()->vide.depiler());
	}


	@Test
	void testSommet() {
		PileContigue p = new PileContigue();
		p = p.empiler(1).empiler("2").empiler(true);

		
		assertEquals(p.sommet(), true);
		p.depiler();
		assertEquals(p.sommet(), "2");
		p.depiler();
		assertEquals(p.sommet(), 1);
		p.depiler();
		
		PileContigue temp = p;
		
		assertThrows(PileVideException.class, 
					 ()-> temp.sommet());

	}


	@Test
	void testGrossePile() {
		PileContigue grossePile = new PileContigue();
			
		for (int i = 0; i < 1_000_000; i++) {
			grossePile.empiler(i);
		}
		
		assertEquals(grossePile.sommet(), 999999);
	
		for (int i = 0; i < 1_000_000; i++) {
			grossePile.depiler();
		}
		
		assertTrue(grossePile.estVide());
		
	}


	@Test
	void testEquals() {
		PileContigue p1 = new PileContigue().empiler(2).empiler("oui");
		PileContigue p2 = new PileContigue().empiler(2).empiler("oui");
		
		assertTrue(p1.equals(p2));
		
		p1.depiler();
		assertNotEquals(p1, p2);
		
	}

}
