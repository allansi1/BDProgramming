package db1_MySql;

import java.sql.*;

public class DropDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url = "jdbc:mysql://localhost:3306/";
			Connection con = DriverManager.getConnection(url, "root", "root");
			
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DROP DATABASE IF EXISTS EMP");
			
			
			stmt.close();
			con.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}


	}

}
