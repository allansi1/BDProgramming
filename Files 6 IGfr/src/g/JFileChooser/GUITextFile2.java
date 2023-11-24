package g.JFileChooser;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.awt.event.ActionEvent;

public class GUITextFile2 {

	private JFrame frame;
	private String currentFile = "";   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITextFile2 window = new GUITextFile2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITextFile2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("JFileChooser Example");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 132);
		frame.getContentPane().add(textArea);
		
// ==========================================================================================		
		/* 
		 * JFileChooser est une fenêtre-outil pour choisir un fichier pour lire ou écrire 
		 */
		JFileChooser fileChooser = new JFileChooser();	
		/* 
		 * Quel sera le dossier initial où la fenêtre ouvre 
		 */
		fileChooser.setCurrentDirectory(new File(".\\")); 
		/* 
		 * Un filtre pour sélectionner les extension que nous voulons travailler 
		 */
		FileFilter filter = new FileNameExtensionFilter("text files", "txt"); 
		/* 
		 * Additionner le filtre à la fenêtre 
		 */
		fileChooser.addChoosableFileFilter(filter);
		/* 
		 * Faire ce filtre le defaut 
		 */
		fileChooser.setFileFilter(filter);
		/* 
		 * Seulement des fichiers peuvent être sélectionnés, pas de dossiers 
		 */
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		/* 
		 * On peut sélectionner un seul fichier, pas de multiple fichiers
		 */
		fileChooser.setMultiSelectionEnabled(false);
// ==========================================================================================
		
		JButton btnNewButton = new JButton("Open");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {		
					/* fileChooser.showOpenDialog est appelé et si un fichier est sélectionné */
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {  
						/* Mettre le chemin absolu du fichier sélectionné dans la variable currentFile */
						currentFile= fileChooser.getSelectedFile().getAbsolutePath();
						/* Nettoyer la fenêtre de fileChooser */
						fileChooser.setSelectedFile(new File(""));
						BufferedReader w = new BufferedReader(new FileReader(currentFile));
						String v = w.readLine();
						while (v != null) {
							textArea.append(v + '\n');  
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
		btnNewButton.setBounds(30, 185, 70, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter w;
				if (currentFile=="") {
					/* 
					 * fileChooser.showSaveDialog est appelé et si un fichier est sélectionné  
					 * ou un nom de fichier est  entré
					 */
					if (fileChooser.showSaveDialog(null) ==  JFileChooser.APPROVE_OPTION) {
						/* Mettre le chemin absolu du fichier sélectionné dans la variable currentFile */
						currentFile= fileChooser.getSelectedFile().getAbsolutePath();
						/* Si l'utilisateur entre un nom sans extension, il faut ajouter .txt */
						String selFileName=fileChooser.getSelectedFile().getName();
						if(selFileName.lastIndexOf(".")<0) {
							currentFile=currentFile+".txt";
						}
						/* Nettoyer la fenêtre de fileChooser */
						fileChooser.setSelectedFile(new File(""));
					}			
				}
				try {
					if (currentFile != "") {
						w = new PrintWriter(new FileWriter(currentFile));
						w.write(textArea.getText());
						w.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}						
			}
		});
		btnNewButton_1.setBounds(118, 185, 70, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Save as");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter w;
				/* 
				 * fileChooser.showSaveDialog est appelé et si un fichier est sélectionné  
				 * ou un nom de fichier est  entré
				 */
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					/* Mettre le chemin absolu du fichier sélectionné dans la variable currentFile */
					currentFile = fileChooser.getSelectedFile().getAbsolutePath();
					/* Si l'utilisateur entre un nom sans extension, il faut ajouter .txt */
					String selFileName = fileChooser.getSelectedFile().getName();
					if (selFileName.lastIndexOf(".") < 0) {
						currentFile = currentFile + ".txt";
					}
					/* Nettoyer la fenêtre de fileChooser */
					fileChooser.setSelectedFile(new File(""));
				}
				try {
					if (currentFile != "") {
						w = new PrintWriter(new FileWriter(currentFile));
						w.write(textArea.getText());
						w.close();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(206, 185, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Clear");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnNewButton_3.setBounds(335, 185, 70, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}
