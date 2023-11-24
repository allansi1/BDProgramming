package db1_MySql;
import java.sql.*;

public class Query {

	public static void main(String[] args) {
		try {
			
			String url = "jdbc:mysql://localhost:3306/EMP";
			Connection con = DriverManager.getConnection(url,"root","root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int  age = rs.getInt("age");
				String adress = rs.getString("adress");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADRESS = " + adress);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();
			con.close();
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
