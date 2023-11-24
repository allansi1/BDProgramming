package db1_MySql;

import java.sql.*;

public class CreateTableAlt2 {

	public static void main(String[] args) {
		try {
			
			//To allow MultiQueries with just one command
			String url = "jdbc:mysql://localhost:3306/EMP?allowMultiQueries=true";
			Connection con = DriverManager.getConnection(url,"root", "root");
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS COMPANY3" +
					"(ID INT PRIMARY KEY NOT NULL," +
					"NAME VARCHAR(50)NOT NULL," +
					"AGE INT NOT NULL," +
					"ADRESS VARCHAR(100)," +
					"SALARY REAL);" +
					"CREATE TABLE IF NOT EXISTS COMPANY4" +
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
	
	

