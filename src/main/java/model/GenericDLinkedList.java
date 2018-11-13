package main.java.model;

import java.io.Serializable;

public class GenericDLinkedList<T> implements Serializable {
	public int searchType  = 0;
	private T data;
	private GenericNode<T> next;
	private GenericNode<T> prev;
	
	private GenericNode<T> head;
	private GenericNode<T> tail;
	
	public GenericDLinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int size() {
		int count = 0;
	       GenericNode<T> p = head;   
	       while (p != null)
	       {
	           // There is an element at p
	           count ++;
	           p = p.next;
	       }
	       return count;
	}
	
	public void add(T genericNode)
    {
      if (isEmpty()) 
      {
          tail = new GenericNode<T>(genericNode);
          head = tail;
      }
      else
      {
          // Add to end of existing list
          tail.next = new GenericNode<T>(genericNode, null, tail);
          tail = tail.next;         
      }      
    }
	
	public GenericNode<T> search(T obj){
		GenericNode<T> found = head;
		
		if(isEmpty()){
			return null;
		}
		
		while(found != null){
			switch (searchType) {
			case 0:
				User user = (User)found.data;
				User checkedUser = (User)obj;
				if(user.checkAuthenticate(checkedUser)){
					return found;
				}
				break;

			default:
				break;
			}
			found = found.next;
			
		}
		
		return found;
	}

	public GenericNode<T> getHead() {
        return head;
    }
	
//	public void print() {
//        GenericNode<T> tmpNode = head;
//
//        while (tmpNode != null) {
//            System.out.println(tmpNode.data.toString() + " <-> ");
//            tmpNode = tmpNode.next;
//        }
//        System.out.print("null");
//        System.out.println("");
//    }
	
//	public void print(int index) {
//		if (index < 0 || index >= size()){
//	          String message = String.valueOf(index);
//	          throw new IndexOutOfBoundsException(message);
//	       }
//		
//		GenericNode<T> target = head;
//		
//		for (int k = 1; k <= index; k++)
//	           target = target.next;
//		
//		System.out.println(target.data.toString() + " <-> " 
//		+ target.next.data.toString());
//	}
	
}
