package pack;
import java.util.*;

public class ExerciceStringValueOf {

	public static void main(String[] args) {
		
		boolean a = true;
		boolean b = false;
		int c = 5;
		long d = 100;
		float e = 3.14f;
		double f = 5.234;
		char g = 'x';
		char [] h = {'a','b', 'c'};
		int [] k = {1,2,3};
		
		String [] s = new String [9];
		s[0] = String.valueOf(a);
		s[1] = String.valueOf(b);
		s[2] = String.valueOf(c);
		s[3] = String.valueOf(d);
		s[4] = String.valueOf(e);
		s[5] = String.valueOf(f);
		s[6] = String.valueOf(g);
		s[7] = String.valueOf(h);
		s[8] = String.valueOf(k); //donne seulement l'adresse
		
		for (int i=0; i <9; i++) {
			System.out.println("s["+i+"]= " + s[i]);
		}
	
		System.out.println("String.valueOf(h) = " + String.valueOf(h));
		System.out.println("h.toString = " + h.toString());
		System.out.println();
		System.out.println("String.valueOf(k) = " + String.valueOf(k));
		System.out.println("k.toString = " + k.toString());
	
		//String.valueOf(k) donne le meme resultat que k.toString --> l'adresse
		
		String st = "Bonjour";
		System.out.println(st);
		System.out.println(st.valueOf(st));
		System.out.println(st);

	}

}
