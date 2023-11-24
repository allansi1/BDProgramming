package c.testTextFiles;

import java.io.*;

public class Test2TextFile {

	public static void main(String[] args) throws IOException {
		
		BufferedWriter w =new BufferedWriter (new FileWriter("test2.txt"));  // rewrite mode 																			 
	    w.write("aaa aaa aaa");                                              // il n'y a pas de writeln(). 
	    w.newLine();
	    w.write("AA AA AA");
	    w.newLine();
	    w.close();
	    
	    w =new BufferedWriter (new FileWriter("test2.txt", true));    // append mode
	    w.write("bbb bbb bbb");                                       // il n'y a pas de writeln(). 
	    w.newLine();
	    w.close();	
	    
	    /*
	     * mode rewrite - Ouvre le fichier pour le re-écrire dès le début. 
	     *                Si le fichier n'existe pas, il va être créé.
	     * 
	     * mode append - Ouvre le fichier pour ajouter des données après la fin courante du fichier.
	     *               Si le fichier n'existe pas, il va être créé.	     *  
	     */
	}
}
