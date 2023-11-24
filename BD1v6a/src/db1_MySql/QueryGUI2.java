package db1_MySql;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueryGUI2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryGUI2 frame = new QueryGUI2();
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
	public QueryGUI2() {
		setTitle("QueryGUI2");
		
		try {
			String url = "jdbc:mysql://localhost:3306/EMP";
			Connection con = DriverManager.getConnection(url, "root", "root");
		
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY");
		
			table = new JTable();
			table.setModel(new MyTableModel(rs));
		
			rs.close();
			stmt.close();
			con.close();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Copy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount()==1) {
					textField.setText(table.getValueAt(table.getSelectedRows()[0], 0)+
							" "+ table.getValueAt(table.getSelectedRows()[0],1));
				}
			}
		});
		btnNewButton.setBounds(36, 204, 85, 21);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(155, 205, 230, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(35, 22, 360, 147);
		contentPane.add(scrollPane);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	class MyTableModel extends DefaultTableModel{
		
		public MyTableModel(ResultSet rs) throws SQLException {
			ResultSetMetaData metaData = rs.getMetaData();
			
			//name of the columns
			Vector <String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}
			
			// data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next()) {
				Vector<Object> row = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= columnCount;columnIndex++) {
					row.add(rs.getObject(columnIndex));
				}
				data.add(row);
			}
			this.setDataVector(data,columnNames);
		
		}
		@Override
		public boolean isCellEditable(int row, int column) {
			//all cells false
			return false;
		}
		
		
		
	
	
	}





}
