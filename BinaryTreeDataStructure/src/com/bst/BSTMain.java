package com.bst;

import java.util.Iterator;

public class BSTMain {
	
	public static void main(String[] args) {

		BST<Integer, String> binarySearchTree = new BST<Integer, String>();
		
		binarySearchTree.add(12, "Apple Tree");
		binarySearchTree.add(17, "Plum Tree");
		binarySearchTree.add(5, "Peach Tree");
		binarySearchTree.add(3, "Walnut Tree");
		binarySearchTree.add(7, "Oak Tree");
		binarySearchTree.add(14, "Birch Tree");
		binarySearchTree.add(22, "Pine Tree");
		binarySearchTree.add(1, "Coconut Tree");
		binarySearchTree.add(9, "Palm Tree");
		binarySearchTree.add(13, "Chery Tree");
		binarySearchTree.add(15, "Red Maple Tree");
		binarySearchTree.add(19, "Hemlock Tree");
		binarySearchTree.add(25, "Cedar Tree");
		binarySearchTree.add(8, "Buckeye Tree");
		binarySearchTree.add(11, "Red Spurce Tree");
		binarySearchTree.add(18, "Hickory Tree");
		binarySearchTree.add(23, "Orange Tree");
		binarySearchTree.add(27, "White Oak Tree");
		binarySearchTree.add(4, "Sycamore Tree");
		binarySearchTree.add(6, "Silver Birch Tree");
		
		/*binarySearchTree.put(12, "Apple Tree");
		binarySearchTree.put(5, "Peach Tree");
		binarySearchTree.put(17, "Plum Tree");
		binarySearchTree.put(3, "Walnut Tree");
		binarySearchTree.put(7, "Oak Tree");
		binarySearchTree.put(14, "Birch Tree");
		binarySearchTree.put(22, "Pine Tree");
		binarySearchTree.put(1, "Coconut Tree");
		binarySearchTree.put(9, "Palm Tree");
		binarySearchTree.put(13, "Chery Tree");
		binarySearchTree.put(15, "Red Maple Tree");
		binarySearchTree.put(19, "Hemlock Tree");
		binarySearchTree.put(25, "Cedar Tree");
		binarySearchTree.put(8, "Buckeye Tree");
		binarySearchTree.put(11, "Red Spurce Tree");
		binarySearchTree.put(18, "Hickory Tree");
		binarySearchTree.put(23, "Orange Tree");
		binarySearchTree.put(27, "White Oak Tree");*/
		
		System.out.println("BST Size:  " + binarySearchTree.size());
		System.out.println("BST Height:  " + binarySearchTree.height());
		
		Iterator<Integer> iterator = binarySearchTree.iterator();
		while(iterator.hasNext()) {
			int num = iterator.next();
			System.out.print(num + " ");
		}
		System.out.println();
		binarySearchTree.arrangeInOrder(binarySearchTree.getRoot());
		System.out.println();
		System.out.println();
		
		binarySearchTree.levelOrderTraversal(binarySearchTree.getRoot());
		System.out.println();
		
		/*binarySearchTree.remove(12);
		binarySearchTree.remove(11);
		binarySearchTree.remove(7);
		binarySearchTree.remove(19);*/
		//binarySearchTree.add(7, "Aspen Tree");
		
		binarySearchTree.preOrderTraversal(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.inOrderTraversal(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.postOrderTraversal(binarySearchTree.getRoot());
		
		binarySearchTree.printBST();
	}
}
