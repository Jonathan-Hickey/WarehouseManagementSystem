package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ItemsStockedFrame extends JFrame {

	private JPanel contentPane;
	private JTextField itemIDToBeStockedField;
	private JList<String> listOfItemsToBeStocked;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ItemsStockedFrame() {
		setTitle("Stock Items Window");
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
				//Calling logOut function in GCC file using GUI Manager to end user session and be directed to the log out frame.
				GUIManager.getGCC().logOut();
				GUIManager.changeFrame(new LogOutFrame());
			}
		});
		logOutButton.setBounds(335, 11, 89, 23);
		contentPane.add(logOutButton);
		
		itemIDToBeStockedField = new JTextField();
		itemIDToBeStockedField.setText("Item ID");
		itemIDToBeStockedField.setBounds(65, 63, 119, 31);
		contentPane.add(itemIDToBeStockedField);
		itemIDToBeStockedField.setColumns(10);
		
		//Transferring data from an arrayList to a JList using a defaultListodel.
		DefaultListModel <String> aListModel = new DefaultListModel <String>();
		ArrayList<String> aArrayList = GUIManager.getGCC().getStockerCurrentBasket();
		listOfItemsToBeStocked = new JList<String>();
		
		for (String temp : aArrayList) 
		 {
		        aListModel.addElement(temp);
		 }
		
		listOfItemsToBeStocked.setModel(aListModel);
		
		listOfItemsToBeStocked.setBounds(65, 113, 241, 95);
		contentPane.add(listOfItemsToBeStocked);
		
		JButton searchForProductButton = new JButton("Search a Product");
		searchForProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new SearchForProductFrame());
			}
		});
		searchForProductButton.setBounds(10, 11, 135, 23);
		contentPane.add(searchForProductButton);
		
		JButton returnHomeButton = new JButton("Generate Item ID");
		returnHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new GenerateItemIDFrame());
			}
		});
		
		returnHomeButton.setBounds(160, 11, 130, 23);
		contentPane.add(returnHomeButton);
		
		JButton itemStockedButton = new JButton("Stock Item");
		itemStockedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Gets itemID from user.
				//Listing updates and removes items that are stocked.
				
				if(itemIDToBeStockedField.getText().matches("[0-9]+"))
				{
					
				String aText = itemIDToBeStockedField.getText();
				int aItemID = Integer.parseInt(aText);
				GUIManager.getGCC().putItemInCubby(aItemID);
				
				DefaultListModel <String> aListModel = new DefaultListModel <String>();
				ArrayList<String> aArrayList = GUIManager.getGCC().getStockerCurrentBasket();
				
				
				 for (String temp : aArrayList) 
				 {
				        aListModel.addElement(temp);
				 }
				
				 listOfItemsToBeStocked.setModel(aListModel);
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "You can't pass String as an Integer.");
				}
				
			}
		});
		itemStockedButton.setBounds(194, 63, 112, 33);
		contentPane.add(itemStockedButton);
		
	}

}
