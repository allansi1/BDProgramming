package TotalDeptsIdsAnnee;
import java.sql.*;
import java.util.Calendar;

public class TotalizationCalculatorAnual {
	private static final String JDBC_URL = "jdbc:mysql://23.235.197.135:3306/instit43_GrandeOurse_DB_test?serverTimezone=UTC";
    private static final String JDBC_USER = "instit43_GOurse_DB_adm";
    private static final String JDBC_PASSWORD = "LGO@ig23";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            TotalizationCalculatorAnual calculator = new TotalizationCalculatorAnual();
            calculator.calculateTotalizations(connection);
            System.out.println("Totalizations completed!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error to process data.");
        }
    }

    public void calculateTotalizations(Connection connection) throws SQLException {
        String selectPeriodeQuery = "SELECT * FROM Periode WHERE TypePeriode = 'Anuelle' AND Année IS NOT NULL";
        try (Statement stmt = connection.createStatement();
             ResultSet periodeResultSet = stmt.executeQuery(selectPeriodeQuery)) {

            while (periodeResultSet.next()) {
                int idPeriode = periodeResultSet.getInt("IdPeriode");
                int annee = periodeResultSet.getInt("Année");

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, annee);
                cal.set(Calendar.MONTH, Calendar.JANUARY);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                Date startDate = new Date(cal.getTimeInMillis());

                cal.set(Calendar.MONTH, Calendar.DECEMBER);
                cal.set(Calendar.DAY_OF_MONTH, 31);
                Date endDate = new Date(cal.getTimeInMillis());

             
                String sumByDeptQuery = "SELECT ID_DEPT, SUM(MONTANT) AS Total_Montant FROM DailyEntry WHERE Date >= ? AND Date <= ? GROUP BY ID_DEPT";
                try (PreparedStatement sumStmt = connection.prepareStatement(sumByDeptQuery)) {
                    sumStmt.setDate(1, startDate);
                    sumStmt.setDate(2, endDate);
                    try (ResultSet sumRS = sumStmt.executeQuery()) {
                        while (sumRS.next()) {
                            int deptId = sumRS.getInt("ID_DEPT");
                            double totalAmount = sumRS.getDouble("Total_Montant");

                           
                            String insertQuery = "INSERT INTO TotalDansLaPeriode (TypedeLigne, IdPeriode, Totalization) VALUES (?, ?, ?)";
                            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                                insertStmt.setInt(1, deptId);
                                insertStmt.setInt(2, idPeriode);
                                insertStmt.setDouble(3, totalAmount);
                                insertStmt.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
    }


}
