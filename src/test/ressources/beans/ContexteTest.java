package beans;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContexteTest {
	
	private Contexte contexte = new Contexte();
	
	private static final String NAME_JOUEUR = "CIA";
	private static final int RAYON_BOMBE_INITIALE=1;
	
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
	public void testerAjoutInfos1() {
		String jsonPlateau = "{\"id\":\"5bdc4924a7b11b0001174f0d\",\"startDate\":\"2018-11-02T12:55:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":false,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":30,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":30.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bdc4924a7b11b0001174f0d");
	
		Contexte contexteResultat = new Contexte();
		contexteResultat.setId("5bdc4924a7b11b0001174f0d");
		contexteResultat.setLevel(3);
		contexteResultat.setStartDate("2018-11-02T12:55:00Z");
		
		Position positionJoueur1 = new Position(0, 8);
		Joueur joueur1 = new Joueur();
		joueur1.setId("5bb4ef2fa7b11b00016c8aa6");
		joueur1.setAlive(false);
		joueur1.setNom("Dummy4");
		joueur1.setPosition(positionJoueur1);
		joueur1.setScore(0);
		
		Position positionJoueur2 = new Position(3, 1);
		Joueur joueur2 = new Joueur();
		joueur2.setId("5bb4ef2fa7b11b00016c8aa8");
		joueur2.setAlive(false);
		joueur2.setNom("Dummy1");
		joueur2.setPosition(positionJoueur2);
		joueur2.setScore(1);
		
		Position positionJoueur3 = new Position(9, 0);
		Joueur joueur3 = new Joueur();
		joueur3.setId("5bb50526a7b11b00016cc038");
		joueur3.setAlive(true);
		joueur3.setNom("STAY2");
		joueur3.setPosition(positionJoueur3);
		joueur3.setScore(0);
		
		Position positionJoueur4 = new Position(9, 1);
		Joueur joueur4 = new Joueur();
		joueur4.setId("5bb5051ea7b11b00016cc036");
		joueur4.setAlive(true);
		joueur4.setNom("STAY1");
		joueur4.setPosition(positionJoueur4);
		joueur4.setScore(30);
		
		contexteResultat.getListeJoueur().add(joueur1);
		contexteResultat.getListeJoueur().add(joueur2);
		contexteResultat.getListeJoueur().add(joueur3);
		contexteResultat.getListeJoueur().add(joueur4);
		
		
		contexteResultat.getListeCaisse().add(new Caisse(new Position(0, 4)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(0, 5)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(1, 2)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(1, 3)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(1, 4)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(1, 5)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 1)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 3)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 4)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 5)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 6)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(2, 9)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(3, 7)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(3, 8)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(4, 7)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(6, 0)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(6, 1)));
		contexteResultat.getListeCaisse().add(new Caisse(new Position(8, 1)));
		
		contexteResultat.getListeBonus().add(new Bonus(new Position(2, 1), false, 2));
		contexteResultat.getListeBonus().add(new Bonus(new Position(4, 0), true, 1));
		contexteResultat.getListeBonus().add(new Bonus(new Position(5, 4), true, 1));
		contexteResultat.getListeBonus().add(new Bonus(new Position(6, 1), false, 1));
		contexteResultat.getListeBonus().add(new Bonus(new Position(8, 6), true, 2));
		
		assertEquals(contexteResultat.getId(), contexte.getId());
		assertEquals(contexteResultat.getLevel(), contexte.getLevel());
		assertEquals(contexteResultat.getStartDate(), contexte.getStartDate());
		
		assertEquals(contexteResultat.getListeBombe().size(), contexte.getListeBombe().size());		
		
		assertEquals(contexteResultat.getListeBonus().size(), contexte.getListeBonus().size());
		
		assertEquals(contexteResultat.getListeCaisse().size(), contexte.getListeCaisse().size());
		
		assertEquals(contexteResultat.getListeJoueur().size(), contexte.getListeJoueur().size());
	}
	
	@Test
	public void testerGetMonJoueur() {
		String jsonPlateau = "{\"id\":\"5bdc4924a7b11b0001174f0d\",\"startDate\":\"2018-11-02T12:55:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":false,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":30,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":30.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bdc4924a7b11b0001174f0d");
		
		Joueur joueurStay1 = contexte.getMonJoueur("STAY1");
		assertEquals("5bb5051ea7b11b00016cc036", joueurStay1.getId());
		assertEquals(true, joueurStay1.isAlive());
		assertEquals("STAY1", joueurStay1.getNom());
		assertEquals(30, joueurStay1.getScore());
		assertEquals(9, joueurStay1.getPosition().getX());
		assertEquals(1, joueurStay1.getPosition().getY());
		assertEquals(1, joueurStay1.getRayonExplosionBombe());
		assertEquals(1, joueurStay1.getNbreBombeMax());
		
		
		Joueur joueurStay2 = contexte.getMonJoueur("STAY2");
		assertEquals("5bb50526a7b11b00016cc038", joueurStay2.getId());
		assertEquals(true, joueurStay2.isAlive());
		assertEquals("STAY2", joueurStay2.getNom());
		assertEquals(0, joueurStay2.getScore());
		assertEquals(9, joueurStay2.getPosition().getX());
		assertEquals(0, joueurStay2.getPosition().getY());
		assertEquals(1, joueurStay2.getRayonExplosionBombe());
		assertEquals(1, joueurStay2.getNbreBombeMax());
	}
	
	
	@Test
	public void getCaisseProche1() {
		String jsonPlateau = "{\"id\":\"5bdc4924a7b11b0001174f0d\",\"startDate\":\"2018-11-02T12:55:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":false,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":30,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":30.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bdc4924a7b11b0001174f0d");
		Joueur monJoueur = contexte.getMonJoueur("STAY1");
		
		assertTrue(contexte.isPointProcheDuJoueur(monJoueur));
		
		
		
	}
	
	@Test
	public void getCaisseProche2() {
		String jsonPlateau = "{\"id\":\"5bdc4924a7b11b0001174f0d\",\"startDate\":\"2018-11-02T12:55:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":false,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":30,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":30.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bdc4924a7b11b0001174f0d");
		Joueur monJoueur = contexte.getMonJoueur("STAY1");
		
		assertFalse(contexte.isPointProcheDuJoueur(monJoueur));
	}
	
	@Test
	public void testerAjoutPointBombe() {
		String jsonPlateau = "{\"id\":\"5bdc4924a7b11b0001174f0d\",\"startDate\":\"2018-11-02T12:55:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa6\",\"name\":\"Dummy4\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":false,\"score\":1,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":1.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":false,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":30,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":30.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bdc4924a7b11b0001174f0d");
		contexte.ajoutPointBombe();
		
		int[][] table = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		assertTrue(Arrays.deepEquals(table, contexte.getTableauPoints()));
	}
	
	@Test
	public void testerAjoutPointBombe2() {
		String jsonPlateau = "{\"id\":\"5bd50254da6ad5000134321b\",\"startDate\":\"2018-10-28T00:27:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"STAY1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\",\"bomb\":{\"timer\":3,\"radius\":1,\"playerName\":\"STAY1\"}},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5052ca7b11b00016cc03a\",\"name\":\"STAY3\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":2},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\",\"bomb\":{\"timer\":3,\"radius\":1,\"playerName\":\"Dummy1\"}},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bd50254da6ad5000134321b");
		
		int[][] tableResultatAvant = {{0,1,-1,3,-1,2,-1,2,1,0},{1,1,3,-1,-1,2,2,-1,-1,2},{-1,-1,-1,-1,3,-1,2,3,-1,-1},{2,3,-1,2,2,-1,-1,-1,2,1},{-1,-1,-1,3,-1,2,3,-1,2,0},{-1,-1,-1,-1,2,1,-1,-1,-1,1},{1,2,-1,3,0,0,3,-1,-1,1},{0, 1, -1, -1, 1, 2, -1, 4, -1, 1},{0, 0, 2, -1, 3, -1, -1, -1, -1, 1},{0, 0, 1, -1, -1, -1, -1, 2, 1, 0}};
		assertTrue(Arrays.deepEquals(tableResultatAvant, contexte.getTableauPoints()));
		
		contexte.ajoutPointBombe();
		
		int[][] tableResultatApres = {{0,0,-1,0,-1,1,-1,2,1,0},{1,1,1,-1,-1,2,2,-1,-1,2},{-1,-1,-1,-1,3,-1,2,3,-1,-1},{2,3,-1,2,2,-1,-1,-1,2,1},{-1,-1,-1,3,-1,2,3,-1,2,0},{-1,-1,-1,-1,2,1,-1,-1,-1,1},{1,2,-1,3,0,0,3,-1,-1,1},{0, 1, -1, -1, 1, 2, -1, 4, -1, 1},{0, 0, 2, -1, 3, -1, -1, -1, -1, 1},{0, 0, 0, -1, -1, -1, -1, 2, 1, 0}};
		assertTrue(Arrays.deepEquals(tableResultatApres, contexte.getTableauPoints()));
	}
	
	
	@Test
	public void testerAjoutPointJoueurCoin() {
		String jsonPlateau= "{\"id\":\"5bd50254da6ad5000134321b\",\"startDate\":\"2018-10-28T00:27:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"CIA\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5052ca7b11b00016cc03a\",\"name\":\"STAY3\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":2},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bd50254da6ad5000134321b");
		
		int[][] tableResultatAvant = {{0, 1, -1, 3, -1, 2, -1, 2, 1, 0},{1, 1, 3, -1, -1, 2, 2, -1, -1, 2},{-1, -1, -1, -1, 3, -1, 2, 3, -1, -1},{2, 3, -1, 2, 2, -1, -1, -1, 2, 1},{-1, -1, -1, 3, -1, 2, 3, -1, 2, 0},{-1, -1, -1, -1, 2, 1, -1, -1, -1, 1},{1, 2, -1, 3, 0, 0, 3, -1, -1, 1},{0, 1, -1, -1, 1, 2, -1, 4, -1, 1},{0, 0, 2, -1, 3, -1, -1, -1, -1, 1},{0, 0, 1, -1, -1, -1, -1, 2, 1, 0}};
		assertTrue(Arrays.deepEquals(tableResultatAvant, contexte.getTableauPoints()));
		
		//contexte.ajoutPointJoueurCoin();
		
		int[][] tableResultatApres = {{0, 1, -1, 3, -1, 2, -1, 2, 6, 0},{1, 1, 3, -1, -1, 2, 2, -1, -1, 7},{-1, -1, -1, -1, 3, -1, 2, 3, -1, -1},{2, 3, -1, 2, 2, -1, -1, -1, 2, 1},{-1, -1, -1, 3, -1, 2, 3, -1, 2, 0},{-1, -1, -1, -1, 2, 1, -1, -1, -1, 1},{1, 2, -1, 3, 0, 0, 3, -1, -1, 1},{0, 1, -1, -1, 1, 2, -1, 4, -1, 1},{0, 0, 2, -1, 3, -1, -1, -1, -1, 6},{0, 0, 1, -1, -1, -1, -1, 2, 6, 0}};
		assertTrue(Arrays.deepEquals(tableResultatApres, contexte.getTableauPoints()));
	}
	
	
	@Test
	public void testerPointPlusProche() {
		String jsonPlateau= "{\"id\":\"5bd50254da6ad5000134321b\",\"startDate\":\"2018-10-28T00:27:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"CIA\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5052ca7b11b00016cc03a\",\"name\":\"STAY3\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":2},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bd50254da6ad5000134321b");
		contexte.ajoutPointBombe();
		//contexte.ajoutPointJoueurCoin();
		Joueur monJoueur = contexte.getMonJoueur("CIA");
		
		Position position = contexte.getPointLePlusProche(monJoueur);
		Position positionResultat = new Position(0, 2);
		
		assertEquals(positionResultat, position);
	}
	
	@Test
	public void testerPointPlusProche2() {
		String jsonPlateau= "{\"id\":\"5bd50254da6ad5000134321b\",\"startDate\":\"2018-10-28T00:27:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5051ea7b11b00016cc036\",\"name\":\"CIA\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb5052ca7b11b00016cc03a\",\"name\":\"STAY3\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\",\"bonus\":2},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb4ef2fa7b11b00016c8aa8\",\"name\":\"Dummy1\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bb50526a7b11b00016cc038\",\"name\":\"STAY2\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}}]}],\"players\":[]}";
		Contexte contexte = new Contexte();
		contexte.addInfos(jsonPlateau,RAYON_BOMBE_INITIALE,"5bd50254da6ad5000134321b");
		contexte.ajoutPointBombe();
		//contexte.ajoutPointJoueurCoin();
		Joueur monJoueur = contexte.getMonJoueur("CIA");
		
		Position position = contexte.getPointLePlusProche(monJoueur);
		Position positionResultat = new Position(2, 0);
		
		assertEquals(positionResultat, position);
	}
}
