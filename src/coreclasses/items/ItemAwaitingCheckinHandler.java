package coreclasses.items;

public class ItemAwaitingCheckinHandler extends ItemHandler{

	@Override
	public void handleRequest(Item item, ItemState request) {
		if (request == ItemState.AWAITING_CHECK_IN) {
			item.setCurrentState(request);
			System.out.println("Item is now Awaiting Checkin " + request);
		} else {
			System.out.println("Item Checkin Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}
	}


}
