package etudiant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Inserer extends JDialog {
	
	protected static Inserer current;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public Inserer() {
		setTitle("Inserer Etudiant");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_1 = new JTextField();
			textField_1.setBounds(112, 23, 173, 19);
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(112, 48, 173, 19);
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(112, 77, 173, 19);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(112, 106, 173, 19);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(112, 135, 173, 19);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			textField_6 = new JTextField();
			textField_6.setBounds(112, 164, 173, 19);
			contentPanel.add(textField_6);
			textField_6.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(31, 26, 45, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prenon");
		lblNewLabel_1.setBounds(31, 51, 45, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom");
		lblNewLabel_2.setBounds(31, 80, 45, 13);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Note MiSession");
		lblNewLabel_3.setBounds(10, 109, 86, 13);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Note Projet");
		lblNewLabel_4.setBounds(10, 138, 66, 13);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Note Examen Final");
		lblNewLabel_5.setBounds(10, 167, 86, 13);
		contentPanel.add(lblNewLabel_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean idValido = false;
				        int id = 0;
				       
				            try {
				                id = Integer.parseInt(textField_1.getText());
				                if (Etudiant.isValidId(id)) {
				                    idValido = true;
				                }
				            } catch (NumberFormatException ex) {
				                JOptionPane.showMessageDialog(null, "ID invalide! Insérez un ID avec 4 chiffres.");
				            }
				       
				        
						Etudiant etudiant = new Etudiant();
						//etudiant.setId(Integer.parseInt((textField_1.getText())));
						if(idValido) {
							etudiant.setId(id);
							etudiant.setPrenom((textField_2.getText()));
							etudiant.setNom((textField_3.getText()));
							etudiant.setNoteMiSession(Double.parseDouble((textField_4.getText())));
							etudiant.setNoteProjet(Double.parseDouble((textField_5.getText())));
							etudiant.setNoteExamenFinal(Double.parseDouble((textField_6.getText())));
							etudiant.calculerMoyenne();
							etudiant.calculerSituation();
						
						
							if(ControleEtudiant.sauvegarde(etudiant)) {
								JOptionPane.showMessageDialog(null, "Etudiant gardé");
								textField_1.setText("");
								textField_2.setText("");
								textField_3.setText("");
								textField_4.setText("");
								textField_5.setText("");
								textField_6.setText("");
							}
							else {
								JOptionPane.showMessageDialog(null, "Erreur");
							}
							Inserer.current.setVisible(false);
						}
					
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Quitter");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Inserer.current.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
