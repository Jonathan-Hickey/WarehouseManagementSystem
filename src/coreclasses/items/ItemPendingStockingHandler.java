package coreclasses.items;

public class ItemPendingStockingHandler extends ItemHandler {

	@Override
	public void handleRequest(Item item,ItemState request) {
		if (request == ItemState.PENDING_STOCKING) {
			item.setCurrentState(request);
			System.out.println("Item is now Pending Stocking " + request);
		} else {
			System.out.println("Item Pending Stocking Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}
		
	}

}
