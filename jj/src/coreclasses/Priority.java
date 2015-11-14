package coreClasses;

import java.util.Date;

public class Priority implements I_Priority
{
	private final int ID;
	
	protected Priority(int id)
	{
		this.ID = id;
	}
	
	public int getPriorityId()
	{
		return ID;
	}
	
	/**Implementation for an instance of I_Priority */
	public int calculatePriority(Item item, Product product)
	{
		//For the final version we may want to make this algorithm more in depth. But the concept remains the same
		int priorityScore = 0;
		Date today = new Date();
		Date expiry = item.getDateOfExpriry();
		Date manufacture = item.getDateOfManufacture();
		if(expiry == null)
		{
			//If we dont have a expiry, for this trivial example simply return a higher priority based on how old the product is.
			priorityScore += (int)( (today.getTime() - manufacture.getTime())  / (1000 * 60 * 60 * 24) );
		}
		else
		{
			//If we have a expiry, for this trivial example simply return a higher priority based on how old the product is.
			priorityScore += (int)( (today.getTime() - expiry.getTime())  / (1000 * 60 * 60 * 24) );
		}
		System.out.println("Product " + product.getID() + ", Item " + item.getID() + ". Priority calculated as :" + priorityScore);
		return priorityScore;
	}
	
}
