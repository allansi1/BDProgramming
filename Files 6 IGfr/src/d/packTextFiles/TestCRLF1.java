package d.packTextFiles;

public class TestCRLF1 {

	public static void main(String[] args) {
		
		System.out.println("Test de \\n:");
		System.out.println("Montreal\nQuebec");    // \n change ligne.
		System.out.println();
		
		System.out.println("Test de \\r:");
		System.out.println("Montreal\rQuebec");    // \r change ligne.
		System.out.println();
		
		System.out.println("Test de \\r\\n:");
		System.out.println("Montreal\r\nQuebec");  // \r\n change ligne. 
		System.out.println();
		
		System.out.println("Test de \\n\\n:");
		System.out.println("Montreal\n\nQuebec");  // \n\n change deux fois de ligne.
		System.out.println();
		
		System.out.println("Test de \\r\\r:");
		System.out.println("Montreal\r\rQuebec");  // \r\r change deux fois de ligne.
		System.out.println();
		
		System.out.println("Test de \\n\\r:");
		System.out.println("Montreal\n\rQuebec");  // \n\r change deux fois de ligne.
		System.out.println();
		
	}

}
