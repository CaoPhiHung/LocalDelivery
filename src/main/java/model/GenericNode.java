package main.java.model;

import java.io.Serializable;

public class GenericNode<T> implements Comparable<T> ,Serializable {
		
		public T data;
		public int height;
		public GenericNode<T> next, right;
		public GenericNode<T> prev, left;
		
		
		public GenericNode() {
			this.data = null;
			this.next = null;
			this.prev = null;
			this.height = 1;
		}
		
		public GenericNode(T data) {
			this(data, null, null);
		}
		
		public GenericNode(T data , GenericNode<T> next, GenericNode<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
			this.height = 1;
		}

		@Override
		public int compareTo(T o) {
			// TODO Auto-generated method stub
			return 0;
		}

}
