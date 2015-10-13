package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchForProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productToSearch;
	private JTextField productSearchBox;
	private JList<String>  productInfoListing;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public SearchForProductFrame() {
		setTitle("Search For a Product Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
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
		
		
		JButton returnHomeButton = new JButton("Generate Item ID");
		returnHomeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new GenerateItemIDFrame());
			}
		});
		
		returnHomeButton.setBounds(150, 11, 130, 23);
		contentPane.add(returnHomeButton);
		
		JButton stockItemsFrameButton = new JButton("Stock Items");
		stockItemsFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new StockItemsFrame());
			}
		});
		
		stockItemsFrameButton.setBounds(10, 11, 130, 23);
		contentPane.add(stockItemsFrameButton);
		
		productSearchBox = new JTextField();
		productSearchBox.setText("Product Name");
		
		productSearchBox.setBounds(51, 44, 154, 39);
		contentPane.add(productSearchBox);
		productSearchBox.setColumns(10);
		
		productInfoListing = new JList<String>();
		productInfoListing.setBounds(51, 94, 273, 128);
		
		contentPane.add(productInfoListing);
		
		JButton productSearchButton = new JButton("Search");
		productSearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//Gets a product name from user
				//Transferring data from an arrayList to a JList using a defaultListodel.
				//Displays product info on JList
				String aProductName = productSearchBox.getText();
				ArrayList<String> aArrayList = GUIManager.getGCC().searchForProduct(aProductName);
				DefaultListModel <String> aListModel = new DefaultListModel <String>();
				
				 for (String temp : aArrayList) 
				 {
				        aListModel.addElement(temp);
				 }
				 
				 productInfoListing.setModel(aListModel);
			}
		});
		
		productSearchButton.setBounds(215, 43, 109, 40);
		contentPane.add(productSearchButton);
	}

}
