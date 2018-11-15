package main.java.model;

import java.io.Serializable;

public class GenericDLinkedList<T> implements Serializable {
	public int searchType  = 0;
	
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
	
	public void addAtTheEnd(T genericNode)
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
	
	public void setHead(GenericNode<T> head) {
        this.head = head;
    }
}
