package pack;

import java.util.Arrays;

public class ExerciceArrayNested {

	public static void main(String[] args) {
		char[] [] tcn = {{'a','b','c'},{'e','f'},{'g','h'}};
		int[] [] tin = {{1,2,3},{4,5},{6,7}};
		
		System.out.println("Voici le tableaux tcn: " + Arrays.toString(tcn));
		System.out.println("Voici le tableaux tin: " + Arrays.toString(tin));
		
		//Solution to print the table
		System.out.println("Voici le tableaux tcn: " + Arrays.deepToString(tcn));
		System.out.println("Voici le tableaux tin: " + Arrays.deepToString(tin));
		
		//table simple
		char[] tc = {'a','b','c'};
		int [] ti = {1,2,3};
		System.out.println("Voici le tableaux tc: " + Arrays.toString(tc));
		System.out.println("Voici le tableaux ti: " + Arrays.toString(ti));
		
		
		//print by loop
		String rc= "[";
		for (int i=0;i<tcn.length;++i) {
			rc=rc+"[";
			for(int j =0;j<tcn[i].length;j++){
				rc=rc+tcn[i][j];
				if(j!=tcn[i].length-1) {
					rc=rc+",";
				}
			}
				rc=rc+"]";
				if(i!=tcn.length-1) {
					rc=rc+",";
				}
			}
			rc=rc+"]";
			System.out.println("Ces les tableaux by loop: " + rc);
		
		

}
		
		

	}
	
	


