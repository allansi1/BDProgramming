package connected;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditForm extends JDialog {

	private JPanel contentPane;
	private JTable table;
	
	boolean isUpdate = true; 
	
	public static EditForm current;      /* pour sauter d'une fenêtre à l'autre */ 

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			EditForm dialog = new EditForm();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	class MyEditTableModel extends DefaultTableModel {
		private String columnNames []={"ID", "NAME", "AGE", "ADDRESS",
		"SALARY"};
		
		public MyEditTableModel(){
			super();
			/* this.setColumnCount(columnNames.length); */
			this.setColumnIdentifiers(columnNames);
			this.setRowCount(1); 
		}
		
		public void setRow(Object lineMain[]){  
			this.setRowCount(0);  		
			this.addRow(lineMain);			    		 
		    }
		
		public Object[] getRow(){  	
			Object line[] = new Object [columnNames.length];
			line[0]=this.getValueAt(0, 0);
			line[1]=this.getValueAt(0, 1);
			line[2]=this.getValueAt(0, 2);
			line[3]=this.getValueAt(0, 3);
			line[4]=this.getValueAt(0, 4) ;
			return line;			  		 
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
		    if (isUpdate && column==0) { return false;} 
			else return true;
		}
	}
	
	MyEditTableModel editDataModel = new MyEditTableModel(); 
	
	public void clear(){ 
		//editDataModel.setRow(new Object[] {null, null, null, null , null});
		editDataModel.setRowCount(0);  // Delete the lines (there is only one)
		editDataModel.setRowCount(1);  // Add an empty line
	}

	/**
	 * Create the dialog.
	 */
	public EditForm() {
		setResizable(false);
		setBounds(100, 100, 730, 183);
		
		setModal(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		table = new JTable(editDataModel);	
		//ou
		// table = new JTable();
		// table.setModel(editDataModel);
		table.setRowHeight(30); 
		/* 
		 * pour garantir que l'edition d'une cellule termine si le focus sort de la table 
		 */
		table.putClientProperty("terminateEditOnFocusLost", true); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 716, 102);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditForm.current.dispose();
			}
		});
		btnNewButton.setBounds(439, 113, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(577, 113, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
	}
}
