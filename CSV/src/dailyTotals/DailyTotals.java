package dailyTotals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DailyTotals {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://23.235.197.135:3306/instit43_GrandeOurse_DB_test?serverTimezone=UTC";
        String user = "instit43_GOurse_DB_adm";
        String password = "LGO@ig23";
        String csvFile = "DailyTotals.csv";

        try (BufferedReader w = new BufferedReader(new FileReader(csvFile));
             Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {

            String insertQuery = "INSERT INTO DailyTotals (Date, PO, TPS, TVQ, Total_Des_Departements, Rabais) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            String line;
            w.readLine(); // skip header
            while ((line = w.readLine()) != null) {
                String[] values = line.split(";", -1);  // Include trailing empty strings

                preparedStatement.setString(1, values[0]); // Date
                preparedStatement.setObject(2, values[1].isEmpty() ? null : Double.parseDouble(values[1])); // PO
                preparedStatement.setObject(3, values[2].isEmpty() ? null : Double.parseDouble(values[2])); // TPS
                preparedStatement.setObject(4, values[3].isEmpty() ? null : Double.parseDouble(values[3])); // TVQ
                preparedStatement.setObject(5, values[4].isEmpty() ? null : Double.parseDouble(values[4])); // Total_Des_Departements
                preparedStatement.setObject(6, values[5].isEmpty() ? null : Double.parseDouble(values[5])); // Rabais

                preparedStatement.executeUpdate();
            }

            System.out.println("Datas inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
