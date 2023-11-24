package e.serialization;

import java.io.*;

public class Serialization {
	
	public static void main(String[] args) {
		
		Employee e1 = new Employee();
		e1.setName("Mary Brown"); 
	    e1.setAddress("123 rue des exemples");
	    e1.setSSN(11122333);
	    e1.setNumber(101);
	    Employee e2 = new Employee();
		e2.setName("John Smith"); 
	    e2.setAddress("555 rue des exemples");
	    e2.setSSN(11144333);
	    e2.setNumber(110);
	    Employee e3 = new Employee();
		e3.setName("Anne Smith"); 
	    e3.setAddress("700 rue des exemples");
	    e3.setSSN(11155333);
	    e3.setNumber(120);
		
		 try {
	         FileOutputStream fileOut = new FileOutputStream("employee.dat");   // rewrite mode
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e1);
	         out.writeObject(e2);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in employee.dat");
	         
	         out = new ObjectOutputStream(new FileOutputStream("employee.dat", true)) // append mode
	                   { protected void writeStreamHeader() throws IOException { 	  // needed to not corrupt the file    
	        	              reset();
	        	         }
                       };
	         out.writeObject(e3);
	         out.close();
	         System.out.println("Additional serialized data is saved in employee.dat");
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
	}

}
