package c.Queues;

import java.util.*;

class MyComparator implements Comparator<String>{
	@Override
    public int compare(String s1, String s2) {
        return -1*s1.compareTo(s2);
    }	
}

public class ExamplePriorityQueue {

	public static void main(String[] args) {
		
		Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return -1*s1.compareTo(s2);
            }
        };	
        
        /* 
		 * PriorityQueue: possible repetition, elements ordered according to 
		 * compareTo (Comparable - default order) or compare (Comparator).
		 * 
		 * PriorityQueue is a Queue, with methods such as .poll().
		 */
		
    //   PriorityQueue<String> myQueue = new PriorityQueue<String>();
    //   PriorityQueue<String> myQueue = new PriorityQueue<String>(new MyComparator());
	     PriorityQueue<String> myQueue = new PriorityQueue<String>(stringComparator);
	//   PriorityQueue<String> myQueue = new PriorityQueue<String>((s1,s2)->-1*s1.compareTo(s2));
		
		myQueue.add("First");
		myQueue.add("Second");  
		myQueue.add("Fourth");
		myQueue.add("Fourth");
		
		System.out.println("myQueue size: "+ myQueue.size());
		
		Iterator<String> myIter = myQueue.iterator();
		
		System.out.print("Elements of myQueue: ");
		
		while(myIter.hasNext()){
			System.out.print(myIter.next()+" ; ");
		}
		System.out.println();
		System.out.println("myQueue size: "+ myQueue.size());
		
		System.out.print("Elements of myQueue: ");
		
		while(!myQueue.isEmpty()){
			System.out.print(myQueue.poll()+" ; ");
		}
		System.out.println();
		System.out.println("myQueue size: "+ myQueue.size());

	}

}
