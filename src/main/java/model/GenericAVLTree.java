package main.java.model;

import java.io.Serializable;

public class GenericAVLTree<T extends Comparable<T>> implements Serializable {
	
	private GenericNode<T> root;
	
	public GenericNode<T> getRoot() {
		return this.root;
	}
	
	public void setRoot(GenericNode<T> root) {
		this.root = root;
	}
	
	public int getHeight(GenericNode<T> node){
		if(node == null)
			return 0;
		
		return node.height;
	}
	
	public int getMax(int a, int b) { 
		return (a > b) ? a : b; 
	} 
	
	public GenericNode<T> rightRotate(GenericNode<T> y) { 
		GenericNode<T> x = y.left; 
		GenericNode<T> T2 = x.right; 

		// Perform rotation 
		x.right = y; 
		y.left = T2; 

		// Update heights 
		y.height = getMax(getHeight(y.left), getHeight(y.right)) + 1; 
		x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1; 

		// Return new root 
		return x; 
	} 
	
	public GenericNode<T> leftRotate(GenericNode<T> x) { 
		GenericNode<T> y = x.right; 
		GenericNode<T> T2 = y.left; 

		// Perform rotation 
		y.left = x; 
		x.right = T2; 

		// Update heights 
		x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1; 
		y.height = getMax(getHeight(y.left), getHeight(y.right)) + 1; 

		// Return new root 
		return y; 
	} 
	
	public int getBalance(GenericNode<T> N) { 
		if (N == null) 
			return 0; 

		return getHeight(N.left) - getHeight(N.right); 
	}
	
	public GenericNode<T> insert(GenericNode<T> node, T key) { 

		/* 1. Perform the normal BST insertion */
		if (node == null) 
			return (new GenericNode<T>(key)); 

		if (key.compareTo(node.data) < 0) 
			node.left = insert(node.left, key); 
		else if (key.compareTo(node.data) > 0) 
			node.right = insert(node.right, key); 
		else // Duplicate keys not allowed 
			return node; 

		/* 2. Update height of this ancestor node */
		node.height = 1 + getMax(getHeight(node.left), 
							getHeight(node.right)); 

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
		int balance = getBalance(node); 
//		System.out.println("Balance: " + balance);
		// If this node becomes unbalanced, then there 
		// are 4 cases Left Left Case 
		if (balance > 1 && key.compareTo(node.left.data) < 0) {
			System.out.println("left left");
			return rightRotate(node); 
		}
		// Right Right Case 
		if (balance < -1 && key.compareTo(node.right.data) > 0) {
			System.out.println("right right");
			return leftRotate(node); 
		}
		// Left Right Case 
		if (balance > 1 && key.compareTo(node.left.data) > 0) { 
			node.left = leftRotate(node.left); 
			System.out.println("left right");
			return rightRotate(node); 
		} 

		// Right Left Case 
		if (balance < -1 && key.compareTo(node.right.data) < 0) { 
			node.right = rightRotate(node.right); 
			System.out.println("right left");
			return leftRotate(node); 
		} 

		/* return the (unchanged) node pointer */
		return node; 
	} 

	// A utility function to print preorder traversal 
	// of the tree. 
	// The function also prints height of every node 
	public void preOrder(GenericNode<T> node) { 
		if (node != null) { 
			System.out.print(node.data + " "); 
			preOrder(node.left); 
			preOrder(node.right); 
		} 
	}
	
	
	public int size(){
	    GenericNode<T> y = root;
	    if (y == null) {
	        return 0;
	    }
        root = y.left;
        int left = size();
        root = y.right;
        int right = size();
        root = y;
        return 1 + left + right;
	}
	
	public void display(GenericNode<T> root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data.toString());
			display(root.right);
		}
	} 
	
	public GenericNode<T> search(T data){
		GenericNode<T> current = root;
		while(current!=null){
			if(current.data.compareTo(data) == 0){
				return current;
			}else if(current.data.compareTo(data) > 0){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return null;
	}
	
//	public static void main(String arg[]){
//		GenericAVLTree<Integer> tree = new GenericAVLTree<Integer>();
//		tree.root = tree.insert(tree.root, 10);
//		tree.root = tree.insert(tree.root, 20);
//		tree.root = tree.insert(tree.root, 30);
//		tree.root = tree.insert(tree.root, 40);
//		tree.root = tree.insert(tree.root, 50);
//		tree.root = tree.insert(tree.root, 25);
//		System.out.println("Preorder traversal" +
//				" of constructed tree is : ");
//		tree.preOrder(tree.root);
//		System.out.println("");
//		System.out.println("Size: " + tree.size());
//		System.out.println("Search 50: " + tree.search(50).data);
//	}
}
