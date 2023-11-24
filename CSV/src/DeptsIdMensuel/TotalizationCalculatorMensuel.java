package DeptsIdMensuel;

import java.sql.*;
import java.util.Calendar;

public class TotalizationCalculatorMensuel {

	private static final String JDBC_URL = "jdbc:mysql://23.235.197.135:3306/instit43_GrandeOurse_DB_test?serverTimezone=UTC";
    private static final String JDBC_USER = "instit43_GOurse_DB_adm";
    private static final String JDBC_PASSWORD = "LGO@ig23";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            TotalizationCalculatorMensuel calculator = new TotalizationCalculatorMensuel();
            calculator.calculateTotalizations(connection);
            System.out.println("Totalizações 'Mensuel' completed!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error to connect to BD.");
        }
    }

    public void calculateTotalizations(Connection connection) throws SQLException {
    	String selectPeriodeQuery = "SELECT * FROM Periode WHERE TypePeriode = 'Mensuel' AND Mois IS NOT NULL";
        try (Statement stmt = connection.createStatement();
             ResultSet periodeResultSet = stmt.executeQuery(selectPeriodeQuery)) {
             
            while (periodeResultSet.next()) {
                int idPeriode = periodeResultSet.getInt("IdPeriode");
                int mois = periodeResultSet.getInt("Mois");
                int annee = periodeResultSet.getInt("Année");

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, annee);
                cal.set(Calendar.MONTH, mois - 1); 
                cal.set(Calendar.DAY_OF_MONTH, 1);
                Date startDate = new Date(cal.getTimeInMillis());
                
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date endDate = new Date(cal.getTimeInMillis());

                String selectTotalsQuery = "SELECT ID_DEPT, SUM(MONTANT) as sumMONTANT FROM DailyEntry WHERE Date BETWEEN ? AND ? GROUP BY ID_DEPT";
                try (PreparedStatement pStmt = connection.prepareStatement(selectTotalsQuery)) {
                    pStmt.setDate(1, startDate);
                    pStmt.setDate(2, endDate);
                    ResultSet totalsResultSet = pStmt.executeQuery();
                    
                    while (totalsResultSet.next()) {
                        int idDept = totalsResultSet.getInt("ID_DEPT");
                        double sumMONTANT = totalsResultSet.getDouble("sumMONTANT");
                        
                       
                        String insertTotalizationQuery = "INSERT INTO TotalDansLaPeriode (TypedeLigne, IdPeriode, Totalization) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertTotalizationQuery)) {
                            insertStmt.setString(1, String.valueOf(idDept));
                            insertStmt.setInt(2, idPeriode);
                            insertStmt.setDouble(3, sumMONTANT);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }

}
