import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class A1Test <T> {
	String[] eingaben = { "Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };
	A1 a1 = new A1();
	
	public A1Test(){
		
	}

	@Test
	void testMain() {
		//fail("Not yet implemented");
	}

	@Test
	void testVeraendernCapsUndSpace() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals("EINGABE", eingabenNeu.get(0));
	}
	
	@Test
	void testVeraendernNullEntfernenLänge8() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals("STRASSEN", eingabenNeu.get(2));
	}
	
	@Test
	void testVeraendernInstanceOfT() {
		List<T> eingabenNeu = a1.veraendern(eingaben);
		assertEquals(true, (eingabenNeu.get(1) instanceof String));
		
		}
		
	}



