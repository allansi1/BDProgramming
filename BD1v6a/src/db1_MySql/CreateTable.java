package db1_MySql;

import java.sql.*;

public class CreateTable {

	public static void main(String[] args) {
		
		try {
		String url = "jdbc:mysql://localhost:3306/EMP";
		Connection con = DriverManager.getConnection(url,"root", "root");
		
		Statement stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY" +
							"(ID INT PRIMARY KEY NOT NULL," +
							"NAME VARCHAR(50)NOT NULL," +
							"AGE INT NOT NULL," +
							"ADRESS VARCHAR(100)," +
							"SALARY REAL)");
		
		stmt.close();
		con.close();
				
				
				
				
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
