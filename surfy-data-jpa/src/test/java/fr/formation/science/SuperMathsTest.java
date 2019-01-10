package fr.formation.science;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class SuperMathsTest {
	private SuperMaths superMaths = new SuperMaths();
	
	
	@BeforeClass
	public static void beforeAll() {
		System.out.println("JE SUIS AVANT TOUT LE MONDE");
	}
	
	
	@Test
	public void additionner() {
		assertEquals(5, superMaths.additionner(2, 3));
	}
	
	
	@Test
	public void soustraire() {
		assertEquals(-1, superMaths.soustraire(2, 3));
	}
}
