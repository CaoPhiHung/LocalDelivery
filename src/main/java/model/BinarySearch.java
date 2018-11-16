package main.java.model;

public class BinarySearch<T> {
	
	private T[] array;
	
	public BinarySearch(T[] array) {
		// TODO Auto-generated constructor stub
		this.array = array;
	}
	
	public <T extends Comparable<T>> int binarySearch(T value) {
		
		return binarySearch(value, 0, this.array.length);
	}

	public <T extends Comparable<T>> int binarySearch(T value, int lo, int hi) {
		if (lo < hi) {
		    int mid = lo  + (hi - lo) / 2;
		    int cmp = ((Comparable<T>) this.array[mid]).compareTo(value);
		    if (cmp < 0) return binarySearch(value, lo, mid - 1);
		    if (cmp > 0) return binarySearch(value, mid + 1, hi);
		    return mid;
		} // if
		return -1;
	    }
	
	public static void main(String[] args){
		String[] words = {"Alpha", "Bravo", "Charlie", "Delta", "Echo", 
	            "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", 
	            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", 
	            "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-Ray", 
	            "Yankee", "Zulu"};
		
		BinarySearch<String> searcher = new BinarySearch<String>(words);
	        System.out.println(searcher.binarySearch("November"));
	        System.out.println("Expected: 13");
	        System.out.println(searcher.binarySearch("October"));
	        System.out.println("Expected: -1");
	}
	    
}
