package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogOutFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public LogOutFrame() {
		setTitle("Log Out Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea logOutOutputMessage = new JTextArea();
		logOutOutputMessage.setText("You have logged out. To gain access, please log in again.");
		logOutOutputMessage.setEditable(false);
		logOutOutputMessage.setBounds(48, 116, 324, 30);
		contentPane.add(logOutOutputMessage);
		
		JButton backToLogIn = new JButton("Back To Log In");
		backToLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new LogInFrame());
			}
		});
		backToLogIn.setBounds(141, 164, 153, 23);
		contentPane.add(backToLogIn);
	}
}
