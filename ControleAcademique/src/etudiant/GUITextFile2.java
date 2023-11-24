package etudiant;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GUITextFile2 {
	
	private JPanel contentPane;
	
	private JTable table;
	private JFrame frmControleAcademique;
	private String currentFile = "";   
	public static Inserer current;
	private JTable table_1;
	public static Modifier current1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITextFile2 window = new GUITextFile2();
					window.frmControleAcademique.setVisible(true);
					Inserer.current = new Inserer();
					Inserer.current.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	class MyTableModel extends DefaultTableModel{
		
		private String columnNames[] = {"id", "Prénom", "Nom", "MiSession", "Projet", "Examen Final", "Moyenne","Situation"};
		public MyTableModel() {
			super();
			this.setColumnCount(columnNames.length);
			this.setColumnIdentifiers(columnNames);
			this.setRowCount(0);
		}
		
		public void setList(ArrayList<Etudiant> list, int actual_length) {
			this.setRowCount(0);
			for(int i=0; i<actual_length;i++) {
				Object ligne[] = new Object [columnNames.length];
				ligne[0]=list.get(i).getId();
				ligne[1] = list.get(i).getPrenom();
				ligne[2] = list.get(i).getNom();
				ligne[3] = list.get(i).getNoteMiSession();
				ligne[4] = list.get(i).getNoteProjet();
				ligne[5] = list.get(i).getNoteExamenFinal();
				ligne[6] = list.get(i).getMoyenne();
				ligne[7] = list.get(i).getStatus();
				this.addRow(ligne);
			}
		}
		
		public Object[] getRow(int row) {
			Object ligne[] = new Object [columnNames.length];
			ligne[0] = this.getValueAt(row, 0);
			ligne[0] = this.getValueAt(row, 1);
			ligne[0] = this.getValueAt(row, 2);
			ligne[0] = this.getValueAt(row, 3);
			ligne[0] = this.getValueAt(row, 4);
			ligne[0] = this.getValueAt(row, 5);
			ligne[0] = this.getValueAt(row, 6);
			ligne[0] = this.getValueAt(row, 7);
			
			return ligne;
		}
}
	MyTableModel dataModel = new MyTableModel();
	
	/**
	 * Create the application.
	 */
	
	private void displayInfos() {
		ArrayList<Etudiant> etud = ControleEtudiant.returnList();
		
				
		for(int i=0; i<etud.size();i++) {
			table.setValueAt(etud.get(i).getId(), i, 0);
			table.setValueAt(etud.get(i).getPrenom(), i, 1);
			table.setValueAt(etud.get(i).getNom(), i, 2);
			table.setValueAt(etud.get(i).getNoteMiSession(), i, 3);
			table.setValueAt(etud.get(i).getNoteProjet(), i, 4);
			table.setValueAt(etud.get(i).getNoteExamenFinal(), i, 5);
			
		}
				
	}
	
	public GUITextFile2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmControleAcademique = new JFrame();
		frmControleAcademique.setTitle("Controle Academique");
		frmControleAcademique.setBounds(100, 100, 447, 284);
		frmControleAcademique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControleAcademique.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 436, 231);
		frmControleAcademique.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(dataModel);
//		table_1.setModel(new DefaultTableModel(
//			new Object[][] {
//				{null, null, null, null, null, null, null, null},
//			},
//			new String[] {
//				"Id", "Prenom", "Nom", "Note MiSession", "Note Projet", "Note Ex Final", "Moyenne", "Situation"
//			}
//		));
		scrollPane.setViewportView(table_1);
		
		
		
