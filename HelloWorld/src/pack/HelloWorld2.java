package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloWorld2 {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello world!");

		System.out.print("Hello ");
		System.out.print("World!");
		System.out.println();
		
		InputStreamReader r=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(r);
		System.out.println("Enter your name");
		String name=br.readLine();
		System.out.println("Welcome "+name);
		

	}

}
