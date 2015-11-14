package coreclasses.items;

public abstract class ItemHandler {
	
	ItemHandler successor;
	
	public void setSuccessor(ItemHandler successor) {
		this.successor = successor;
	}

	// inject the enumeration class into the abstract method
	public abstract void handleRequest(Item item, ItemState request);

}