// ==========================================================================================		
		/* 
		 * JFileChooser est une fen�tre-outil pour choisir un fichier pour lire ou �crire 
		 */
		JFileChooser fileChooser = new JFileChooser();	
		/* 
		 * Quel sera le dossier initial o� la fen�tre ouvre 
		 */
		fileChooser.setCurrentDirectory(new File(".\\")); 
		/* 
		 * Un filtre pour s�lectionner les extension que nous voulons travailler 
		 */
		FileFilter filter = new FileNameExtensionFilter("slt files", "slt"); 
		/* 
		 * Additionner le filtre � la fen�tre 
		 */
		fileChooser.addChoosableFileFilter(filter);
		/* 
		 * Faire ce filtre le defaut 
		 */
		fileChooser.setFileFilter(filter);
		/* 
		 * Seulement des fichiers peuvent �tre s�lectionn�s, pas de dossiers 
		 */
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		/* 
		 * On peut s�lectionner un seul fichier, pas de multiple fichiers
		 */
		fileChooser.setMultiSelectionEnabled(false);
// ==========================================================================================
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frmControleAcademique.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fichier");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					ControleEtudiant.le = new ArrayList<Etudiant>();
				    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {  
				        
				        currentFile= fileChooser.getSelectedFile().getAbsolutePath();
				        
				        fileChooser.setSelectedFile(new File(""));
				        dataModel.setRowCount(0);
				        				     
				        BufferedReader w = new BufferedReader(new FileReader(currentFile));
				        String v = w.readLine();
				        String[] row;
				        while (v != null) {
				        	row = v.split("/");
				        	if (row.length==8) {
				        		
				        		dataModel.addRow(row);
				        		Etudiant e1 = new Etudiant(Integer.parseInt(row[0]),row[1],row[2],Double.parseDouble(row[3]),Double.parseDouble(row[4]),Double.parseDouble(row[5]),
				            	Double.parseDouble(row[6]),row[7]);
				        		ControleEtudiant.le.add(e1);
				        	}
				            v = w.readLine();
				            				            
				        }
				        w.close();
				    }
				} catch (FileNotFoundException e1) {					
				    e1.printStackTrace();
				} catch (IOException e1) {					
				    e1.printStackTrace();
				}
				
				
				
			}
		});
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Nouveau");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		       ControleEtudiant.le = new ArrayList<Etudiant>();
		        
		       dataModel.setList(ControleEtudiant.le, 0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_12);
		mnNewMenu.add(mntmOuvrir);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Enregistrer");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {PrintWriter w;
	        if (currentFile=="") {
	            if (fileChooser.showSaveDialog(null) ==  JFileChooser.APPROVE_OPTION) {
	                currentFile= fileChooser.getSelectedFile().getAbsolutePath();
	                String selFileName=fileChooser.getSelectedFile().getName();
	                if(selFileName.lastIndexOf(".")<0) {
	                    currentFile=currentFile+".slt";
	                }
	                fileChooser.setSelectedFile(new File(""));
	            }           
	        }
	        try {
	            if (currentFile != "") {
	                w = new PrintWriter(new FileWriter(currentFile));
	                for (int i = 0; i < table_1.getRowCount(); i++) {
	                    for (int j = 0; j < table_1.getColumnCount(); j++) {
	                        w.print(table_1.getValueAt(i, j) + "/");
	                    }
	                    w.println();
	                }
	                w.close();
	            }
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }  }
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Enregistrer sous");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter w;
				
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					currentFile = fileChooser.getSelectedFile().getAbsolutePath();
					String selFileName = fileChooser.getSelectedFile().getName();
					if (selFileName.lastIndexOf(".") < 0) {
						currentFile = currentFile + ".slt";
					}
					fileChooser.setSelectedFile(new File(""));
					try {
						if (currentFile != "") {
							w = new PrintWriter(new FileWriter(currentFile));
							for (int i = 0; i < table_1.getRowCount(); i++) {
								for (int j = 0; j < table_1.getColumnCount(); j++) {
									w.print(table_1.getValueAt(i, j) + "/");
								}
								w.println();
							}
							w.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Fermer");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		//=============================================================================================
		JMenu mnNewMenu_1 = new JMenu("Édition");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ajouter");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inserer.current.setVisible(true);
				System.out.println("Ajouter:"+ControleEtudiant.returnList().size());
				dataModel.setList(ControleEtudiant.le, ControleEtudiant.le.size());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Modifier");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_1.getSelectedRow();
		        if (selectedRow != -1) {
		            Object[] rowData = new Object[table_1.getColumnCount()];
		            for (int i = 0; i < table_1.getColumnCount(); i++) {
		                rowData[i] = table_1.getValueAt(selectedRow, i);
		            }
		            Modifier.current1 = new Modifier(rowData, dataModel);
		            Modifier.current1.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne à modifier", "Erreur",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    
				
			}
			
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Supprimer");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int[] selectedRows = table_1.getSelectedRows();
		        if (selectedRows.length == 0) { 
		            JOptionPane.showMessageDialog(null, "Aucune ligne séléctionée");
		        }
		        else {
		            int n_lines_to_delete = selectedRows.length;
		            int opt = JOptionPane.showConfirmDialog(null, 
		                               "Vous êtes sûr pour supprimer le(s) "+ n_lines_to_delete+ " ligne(s)?",
		                               "Confirmation",
		                               JOptionPane.YES_NO_OPTION,
		                               JOptionPane.QUESTION_MESSAGE,
		                               null); 
		            if (opt == 0) {
		                for (int i = n_lines_to_delete - 1; i >= 0; i--) {
		                    int selectedRowIndex = selectedRows[i];
		                    dataModel.removeRow(selectedRowIndex);
		                    ControleEtudiant.le.remove(selectedRowIndex);
		                }
		            }
		        }
		    }
			
				
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Supprimer tout");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleEtudiant.clearList();
				dataModel.setRowCount(0);  // Delete the lines (there is only one)
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
	//==================================================================================	
		JMenu mnNewMenu_2 = new JMenu("Outils");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Rechercher");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rechercher.current2 = new Rechercher(dataModel);
		        Rechercher.current2.setVisible(true);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Lister tout");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ArrayList<Etudiant> etudiants = ControleEtudiant.returnList();
		               
		        Object[][] data = new Object[etudiants.size()][8];
		        
		        for (int i = 0; i < etudiants.size(); i++) {
		            Etudiant etudiant = etudiants.get(i);
		            data[i][0] = etudiant.getId();
		            data[i][1] = etudiant.getPrenom();
		            data[i][2] = etudiant.getNom();
		            data[i][3] = etudiant.getNoteMiSession();
		            data[i][4] = etudiant.getNoteProjet();
		            data[i][5] = etudiant.getNoteExamenFinal();
		            data[i][6] = etudiant.getMoyenne();
		            data[i][7] = etudiant.getStatus();
		            			
			}
		        dataModel.setList(etudiants, etudiants.size());
			}});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_4 = new JMenu("Ordonner");
		mnNewMenu_2.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("par id");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(ControleEtudiant.returnList(), new Comparator<Etudiant>() {
		            @Override
		            public int compare(Etudiant e1, Etudiant e2) {
		                return Integer.compare(e1.getId(), e2.getId());
		            }
		        });

		        dataModel.setList(ControleEtudiant.returnList(), ControleEtudiant.returnList().size());
				
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("par nom");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Collections.sort(ControleEtudiant.returnList(), new Comparator<Etudiant>() {
		            @Override
		            public int compare(Etudiant e1, Etudiant e2) {
		                return e1.getNom().compareTo(e2.getNom());
		            }
		        });

		        
		        dataModel.setList(ControleEtudiant.returnList(), ControleEtudiant.returnList().size());
				
				
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_10);
		//========================================================================================
		JMenu mnNewMenu_3 = new JMenu("Aide");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("À propos");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "CONTRÔLE ACADÉMIQUE\n "
						+ "Travail Practique. Prof: Ramiro\n"
						+ "Étudiant: Allan Inhauser");
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_11);
				
		
	}
	

	
	
	
}
