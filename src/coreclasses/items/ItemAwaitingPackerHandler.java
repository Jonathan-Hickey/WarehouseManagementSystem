package coreclasses.items;

public class ItemAwaitingPackerHandler extends ItemHandler {


	@Override
	public void handleRequest(Item item, ItemState request) {
		if (request == ItemState.AWAITING_PACKER) {
			item.setCurrentState(request);
			System.out.println("Item is now Awaiting Packer " + request);
		} else {
			System.out.println("Item Packer Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item, request);
			}
		}
		
	}

}
