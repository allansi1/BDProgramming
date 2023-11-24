package methode2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame1 extends JFrame {
	
	static Frame1 current;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		current = new Frame1();
		current.setVisible(true);
		Frame2.current = new Frame2();
		Frame2.current.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public Frame1() {
		setTitle("Frame1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFrame2 = new JButton("Go to Frame2");
		btnFrame2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setVisible(false);
				Frame2.current.setVisible(true);
			}
		});
		btnFrame2.setBounds(72, 124, 140, 23);
		contentPane.add(btnFrame2);
	}
}
