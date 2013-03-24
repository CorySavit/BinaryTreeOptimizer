/*
 * Cory Savit
 * cas241@pitt.edu
 * Project 3: Binary Search Tree Optimizer
 */


public class BinaryTree {
	
	public Node root;
	public int cost;	//weighted internal path length
	
	//empty constructor
	public BinaryTree(){
		this.root = null;
	}
	
	//constuctor with a key and a cost, but no children
	public BinaryTree(String key, Integer c) {
		this.root = new Node(this, key, c, null, null);
		this.cost = c;
	}	
	
	//constructor with key, cost, and children
	public BinaryTree(String key, int c, BinaryTree tree1, BinaryTree tree2) {
		this.root = new Node(this, key, c, tree1.root, tree2.root);
		this.cost = c;
		
	}
	
	//redirect to take a value
	public String toString(){
		return getTree(this.root);
	}
	
	//formated output of tree values in a string. done recursively.
	public String getTree(Node tree){
		if (tree.leftChild != null && tree.rightChild != null){
			return "(" + tree.key + "   " + getTree(tree.leftChild) + "  " + getTree(tree.rightChild) + ")    ";	
		}
		else if (tree.leftChild == null && tree.rightChild != null){
			return "(" + tree.key + "   null  " + getTree(tree.rightChild) + ")    ";
		}
		else if (tree.leftChild != null && tree.rightChild == null){
			return "(" + tree.key + "   " + getTree(tree.leftChild) + "  null)    ";	
		}
		else {
			return "(" + tree.key + "   null  null    ";	
		}
	}


private class Node {
	private String key;
	private Node leftChild;
	private Node rightChild;
	
	//used to store information about children. Only used for string output.
	public Node(BinaryTree parent, String k, int cost, Node l, Node r){
		this.key = k;
		this.leftChild = l;
		this.rightChild = r;
	}

}

}


