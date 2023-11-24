package connected;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
					EditForm.current=new EditForm();
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
		
		setTitle("EmpGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Edit");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Insert");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditForm.current.setTitle("Ins�rer employ�");
				EditForm.current.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Update");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Delete");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("About");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "Example - Database programming course (Prof. Ramiro)";
				JOptionPane.showMessageDialog(null,msg, "About", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// JScrollPane scrollPane = new JScrollPane(table);  // put table in the constructor or use setViewportView 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(1, 0, 436, 241);
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
