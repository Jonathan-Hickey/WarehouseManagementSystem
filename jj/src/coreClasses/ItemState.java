package coreClasses;

/*
 * Options are:
 *  AWAITING_STOCKER
 *	PENDING_STOCKING
 * 	AVAILABLE
 *	AWAITING_PICKER
 *	AWAITING_CHECK_IN
 *	AWAITING_PACKER
 *	PACKED
 *	SHIPPED
 * 
 */

//State an item can be in as it travels through the system. Ordered in sequential fashion from first state to last
public enum ItemState 
{
	//Item has been created and is awaiting assignment to a stocker for stocking
	AWAITING_STOCKER,
	//Item has been assigned to a stocker, waiting for confirmation of stocking from stocker
	PENDING_STOCKING,
	//Item has been stocked and is available to fulfil customer orders.
	AVAILABLE,
	//Item has been chosen to be used to fulfil customer order, waiting to be assigned to a picker in its sector.
	AWAITING_PICKER,
	//Item has been assigned to a picker, waiting for picker to confirm Item has been picked
	AWAITING_CHECK_IN,
	//Item has been picked and is waiting to be scanned by a Packer
	AWAITING_PACKER,
	//Item has been packed by a packer
	PACKED,
	//Item has been shipped to the customer.
	SHIPPED
}
