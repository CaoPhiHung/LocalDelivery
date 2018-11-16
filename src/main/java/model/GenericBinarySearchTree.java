package main.java.model;

import java.io.Serializable;

public class GenericBinarySearchTree<T extends Comparable<T>> implements Serializable {
	private GenericNode<T> root;
	
	public GenericNode<T> getRoot() {
		return this.root;
	}
	
	public void insert(T data) {
		GenericNode<T> newNode = new GenericNode(data);
		
		if(root == null) {
			root = newNode;
			return;
		}
		
		GenericNode<T> current = root;
		GenericNode<T> parent = null;
		while(true) {
			parent = current;
			if(data.compareTo(current.data) <= 0) {
				current = current.prev;
				if(current == null) {
					parent.prev = newNode;
					return;
				}
			}else if (data.compareTo(current.data) > 0){
				current = current.next;
				if(current == null) {
					parent.next = newNode;
					return;
				}
			}else {
				return;
			}
		}
		
	}
	
	public void display(GenericNode<T> root){
		if(root!=null){
			display(root.prev);
			System.out.print(" " + root.data.toString());
			display(root.next);
		}
	} 
	
	public GenericNode<T> search(T data){
		GenericNode<T> current = root;
		while(current!=null){
			if(current.data.compareTo(data) == 0){
				return current;
			}else if(current.data.compareTo(data) > 0){
				current = current.prev;
			}else{
				current = current.next;
			}
		}
		return null;
	}
	
	public static void main(String arg[]){
		GenericBinarySearchTree<Integer> b = new GenericBinarySearchTree<Integer>();
		b.insert(3);
		b.insert(8);
		b.insert(9);
		System.out.println("Root is: " + b.root.data);
		int data = (b.search(11) != null) ? 1 : -1;
		System.out.println("Left data is : " + data);

		b.insert(4);
		b.insert(6);
		b.insert(2);
		b.insert(10);
		b.insert(9);
		b.insert(20);
		b.insert(25);
		b.insert(15);
		b.insert(16);
		System.out.println("Original Tree : ");
		b.display(b.root);		

	}
}
