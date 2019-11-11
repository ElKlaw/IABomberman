package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BonusTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testerContructeur() {
		Position positionBonus = new Position(2, 3);
		Bonus bonus1 = new Bonus(positionBonus);
		assertEquals(2, bonus1.getPosition().getX());
		assertEquals(3, bonus1.getPosition().getY());
		
		Position positionBonus2 = new Position(1, 5);
		Bonus bonus2 = new Bonus(positionBonus2,false);
		assertEquals(1, bonus2.getPosition().getX());
		assertEquals(5, bonus2.getPosition().getY());
		assertEquals(false, bonus2.isDisponibleSansCasserCaisse());
		
		Position positionBonus3 = new Position(8, 9);
		Bonus bonus3 = new Bonus(positionBonus3,true,1);
		assertEquals(8, bonus3.getPosition().getX());
		assertEquals(9, bonus3.getPosition().getY());
		assertEquals(true, bonus3.isDisponibleSansCasserCaisse());
		assertEquals(1, bonus3.getTypeBonus());
	}
	
	
	@Test
	public void testerGetterSetter() {
		Position positionBonus = new Position(7, 6);
		
		Bonus bonus = new Bonus();
		
		bonus.setDisponibleSansCasserCaisse(false);
		bonus.setPosition(positionBonus);
		bonus.setTypeBonus(2);
		
		assertEquals(7, bonus.getPosition().getX());
		assertEquals(6, bonus.getPosition().getY());
		assertEquals(false, bonus.isDisponibleSansCasserCaisse());
		assertEquals(2, bonus.getTypeBonus());
	}

}
