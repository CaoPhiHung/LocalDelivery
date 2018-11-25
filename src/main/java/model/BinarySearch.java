package main.java.model;

public class BinarySearch<T> {
	
	private T[] array;
	
	public BinarySearch(T[] array) {
		this.array = array;
	}
	
	public <T extends Comparable<T>> int binarySearch(T value) {
		
		return binarySearch(value, 0, this.array.length);
	}
	
	/**
	 * 
	 * @param value is a search value
	 * @param lo is low index
	 * @param hi is high index
	 * @return search value if found otherwise return -1
	 */
	public <T extends Comparable<T>> int binarySearch(T value, int lo, int hi) {
		
		//check if lo index  less than high index
		if (lo < hi) {
			
			//get the middle index
		    int mid = lo  + (hi - lo) / 2;
		    
		    //compare value of mid index and search value 
		    int cmp = ((Comparable<T>) this.array[mid]).compareTo(value);
		    
		    //cmp less than 0 then go between lo index and mid -1 index
		    if (cmp < 0) return binarySearch(value, lo, mid - 1);
		   
		    //cmp higher than 0 then go between mid + 1 index and hi index
		    if (cmp > 0) return binarySearch(value, mid + 1, hi);
		    
		    //return mid index if found (cmp = 0) 
		    return mid;
		}
		return -1;
	    }
	
//	public static void main(String[] args){
//		String[] words = {"Alpha", "Bravo", "Charlie", "Delta", "Echo",
//	            "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima",
//	            "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo",
//	            "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-Ray",
//	            "Yankee", "Zulu"};
//
//		BinarySearch<String> searcher = new BinarySearch<String>(words);
//	        System.out.println(searcher.binarySearch("November"));
//	        System.out.println("Expected: 13");
//	        System.out.println(searcher.binarySearch("October"));
//	        System.out.println("Expected: -1");
//	}
	    
}
