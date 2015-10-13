package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateReportPartTwoFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public GenerateReportPartTwoFrame() {
		setTitle("Manager Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.getGCC().logOut();
				GUIManager.changeFrame(new LogOutFrame());
			}
		});
		logOutButton.setBounds(335, 11, 89, 23);
		contentPane.add(logOutButton);
		
		JButton generateReportFrameButton = new JButton("Generate Report");
		generateReportFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Exports report to PDF.
			}
		});
		generateReportFrameButton.setBounds(10, 11, 125, 23);
		contentPane.add(generateReportFrameButton);
		
		JButton registerNewProductFrameButton = new JButton("Register New Product");
		registerNewProductFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new RegisterNewProductFrame());
			}
		});
		registerNewProductFrameButton.setBounds(161, 11, 144, 23);
		contentPane.add(registerNewProductFrameButton);
	}
}
