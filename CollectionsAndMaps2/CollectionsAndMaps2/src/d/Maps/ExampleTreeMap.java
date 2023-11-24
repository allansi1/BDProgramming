package d.Maps;

import java.util.*;

class MyComparator2 implements Comparator<Integer>{
	@Override
    public int compare(Integer s1, Integer s2) {
        return -1*(s1 - s2);
    }	
}

public class ExampleTreeMap {

	public static void main(String[] args) {
		
		Comparator<Integer> integerComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer s1, Integer s2) {
                return -1*(s1 - s2);
            }
        };	
        
        /* 
		 * TreeHashMap: elements ordered according to 
		 * compareTo (Comparable - default order) or compare (Comparator).
		 */
		
//    	TreeMap<Integer, String> myMap = new TreeMap<Integer, String>();
//      TreeMap<Integer, String> myMap = new TreeMap<Integer, String>(new MyComparator2());
		TreeMap<Integer, String> myMap = new TreeMap<Integer, String>(integerComparator);
//		TreeMap<Integer, String> myMap = new TreeMap<Integer, String>((s1,s2)->-1*(s1 - s2));
		
		myMap.put(3,"First");
		myMap.put(5, "Second");
		myMap.put(1, "Fourth");
		
		System.out.println("myMap size: "+ myMap.size());
		
        Iterator<Integer>myIter= myMap.keySet().iterator();
		
		System.out.print("Elements of myMap (key,value): ");
		
		while(myIter.hasNext()){
			Integer i=myIter.next();
			System.out.print("("+i+", "+myMap.get(i)+") ; ");
		}
		System.out.println();  



	}

}
