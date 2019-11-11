package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BombeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testerGetterSetter() {
		Position positionBombe = new Position(1, 1);
		
		Bombe bombe = new Bombe();
		bombe.setPosition(positionBombe);
		bombe.setNomPoseur("CIA");
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(3);
		
		assertEquals(1, bombe.getRayon());
		assertEquals(3, bombe.getToursAvantExplosion());
		assertEquals("CIA", bombe.getNomPoseur());
		assertEquals(1, bombe.getPosition().getX());
		assertEquals(1, bombe.getPosition().getY());
		
		
		Position positionBombe2 = new Position(3,6);
		
		Bombe bombe2 = new Bombe();
		bombe2.setPosition(positionBombe2);
		bombe2.setNomPoseur("CIA2");
		bombe2.setRayon(4);
		bombe2.setToursAvantExplosion(1);
		
		assertEquals(4, bombe2.getRayon());
		assertEquals(1, bombe2.getToursAvantExplosion());
		assertEquals("CIA2", bombe2.getNomPoseur());
		assertEquals(3, bombe2.getPosition().getX());
		assertEquals(6, bombe2.getPosition().getY());		
	}
	
	
	
	@Test
	public void testerZoneExplosionRayon1() {
		Position positionBombe = new Position(1, 1);
		
		Bombe bombe = new Bombe();
		bombe.setPosition(positionBombe);
		bombe.setRayon(1);
		
		Position pointDroite = new Position(positionBombe);
		pointDroite.addToY(1);
		Position pointGauche= new Position(positionBombe);
		pointGauche.addToY(-1);
		Position pointHaut= new Position(positionBombe);
		pointHaut.addToX(-1);
		Position pointBas=new Position(positionBombe);
		pointBas.addToX(1);
		
		List<Position> listePointExplosion = new ArrayList<Position>();
		listePointExplosion.add(positionBombe);
		listePointExplosion.add(pointBas);
		listePointExplosion.add(pointDroite);
		listePointExplosion.add(pointGauche);
		listePointExplosion.add(pointHaut);
		
		assertEquals(listePointExplosion.size(), bombe.getListePointExplosionBombe().size());
		assertTrue(bombe.getListePointExplosionBombe().containsAll(listePointExplosion));
		
	}
	
	@Test
	public void testerZoneExplosionRayon2() {
		Position positionBombe = new Position(1, 1);
		
		Bombe bombe = new Bombe();
		bombe.setPosition(positionBombe);
		bombe.setRayon(2);
		
		Position pointDroite = new Position(positionBombe);
		pointDroite.addToY(1);
		Position pointGauche= new Position(positionBombe);
		pointGauche.addToY(-1);
		Position pointHaut= new Position(positionBombe);
		pointHaut.addToX(-1);
		Position pointBas=new Position(positionBombe);
		pointBas.addToX(1);
		Position pointDroite2 = new Position(positionBombe);
		pointDroite2.addToY(2);
		Position pointGauche2= new Position(positionBombe);
		pointGauche2.addToY(-2);
		Position pointHaut2= new Position(positionBombe);
		pointHaut2.addToX(-2);
		Position pointBas2=new Position(positionBombe);
		pointBas2.addToX(2);
		
		List<Position> listePointExplosion = new ArrayList<Position>();
		listePointExplosion.add(positionBombe);
		listePointExplosion.add(pointBas);
		listePointExplosion.add(pointDroite);
		listePointExplosion.add(pointGauche);
		listePointExplosion.add(pointHaut);
		listePointExplosion.add(pointBas2);
		listePointExplosion.add(pointDroite2);
		listePointExplosion.add(pointGauche2);
		listePointExplosion.add(pointHaut2);
		
		assertEquals(listePointExplosion.size(), bombe.getListePointExplosionBombe().size());
		assertTrue(bombe.getListePointExplosionBombe().containsAll(listePointExplosion));
		
	}

}
