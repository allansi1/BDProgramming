package a.Sets;

import java.util.*;

public class ExampleHashSet {

	public static void main(String[] args) {
		
		/* 
		 * HashSet: no repetition, order does not matter
		 */
		
		HashSet<String> mySet = new HashSet<String>();
		
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
