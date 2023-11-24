package db1_MySql;

import java.sql.*;

public class CreateDB {

	public static void main(String[] args) {
		try {
		String url = "jdbc:mysql://localhost:3306/";
		Connection con = DriverManager.getConnection(url,"root", "root"); //connect server with user and password
		
		Statement stmt = con.createStatement(); //Create statement(commands)
		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS EMP"); //commands
		stmt.close();
		con.close();
		}
		catch (Exception e) {
			e.printStackTrace();//Show errors list to user
		}

	}

}
