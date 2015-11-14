package coreclasses.items;

public class ItemShippedHandler extends ItemHandler{

	@Override
	public void handleRequest(Item item, ItemState request) {
		if (request == ItemState.SHIPPED) {
			item.setCurrentState(request);
			System.out.println("Item is now Shipped " + request);
		} else {
			System.out.println("Item Shipped Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item,request);
			}
		}
		
	}


}
