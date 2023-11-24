package db1_MySql;

import java.sql.*;

public class QueryParam {

	public static void main(String[] args) {
		try {
			 String url = "jdbc:mysql://localhost:3306/EMP";
			 Connection con = DriverManager.getConnection(url,"root", "root");
			 
//		     String s1 ="24";
//			 String s1 ="24  -- ";
			 String s1 ="24 ' -- '";
			 String s2 = "30000";
			 
//			 Statement stmt =con.createStatement();
//    		 String query = "SELECT * FROM COMPANY WHERE age > "+ s1 +" AND salary > "+ s2;
//    		 String query = "SELECT * FROM COMPANY WHERE age > '"+ s1 +"' AND salary > '"+ s2+"'";
    		 
//    		 ResultSet rs = stmt.executeQuery(query);	
//    		 System.out.println(query); 
//     	     System.out.println();
			 
			 PreparedStatement stmt =con.prepareStatement("SELECT * FROM COMPANY WHERE age > ? AND salary > ?");
			 stmt.setString(1, s1); 
			 stmt.setString(2, s2);
			 ResultSet rs = stmt.executeQuery(); 			 
			 System.out.println(stmt);
			 System.out.println();
    		 
    		 while ( rs.next() ) {
		         int id = rs.getInt("id");
		         String  name = rs.getString("name");
		         int age  = rs.getInt("age");
		         String  address = rs.getString("address");
		         float salary = rs.getFloat("salary");
		         System.out.println( "ID = " + id );
		         System.out.println( "NAME = " + name );
		         System.out.println( "AGE = " + age );
		         System.out.println( "ADDRESS = " + address );
		         System.out.println( "SALARY = " + salary );
		         System.out.println();
		      }
    		 
		      rs.close();
		      stmt.close();
		      con.close();		
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
