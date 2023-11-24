package pack;

import java.util.Scanner;


public class HelloWorld3 {

	public static void main(String[] args) {
		System.out.println("Hello world!");

		System.out.print("Hello ");
		System.out.print("World!");
		System.out.println();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your name");
		String name = sc.nextLine();
		System.out.println("Welcome "+name);
		sc.close();

	}

}
