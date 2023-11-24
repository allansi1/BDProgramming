

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DailyEntry {

	public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://23.235.197.135:3306/instit43_GrandeOurse_DB_test?serverTimezone=UTC";
        String user = "instit43_GOurse_DB_adm";
        String password = "LGO@ig23";

        String csvFile = "DailyEntry2.csv";

        try (BufferedReader w = new BufferedReader(new FileReader(csvFile));
             Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {

            String insertQuery = "INSERT INTO DailyEntry (Date, ID_DEPT, Montant) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            String line;
            w.readLine();
            while ((line = w.readLine()) != null) {
                String[] values = line.split(";");
                
                preparedStatement.setString(1, values[0]); // Date
                preparedStatement.setInt(2, Integer.parseInt(values[1])); // ID_DEPT
                preparedStatement.setDouble(3, Double.parseDouble(values[2])); // Montant
                preparedStatement.executeUpdate();
            }            

            System.out.println("Datas inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
