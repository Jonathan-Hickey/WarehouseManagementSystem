package client;

import java.awt.BorderLayout;

import javax.swing.JComboBox;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class GenerateItemIDFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productIDField;
	private JTextField manufactureDateField;
	private JTextField expiryDateField;
	private JComboBox<Integer> sectorList = new JComboBox<Integer>();

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public GenerateItemIDFrame() {
		setTitle("Stocker Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Transferring data from an arrayList to a JList using a defaultListodel.
		sectorList.setEditable(false);
		ArrayList<Integer> aSectorList = GUIManager.getGCC().getSectorsIDs();
		int i;
		
        for (i = 0; i < aSectorList.size(); i++) 
        {
            sectorList.addItem(aSectorList.get(i));
        }
		
		sectorList.setBounds(170, 11, 100, 23);
		contentPane.add(sectorList);
		
		sectorList.addActionListener(new ActionListener()
		{
	        public void actionPerformed(ActionEvent e) 
	        {
	        	//Setting sector IDs into a local arraylist and added to a dropdown list.
	        	GUIManager.getGCC().setCurrentSectorLocation((int) sectorList.getSelectedItem());
	        }
		});
		
		
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
		
		productIDField = new JTextField();
		productIDField.setText("Product ID");
		productIDField.setBounds(134, 85, 129, 20);
		contentPane.add(productIDField);
		productIDField.setColumns(10);
		
		manufactureDateField = new JTextField();
		manufactureDateField.setText("Manufacture Date");
		manufactureDateField.setBounds(134, 116, 129, 20);
		contentPane.add(manufactureDateField);
		manufactureDateField.setColumns(10);
		
		expiryDateField = new JTextField();
		expiryDateField.setText("Expiry Date");
		expiryDateField.setBounds(134, 147, 129, 20);
		contentPane.add(expiryDateField);
		expiryDateField.setColumns(10);
		
		JButton generateItemIDButton = new JButton("Generate Item ID");
		generateItemIDButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Registering a new item to system and generate ID label for that item
				String aText = productIDField.getText();
				int aProductID = Integer.parseInt(aText);
				String aManDateText = manufactureDateField.getText();
				String aExpDateText = expiryDateField.getText();
				
				if ((aManDateText.matches("\\d{4}-\\d{2}-\\d{2}") && aExpDateText.matches("\\d{4}-\\d{2}-\\d{2}"))) 
				{
					int aItem = GUIManager.getGCC().registorItem(aProductID, aManDateText, aExpDateText);
					System.out.print(aItem);
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "Date format invalid. Make sure you're using the format YYYY-MM-DD for both Manufacture Date and Expiry Date.");
				}
				
			}
		});
		
		generateItemIDButton.setBounds(134, 187, 129, 23);
		contentPane.add(generateItemIDButton);
		
		JButton stockItemsFrameButton = new JButton("Stock Items");
		stockItemsFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new StockItemsFrame());
			}
		});
		stockItemsFrameButton.setBounds(10, 11, 130, 23);
		contentPane.add(stockItemsFrameButton);
		
		JButton searchForProductButton = new JButton("Search a Product");
		searchForProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new SearchForProductFrame());
			}
		});
		searchForProductButton.setBounds(10, 40, 140, 23);
		contentPane.add(searchForProductButton);
	}

}
