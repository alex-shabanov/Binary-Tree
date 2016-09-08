package com.bst;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import com.logfile.LogFile;

public class BST<Key extends  Comparable<Key>, Value> {
	
	private Node root;
	private int height;
	private int size;
	
	BST(){
		root = null;
		size = height = 0;
	}
	
	protected class Node {
		private Key key;
		private Value value;
		private Node left, right;
		private Node(Key key, Value value){
			this.key = key;
			this.value = value;
			setLeft(null);
			setRight(null);
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		public Key getKey() {
			return key;
		}
		public Value getValue() {
			return value;
		}
	}

	private class BSTIterator implements Iterator<Key> {

		Stack<Node> stack = new Stack<Node>();
		
		BSTIterator(){
			pushNodeLeft(root);
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Key next() {
			Node node = stack.pop();
			pushNodeLeft(node.right);
			return node.key;
		}
		/*
		 * Initially pushNodeLeft method would start at the root and traverses the left most branch
		 * or the tree until the null node is reached. Then, as we use iterator next() method, we traverse
		 * the left most branch of the right node which we recently popped from the top of the stack. This process
		 * continues until all nodes of the tree are traversed, that is, until the stack is empty.  
		 */
		public void pushNodeLeft(Node node) {
			while(node != null){
				stack.push(node);
				node = node.left;
			}
		}
	}
	
	public Node getRoot() {
		return root;
	}
	
	public int size() {
		return size;
	}
	
	public int height() {
		height = findHeight(root);
		return height;
	}
	
	private int findHeight(Node node) {
		  if(node == null) return -1;
		  return 1 + Math.max(findHeight(node.left), findHeight(node.right));
	}
	
	public void printBST() {
		new DisplayBST(this);
	}
	
	public BSTIterator iterator() {
		return new BSTIterator();
	}
	
	public void traverse(Node node) {
		if(node == null) return;
		traverse(node.left);
		System.out.println(node.key);
		traverse(node.right);
	}
	
	public boolean isLeafNode(Node node) {
		return node.left == null && node.right == null;
	}
	
	public void put(Key key, Value value) {
		try {
			if(key == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "put(Key " + key + ", Value " + value + ")", "Null value not allowed as a key", e);
			return;
		}
		try {
			if(value == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "put(Key " + key + ", Value " + value + ")", "Null value not allowed as a Value prameter to Node", e);
			return;
		}
		if(root == null){
			Node newNode = new Node(key, value);
			root = newNode;
			size++;
			return;
		}
		Node currNode = root;
		Node prevNode = null;
		boolean isLeftOrRight = false;
		while(currNode != null){
			prevNode = currNode;
			int compare = key.compareTo(currNode.key);
			if(compare == 0){ // key already exists in the tree, so we just simply replace its value
				currNode.value = value;
				return;
			}
			else if(compare < 0){
				currNode = currNode.left;
				isLeftOrRight = true;
			}
			else {
				currNode = currNode.right;
				isLeftOrRight = false;
			}
		}
		if(isLeftOrRight) {
			prevNode.left = new Node(key, value);
		}
		else {
			prevNode.right = new Node(key, value);
		}
		size++;
	}
	
	public boolean add(Key key, Value value) {
		try {
			if(key == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "add(Key " + key + ", Value " + value + ")", "Null value not allowed as a key", e);
			return false;
		}
		try {
			if(value == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "add(Key " + key + ", Value " + value + ")", "Null value not allowed as a Value prameter to Node", e);
			return false;
		}
		if(root == null){
			Node newNode = new Node(key, value);
			root = newNode;
			size++;
			return true;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node currNode = stack.pop();
			int compare = key.compareTo(currNode.key);
			if(compare == 0){
				currNode.value = value;
				return true;
			}
			else if(compare < 0){
				if(currNode.left == null) {
					currNode.left = new Node(key, value);
					size++;
					return true;
				}
				else stack.push(currNode.left);
			}
			else {
				if(currNode.right == null) {
					currNode.right = new Node(key, value);
					size++;
					return true;
				}
				else stack.push(currNode.right);
			}
		}
		return false;
	}
	
	public Node findMinKey(Node prevNode, Node nextNode) {
		if(nextNode.left == null) return prevNode;
		return findMinKey(nextNode, nextNode.left);
	}
	
	/*
	 * This method removes the node along with its key and value for a binary search tree.
	 * Method returns true if the node was successfully removed from the tree, or false otherwise.
	 * This method uses traversal instead of recursion, although method findMinKey is recursive.
	 * 
	 */
	public boolean remove(Key key) {
		try {
			if(key == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "delete(Key " + key + ")", "Null value not allowed as a key", e);
			return false;
		}
		Node currNode = null;
		Node prevNode = null;
		Node minNode = null;
		boolean isLeftOrRight = false;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			currNode = stack.pop();
			int compare = key.compareTo(currNode.key);
			if(compare == 0){
				if(isLeafNode(currNode) && currNode == root){
					root = null;
				}
				else if(isLeafNode(currNode) && isLeftOrRight == true){
					prevNode.left = null;
				}
				else if(isLeafNode(currNode) && isLeftOrRight == false){
					prevNode.right = null;
				}
				else if(currNode.left == null && isLeftOrRight == false){
					prevNode.right = currNode.right;
					currNode.right = null;
				}
				else if(currNode.right == null && isLeftOrRight == false){
					prevNode.right = currNode.left;
					currNode.left = null;
				}
				else if(currNode.left == null && isLeftOrRight == true){
					prevNode.left = currNode.right;
					currNode.right = null;
				}
				else if(currNode.right == null && isLeftOrRight == true){
					prevNode.left = currNode.left;
					currNode.left = null;
				}
				else {
					minNode = findMinKey(currNode, currNode.right);
					if(minNode == currNode && minNode.right.right == null){
						currNode.key = minNode.right.key;
						currNode.value = minNode.right.value;
						currNode.right = null;
					}
					else if(minNode == currNode && minNode.right.right != null){
						currNode.key = minNode.right.key;
						currNode.value = minNode.right.value;
						currNode.right = currNode.right.right;
						currNode.right.right = null;
					}
					else {
						currNode.key = minNode.left.key;
						currNode.value = minNode.left.value;
						if(isLeafNode(minNode.left)) minNode.left = null;
						else {
							minNode.left = minNode.left.right;
							minNode.left.right = null;
						}
					}
				}
				size--;
				return true;
			}
			else if(compare < 0 && currNode.left != null){
				stack.push(currNode.left);
				prevNode = currNode;
				isLeftOrRight = true;
			}
			else if(compare > 0 && currNode.right != null){
				stack.push(currNode.right);
				prevNode = currNode;
				isLeftOrRight = false;
			}
		}
		return false;
	}
	
