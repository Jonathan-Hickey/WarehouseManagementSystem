package coreclasses.items;

public class ItemPackedHandler extends ItemHandler{

	@Override
	public void handleRequest(Item item, ItemState request) {
		if (request == ItemState.PACKED) {
			item.setCurrentState(request);
			System.out.println("Item is now Packed " + request);
		} else {
			System.out.println("Item Packed Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}	
	}
}
