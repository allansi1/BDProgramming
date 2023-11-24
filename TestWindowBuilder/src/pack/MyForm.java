package pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyForm frame = new MyForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyForm() {
		setTitle("Hello World Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(56, 38, 279, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Click me");
		
		
		btnNewButton.addActionListener(new ActionListener() {
			boolean flag = true;
			public void actionPerformed(ActionEvent e) {
				//if(textField.getText().equals("")) {
				//if(btnNewButton.getText().equals("Click me")) {
				if (flag) {
					textField.setText("Hello World");
					btnNewButton.setText("Clear");
					flag = false;
				}
				else {
					textField.setText("");
					btnNewButton.setText("Click me");
					flag = true;
				}
				
			}
			
			
			
		});
		
		
		
		
		btnNewButton.setBounds(246, 121, 85, 21);
		contentPane.add(btnNewButton);
	}
}
