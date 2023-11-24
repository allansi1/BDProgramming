package c.testTextFiles;

import java.io.*;

public class Test3TextFile {

	public static void main(String[] args) throws IOException {
				
		PrintWriter w =new PrintWriter( new BufferedWriter (new FileWriter("test3.txt")));	// rewrite mode	
	    w.write("aaa aaa aaa aaa");
	    w.println();
	    w.println("AA AA AA AA");
	    w.print("AAAAA AAA");
	    w.println();	   
	    w.close();
	    
	    w =new PrintWriter( new BufferedWriter (new FileWriter("test3.txt",true)));  // append mode
	    w.write("bbb bbb bbb bbb");
	    w.println();
	    w.println("ccc ccc ccc ccc");
	    w.close();  
	    
	    /*
	     * mode rewrite - Ouvre le fichier pour le re-�crire d�s le d�but. 
	     *                Si le fichier n'existe pas, il va �tre cr��.
	     * 
	     * mode append - Ouvre le fichier pour ajouter des donn�es apr�s la fin courante du fichier.
	     *               Si le fichier n'existe pas, il va �tre cr��.	     *  
	     */
	}
}
