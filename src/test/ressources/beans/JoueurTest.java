package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Mouvement;

public class JoueurTest {
	
	private static final String NAME_JOUEUR = "CIA";
	private static final int RAYON_BOMBE_INITIALE=1;
	private static final int NBRE_BOMBE_INITIALE=1;
	private static final int X_MIN=0;
	private static final int X_MAX=9;
	private static final int Y_MIN=0;
	private static final int Y_MAX=9;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testerGetterSetter() {
		Position positionJoueur = new Position(3,2);
		
		Joueur joueur = new Joueur();
		joueur.setAlive(false);
		joueur.setId("abc1");
		joueur.setNom("CIA");
		joueur.setScore(51);
		joueur.setPosition(positionJoueur);
		joueur.initBonus(RAYON_BOMBE_INITIALE,NBRE_BOMBE_INITIALE);
		
		assertEquals("abc1", joueur.getId());
		assertEquals(false, joueur.isAlive());
		assertEquals("CIA", joueur.getNom());
		assertEquals(51, joueur.getScore());
		assertEquals(3, joueur.getPosition().getX());
		assertEquals(2, joueur.getPosition().getY());
		assertEquals(1, joueur.getRayonExplosionBombe());
		assertEquals(1, joueur.getNbreBombeMax());
	}

	
	@Test
	public void testEviterContourCoinHautGauche() {
		Contexte contexte = new Contexte();
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(new Position(X_MIN,Y_MIN));
		
		contexte.getListeJoueur().add(joueur);
		List<Mouvement> listeMouvementPossible = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		listeMouvementPossible = monJoueur.eviterContour(listeMouvementPossible);
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_BOTTOM);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_RIGHT);
		listeMouvementPossibleResultat.add(Mouvement.STAY);
		
		assertEquals(listeMouvementPossible.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossible.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterContourCoinHautDroit() {
		Contexte contexte = new Contexte();
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(new Position(X_MIN, Y_MAX));
		
		contexte.getListeJoueur().add(joueur);
		List<Mouvement> listeMouvementPossible = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		listeMouvementPossible = monJoueur.eviterContour(listeMouvementPossible);
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_BOTTOM);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.add(Mouvement.STAY);
		
		assertEquals(listeMouvementPossible.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossible.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterContourCoinBasGauche() {
		Contexte contexte = new Contexte();
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(new Position(X_MAX, Y_MIN));
		
		contexte.getListeJoueur().add(joueur);
		List<Mouvement> listeMouvementPossible = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		listeMouvementPossible = monJoueur.eviterContour(listeMouvementPossible);
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_TOP);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_RIGHT);
		listeMouvementPossibleResultat.add(Mouvement.STAY);
		
		assertEquals(listeMouvementPossible.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossible.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterContourCoinBasDroit() {
		Contexte contexte = new Contexte();
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(new Position(X_MAX, Y_MAX));
		
		contexte.getListeJoueur().add(joueur);
		List<Mouvement> listeMouvementPossible = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossible.remove(Mouvement.BOMB);
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		listeMouvementPossible = monJoueur.eviterContour(listeMouvementPossible);
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_TOP);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.add(Mouvement.STAY);
		
		assertEquals(listeMouvementPossible.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossible.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testJoueurDansCoin() {
		
		Position positionJoueur = new Position(0,0);
		Joueur joueur = new Joueur();
		joueur.setPosition(positionJoueur);
		
		Position positionJoueur2 = new Position(0,9);
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(positionJoueur2);
		
		Position positionJoueur3 = new Position(9,0);
		Joueur joueur3 = new Joueur();
		joueur3.setPosition(positionJoueur3);
		
		Position positionJoueur4 = new Position(9,9);
		Joueur joueur4 = new Joueur();
		joueur4.setPosition(positionJoueur4);
		
		Position positionJoueur5 = new Position(1,8);
		Joueur joueur5 = new Joueur();
		joueur5.setPosition(positionJoueur5);
		
		assertTrue(joueur.estDansCoin());
		assertTrue(joueur2.estDansCoin());
		assertTrue(joueur3.estDansCoin());
		assertTrue(joueur4.estDansCoin());
		assertFalse(joueur5.estDansCoin());
	}
	
	@Test
	public void testPoserBombeFaitPoint() {
		Contexte contexte = new Contexte();
		
		int X = X_MIN;
		int Y = Y_MIN;
		
		Position positionBombe1 = new Position(X,Y);
		Bombe bombe = new Bombe();
		bombe.setPosition(positionBombe1);
		bombe.setNomPoseur("Ennemi");
		bombe.setRayon(1);
		
		Position positionCaisse = new Position(X+1,Y);
		Caisse caisse = new Caisse();
		caisse.setPosition(positionCaisse);
		
		Position positionJoueur = new Position(X+2,Y);
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.setAlive(true);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeCaisse().add(caisse);
		
		assertEquals(false, joueur.poserBombeFaitPoint(contexte));
	}
	
	
	@Test
	public void testPoserBombeFaitPointSituationComplexe() {
		Contexte contexte = new Contexte();
		
		int X = X_MIN;
		int Y = Y_MIN;
		
		Position positionBombe1 = new Position(X,Y);
		Bombe bombe = new Bombe();
		bombe.setPosition(positionBombe1);
		bombe.setNomPoseur("Ennemi");
		bombe.setRayon(1);
		
		Position positionCaisse = new Position(X+1,Y);
		Caisse caisse = new Caisse();
		caisse.setPosition(positionCaisse);
		
		Position positionCaisse2 = new Position(X+2,Y+1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		
		Position positionJoueur = new Position(X+2,Y);
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.setAlive(true);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeCaisse().add(caisse);
		contexte.getListeCaisse().add(caisse2);
		
		assertEquals(true, joueur.poserBombeFaitPoint(contexte));
	}
	
	
	@Test
	public void testerEviterBombe() {
		Contexte contexte = new Contexte();
		
		Bombe bombe= new Bombe();
		bombe.setPosition(new Position(5, 5));
		bombe.setRayon(2);
		bombe.setToursAvantExplosion(1);
		contexte.getListeBombe().add(bombe);
		
		Joueur joueur = new Joueur();
		joueur.setPosition(new Position(6, 5));
		contexte.getListeJoueur().add(joueur);
		
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(new Position(4, 5));
		contexte.getListeJoueur().add(joueur2);
		
		Joueur joueur3 = new Joueur();
		joueur3.setPosition(new Position(5, 6));
		contexte.getListeJoueur().add(joueur3);
		
		Joueur joueur4 = new Joueur();
		joueur4.setPosition(new Position(5, 4));
		contexte.getListeJoueur().add(joueur4);
		
		List<Mouvement> listeMouvementEviterBombeJoueur1 = joueur.eviterBombes(contexte);
		List<Mouvement> listeMouvementEviterBombeJoueur1Resultat = new ArrayList<Mouvement>();
		listeMouvementEviterBombeJoueur1Resultat.add(Mouvement.STAY);
		listeMouvementEviterBombeJoueur1Resultat.add(Mouvement.MOVE_TOP);
		listeMouvementEviterBombeJoueur1Resultat.add(Mouvement.MOVE_BOTTOM);
		
		assertEquals(listeMouvementEviterBombeJoueur1.size(), listeMouvementEviterBombeJoueur1Resultat.size());
		assertTrue(listeMouvementEviterBombeJoueur1.containsAll(listeMouvementEviterBombeJoueur1Resultat));
		
	}
	
	@Test 
	public void testerPeutPoserBombe() {
		Contexte contexte = new Contexte();
		
		Bombe bombe= new Bombe();
		bombe.setNomPoseur("CIA");
		bombe.setPosition(new Position(5, 5));
		bombe.setRayon(2);
		bombe.setToursAvantExplosion(1);
		contexte.getListeBombe().add(bombe);
		
		Joueur joueur = new Joueur();
		joueur.setPosition(new Position(6, 5));
		joueur.setNom("CIA");
		joueur.initBombesJoueur(contexte);
		
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(new Position(8, 5));
		joueur2.setNom("ENNEMI");
		joueur2.initBombesJoueur(contexte);
		
		assertEquals(true, joueur2.peutPoserBombe());
		assertEquals(false, joueur.peutPoserBombe());
	}
	
	@Test 
	public void testerNbreDeToursAvantPoserBombe() {
		Contexte contexte = new Contexte();
		
		Bombe bombe= new Bombe();
		bombe.setNomPoseur("CIA");
		bombe.setPosition(new Position(5, 5));
		bombe.setRayon(2);
		bombe.setToursAvantExplosion(1);
		contexte.getListeBombe().add(bombe);
		
		Bombe bombe2= new Bombe();
		bombe2.setNomPoseur("ENNEMI");
		bombe2.setPosition(new Position(8, 5));
		bombe2.setRayon(2);
		bombe2.setToursAvantExplosion(3);
		contexte.getListeBombe().add(bombe2);
		
		Joueur joueur = new Joueur();
		joueur.setPosition(new Position(6, 5));
		joueur.setNom("CIA");
		joueur.initBombesJoueur(contexte);
		
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(new Position(8, 5));
		joueur2.setNom("ENNEMI");
		joueur2.initBombesJoueur(contexte);
		
		Joueur joueur3 = new Joueur();
		joueur3.setPosition(new Position(8, 5));
		joueur3.setNom("ENNEMI2");
		joueur3.initBombesJoueur(contexte);
		
		assertEquals(0, joueur3.getNbreDeToursAvantPoserBombe());
		assertEquals(3, joueur2.getNbreDeToursAvantPoserBombe());
		assertEquals(1, joueur.getNbreDeToursAvantPoserBombe());
	}
	
	@Test 
	public void testerAllerJusquauPoint() {
		Joueur joueur = new Joueur();
		joueur.setPosition(new Position(6, 5));
		joueur.setNom("CIA");
		
		List<Mouvement> listeMouvementJoueur1 = joueur.allerJusquauPoint(new Position(9, 7));
		List<Mouvement> listeMouvementResultatJoueur1 = new ArrayList<Mouvement>();
		listeMouvementResultatJoueur1.add(Mouvement.MOVE_RIGHT);
		listeMouvementResultatJoueur1.add(Mouvement.MOVE_BOTTOM);
		
		assertEquals(listeMouvementJoueur1.size(), listeMouvementResultatJoueur1.size());
		assertTrue(listeMouvementJoueur1.containsAll(listeMouvementResultatJoueur1));
	
		
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(new Position(8, 5));
		joueur2.setNom("ENNEMI");
		
		List<Mouvement> listeMouvementJoueur2 = joueur.allerJusquauPoint(new Position(4, 3));
		List<Mouvement> listeMouvementResultatJoueur2 = new ArrayList<Mouvement>();
		listeMouvementResultatJoueur2.add(Mouvement.MOVE_LEFT);
		listeMouvementResultatJoueur2.add(Mouvement.MOVE_TOP);
		
		assertEquals(listeMouvementJoueur2.size(), listeMouvementResultatJoueur2.size());
		assertTrue(listeMouvementJoueur2.containsAll(listeMouvementResultatJoueur2));
	}
	
	
	@Test
	public void testerPrisBonus() {
		Contexte contexte = new Contexte();
		
		Joueur joueur1 = new Joueur();
		joueur1.setPosition(new Position(0, 0));
		Bonus bonus1 = new Bonus(new Position(1,0));
		bonus1.setTypeBonus(2);
		contexte.getListeBonus().add(bonus1);
		contexte.getListeJoueur().add(joueur1);
		
		Joueur joueur2 = new Joueur();
		joueur2.setPosition(new Position(1, 0));
		Bonus bonus2 = new Bonus(new Position(0,0));
		bonus2.setTypeBonus(2);
		contexte.getListeBonus().add(bonus2);
		contexte.getListeJoueur().add(joueur2);
		
		Joueur joueur3 = new Joueur();
		joueur3.setPosition(new Position(0, 1));
		Bonus bonus3 = new Bonus(new Position(0,0));
		bonus3.setTypeBonus(1);
		contexte.getListeBonus().add(bonus3);
		contexte.getListeJoueur().add(joueur3);
		
		Joueur joueur4 = new Joueur();
		joueur4.setPosition(new Position(0, 0));
		Bonus bonus4 = new Bonus(new Position(0,1));
		bonus4.setTypeBonus(1);
		contexte.getListeBonus().add(bonus4);
		contexte.getListeJoueur().add(joueur4);
		
		Joueur joueur5 = new Joueur();
		joueur5.setPosition(new Position(0, 0));
		Bonus bonus5 = new Bonus(new Position(0,1));
		bonus5.setTypeBonus(1);
		contexte.getListeBonus().add(bonus5);
		contexte.getListeJoueur().add(joueur5);
		
		assertEquals(2, joueur1.prisBonus(contexte, Mouvement.MOVE_BOTTOM));
		assertEquals(2, joueur2.prisBonus(contexte, Mouvement.MOVE_TOP));
		assertEquals(2, joueur3.prisBonus(contexte, Mouvement.MOVE_LEFT));
		assertEquals(1, joueur4.prisBonus(contexte, Mouvement.MOVE_RIGHT));
		assertEquals(0, joueur5.prisBonus(contexte, Mouvement.MOVE_TOP));
	}
	
	
	@Test
	public void testerPositionLaPlusInterresante() {
		Contexte contexte = new Contexte();
		Contexte contexte2 = new Contexte();
		Contexte contexte3 = new Contexte();
		
		Joueur joueur1 = new Joueur();
		joueur1.setPosition(new Position(0, 1));
		contexte.getListeJoueur().add(joueur1);
		
		Bombe bombe= new Bombe();
		bombe.setNomPoseur("CIA");
		bombe.setPosition(new Position(0, 1));
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(3);
		contexte.getListeBombe().add(bombe);
		
		float[][] table = {{0,0,-1,0,0,0,0,0,0,0},{1,-1,0,0,0,0,0,0,0,0},{-1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		Node nodeLePlusInterresante = joueur1.getPositionLaPlusInterresante(contexte,0);
		
		assertEquals(1, nodeLePlusInterresante.getPosition().getX());
		assertEquals(0, nodeLePlusInterresante.getPosition().getY());
		
		contexte2.getListeJoueur().add(joueur1);
		contexte2.getListeBombe().add(bombe);
		
		float[][] table2 = {{0,0,1,-1,1,0,0,0,0,0},{1,0,1,3,-1,1,0,0,0,0},{-1,2,-1,-1,2,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte2.setTableauPoints(table2);
		
		Node nodeLePlusInterresante2 = joueur1.getPositionLaPlusInterresante(contexte2,0);
		
		assertEquals(1, nodeLePlusInterresante2.getPosition().getX());
		assertEquals(3, nodeLePlusInterresante2.getPosition().getY());
		
		contexte3.getListeJoueur().add(joueur1);
		
		float[][] table3 = {{0,0,2,-1,1,0,0,0,0,0},{0,1,-1,2,0,0,0,0,0,0},{0,2,-1,1,0,0,0,0,0,0},{2,-1,2,0,0,0,0,0,0,0},{-1,1,0,0,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte3.setTableauPoints(table3);
		
		Node nodeLePlusInterresante3 = joueur1.getPositionLaPlusInterresante(contexte3,0);
		
		assertEquals(0, nodeLePlusInterresante3.getPosition().getX());
		assertEquals(2, nodeLePlusInterresante3.getPosition().getY());
		
	}
}
