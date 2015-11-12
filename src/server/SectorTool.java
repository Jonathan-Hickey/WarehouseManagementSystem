package server;
import java.util.ArrayList;

import coreclasses.items.Item;
import coreclasses.items.ItemState;
import coreclasses.sector.I_Sector;
import coreclasses.users.Picker;
public class SectorTool extends Thread
{
	private I_Sector sector;
	public boolean running = false;  
	
	public SectorTool()
	{
		sector = null;
	}
	
	public SectorTool(I_Sector sector)
	{
		this.sector = sector;
	}
	
	public void addItemToQueue(Item item)
	{
		//The reason for encapsulating this is I think 
		//More functionality may be needed here when the 
		//design becomes more fleshed out
		sector.putItemInQueue(item);
	}
	
	public boolean isItemOnQueue()
	{
		//Same as above function
		return !sector.getItemQueue().isEmpty();
	}
	
	public int getSectorId()
	{
		return sector.getID();
	}
	

	 @Override  
	  public void run()   
	  {  
	    this.running = true;  
	    System.out.println("Sector Tool: This is currently running on a separate thread, " +  
	        "the id is: " + Thread.currentThread().getId());  
	      
	    try   
	    {  
	      // this will pause this spawned thread for 5 seconds  
	      //  (5000 is the number of milliseconds to pause)  
	      // Also, the Thread.sleep() method throws an InterruptedException  
	      //  so we must "handle" this possible exception, that's why I've  
	      //  wrapped the sleep() method with a try/catch block  
	      Thread.sleep(2000);  
	    }   
	    catch (InterruptedException e)   
	    {  
	      // As user Bernd points out in the comments section below, you should  
	      //  never swallow an InterruptedException.  
	      Thread.currentThread().interrupt();  
	    }  
	    this.running = false;  
	  } 

	public ArrayList<Item> assignItemsForPicker(Picker picker)
	{
		//This function requires DB access to set certain variables to maintain state.
		//Also its required that the server message objects always carry the user ID
		//of the person sending request.
		ArrayList<Item> assignedItems = new ArrayList<Item>();
		for(int i = 0; i < 5; i++)
		{
			Item tempItem = sector.getNextItem();
			if(tempItem == null)
				break;
			//TODO: Duplication of logic .... needs to be revised
			tempItem.setAssignedUserID(picker.getID());
			tempItem.handleRequest(ItemState.AWAITING_CHECK_IN);
			picker.addItemToBasket(tempItem);
			assignedItems.add(tempItem);
		}
		return assignedItems;
	}
	
}
