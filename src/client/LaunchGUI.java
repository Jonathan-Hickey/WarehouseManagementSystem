package client;
import java.awt.EventQueue;

public class LaunchGUI {

	public static void main(String[] args) 
	{
		System.out.println("GUI Thread: This is currently running on main GUI thread, " +  
	            "the id is: " + Thread.currentThread().getId()); 
		
		
		//GUICommunicatorController GCC = new GUICommunicatorController();
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				System.out.println("GUI Thread: This is currently running on a separate thread, " +  
			            "the id is: " + Thread.currentThread().getId()); 
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
