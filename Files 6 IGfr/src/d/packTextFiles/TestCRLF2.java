package d.packTextFiles;

import java.io.*;

public class TestCRLF2 {

	public static void main(String[] args) throws IOException {
		PrintWriter w =new PrintWriter (new FileWriter("mytext3.txt"));
		w.println("Montreal\nQuebec");
		w.println();
		w.println("Montreal\rQuebec");
		w.println();
		w.println("Montreal\r\nQuebec");
		w.println();
		w.println("Montreal\n\nQuebec");
		w.println();
		w.println("Montreal\r\rQuebec");
		w.println();
		w.println("Montreal\n\rQuebec");
		w.println();
		w.close();
	}

}
