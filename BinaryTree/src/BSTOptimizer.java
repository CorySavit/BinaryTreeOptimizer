/*
 * Cory Savit
 * cas241@pitt.edu
 * Project 3: Binary Search Tree Optimizer
 * 
 * Notes: The # of recursive calls does not match Dr. Aronis'. I don't know why.
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

public class BSTOptimizer {

	public boolean MEMOIZE;						//memoize on or off
	public int CALLS;							//counts recursive calls
	public BinaryTree[][] optTree;				//matrix of optimized binary trees
	public BinaryTree best2;
	public ArrayList<String> keys;				//array list of keys
	public Hashtable<String, Integer> values;	//Hashtable of values, indexed by keys
	
	//empty constructor
	public BSTOptimizer(){
		this.keys = new ArrayList<String>();
		this.values = new Hashtable<String, Integer>();
	}
	
	//adds a key
	public void addKey(String key, int value) {
		this.keys.add(key);
		this.values.put(key, value);
	}
	
	/*Initial call to optimize with no arguments.
	 * It sorts the array list of keys, creates a matrix for the
	 * BST and then calls
	 * optimize with 2 arguments:
	 */
	public BinaryTree optimize() {
		Collections.sort(this.keys);					//use collections sort(I hope thats ok)
		int n = this.keys.size();						//n is the size of the array of keys
		this.optTree = new BinaryTree[n+1][n+1];		//initialize matrix
		best2= optimize(0, n-1);							//call optimize with two arguments, initially with lower and upper bounds
		return best2;
	}
	
	private BinaryTree optimize(int left, int right) {
		
		int tempLowCost;
		int freqs=0;
		BinaryTree best = new BinaryTree(null, 1251261234) ;		//best tree initialized without a key and with an arbitrarily large cost
		BinaryTree tempTree= new BinaryTree();						//potential parent
		BinaryTree tempTree1;										//leaf
		BinaryTree tempTree2;										//leaf
		
		CALLS +=1;
		
		//if memoization is on, don't bother doing work
		if (this.MEMOIZE == true && left<=right && this.optTree[left][right]!=null){
			return this.optTree[left][right];
		}
		
		if(right<left) {
			return new BinaryTree();
		}
		
		//if left and right are equal we are dealing with node with no childen
		else if(left==right) {
			String j = this.keys.get(left);
			int k= this.values.get(j);
			tempTree= new BinaryTree(j, k);
			this.optTree[left][right]= tempTree;
			return tempTree;
		}
		
		//if we are dealing with parent nodes add up all possible frequencies
		//used later to add to the best cost to simulate comparison cost
		for(int i=left;i<=right;i++) {
			String j= this.keys.get(i);
			freqs += this.values.get(j);
		}
		
		//pick i as root.. and cycle through all possible 'i's
		for(int i=left;i<=right;i++) {
			tempTree1= optimize(left, i-1);									//recursively call optimize for left subtree
			tempTree2= optimize(i+1,right);									//same for right
			tempLowCost = tempTree1.cost + tempTree2.cost + freqs ;			//add the cost of both them and then add the frequencies
			tempTree= new BinaryTree(this.keys.get(i), tempLowCost, tempTree1, tempTree2);
			if(best.root == null || tempTree.cost<best.cost) {				//if temp cost is less than current best cost in this loop, update best
				best = tempTree;
			}
		}
				

		this.optTree[left][right] = best;
		return best;
			
	}
}
