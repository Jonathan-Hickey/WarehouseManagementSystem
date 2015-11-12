package coreclasses.items;

public class ItemAwaitingStockerHandler extends ItemHandler{

	@Override
	public void handleRequest(Item item, ItemState request) {
		
		if (request == ItemState.AWAITING_STOCKER) {
			item.setCurrentState(request);
			System.out.println("Item is now Pending Stocking " + request);
		} else {
			System.out.println("Item Awaiting Stocker Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}
	}
}
