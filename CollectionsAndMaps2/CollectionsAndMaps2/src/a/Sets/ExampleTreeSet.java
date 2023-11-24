package a.Sets;

import java.util.*;

class MyComparator implements Comparator<String>{
	@Override
    public int compare(String s1, String s2) {
        return -1*s1.compareTo(s2);
    }	
}

public class ExampleTreeSet {
		
	public static void main(String[] args) {
			
		Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return -1*s1.compareTo(s2);
            }
        };	
        
        /* 
		 * TreeHashSet: no repetition, elements ordered according to 
		 * compareTo (Comparable - default order) or compare (Comparator).
		 */
		
//      TreeSet<String> mySet = new TreeSet<String>();
//      TreeSet<String> mySet = new TreeSet<String>(new MyComparator());
        TreeSet<String> mySet = new TreeSet<String>(stringComparator);    	
//		TreeSet<String> mySet = new TreeSet<String>((s1,s2)->-1*s1.compareTo(s2));
		
		mySet.add("First");
		mySet.add("Second");
		mySet.add("Fourth");
		mySet.add("Fourth");
		
		System.out.println("mySet size: "+ mySet.size());
		
        Iterator<String>myIter= mySet.iterator(); 
		
		System.out.print("Elements of mySet: ");
		
		while(myIter.hasNext()){
			System.out.print(myIter.next()+" ; ");
		}
		System.out.println();

	}

}
