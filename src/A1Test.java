import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Lars Kowoll, Philip Zirfa�
 * @version 11/2018
 * 
 */

class A1Test <T> {
	String[] eingaben = { "Eingabe ", "�u�eres ", null, "Strassen-Feger", " ein Haus" };
	A1 a1 = new A1();
	
	/**
	 * Testet Methode 'veraendern' anhand des Arrays 'eingaben'.
	 */
	@Test
	void testVeraendernCapsUndSpace() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals("EINGABE", eingabenNeu.get(0));
	}
	
	/**
	 * Testet, ob leere Strings entfernt werden und die L�nge der Strings auf 8 gek�rzt wird anhand des Arrays 'eingaben'.
	 */
	@Test
	void testVeraendernNullEntfernenL�nge8() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals("STRASSEN", eingabenNeu.get(2));
	}
	
	/**
	 * Testet die Art des R�ckgabewertes der Methode 'veraendern' anhand des Arrays 'eingaben'.
	 */
	@Test
	void testVeraendernInstanceOfT() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals(true, (eingabenNeu.get(1) instanceof String));
		
		}
	}



