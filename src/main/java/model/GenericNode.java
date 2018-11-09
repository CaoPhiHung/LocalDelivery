package main.java.model;

import java.io.Serializable;

public class GenericNode<T> implements Serializable {
		
		public T data;
		public GenericNode<T> next;
		public GenericNode<T> prev;
		
		
		public GenericNode() {
			this.data = null;
			this.next = null;
			this.prev = null;
		}
		
		public GenericNode(T data) {
			this(data, null, null);
		}
		
		public GenericNode(T data , GenericNode<T> next, GenericNode<T> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

	
}
