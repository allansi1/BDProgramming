package avecEncrypt2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	static Login current;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login.current = new Login();
					Login.current.setVisible(true);	
					CreateLogin.current = new CreateLogin();
					CreateLogin.current.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 247);
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
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 try {
				String url = "jdbc:mysql://localhost:3306/LOGINSDB";
				 Connection con = DriverManager.getConnection(
						          url,"root", "root");
				 			 
				 PreparedStatement stmt =con.prepareStatement("SELECT AES_ENCRYPT(?,?)");
				 stmt.setString(1, new String(passwordField.getPassword()));
				 stmt.setString(2, new String(passwordField.getPassword())+textField.getText());
				 ResultSet rs = stmt.executeQuery();
				 rs.next(); 
		     	 String mdpE=rs.getString(1);
				 stmt =con.prepareStatement("SELECT PW FROM LOGINS_ENCRYPT WHERE user = ?");
				 stmt.setString(1, textField.getText());
				 rs = stmt.executeQuery(); 
				 if( rs.next()  &&  rs.getString(1).equals(mdpE) ){
					 JOptionPane.showMessageDialog(null, "OK");
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "user/pw invalid");
				 }
				 rs.close();
				 stmt.close();
				 con.close();
			 }
			 catch( Exception e1 ) {
				 e1.printStackTrace();
			}
				
			}
		});
		btnNewButton.setBounds(206, 146, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Create an account?");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Login.current.setVisible(false);	
				CreateLogin.current.setVisible(true);
			}
		});
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(67, 150, 129, 14);
		contentPane.add(lblNewLabel_2);
	}
}
