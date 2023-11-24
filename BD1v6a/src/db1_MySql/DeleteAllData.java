package db1_MySql;

import java.sql.*;


public class DeleteAllData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String url = "jdbc:mysql://localhost:3306/EMP";
			Connection con = DriverManager.getConnection(url, "root", "root");
			
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM COMPANY"); //Le DELETE ne fait pas le reset du compteur
			stmt.executeUpdate("TRUNCATE TABLE COMPANY");//S'il y a des colonnes auto-increment, TRUNCATE reset le compteur  
			
			
			stmt.close();
			con.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}
