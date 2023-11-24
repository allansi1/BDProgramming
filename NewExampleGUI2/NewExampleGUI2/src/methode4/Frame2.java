package methode4;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame2 extends JFrame {

	static Frame2 current;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Frame2 frame = new Frame2();
//		frame.setVisible(true);
//	}

	/**
	 * Create the frame.
	 */
	public Frame2() {
		current=this;
		setTitle("Frame2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFrame1 = new JButton("Return to Frame1");
		btnFrame1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame1.current.setVisible(true);
				Frame2.current=null;    // reset current to null  
				dispose();              // this.dispose();				
			}
		});
		btnFrame1.setBounds(47, 138, 170, 23);
		contentPane.add(btnFrame1);
	}

}
