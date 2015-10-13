package server;

import coreClasses.Order;

public interface I_ServerTool 
{
	//Pre condtion - Order must have shipping information
	//			   - Order must have one or more valid products
	//Post condition - Order has been processed successfully (Represented by the boolean)
	public boolean processNewOrder(Order newOrder);
}