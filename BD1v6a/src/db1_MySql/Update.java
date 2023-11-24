package db1_MySql;

import java.sql.*;


public class Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		String url = "jdbc:mysql://localhost:3306/EMP";
		Connection con = DriverManager.getConnection(url, "root", "root");
		
		
		Statement stmt = con.createStatement();
		con.setAutoCommit(false);//ativa o modo rascunho para os comandos abaixo (quando está false)
		stmt.executeUpdate("UPDATE COMPANY SET salary = 25000.00 where ID=1;");
		stmt.executeUpdate("DELETE from COMPANY where ID=2;");
		con.commit();
		
		con.setAutoCommit(true);
	
		ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY");
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String adress = rs.getString("adress");
			float salary = rs.getFloat("salary");
			System.out.println("ID = " + id);
			System.out.println("NAME = " + name);
			System.out.println("AGE = " + age);
			System.out.println("ADRESS = " + adress);
			System.out.println("SALARY = " + salary);
			
		}
		rs.close();
		stmt.close();
		con.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
