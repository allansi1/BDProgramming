package pack;

import java.util.Arrays;

public class ExerciceArrayComparaision {

	public static void main(String[] args) {
		int [] a = {1,2,3,4,5};
		int [] b = {1,2,3,4,5};
		int [] c;
		c=a;
		//Comparation on PILE
		System.out.println("a==b est " + (a==b));
		System.out.println("a==c est " + (a==c));
		System.out.println("a.equals(b) est " + (a.equals(b)));
		System.out.println("a.equals(c) est " + (a.equals(c)));
		//comparation on TAS
		System.out.println("Arrays.equals(a,b) est " + (Arrays.equals(a, b)));
		System.out.println("Arrays.equals(a,c) est " + (Arrays.equals(a, c)));
	}

}
