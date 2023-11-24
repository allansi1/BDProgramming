package connected;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public static EmpGUI current;      /* pour sauter d'une fen�tre � l'autre */ 
	
	Connection con;
	Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpGUI.current = new EmpGUI();
					EmpGUI.current.setVisible(true);
					EditForm.current = new EditForm(); 
					EditForm.current.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpGUI() {
		
		try {
			 String url = "jdbc:mysql://localhost:3306/EMP";
			 con = DriverManager.getConnection(url,"root", "root");
			 stmt = con.createStatement();
			 
			 table = new JTable();
			 reload();
			
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {					 
					stmt.close();
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
			}
		});
		
		setResizable(false);
		setTitle("EmpGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 245);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Edit");
		menuBar.add(mnFile);
		
		JMenuItem mntmInsert = new JMenuItem("Insert");
		mntmInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditForm.current.clear();
				EditForm.current.isUpdate = false;
				EditForm.current.setTitle("Ins�rer employ�");
				EditForm.current.setVisible(true);
			}
		});
		mnFile.add(mntmInsert);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (table.getSelectedRowCount() == 0) { 
					JOptionPane.showMessageDialog(null, "No line is selected");
				}
				else if (table.getSelectedRowCount() >1) {  
					JOptionPane.showMessageDialog(null, "Multiple lines are selected");
				}
				else {
					EditForm.current.editDataModel.setRow(getRow(table.getSelectedRows()[0]));
					EditForm.current.isUpdate = true;
					EditForm.current.setTitle("Modifier employ�");
					EditForm.current.setVisible(true);
				}
			}
		});
		mnFile.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRowCount() == 0) { 
					JOptionPane.showMessageDialog(null, "No line is selected");
				}
				else {
					int n_lines_to_delete = table.getSelectedRows().length;
					int opt = JOptionPane.showConfirmDialog(null, 
							   "Are you sure you want to delete "+ n_lines_to_delete+ " line(s)?",
							   "Confirmation",
							   JOptionPane.YES_NO_OPTION,
							   JOptionPane.QUESTION_MESSAGE,
							   null); 
					if (opt == 0) {
						try {
							for (int i = 0; i < n_lines_to_delete; i++) {
								stmt.executeUpdate("DELETE FROM company WHERE id = "
										+ table.getValueAt(table.getSelectedRows()[i], 0));
							}
							reload();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		mnFile.add(mntmDelete);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Example - Database programming course (Prof. Ramiro)";
				JOptionPane.showMessageDialog(null,msg, "About", JOptionPane.PLAIN_MESSAGE);	
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// JScrollPane scrollPane = new JScrollPane(table);  // put table in the constructor or use setViewportView 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 0, 428, 183);
		contentPane.add(scrollPane);		
		
	}
	
	void reload() {
		try {
			 ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY" );

			 table.setModel(buildTableModel(rs));   
			 
		     rs.close();
		}
		catch( Exception e ) {
			 e.printStackTrace();
		}
	}
	
	private  Object[] getRow(int row){  	
		Object ligne[] = new Object [table.getColumnCount()]; 
		for (int i=0; i < table.getColumnCount(); i++ ) {
			ligne[i] = table.getValueAt(row, i);
		}		
//		ligne[0] = table.getValueAt(row, 0);
//		ligne[1] = table.getValueAt(row, 1); 
//		ligne[2] = table.getValueAt(row, 2);
//		ligne[3] = table.getValueAt(row, 3);
//		ligne[4] = table.getValueAt(row, 4) ;
		return ligne;			  		 
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
