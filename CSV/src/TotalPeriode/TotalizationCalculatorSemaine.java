package TotalPeriode;
import java.sql.*;

public class TotalizationCalculatorSemaine {
    private static final String JDBC_URL = "jdbc:mysql://23.235.197.135:3306/instit43_GrandeOurse_DB_test?serverTimezone=UTC";
    private static final String JDBC_USER = "instit43_GOurse_DB_adm";
    private static final String JDBC_PASSWORD = "LGO@ig23";
    
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
        	TotalizationCalculatorSemaine calculator = new TotalizationCalculatorSemaine();
            calculator.calculateTotalizations(connection);
            System.out.println("Totalizations completed!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error to connect to database.");
        }
    }
    
    public void calculateTotalizations(Connection connection) throws SQLException {
        String selectPeriodeQuery = "SELECT * FROM Periode";
        try (Statement stmt = connection.createStatement();
             ResultSet periodeResultSet = stmt.executeQuery(selectPeriodeQuery)) {
             
            while (periodeResultSet.next()) {
                int idPeriode = periodeResultSet.getInt("IdPeriode");
                Date dateDebut = periodeResultSet.getDate("DateDebut");
                
                if (dateDebut == null) {
                    System.err.println("Aviso:Line with DateDebut null for IdPeriode = " + idPeriode);
                    continue;
                }
                int mois = periodeResultSet.getInt("Mois");
                int trimestre = periodeResultSet.getInt("Trimestre");
                int annee = periodeResultSet.getInt("Année");

                // Determine the start and end dates based on the period type
                Date startDate = dateDebut;  // default for "Dans la semaine"
                Date endDate = new Date(dateDebut.getTime() + 6 * 24 * 60 * 60 * 1000);  // default for "Dans la semaine"
                // TODO: Adjust startDate and endDate based on Mois, Trimestre, Année if needed
                
                String selectTotalsQuery = "SELECT SUM(PO) as sumPO, SUM(TPS) as sumTPS, SUM(TVQ) as sumTVQ, SUM(Total_Des_Departements) as sumTDD, SUM(Rabais) as sumRabais FROM DailyTotals WHERE Date BETWEEN ? AND ?";
                try (PreparedStatement pStmt = connection.prepareStatement(selectTotalsQuery)) {
                    pStmt.setDate(1, startDate);
                    pStmt.setDate(2, endDate);
                    ResultSet totalsResultSet = pStmt.executeQuery();
                    
                    if (totalsResultSet.next()) {
                        double sumPO = totalsResultSet.getDouble("sumPO");
                        double sumTPS = totalsResultSet.getDouble("sumTPS");
                        double sumTVQ = totalsResultSet.getDouble("sumTVQ");
                        double sumTDD = totalsResultSet.getDouble("sumTDD");
                        double sumRabais = totalsResultSet.getDouble("sumRabais");
                        
                        // Insert these sums into TotalDansLaPeriode
                        String insertTotalizationQuery = "INSERT INTO TotalDansLaPeriode (TypedeLigne, IdPeriode, Totalization) VALUES (?, ?, ?)";
                        try (PreparedStatement insertStmt = connection.prepareStatement(insertTotalizationQuery)) {
                            insertStmt.setString(1, "PO");
                            insertStmt.setInt(2, idPeriode);
                            insertStmt.setDouble(3, sumPO);
                            insertStmt.executeUpdate();

                            insertStmt.setString(1, "TPS");
                            insertStmt.setDouble(3, sumTPS);
                            insertStmt.executeUpdate();

                            insertStmt.setString(1, "TVQ");
                            insertStmt.setDouble(3, sumTVQ);
                            insertStmt.executeUpdate();

                            insertStmt.setString(1, "Total_Des_Departements");
                            insertStmt.setDouble(3, sumTDD);
                            insertStmt.executeUpdate();

                            insertStmt.setString(1, "Rabais");
                            insertStmt.setDouble(3, sumRabais);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }
	

	

}
