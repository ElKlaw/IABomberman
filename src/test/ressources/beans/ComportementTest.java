package beans;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Mouvement;

public class ComportementTest {

	
	private Contexte contexte = new Contexte();
	
	private static final String NAME_JOUEUR = "CIA";
	
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
	public void testEviterRetournerCentreBombeBas() {
		int X = X_MIN+2;
		int Y = Y_MIN+2; 
		Position positionBombe = new Position(X,Y);
		Position positionJoueur = new Position(X+1, Y);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeJoueur().add(joueur);	
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_TOP);
		
		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterRetournerCentreBombeHaut() {
		int X = X_MIN+2;
		int Y = Y_MIN+2; 
		Position positionBombe = new Position(X,Y);
		Position positionJoueur = new Position(X-1, Y);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeJoueur().add(joueur);	
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_BOTTOM);
		
		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterRetournerCentreBombeDroite() {
		int X = X_MIN+2;
		int Y = Y_MIN+2; 
		Position positionBombe = new Position(X,Y);
		Position positionJoueur = new Position(X, Y+1);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeJoueur().add(joueur);	
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_LEFT);
		
		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void testEviterRetournerCentreBombeGauche() {
		int X = X_MIN+2;
		int Y = Y_MIN+2; 
		Position positionBombe = new Position(X,Y);
		Position positionJoueur = new Position(X, Y-1);		
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeBombe().add(bombe);
		contexte.getListeJoueur().add(joueur);	
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_RIGHT);
		
		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}	
	
	@Test
	public void eviterBloquageSituation1() {
		int X = X_MIN;
		int Y = Y_MIN; 
		Position positionJoueur = new Position(X,Y+1);
		Position positionBombe = new Position(X,Y+1);
		
		Position positionCaisse1 = new Position(X,Y+3);
		Position positionCaisse2 = 	new Position(X+1,Y+2);	
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,2,-1,1,0,0,0,0,0},{0,1,-1,2,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}		
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_BOTTOM);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterBloquageSituation2() {
		int X = X_MIN;
		int Y = Y_MIN; 
		Position positionJoueur = new Position(X,Y+1);
		Position positionBombe = new Position(X,Y+1);
		
		Position positionCaisse1 = new Position(X,Y+3);
		Position positionCaisse2 = 	new Position(X+1,Y+2);	
		Position positionCaisse3 = 	new Position(X+1,Y+1);	
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		Caisse caisse3 = new Caisse();
		caisse3.setPosition(positionCaisse3);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeCaisse().add(caisse3);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,2,-1,1,0,0,0,0,0},{0,-1,-1,2,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}		
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterBloquageSituation3() {
		int X = X_MAX;
		int Y = Y_MIN; 
		Position positionJoueur = new Position(X,Y+1);
		Position positionBombe = new Position(X,Y+1);
		
		Position positionCaisse1 = new Position(X,Y+3);
		Position positionCaisse2 = 	new Position(X-1,Y+2);	
		Position positionCaisse3 = 	new Position(X-1,Y+1);	
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		Caisse caisse3 = new Caisse();
		caisse3.setPosition(positionCaisse3);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeCaisse().add(caisse3);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0},{0,-1,-1,2,0,0,0,0,0,0},{0,0,2,-1,1,0,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterBloquageSituation4() {
		int X = X_MIN;
		int Y = Y_MIN; 
		Position positionJoueur = new Position(X,Y+2);
		Position positionBombe = new Position(X,Y+2);
		
		Position positionCaisse1 = new Position(X+1,Y+2);
		Position positionCaisse2 = 	new Position(X+1,Y+3);	
		Position positionCaisse3 = 	new Position(X,Y+4);	
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		Caisse caisse3 = new Caisse();
		caisse3.setPosition(positionCaisse3);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeCaisse().add(caisse3);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,0,2,-1,1,0,0,0,0},{0,0,-1,-1,2,0,0,0,0,0},{0,0,0,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}			
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterBloquageSituation5() {
		int X = X_MAX;
		int Y = Y_MAX; 
		Position positionJoueur = new Position(X,Y-3);
		Position positionBombe = new Position(X,Y-3);
		
		Position positionCaisse1 = new Position(X-1,Y-3);
		Position positionCaisse2 = 	new Position(X-1,Y-4);	
		Position positionCaisse3 = 	new Position(X-1,Y-5);
		Position positionCaisse4 = 	new Position(X,Y-5);
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		Caisse caisse3 = new Caisse();
		caisse3.setPosition(positionCaisse3);
		Caisse caisse4 = new Caisse();
		caisse4.setPosition(positionCaisse4);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.getListeBombe().add(bombe);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeCaisse().add(caisse3);
		contexte.getListeCaisse().add(caisse4);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,1,1,0,0,0,0},{0,0,0,1,-1,-1,-1,0,0,0},{0,0,0,1,-1,2,0,0,0,0}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}			
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_RIGHT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterBloquageSituation6() {
		int X = X_MAX;
		int Y = Y_MAX; 
		Position positionJoueur = new Position(X-2,Y-1);
		Position positionBombe = new Position(X-2,Y-1);
		
		Position positionCaisse1 = new Position(X,Y-1);
		Position positionCaisse2 = 	new Position(X-1,Y);	
		Position positionCaisse3 = 	new Position(X-2,Y);
		Position positionCaisse4 = 	new Position(X-1,Y-2);
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		Caisse caisse2 = new Caisse();
		caisse2.setPosition(positionCaisse2);
		Caisse caisse3 = new Caisse();
		caisse3.setPosition(positionCaisse3);
		Caisse caisse4 = new Caisse();
		caisse4.setPosition(positionCaisse4);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(2);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeCaisse().add(caisse2);
		contexte.getListeCaisse().add(caisse3);
		contexte.getListeCaisse().add(caisse4);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		float[][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,0,-1},{0,0,0,0,0,0,1,-1,3,-1},{0,0,0,0,0,0,0,2,-1,2}};
		contexte.setTableauPoints(table);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_TOP);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
		
	}
	
	@Test
	public void eviterMourirEnPosantBombe(){
		int X = X_MIN;
		int Y = Y_MIN; 
		Position positionJoueur = new Position(X+1,Y+3);
		Position positionBombe = new Position(X+1,Y+4);
		
		Position positionCaisse1 = new Position(X+2,Y+3);
		
		Caisse caisse1 = new Caisse();
		caisse1.setPosition(positionCaisse1);
		
		Bombe bombe = new Bombe();
		bombe.setRayon(1);
		bombe.setToursAvantExplosion(1);
		bombe.setPosition(positionBombe);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		
		contexte.getListeJoueur().add(joueur);	
		contexte.getListeCaisse().add(caisse1);
		contexte.getListeBombe().add(bombe);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.add(Mouvement.MOVE_TOP);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	
	
	@Test
	public void issueBlocage1() {
		String contexteJson = "{\"id\":\"5bcd9370857aba0001171750\",\"startDate\":\"2018-10-22T09:08:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa7\",\"name\":\"Dummy3\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4f07ba7b11b00016c8add\",\"name\":\"CIA\"},\"alive\":true,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0},\"bomb\":{\"timer\":3,\"radius\":1,\"playerName\":\"CIA\"}},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		contexte.addInfos(contexteJson,1,"5bcd9370857aba0001171750");
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_LEFT);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void issueBlocage2() {
		String contexteJson = "{\"id\":\"5bca0f34857aba000116d890\",\"startDate\":\"2018-10-19T17:07:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4f07ba7b11b00016c8add\",\"name\":\"CIA\"},\"alive\":true,\"score\":10,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":10.0},\"bomb\":{\"timer\":3,\"radius\":1,\"playerName\":\"CIA\"}},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa5\",\"name\":\"Dummy2\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		contexte.addInfos(contexteJson,1,"5bca0f34857aba000116d890");
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>();
		listeMouvementPossibleResultat.add(Mouvement.MOVE_TOP);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	
	
	@Test
	public void eviterJoueurCoin() {
		int X = X_MIN;
		int Y = Y_MIN; 
		
		Position positionJoueur = new Position(X+1,Y);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.setAlive(true);
		
		Position positionJoueur2 = new Position(X,Y);
		
		Joueur joueur2 = new Joueur();
		joueur2.setNom("ennemi");
		joueur2.setPosition(positionJoueur2);
		joueur2.setAlive(true);
		
		contexte.getListeJoueur().add(joueur);
		contexte.getListeJoueur().add(joueur2);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_TOP);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void eviterJoueurCoin2() {
		int X = X_MAX;
		int Y = Y_MAX; 
		
		Position positionJoueur = new Position(X-1,Y);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.setAlive(true);
		
		Position positionJoueur2 = new Position(X,Y);
		
		Joueur joueur2 = new Joueur();
		joueur2.setNom("ennemi");
		joueur2.setPosition(positionJoueur2);
		joueur2.setAlive(true);
		
		contexte.getListeJoueur().add(joueur);
		contexte.getListeJoueur().add(joueur2);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_BOTTOM);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_RIGHT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}
	
	@Test
	public void tuerJoueurCoin() {
		int X = X_MIN;
		int Y = Y_MIN;
		
		Position positionJoueurEnnemi = new Position(X,Y);
		
		Joueur joueur1 = new Joueur();
		joueur1.setNom("Ennemi");
		joueur1.setPosition(positionJoueurEnnemi);
		joueur1.setAlive(true);
		
		Position positionJoueur = new Position(X+1,Y);
		
		Joueur joueur = new Joueur();
		joueur.setNom(NAME_JOUEUR);
		joueur.setPosition(positionJoueur);
		joueur.setAlive(true);
		
		contexte.getListeJoueur().add(joueur);
		contexte.getListeJoueur().add(joueur1);
		
		Joueur monJoueur = contexte.getMonJoueur(NAME_JOUEUR);
		List<Mouvement> listeMouvementPossible = monJoueur.getDeplacementPossible(contexte);
		List<Mouvement> listeMouvementMortel = monJoueur.getDeplacementMortel(contexte);
		List<Mouvement> listeMouvementPossibleNonMortel  = new ArrayList<Mouvement>(); 
		for(Mouvement mouvement : listeMouvementPossible) {
			if(!listeMouvementMortel.contains(mouvement)) {
				listeMouvementPossibleNonMortel.add(mouvement);
			}
		}	
		
		List<Mouvement> listeMouvementPossibleResultat = new ArrayList<Mouvement>(Arrays.asList(Mouvement.values()));
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_TOP);
		listeMouvementPossibleResultat.remove(Mouvement.MOVE_LEFT);
		listeMouvementPossibleResultat.remove(Mouvement.BOMB);

		assertEquals(listeMouvementPossibleNonMortel.size(), listeMouvementPossibleResultat.size());
		assertTrue(listeMouvementPossibleNonMortel.containsAll(listeMouvementPossibleResultat));
	}	
	
	@Test
	public void verifierTestInitTableauPoint() {
		float[][] tableau = contexte.getTableauPoints();
		for(int i = 0; i < tableau.length; i++){
		   for(int j = 0; j < tableau[i].length; j++){
		     assertEquals(0, tableau[i][j]); 
		   }
		}
	}
	
	@Test
	public void verifierAjoutPointCaisse() {
		Position positionCaisse = new Position(1,1);
		contexte.ajoutPointCaisse(positionCaisse,1);
		float[][] tableau = contexte.getTableauPoints();
		assertEquals(1, tableau[0][1]);
		assertEquals(1, tableau[1][2]);
		assertEquals(1, tableau[2][1]);
		assertEquals(1, tableau[1][0]);
		
		Position positionCaisse2 = new Position(3,1);
		contexte.ajoutPointCaisse(positionCaisse2,1);
		assertEquals(1, tableau[0][1]);
		assertEquals(1, tableau[1][2]);
		assertEquals(2, tableau[2][1]);
		assertEquals(1, tableau[1][0]);
		assertEquals(1, tableau[3][0]);
		assertEquals(1, tableau[4][1]);
		assertEquals(1, tableau[3][2]);
		
	}

}
