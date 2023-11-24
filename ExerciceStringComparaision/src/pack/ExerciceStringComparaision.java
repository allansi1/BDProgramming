package pack;

import java.util.Arrays;

public class ExerciceStringComparaision {

	public static void main(String[] args) {
		String a = "Bonjour";
		String b = "Bonjour";
		String c = a;
		//new way to do allocation of memory
		String d = new String("Bonjour");
		
		System.out.println("a==b est " + (a==b));
		System.out.println("a==c est " + (a==c));
		System.out.println("a==d est " + (a==d));
		System.out.println("a.equals(b) est " + (a.equals(b)));
		System.out.println("a.equals(c) est " + (a.equals(c)));
		System.out.println("a.equals(d) est " + (a.equals(d)));
		
		

	}

}
