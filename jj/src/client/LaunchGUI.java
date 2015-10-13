package client;
import java.awt.EventQueue;

public class LaunchGUI {

	public static void main(String[] args) 
	{
		//GUICommunicatorController GCC = new GUICommunicatorController();
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					//LogInFrame frame = new LogInFrame();
					//frame.setVisible(true);
					//frame.addGUICommunicationController(GCC);
					
					//ItemsCollectedFrame frame = new ItemsCollectedFrame();
					//frame.setVisible(true);
					
					GUIManager.init();
					
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

}
