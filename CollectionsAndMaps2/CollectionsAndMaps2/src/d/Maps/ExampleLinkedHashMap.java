package d.Maps;

import java.util.*;

public class ExampleLinkedHashMap {

	public static void main(String[] args) {
		
		/* 
		 * LinkedHashMap: order of the elements is the input order
		 */
		
		LinkedHashMap<Integer, String> myMap = new LinkedHashMap<Integer, String>();
		
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
