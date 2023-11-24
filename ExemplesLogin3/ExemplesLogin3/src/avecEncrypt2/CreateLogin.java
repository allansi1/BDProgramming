package avecEncrypt2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateLogin extends JFrame {

	static CreateLogin current;
	 
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateLogin.current = new CreateLogin();
//					CreateLogin.current.setVisible(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CreateLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Login.current.setVisible(true);	
				CreateLogin.current.setVisible(false);			
			}
		});
		setResizable(false);
		setTitle("Create Login");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // important
		setBounds(100, 100, 377, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User: ");
		lblNewLabel.setBounds(49, 50, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setBounds(49, 95, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(128, 47, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 89, 96, 20);
		contentPane.add(passwordField);
		
		JLabel lblConfirm = new JLabel("Confirm: ");
		lblConfirm.setBounds(49, 146, 69, 14);
		contentPane.add(lblConfirm);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(128, 140, 96, 20);
		contentPane.add(passwordField_1);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "jdbc:mysql://localhost:3306/LOGINSDB";

				boolean newUser = false;
				try {
					Connection con = DriverManager.getConnection(url, "root", "root");

					PreparedStatement stmt = con.prepareStatement("SELECT * FROM LOGINS_ENCRYPT WHERE user = ?");
					stmt.setString(1, textField.getText());
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						newUser = false;
						JOptionPane.showMessageDialog(null, "User name unavailable");
					} else {
						newUser = true;
					}
					rs.close();
					stmt.close();
					con.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (newUser) {
					boolean match = Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword());

					if (match) {
						try {
							Connection con = DriverManager.getConnection(url, "root", "root");
							PreparedStatement stmt = con
									.prepareStatement("INSERT INTO LOGINS_ENCRYPT (USER, PW) VALUES (?, AES_ENCRYPT(?,?))");
							stmt.setString(1, textField.getText());
							stmt.setString(2, new String(passwordField.getPassword()));
							stmt.setString(3, new String(passwordField.getPassword())+textField.getText());
							stmt.executeUpdate();							
							stmt.close();
							con.close();
							JOptionPane.showMessageDialog(null, "Login créé");
							textField.setText("");
							passwordField.setText("");
							passwordField_1.setText("");
							Login.current.setVisible(true);	
							CreateLogin.current.setVisible(false);

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Erreur : mot de passe n'est pas le même");
					}
				}
				
			}
		});
		btnNewButton.setBounds(206, 199, 89, 23);
		contentPane.add(btnNewButton);	
			
		
	}

}
