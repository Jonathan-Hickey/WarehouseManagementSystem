package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateReportPartOneFrame extends JFrame {

	private JPanel contentPane;
	private JTextField startDateField;
	private JTextField endDateField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public GenerateReportPartOneFrame() {
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
		
		JButton registerNewProductFrameButton = new JButton("Register New Product");
		registerNewProductFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new RegisterNewProductFrame());
			}
		});
		registerNewProductFrameButton.setBounds(75, 11, 161, 23);
		contentPane.add(registerNewProductFrameButton);
		
		startDateField = new JTextField();
		startDateField.setText("DD/MM/YY");
		startDateField.setBounds(27, 76, 86, 20);
		contentPane.add(startDateField);
		startDateField.setColumns(10);
		
		endDateField = new JTextField();
		endDateField.setText("DD/MM/YY");
		endDateField.setBounds(123, 76, 86, 20);
		contentPane.add(endDateField);
		endDateField.setColumns(10);
		
		JList productIDsList = new JList();
		productIDsList.setBounds(27, 107, 132, 52);
		contentPane.add(productIDsList);
		
		JRadioButton pieChartRadioButton = new JRadioButton("Pie Chart");
		pieChartRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Gets data from database
			}
		});
		pieChartRadioButton.setBounds(27, 175, 109, 23);
		contentPane.add(pieChartRadioButton);
		
		JRadioButton barChartRadioButton = new JRadioButton("Bar Chart");
		barChartRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Gets data from database.
			}
		});
		barChartRadioButton.setBounds(27, 197, 109, 23);
		contentPane.add(barChartRadioButton);
		
		JRadioButton lineChartRadioButton = new JRadioButton("Line Chart");
		lineChartRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Gets data from database
			}
		});
		lineChartRadioButton.setBounds(27, 218, 109, 23);
		contentPane.add(lineChartRadioButton);
		
		JButton generateReportButton = new JButton("Generate Report");
		generateReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new GenerateReportPartTwoFrame());
			}
		});
		generateReportButton.setBounds(281, 186, 123, 45);
		contentPane.add(generateReportButton);
		
		JLabel startDateLabel = new JLabel("Start Date");
		startDateLabel.setBounds(42, 57, 59, 14);
		contentPane.add(startDateLabel);
		
		JLabel endDateLabel = new JLabel("End Date");
		endDateLabel.setBounds(142, 57, 46, 14);
		contentPane.add(endDateLabel);
	}
}
