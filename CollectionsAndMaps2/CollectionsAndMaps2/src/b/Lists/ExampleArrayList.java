package b.Lists;

import java.util.*;

public class ExampleArrayList {

	public static void main(String[] args) {
		
		/* 
		 * ArrayList: possible repetition, order is the input order.
		 * 
		 * It has a method .sort() to sort according to 
		 * compareTo (Comparable - default order) or compare (Comparator).
		 */
		
		ArrayList<String> myList = new ArrayList<String>();
		
		myList.add("First");   
		myList.add("Second");
		myList.add("Fourth");
		myList.add("Fourth");  
		
		System.out.println("myList size: "+ myList.size());
		
		ListIterator<String>myIter= myList.listIterator();
		
		System.out.print("Elements of myList: ");
		
		while(myIter.hasNext()){
			System.out.print(myIter.next()+" ; ");
		}
		System.out.println(); 
		
        System.out.print("Elements of myList (from the end to the beginnig): ");
		
		while(myIter.hasPrevious()){
			System.out.print(myIter.previous()+" ; ");
		}
		System.out.println(); 
			
	}

}
