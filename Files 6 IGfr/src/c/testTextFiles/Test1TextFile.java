package c.testTextFiles;

import java.io.*;


public class Test1TextFile {

	public static void main(String[] args) throws IOException{
				
		BufferedReader w =new BufferedReader (new FileReader("test1.txt")); 
		String v= w.readLine();
		while (v!=null){
			System.out.println(v);
			v= w.readLine();
		}
		w.close();
	}
}
