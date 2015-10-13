package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterNewProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productIDField;
	private JTextField productNameField;
	private JTextField productHeightField;
	private JTextField productWidthField;
	private JTextField productDepthField;
	private JTextField productWeightField;
	private JTextField productDescriptionField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public RegisterNewProductFrame() {
		setTitle("Manager Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		productIDField = new JTextField();
		productIDField.setText("Product ID");
		productIDField.setBounds(133, 39, 86, 20);
		contentPane.add(productIDField);
		productIDField.setColumns(10);
		
		productNameField = new JTextField();
		productNameField.setText("Product Name");
		productNameField.setBounds(133, 66, 86, 20);
		contentPane.add(productNameField);
		productNameField.setColumns(10);
		
		productHeightField = new JTextField();
		productHeightField.setText("Product Height");
		productHeightField.setBounds(133, 97, 86, 20);
		contentPane.add(productHeightField);
		productHeightField.setColumns(10);
		
		productWidthField = new JTextField();
		productWidthField.setText("Product Width");
		productWidthField.setBounds(133, 128, 86, 20);
		contentPane.add(productWidthField);
		productWidthField.setColumns(10);
		
		productDepthField = new JTextField();
		productDepthField.setText("Product Depth");
		productDepthField.setBounds(133, 159, 86, 20);
		contentPane.add(productDepthField);
		productDepthField.setColumns(10);
		
		productWeightField = new JTextField();
		productWeightField.setText("Product Weight");
		productWeightField.setBounds(133, 190, 86, 20);
		contentPane.add(productWeightField);
		productWeightField.setColumns(10);
		
		productDescriptionField = new JTextField();
		productDescriptionField.setText("Product Description");
		productDescriptionField.setBounds(133, 220, 127, 29);
		contentPane.add(productDescriptionField);
		productDescriptionField.setColumns(10);
		
		JButton generateReportFrameButton = new JButton("Generate Report");
		generateReportFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.changeFrame(new GenerateReportPartOneFrame());
			}
		});
		generateReportFrameButton.setBounds(10, 11, 113, 20);
		contentPane.add(generateReportFrameButton);
		
		JButton registerNewProductButton = new JButton("Register New Product");
		registerNewProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Updates Database with a new product.
			}
		});
		registerNewProductButton.setBounds(287, 221, 137, 26);
		contentPane.add(registerNewProductButton);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUIManager.getGCC().logOut();
				GUIManager.changeFrame(new LogOutFrame());
			}
		});
		logOutButton.setBounds(335, 10, 89, 23);
		contentPane.add(logOutButton);
	}

}
