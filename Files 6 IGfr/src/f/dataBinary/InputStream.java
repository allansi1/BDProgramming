package f.dataBinary;

import java.io.*;
import java.util.*;

public class InputStream {

	public static void main(String[] args) {
		
		  ArrayList<Employee> le = new ArrayList<Employee>();
	      try {
	         FileInputStream fileIn = new FileInputStream("employee2.dat");
	         DataInputStream in = new DataInputStream(fileIn);
	         
	         while (fileIn.available()>0) {	 
	        	 
	        	 // read version ===========================
	        	 int version = in.readInt();
	        	 if (version!=Employee.version) {
	        		 in.close();
	        		 throw (new InvalidClassException("Inconsistent versions"));
	        	 } 
	        	 
	        	 // read name ==============================
	        	 int len = in.readInt();	        	
	        	 byte[] string1 = new byte[len];
	        	 in.read(string1);
	        	 String name =new String(string1);
	        	 
	        	 // read address  ==========================
                 len = in.readInt();
                 string1 = new byte[len];
                 in.read(string1);
	        	 String address = new String(string1);
	        	 
	        	 // read number ============================
	        	 int number = in.readInt();
	        	 
	        	 // Employee  ==============================
	        	 Employee emp = new Employee();
	        	 emp.setName(name);
	        	 emp.setAddress(address);
	        	 emp.setNumber(number);
	        	 
	        	 // add to the list ========================
	        	 le.add(emp) ;
	         }
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      }
	      
	      System.out.println("Binary data retrieved - Employees...");
	      System.out.println("Number of emplyees: "+le.size());
	      for (int i =0; i<le.size(); i++) {
	    	  System.out.println("Name: " + le.get(i).getName());
	    	  System.out.println("Address: " + le.get(i).getAddress());
	    	  System.out.println("SSN: " + le.get(i).getSSN());
	    	  System.out.println("Number: " + le.get(i).getNumber());
	      }

	}

}
