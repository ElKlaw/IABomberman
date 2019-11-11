package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateTree() {
		float[][] table = {{0,0,-1,0,0,0,0,0,0,0},{0,-1,0,0,0,0,0,0,0,0},{-1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		Tree tree = new Tree();
		tree.createTree(table, new Position(0,0));
		assertEquals(0, tree.getNodeSource().getPosition().getX());
		assertEquals(0, tree.getNodeSource().getPosition().getY());
		assertEquals(3, tree.getListeDesNoeud().size());
	}
	
	@Test
	public void testBestNode() {
		Contexte contexte = new Contexte();
		Bombe bombe = new Bombe();
		bombe.setPosition(new Position(2, 2));
		contexte.getListeBombe().add(bombe);
		float[][] table = {{0,2,-1,0,0,0,0,0,0,0},{1,-1,0,0,0,0,0,0,0,0},{2,-1,0,0,0,0,0,0,0,0},{-1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
		Tree tree = new Tree();
		tree.createTree(table, new Position(0,0));
		Node node = tree.getBestNode(contexte,1);
		assertEquals(0,node.getPosition().getX());
		assertEquals(1,node.getPosition().getY());
	}
}
