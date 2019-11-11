package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testerConstructeur() {
		Position position = new Position(1, 2);
		
		assertEquals(1, position.getX());
		assertEquals(2, position.getY());
		
		Position position2 = new Position(8, 1);
		
		assertEquals(8, position2.getX());
		assertEquals(1, position2.getY());
	}
	
	@Test
	public void testerGetterSetter() {
		Position position = new Position();
		position.setX(6);
		position.setY(9);
		
		assertEquals(6, position.getX());
		assertEquals(9, position.getY());
		
		Position position2 = new Position();
		position2.setLocation(0, 9);;
		
		assertEquals(0, position2.getX());
		assertEquals(9, position2.getY());
	}
	
	@Test
	public void testerVoisin() {
		Position position = new Position(1,3);
		
		Position positionVoisinDroite = position.getVoisinDroite();
		assertEquals(1, positionVoisinDroite.getX());
		assertEquals(4, positionVoisinDroite.getY());
		
		Position positionVoisinGauche = position.getVoisinGauche();
		assertEquals(1, positionVoisinGauche.getX());
		assertEquals(2, positionVoisinGauche.getY());
		
		Position positionVoisinHaut = position.getVoisinHaut();
		assertEquals(0, positionVoisinHaut.getX());
		assertEquals(3, positionVoisinHaut.getY());
		
		Position positionVoisinBas = position.getVoisinBas();
		assertEquals(2, positionVoisinBas.getX());
		assertEquals(3, positionVoisinBas.getY());
	}

	@Test
	public void testerVoisin2() {
		Position position = new Position(8,5);
		
		Position positionVoisinDroite = position.getVoisinDroite();
		assertEquals(8, positionVoisinDroite.getX());
		assertEquals(6, positionVoisinDroite.getY());
		
		Position positionVoisinGauche = position.getVoisinGauche();
		assertEquals(8, positionVoisinGauche.getX());
		assertEquals(4, positionVoisinGauche.getY());
		
		Position positionVoisinHaut = position.getVoisinHaut();
		assertEquals(7, positionVoisinHaut.getX());
		assertEquals(5, positionVoisinHaut.getY());
		
		Position positionVoisinBas = position.getVoisinBas();
		assertEquals(9, positionVoisinBas.getX());
		assertEquals(5, positionVoisinBas.getY());
	}
	
	@Test
	public void testerNbreCoupMin() {
		Position positionDepart = new Position(1,1);
		Position positionArrive = new Position(1,3);
		assertEquals(2, positionDepart.getNbreCoupMin(positionArrive));
		
		Position positionDepart1 = new Position(1,1);
		Position positionArrive1 = new Position(3,6);
		assertEquals(7, positionDepart1.getNbreCoupMin(positionArrive1));
	}
}
