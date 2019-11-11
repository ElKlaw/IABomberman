package beans;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testerGetterSetter() {
		Position positionNodeParent = new Position(3, 2);
		Node nodeParent = new Node();
		nodeParent.setNodeParent(null);
		nodeParent.setPosition(positionNodeParent);
		
		Position positionNode = new Position(4, 2);
		Node node = new Node();
		node.setNodeParent(nodeParent);
		node.setPosition(positionNode);
		node.setValue(1);
		
		assertEquals(1, node.getValue());
		assertEquals(4, node.getPosition().getX());
		assertEquals(2, node.getPosition().getY());
		assertEquals(nodeParent, node.getNodeParent());
	}
	
	@Test 
	public void testerConstructeur() {
		Position positionNode = new Position(1, 2);
		Node node = new Node(null, 2, positionNode);
		
		assertEquals(1, node.getPosition().getX());
		assertEquals(2, node.getPosition().getY());
		assertEquals(2, node.getValue());
		assertEquals(null, node.getNodeParent());
	}

	@Test
	public void testerDepth() {
		Node node1 = new Node();
		node1.setNodeParent(null);

		Node node2 = new Node();
		node2.setNodeParent(node1);
		
		Node node3 = new Node();
		node3.setNodeParent(node2);
		
		Node node4 = new Node();
		node4.setNodeParent(node3);
		
		assertEquals(1, node1.getDepth());
		assertEquals(2, node2.getDepth());
		assertEquals(3, node3.getDepth());
		assertEquals(4, node4.getDepth());
	}
	
	
	
}
