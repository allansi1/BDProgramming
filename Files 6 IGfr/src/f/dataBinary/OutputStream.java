package f.dataBinary;

import java.io.*;

public class OutputStream {
	
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
	         FileOutputStream fileOut = new FileOutputStream("employee2.dat");   // rewrite mode
	         DataOutputStream out = new DataOutputStream(fileOut);
	         
	         out.writeInt(Employee.version);
	         out.writeInt(e1.getName().length());
	         out.writeBytes(e1.getName());  
	         out.writeInt(e1.getAddress().length());
	         out.writeBytes(e1.getAddress());
	         out.writeInt(e1.getNumber());
	         
	         out.writeInt(Employee.version);
	         out.writeInt(e2.getName().length());
	         out.writeBytes(e2.getName());
	         out.writeInt(e2.getAddress().length());
	         out.writeBytes(e2.getAddress());
	         out.writeInt(e2.getNumber());
	         
	         out.close();
	         fileOut.close();
	         System.out.println("Binary data is saved in employee2.dat");
	         
	         out = new DataOutputStream(new FileOutputStream("employee2.dat", true));  // append mode
	         
	         out.writeInt(Employee.version);
	         out.writeInt(e3.getName().length());
	         out.writeBytes(e3.getName());
	         out.writeInt(e3.getAddress().length());
	         out.writeBytes(e3.getAddress());
	         out.writeInt(e3.getNumber());
	         
	         out.close();
	         System.out.println("Additional binary data is saved in employee2.dat");
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
	}

}
