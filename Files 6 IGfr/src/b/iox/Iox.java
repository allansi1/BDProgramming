package b.iox;

import java.io.*;

public class Iox {
	
/*
 ******************************************************************************* 
 * Fichier Binaire: 
 * 
 * 		Classes basiques: 	  FileInputSteam, FileOutputStream
 * 
 * 		Decorateurs tipiques: BufferedInputStream, BufferedOutputStream
 * 							  DataInputStream, DataOutputStream		
 * 							  ObjectInputStream, ObjectOutputStream		
 * 
 * Fichier Texte: 
 * 
 * 		Classes basiques: 	  FileReader, FileWriter
 * 
 * 		Decorateurs tipiques: BufferedReader, BufferedWriter
 * 											  PrintWriter
 * 
 *  
 ********************************************************************************
*/
/*
 *********************************************************************************
 * Fichier Binaire: 
 * 
 * 		Accès direct: RandomAccessFile
 * 
 *********************************************************************************
 */
	
	public DataInputStream DIS (String nomfichier, boolean b)throws IOException{
		DataInputStream w =null;
		if (b) {w=new DataInputStream(new BufferedInputStream(new FileInputStream(nomfichier)));}
		else {w=new DataInputStream(new FileInputStream(nomfichier));}
		return w;		
	}
	
	public DataOutputStream DOS (String nomfichier, boolean b)throws IOException{
		DataOutputStream w =null;
		if (b) {w=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomfichier)));}
		else {w=new DataOutputStream(new FileOutputStream(nomfichier));}
		return w;
	}
	
	public ObjectInputStream OIS (String nomfichier, boolean b)throws IOException{
		ObjectInputStream w =null;
		if (b) {w=new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomfichier)));}
		else {w=new ObjectInputStream(new FileInputStream(nomfichier));}
		return w;		
	}
	
	public ObjectOutputStream OOS (String nomfichier, boolean b)throws IOException{
		ObjectOutputStream w =null;
		if (b) {w=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomfichier)));}
		else {w=new ObjectOutputStream(new FileOutputStream(nomfichier));}
		return w;
	}
	
	public BufferedReader BR (String nomfichier)throws IOException{
		return new BufferedReader (new FileReader(nomfichier));
	}
	
	public BufferedWriter BW (String nomfichier)throws IOException{
		return new BufferedWriter (new FileWriter(nomfichier));
	}
	
	public PrintWriter PW (String nomfichier, boolean b)throws IOException{
		PrintWriter w =null;
		if (b) {w=new PrintWriter(new BufferedWriter(new FileWriter(nomfichier)));}
		else {w=new PrintWriter(new FileWriter(nomfichier));}
		return w;
	}
	
	public RandomAccessFile RAF (String nomfichier, String mode)throws IOException {
		// mode = "r" ou "rw"
		return new RandomAccessFile(nomfichier, mode);
	}
	
	
}
