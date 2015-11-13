package client;
import java.awt.EventQueue;

import coreClasses.InterceptorContext;
import coreClasses.InterceptorDispatcher;
import outOfBandServices.Interceptor;

public class LaunchGUI {

	public static void main(String[] args) 
	{
		System.out.println("GUI Thread: This is currently running on main GUI thread, " +  
	            "the id is: " + Thread.currentThread().getId()); 
		
		InterceptorContext context;
		InterceptorDispatcher dispatch;
		

		
		
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
		

		Interceptor interceptor = new Interceptor();
		interceptor.setEvent("1");
		context = new InterceptorContext();
		dispatch = new InterceptorDispatcher();
		dispatch.register(interceptor);
		
		
		for (int i = 0; i < 100; i++)
		{
			String event = null;
			String message = Integer.toString(i);

			int rand = (int) (Math.random() * 100);

			if (rand <= 50)
				event = "1";

			if (rand >= 51)
				event = "2";

			if (event != null)
			{
				System.out.println("Ross is here!!!");
				context.setEvent(event);
				context.setMessage(message);
				
				dispatch.notify(context);
			}
			else
				System.out.println(i + "\tnormal operation");
		}
	}

	// called by context object
	protected static void terminate(int operation)
	{
		System.out.println(operation + "\tinvokes system shut down");
		System.exit(0);
	}
	

}
