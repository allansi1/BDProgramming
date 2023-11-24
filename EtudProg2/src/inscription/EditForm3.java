package inscription;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.awt.event.ActionEvent;

public class EditForm3 extends JDialog {

	private final JPanel contentPane;
	private JTable table;
	
	boolean isUpdate = true; 
	
	public static EditForm3 current;    /* pour sauter d'une fen�tre � l'autre */
	
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
		private String columnNames []={"E_Id", "C_id"};
		
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
			//line[2]=this.getValueAt(0, 2);
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
	public EditForm3() {
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
				if (isUpdate) {
				    if (myValidate()) {
				        /*try {
				        	String updateQuery = "UPDATE cours SET C_nom = ?, C_duree = ? WHERE C_Id = ?";
				            PreparedStatement pstmt = EmpGUI3.current.con.prepareStatement(updateQuery);
				            pstmt.setString(1, (String) table.getValueAt(0, 1));
				            pstmt.setInt(2, Integer.parseInt((String) table.getValueAt(0, 2)));
				            pstmt.setString(3, (String) table.getValueAt(0, 0));
				            pstmt.executeUpdate();

				            EmpGUI3.current.reload();
				            JOptionPane.showMessageDialog(null, "Base de donnée modifiée");
				        } catch (SQLException e1) {
				            JOptionPane.showMessageDialog(null, "Base de donnée pas modifiée");
				            e1.printStackTrace();
				        }
				        EditForm3.current.dispose();*/
				    } // end if validate()
				} else { // not isUpdate -- add
				    if (myValidate()) {
				        try {
				        	 String insertQuery = "INSERT INTO inscription (E_Id, C_Id) VALUES (?, ?)";
				             PreparedStatement pstmt = EmpGUI3.current.con.prepareStatement(insertQuery);
				             pstmt.setString(1, (String) table.getValueAt(0, 0));
				             if (table.getValueAt(0, 1) != null) {
				                 pstmt.setString(2, (String) table.getValueAt(0, 1));
				             } else {
				                 pstmt.setNull(2, Types.VARCHAR);
				             }
				             //pstmt.setString(2, (String) table.getValueAt(0, 1));
				             //pstmt.setInt(3, Integer.parseInt((String) table.getValueAt(0, 2)));
				             pstmt.executeUpdate();

				             EmpGUI3.current.reload();
				             JOptionPane.showMessageDialog(null, "Données insérées dans la base de données");
				        } catch (SQLException e1) {
				            JOptionPane.showMessageDialog(null, "Exception: Données pas insérées");
				            e1.printStackTrace();
				        }
				        EditForm3.current.dispose();
				    } // end if validate()
				} // end else isUpdate
			}
		});
		btnOk.setBounds(476, 100, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditForm3.current.dispose();   // pour fermer la fen�tre. 
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
			if(!isValidEId(table.getValueAt(0, 0))) {    // si le nouveau id est invalse (n�gatif)
				JOptionPane.showMessageDialog(null, "E_Id invalide");
				return false;				                  // dans ce cas, la fonction fini ici 
			}
			
			if (!isValidCId(table.getValueAt(0, 1))) {
	            JOptionPane.showMessageDialog(null, "C_Id invalide");
	            return false;
	        }
			
			
			/*
			 * Si le nouveu id est valide, if faut v�rifier s'il n'existe pas d�j� dans la base de donn�es
			 */
			try {
				ResultSet rs = EmpGUI3.current.stmt.executeQuery( 
						"SELECT * FROM inscription WHERE E_Id =  '" + table.getValueAt(0, 0) + "' AND C_Id =  '" + table.getValueAt(0, 1) + "'"
						);
				if (rs.first()) {
					JOptionPane.showMessageDialog(null, "Id répété: ids existent déjà dans la base de données");
					return false;                            // dans ce cas, la fonction fini ici 
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Base de données inaccessible");
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
		 * Validation de l'�ge.
		 */
		/*if(!isNonNegInteger(table.getValueAt(0, 2))) {			
			JOptionPane.showMessageDialog(null, "�ge invalide: �ge doit �tre un entier non-negatif");
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
	
	static boolean isValidEId(Object eId) {
		if (eId==null){return false; }
		try{
			return ((String) eId).matches("^E[0-9]{7}$");
		}
		catch (Exception e){
			return false;
		}
	}
	
	static boolean isValidCId(Object cId) {
	    if (cId == null) {
	        return true;
	    }
	    try {
	        return ((String) cId).matches("^C[0-9]{5}$");
	    } catch (Exception e) {
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
