package d.Maps;

import java.util.*;

public class ExampleHashMap {

	public static void main(String[] args) {
		
		/* 
		 * HashMap: order of the elements does not matter
		 */
		
		HashMap<Integer, String> myMap = new HashMap<Integer, String>();
		
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
