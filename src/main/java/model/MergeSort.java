package main.java.model;

public class MergeSort<T extends Comparable<T>> {
	
    void print(GenericNode<T> node) { 
    	GenericNode<T>  temp = node; 
        System.out.println("Forward Traversal using next pointer"); 
        while (node != null) { 
            System.out.print(node.data + " "); 
            temp = node; 
            node = node.next; 
        } 
        System.out.println("\nBackward Traversal using prev pointer"); 
        while (temp != null) { 
            System.out.print(temp.data + " "); 
            temp = temp.prev; 
        } 
    } 
  
    // Split a doubly linked list (DLL) into 2 DLLs of 
    // half sizes 
    GenericNode<T>  split(GenericNode<T>  head) { 
    	GenericNode<T>  fast = head, slow = head; 
        while (fast.next != null && fast.next.next != null) { 
            fast = fast.next.next; 
            slow = slow.next; 
        } 
        GenericNode<T>  temp = slow.next; 
        slow.next = null; 
        return temp; 
    } 
  
    GenericNode<T>  mergeSort(GenericNode<T>  node) { 
        if (node == null || node.next == null) { 
            return node; 
        } 
        GenericNode<T>  second = split(node); 
  
        // Recur for left and right halves 
        node = mergeSort(node); 
        second = mergeSort(second); 
  
        // Merge the two sorted halves 
        return merge(node, second); 
    } 
  
    // Function to merge two linked lists 
    GenericNode<T>  merge(GenericNode<T>  first, GenericNode<T>  second) { 
        // If first linked list is empty 
        if (first == null) { 
            return second; 
        } 
  
        // If second linked list is empty 
        if (second == null) { 
            return first; 
        } 
  
        // Pick the smaller value 
        if (first.data.compareTo(second.data) < 0) { 
            first.next = merge(first.next, second); 
            first.next.prev = first; 
            first.prev = null; 
            return first; 
        } else { 
            second.next = merge(first, second.next); 
            second.next.prev = second; 
            second.prev = null; 
            return second; 
        } 
    } 
  
    // Driver program to test above functions 
//    public static void main(String[] args) {
//    	GenericDLinkedList<Integer> dll = new GenericDLinkedList<>();
//    	dll.addAtTheEnd(10);
//    	dll.addAtTheEnd(30);
//    	dll.addAtTheEnd(3);
//    	dll.addAtTheEnd(4);
//    	dll.addAtTheEnd(20);
//    	dll.addAtTheEnd(5);
//
//    	System.out.println("Size: " + dll.size());
//
//    	MergeSort<Integer> mergeSort = new MergeSort<>();
//    	mergeSort.print(dll.getHead());
//
//
//    	System.out.println("");
//
//    	dll.setHead(mergeSort.mergeSort(dll.getHead()));
//    	mergeSort.print(dll.getHead());
//
//    }
	
}
