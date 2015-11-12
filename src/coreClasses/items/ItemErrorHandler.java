package coreclasses.items;

public class ItemErrorHandler extends ItemHandler {

	@Override
	public void handleRequest(Item item, ItemState request) {
		
		System.out.println("Error");
		
	}

}
