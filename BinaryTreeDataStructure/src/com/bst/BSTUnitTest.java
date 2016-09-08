package com.bst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BSTUnitTest {
	
BST<Integer, String> binarySearchTree;
	
	@Before
	public void setUp() {
		binarySearchTree = new BST<Integer, String>();
	}
	
	@After
	public void tearDown(){
		binarySearchTree = new BST<Integer, String>();
	}
	
	@Test
	public void addTest() {
		assertEquals(binarySearchTree.add(12, "Apple Tree"), true);
		assertEquals(binarySearchTree.add(5, "Peach Tree"), true);
		assertEquals(binarySearchTree.contains(12), true);
		assertEquals(binarySearchTree.contains(100), false);
	}
	
	@Test
	public void remveRootTest() {
		assertEquals(binarySearchTree.add(12, "Apple Tree"), true);
		assertEquals(binarySearchTree.contains(12), true);
		assertEquals(binarySearchTree.remove(12), true);
		assertEquals(binarySearchTree.add(14, "Birch Tree"), true);
		assertEquals(binarySearchTree.add(5, "Peach Tree"), true);
		assertEquals(binarySearchTree.contains(5), true);
		assertEquals(binarySearchTree.contains(101), false);
		assertEquals(binarySearchTree.remove(5), true);
		assertEquals(binarySearchTree.remove(101), false);
		assertEquals(binarySearchTree.contains(5), false);
	}
	
	@Test
	public void removeLeafNodeTest() {
		assertEquals(binarySearchTree.add(12, "Apple Tree"), true);
		assertEquals(binarySearchTree.add(17, "Plum Tree"), true);
		assertEquals(binarySearchTree.add(5, "Peach Tree"), true);
		assertEquals(binarySearchTree.add(3, "Walnut Tree"), true);
		assertEquals(binarySearchTree.add(7, "Oak Tree"), true);
		assertEquals(binarySearchTree.add(14, "Birch Tree"), true);
		assertEquals(binarySearchTree.add(22, "Pine Tree"), true);
		assertEquals(binarySearchTree.remove(3), true);
		assertEquals(binarySearchTree.remove(7), true);
		assertEquals(binarySearchTree.remove(14), true);
		assertEquals(binarySearchTree.remove(22), true);
	}

	@Test
	public void treeHeightTest() {
		assertEquals(binarySearchTree.height(), -1);
		assertEquals(binarySearchTree.add(12, "Apple Tree"), true);
		assertEquals(binarySearchTree.height(), 0);
		assertEquals(binarySearchTree.add(17, "Plum Tree"), true);
		assertEquals(binarySearchTree.height(), 1);
		assertEquals(binarySearchTree.add(5, "Peach Tree"), true);
		assertEquals(binarySearchTree.height(), 1);
		assertEquals(binarySearchTree.add(3, "Walnut Tree"), true);
		assertEquals(binarySearchTree.height(), 2);
		assertEquals(binarySearchTree.add(7, "Oak Tree"), true);
		assertEquals(binarySearchTree.height(), 2);
		assertEquals(binarySearchTree.add(14, "Birch Tree"), true);
		assertEquals(binarySearchTree.height(), 2);
		assertEquals(binarySearchTree.add(22, "Pine Tree"), true);
		assertEquals(binarySearchTree.height(), 2);
		assertEquals(binarySearchTree.add(25, "Oak Tree"), true);
		assertEquals(binarySearchTree.height(), 3);
		assertEquals(binarySearchTree.remove(22), true);
		assertEquals(binarySearchTree.height(), 2);
		assertEquals(binarySearchTree.remove(14), true);
		assertEquals(binarySearchTree.remove(3), true);
		assertEquals(binarySearchTree.remove(7), true);
		assertEquals(binarySearchTree.remove(25), true);
		assertEquals(binarySearchTree.height(), 1);
	}
	
	@Test
	public void removeNodeTest() {
		assertEquals(binarySearchTree.add(12, "Apple Tree"), true);
		assertEquals(binarySearchTree.remove(12), true);
		assertEquals(binarySearchTree.add(14, "Birch Tree"), true);
		assertEquals(binarySearchTree.add(17, "Plum Tree"), true);
		assertEquals(binarySearchTree.add(5, "Peach Tree"), true);
		assertEquals(binarySearchTree.add(3, "Walnut Tree"), true);
		assertEquals(binarySearchTree.add(7, "Oak Tree"), true);
		assertEquals(binarySearchTree.add(22, "Pine Tree"), true);
		assertEquals(binarySearchTree.add(1, "Coconut Tree"), true);
		assertEquals(binarySearchTree.add(9, "Palm Tree"), true);
		assertEquals(binarySearchTree.add(13, "Chery Tree"), true);
		assertEquals(binarySearchTree.add(15, "Red Maple Tree"), true);
		assertEquals(binarySearchTree.add(19, "Hemlock Tree"), true);
		assertEquals(binarySearchTree.add(25, "Cedar Tree"), true);
		assertEquals(binarySearchTree.add(8, "Buckeye Tree"), true);
		assertEquals(binarySearchTree.add(11, "Red Spurce Tree"), true);
		assertEquals(binarySearchTree.add(18, "Hickory Tree"), true);
		assertEquals(binarySearchTree.add(23, "Orange Tree"), true);
		assertEquals(binarySearchTree.add(27, "White Oak Tree"), true);
		
		assertEquals(binarySearchTree.remove(7), true);
		assertEquals(binarySearchTree.remove(11), true);
		assertEquals(binarySearchTree.remove(8), true);
		assertEquals(binarySearchTree.remove(14), true);
	}
}
