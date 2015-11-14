package coreclasses.items;

public class ItemAvailableHandler extends ItemHandler{

	@Override
	public void handleRequest(Item item, ItemState request) {

		if (request == ItemState.AVAILABLE) {
			item.setCurrentState(request);
			System.out.println("Item is now Available " + request);
			
		} else {
			System.out.println("Item Available Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}
	}

	
}
