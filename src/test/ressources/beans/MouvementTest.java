package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Mouvement;

public class MouvementTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testerMessageJson() {
		assertEquals("{ \"message\" : \"MOVE bottom\" }", Mouvement.MOVE_BOTTOM.getMessageJson());
		assertEquals("{ \"message\" : \"MOVE top\" }", Mouvement.MOVE_TOP.getMessageJson());
		assertEquals("{ \"message\" : \"MOVE right\" }", Mouvement.MOVE_RIGHT.getMessageJson());
		assertEquals("{ \"message\" : \"MOVE left\" }", Mouvement.MOVE_LEFT.getMessageJson());
		assertEquals("{ \"message\" : \"STAY\" }", Mouvement.STAY.getMessageJson());
		assertEquals("{ \"message\" : \"BOMB\" }", Mouvement.BOMB.getMessageJson());
	}
	
	@Test
	public void testerKey() {
		assertEquals("MOVE_BOTTOM", Mouvement.MOVE_BOTTOM.getKey());
		assertEquals("MOVE_TOP", Mouvement.MOVE_TOP.getKey());
		assertEquals("MOVE_RIGHT", Mouvement.MOVE_RIGHT.getKey());
		assertEquals("MOVE_LEFT", Mouvement.MOVE_LEFT.getKey());
		assertEquals("STAY", Mouvement.STAY.getKey());
		assertEquals("BOMB", Mouvement.BOMB.getKey());
	}
}
