package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchForOrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField itemIDToSearch;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SearchForOrderFrame() {
		setTitle("Packer Main Window");
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
		
		itemIDToSearch = new JTextField();
		itemIDToSearch.setText("Item ID");
		itemIDToSearch.setBounds(40, 63, 161, 51);
		contentPane.add(itemIDToSearch);
		itemIDToSearch.setColumns(10);
		
		JButton searchForOrderButton = new JButton("Search For Order");
		searchForOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Searches information on database
			}
		});
		searchForOrderButton.setBounds(211, 62, 133, 23);
		contentPane.add(searchForOrderButton);
		
		JButton printAddressButton = new JButton("Print Address");
		printAddressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Searches information on database.
			}
		});
		printAddressButton.setBounds(211, 153, 133, 23);
		contentPane.add(printAddressButton);
		
		JTextArea orderIDDetails = new JTextArea();
		orderIDDetails.setBounds(40, 152, 161, 72);
		contentPane.add(orderIDDetails);
		
		JButton dispatchOrderButton = new JButton("Dispatch Order");
		dispatchOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new DispatchOrderFrame());
			}
		});
		dispatchOrderButton.setBounds(211, 91, 133, 23);
		contentPane.add(dispatchOrderButton);
	}

}
