package client;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	JButton logInButton;
	

	/**
	 * Create the frame.
	 */
	public LogInFrame() {
		setTitle("Log In Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setText("Username");
		usernameField.setBounds(171, 95, 86, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 126, 86, 20);
		contentPane.add(passwordField);
		
		logInButton = new JButton("Log In");
		logInButton.setBounds(171, 157, 89, 23);
		contentPane.add(logInButton);
		
		logInButton.addActionListener(this);
		
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		GUICommunicatorController aGCC = GUIManager.getGCC();
		
		if (aGCC != null
				)
		{
			//Validating log in credentials
			if (aGCC.loginUser(usernameField.getText(), new String(passwordField.getPassword())))
			{
				switch(aGCC.getUserType())
				{
					case 1 :  //Picker entry point to application
						System.out.println("You are signed in as a picker.");
						GUIManager.changeFrame(new CollectItemsFrame());
						break;
						
					case 2 :  //Packer entry point t application
						System.out.println("You are signed in as a packer.");
						GUIManager.changeFrame(new SearchForOrderFrame());
						break;
						
					case 3 :  //Manager entry point to application
						System.out.println("You are signed in as a manager.");
						GUIManager.changeFrame(new RegisterNewProductFrame());
						break;
						
					case 4 :  //Stocker entry point to application
						System.out.println("You are signed in as a stocker.");
						GUIManager.changeFrame(new GenerateItemIDFrame());
						break;
						
					default: 
						break;
				}
				
			}
			
		}
		
		
	}
}
