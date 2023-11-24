package connected;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connected.EditForm.MyEditTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EditForm extends JDialog {

	private final JPanel contentPane;
	private JTable table;
	
	boolean isUpdate = true; 
	
	public static EditForm current;    /* pour sauter d'une fen�tre � l'autre */
	
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
		private String columnNames []={"ID", "NAME", "GRADE"};
		
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
			//line[3]=this.getValueAt(0, 3);
			//line[4]=this.getValueAt(0, 4) ;
			return line;			  		 
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
		    if (isUpdate && (column==0|| column==1)) { return false;} 
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
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditForm frame = new EditForm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		// ou 
		// JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 0, 717, 53);
		contentPane.add(scrollPane);
			
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isUpdate) {					
					if (myValidate()) {
						try {
							EtudGUI.current.stmt.executeUpdate(
									"UPDATE students SET name = '"+ table.getValueAt(0, 1)+"',"+
									" grade = "+table.getValueAt(0, 2)+									
									//" salary = "+ table.getValueAt(0, 4) +
									" WHERE id = "+table.getValueAt(0, 0)
							);
							EtudGUI.current.reload();
							JOptionPane.showMessageDialog(null, "Base de donn�e modifi�e");
						} 
						catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Base de donn�e pas modifi�e");
							e1.printStackTrace();
						}						
						EditForm.current.dispose();  // pour fermer la fen�tre.  
					} //end if validate()
				} //end if isUpdate 
				else { // not isUpdate  -- add
					if (myValidate()) {
						try {
							EtudGUI.current.stmt.executeUpdate(
									"INSERT INTO students (id,name,grade)" +
									" VALUES (" +
										table.getValueAt(0, 0)+","+
										"'" + table.getValueAt(0, 1)+"',"+
										table.getValueAt(0, 2)+")");
										//"'"+ table.getValueAt(0, 3) +"',"+
										//table.getValueAt(0, 4) + 
																
							
							EtudGUI.current.reload();
							JOptionPane.showMessageDialog(null, "Donn�es ins�r�es dans la base de donn�es");
						} 
						catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Exception: Donn�s pas ins�r�es");
							// e1.printStackTrace();
						}						
						EditForm.current.dispose();
					} //end if validate()
				} //end else isUpdate 
			}
		});
		btnOk.setBounds(476, 100, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditForm.current.dispose();   // pour fermer la fen�tre. 
			}
		});
		btnCancel.setBounds(594, 100, 89, 23);
		contentPane.add(btnCancel);
	}
	
	private boolean myValidate() {	
		/* 
		 *  Si c'est une insertion, nous devons nous assur� que l'id n'existe pas d�j� 
		 *  dans la base de donn�es, parce que l'id est cl�. 
		 * 
		 */ 
		if(!isUpdate) {      // si c'est une insertion
			if(!isNonNegInteger(table.getValueAt(0, 0))) {    // si le nouveau id est invalide (n�gatif)
				JOptionPane.showMessageDialog(null, "Id invalide");
				return false;				                  // dans ce cas, la fonction fini ici 
			}
			/*
			 * Si le nouveu id est valide, if faut v�rifier s'il n'existe pas d�j� dans la base de donn�es
			 */
			try {
				ResultSet rs = EtudGUI.current.stmt.executeQuery( 
						"SELECT * FROM students WHERE id =  "+ table.getValueAt(0, 0)
						);
				if (rs.first()) {
					JOptionPane.showMessageDialog(null, "Id r�p�t�: id existe d�j� dans la base de donn�es");
					return false;                            // dans ce cas, la fonction fini ici 
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Base de donn�es inaccessible");
				return false;                                // dans ce cas, la fonction fini ici 
			}		
		}  // end if(!isUpdate)
		/*
		 * Alors, � ce point:
		 * 1. Si c'est une insertion, l'id est valide et il n'existe pas dans la base de donn�es.
		 * 2. Si c'est un mis-�-jour, alors il n'a rien a v�rifier, parce que l'id ne peut pas �tre chang�.	
		 * 
		 * Tout ce qui reste � faire est la validation des autres champs.
		 * 	  
		 */
		/* 
		 * Validation de la grade.
		 */
		if(!isNonNegInteger(table.getValueAt(0, 2))) {			
			JOptionPane.showMessageDialog(null, "grade invalide: Grade doit �tre un entier non-negatif");
			return false;				
		}
		/* 
		 * Validation du salaire
		 */
		/*if(!isNonNegDouble(table.getValueAt(0, 4))) {
			JOptionPane.showMessageDialog(null, "Salaire invalide: salaire don't �tre un double non-negatif");
			return false;				
		}*/
		return true;
	}
	
	static boolean isNonNegInteger(Object s) {
		if (s==null){return false; }
		try{
			 int r=Integer.parseInt(""+s);  
			 if (r>=0) { return true; }
			 else { return false; }
		}
		catch (Exception e){
			return false;
		}
	}
	
	/*static boolean isNonNegDouble(Object s) {
		if (s==null){return false; }
		try{
			 double r=Double.parseDouble(""+s);
			 if (r>=0) { return true; }
			 else { return false; }
		}
		catch (Exception e){
			return false;
		}
	}*/

}
