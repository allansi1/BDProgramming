package a.console;


import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestConsole1 {

	public static void main(String[] args) throws IOException{
	
	// Solution 1 
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String name1 = sc.nextLine();
		System.out.println("Welcome "+name1);
	//	sc.close();    // It can not be closed now, because it closes the stream System.in
	
	// Solution 2 - requires "throws IOException"		
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your name");
		String name2=br.readLine();
		System.out.println("Welcome "+name2);
		
	 // Solution 3 - requires "throws IOException"
		/* 
		 * Sans le décorateur BufferedReader, nous n'avons pas readline(). 
		 * Cette "solution" est seulement pour montre l'utilité  du 
		 * décorateur BufferedReader, qui prend soin de la gestion du buffer
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		System.out.println("Enter your name");
		char[] buffer= new char[10] ;   // c'est à nous de faire la gestion du buffer
		isr.read(buffer); 
		String name3 = new String (buffer);
		System.out.println("Welcome "+name3);
		
		sc.close();

	}

}
