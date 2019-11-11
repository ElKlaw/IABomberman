package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaisseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testerConstructeur() {
		Position positionCaisse = new Position(3,2);
		
		Caisse caisse = new Caisse(positionCaisse);
		
		assertEquals(3, caisse.getPosition().getX());
		assertEquals(2, caisse.getPosition().getY());
	}

	@Test
	public void testerGetterSetter() {
		Position positionCaisse = new Position(6,7);
		
		Caisse caisse = new Caisse();
		caisse.setPosition(positionCaisse);
		
		assertEquals(6, caisse.getPosition().getX());
		assertEquals(7, caisse.getPosition().getY());
	}

}