	public void arrangeInOrder(Node node) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "arrangeInOrder(Node " + node + ")", "Null value not allowed as a Node", e);
			return;
		}
		Deque<Node> deque = new LinkedList<Node>();
		ArrayList<Node> list = new ArrayList<Node>();
		deque.push(node);
		while(!deque.isEmpty()){
			Node currNode = deque.removeLast();
			list.add(currNode);
			if(currNode.left != null){
				deque.push(currNode.left);
			}
			if(currNode.right != null){
				deque.push(currNode.right);
			}
		}
		for(Node n: list){
			System.out.print(String.valueOf(n.key) + " ");
		}
	}
	
	public void levelOrderTraversal(Node node) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "levelOrderTraversal(Node " + node + ")", "Null value not allowed as a Node", e);
			return;
		}
		int count1 = 0, count2 = 1;
		Deque<Node> deque = new LinkedList<Node>();
		deque.push(node);
		while(!deque.isEmpty()){
			count2--;
			Node currNode = deque.removeLast();
			System.out.print(String.valueOf(currNode.key) + " ");
			if(currNode.left != null){
				count1++;
				deque.push(currNode.left);
			}
			if(currNode.right != null){
				count1++;
				deque.push(currNode.right);
			}
			if(count2 == 0){
				count2 = count1;
				count1 = 0;
				System.out.println("");
			}
		}
	}
	
	public Node get(Key key) {
		try {
			if(key == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "get(Key " + key + ")", "Null value not allowed as a Key", e);
			return null;
		}
		Node currNode = root;
		while(currNode != null){
			int compare = key.compareTo(currNode.key);
			if(compare == 0) return currNode;
			else if(compare < 0) currNode = currNode.left;
			else currNode = currNode.right;
		}
		return null;
	}
	
	public boolean contains(Key key) {
		try {
			if(key == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "contains(Key " + key + ")", "Null value not allowed as a Key", e);
			return false;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node currNode = stack.pop();
			int compare = key.compareTo(currNode.key);
			if(compare == 0) return true;
			else if(compare < 0)
				if(currNode.left != null) stack.push(currNode.left);
			else
				if(currNode.right != null) stack.push(currNode.right);
		}
		return false;
	}
	
	public void preOrderTraversal(Node node) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "preOrderTraversal(Node " + node + ")", "Null value not allowed as a node", e);
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Node currNode = node;
		stack.push(currNode);
		while(!stack.isEmpty()){
			currNode = stack.pop();
			System.out.print(String.valueOf(currNode.key) + " ");
			if(currNode.right != null){
				stack.push(currNode.right);
			}
			if(currNode.left != null){
				stack.push(currNode.left);
			}
		}
	}
	
	public void inOrderTraversal(Node node) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "inOrderTraversal(Node " + node + ")", "Null value not allowed as a node", e);
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Node currNode = node, nextNode = null, leftNode = null;
		stack.push(currNode);
		while(!stack.isEmpty()){
			currNode = stack.peek();
			if(currNode.left != null && currNode.left != leftNode){
				stack.push(currNode.left);
			}
			else {
				currNode = stack.pop();
				System.out.print(String.valueOf(currNode.key) + " ");
				if(stack.isEmpty() || currNode == root){
					if(currNode.right != null) stack.push(currNode.right);
					else break;
				}
				else {
					nextNode = stack.peek();
					leftNode = nextNode.left;
					if(currNode.right != null) stack.push(currNode.right);
				}
			}
		}
	}
	
	public void postOrderTraversal(Node node) {
		try {
			if(node == null) throw new IllegalArgumentException();
		} catch(IllegalArgumentException e){
			LogFile.warn(this, "postOrderTraversal(Node " + node + ")", "Null value not allowed as a node", e);
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Node currNode = node, nextNode = null;
		Node leftNode = null, rightNode = null;
		stack.push(currNode);
		while(!stack.isEmpty()){
			currNode = stack.peek();
			if(currNode.left != null && currNode.left != leftNode){
				stack.push(currNode.left);
			}
			else if(currNode.right != null && currNode.right != rightNode){
				stack.push(currNode.right);
			}
			else {
				currNode = stack.pop();
				System.out.print(String.valueOf(currNode.key) + " ");
				if(stack.isEmpty()) break;
				nextNode = stack.peek();
				leftNode = nextNode.left;
				if(currNode == nextNode.right) rightNode = currNode;
			}
		}
	}
}
