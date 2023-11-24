package db1_MySql;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Test extends JFrame {

	private JPanel contentPane;
	
	Connection con;
	Statement stmt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		try { 
		    String url = "jdbc:mysql://localhost:3306/EMP"; 
		    con = DriverManager.getConnection(url,"root", "root"); 
		    con.setAutoCommit(false); // toutes les mises à jour réussissent ou aucune
		    stmt = con.createStatement();
		    // insérer ici les instructions SQL à exécuter
		    String sql = "INSERT INTO company (id, name, age, salary) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setString(2, "John Doe");
            stmt.setInt(3, 30);
            stmt.setDouble(4, 55000);

            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Enregistrement ajouté avec succès");
            } else {
                System.out.println("Impossible d'ajouter l'enregistrement");
            }
		    con.commit(); 
		    stmt.close(); 
		    con.close();
		} catch (Exception e) { 
		    e.printStackTrace(); 
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 426, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
	}
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> row = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				row.add(rs.getObject(columnIndex));
			}
			data.add(row);
		}

		return new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
	}
}
