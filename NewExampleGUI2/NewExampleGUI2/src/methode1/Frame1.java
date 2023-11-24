package methode1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame1 extends JFrame {
	
	// La fenêtre principale garde un inventaire de fenêtre de l'application
	// Cela est fait en utilisant des variables statiques
	// on controle la visibilité de chaque fenêtre.
	static Frame1 frame1;
	static Frame2 frame2;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		frame1 = new Frame1();
		frame1.setVisible(true);
		frame2 = new Frame2();
		frame2.setVisible(false);
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
				frame1.setVisible(false);
				frame2.setVisible(true);
			}
		});
		btnFrame2.setBounds(72, 124, 140, 23);
		contentPane.add(btnFrame2);
	}
}
