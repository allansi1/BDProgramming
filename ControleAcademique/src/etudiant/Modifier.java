package etudiant;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Modifier extends JDialog {
	
	protected static Modifier current1;
	
	
	private final JPanel contentPanel = new JPanel();
	/*private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;*/
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	protected Etudiant etudiant;


	private Object[] rowData;


	private DefaultTableModel dataModel;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public Modifier(Object[] rowData, DefaultTableModel dataModel) {
		this.rowData = rowData;
        this.dataModel = dataModel;
        setModal(true);
        setTitle("Modifier");
        setBounds(100, 100, 450, 179);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
//		{
//			textField_1 = new JTextField();
//			textField_1.setBounds(112, 23, 173, 19);
//			textField_1.setColumns(10);
//			contentPanel.add(textField_1);
//		}
//		{
//			textField_2 = new JTextField();
//			textField_2.setBounds(112, 48, 173, 19);
//			textField_2.setColumns(10);
//			contentPanel.add(textField_2);
//		}
//		{
//			textField_3 = new JTextField();
//			textField_3.setBounds(112, 77, 173, 19);
//			contentPanel.add(textField_3);
//			textField_3.setColumns(10);
//		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(112, 20, 173, 19);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(112, 49, 173, 19);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			textField_6 = new JTextField();
			textField_6.setBounds(112, 78, 173, 19);
			contentPanel.add(textField_6);
			textField_6.setColumns(10);
		}
		
//		JLabel lblNewLabel = new JLabel("Id");
//		lblNewLabel.setBounds(31, 26, 45, 13);
//		contentPanel.add(lblNewLabel);
		
//		JLabel lblNewLabel_1 = new JLabel("Prenon");
//		lblNewLabel_1.setBounds(31, 51, 45, 13);
//		contentPanel.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_2 = new JLabel("Nom");
//		lblNewLabel_2.setBounds(31, 80, 45, 13);
//		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Note MiSession");
		lblNewLabel_3.setBounds(10, 23, 86, 13);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Note Projet");
		lblNewLabel_4.setBounds(10, 52, 66, 13);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Note Examen Final");
		lblNewLabel_5.setBounds(10, 81, 86, 13);
		contentPanel.add(lblNewLabel_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String miSessionText = textField_4.getText().trim();
			                String projetText = textField_5.getText().trim();
			                String examenFinalText = textField_6.getText().trim();

			                double miSession = Double.parseDouble(miSessionText);
			                double projet = Double.parseDouble(projetText);
			                double examenFinal = Double.parseDouble(examenFinalText);

			                rowData[3] = miSession;
			                rowData[4] = projet;
			                rowData[5] = examenFinal;

			                dataModel.setValueAt(miSession, dataModel.getRowCount() - 1, 3);
			                dataModel.setValueAt(projet, dataModel.getRowCount() - 1, 4);
			                dataModel.setValueAt(examenFinal, dataModel.getRowCount() - 1, 5);

			                Modifier.current1.setVisible(false);
					
						
						
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
						current1.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	
}
