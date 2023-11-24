package etudiant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import etudiant.GUITextFile2.MyTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Rechercher extends JDialog {
	
	protected static Rechercher current2;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private MyTableModel dataModel;


	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	
	
	public Rechercher(MyTableModel dataModel) {
		setTitle("Rechercher");
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_1 = new JTextField();
			textField_1.setBounds(161, 23, 173, 19);
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(161, 52, 173, 19);
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		
		this.dataModel = dataModel;
		current2 = this;
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(31, 26, 45, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom de famille");
		lblNewLabel_1.setBounds(31, 51, 77, 13);
		contentPanel.add(lblNewLabel_1);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String idString = textField_1.getText().trim();
						String nom = textField_2.getText().trim();

						if (idString.isEmpty() && nom.isEmpty()) {
						    JOptionPane.showMessageDialog(null, "Veuillez saisir un ID ou un nom de famille", "Erreur",
						            JOptionPane.ERROR_MESSAGE);
						    return;
						}

						int id = 0;
						if (!idString.isEmpty()) {
						    try {
						        id = Integer.parseInt(idString);
						    } catch (NumberFormatException e1) {
						        JOptionPane.showMessageDialog(null, "ID doit être un nombre entier", "Erreur",
						                JOptionPane.ERROR_MESSAGE);
						        return;
						    }
						}

						ArrayList<Etudiant> filteredList = new ArrayList<>();
						boolean found = false;
						for (Etudiant etudiant : ControleEtudiant.le) {
						    if ((id != 0 && etudiant.getId() == id) || (!nom.isEmpty() && etudiant.getNom().equals(nom))) {
						        filteredList.add(etudiant);
						        found = true;
						    }
						}

						if (!found) {
						    JOptionPane.showMessageDialog(null, "Aucun étudiant trouvé avec cet ID ou ce nom de famille", "Erreur",
						            JOptionPane.ERROR_MESSAGE);
						} else {
						    
						   dataModel.setList(filteredList, filteredList.size());
						}

						Rechercher.current2.setVisible(false);
						
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
						Rechercher.current2.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
