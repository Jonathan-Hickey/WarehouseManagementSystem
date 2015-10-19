package coreClasses;

public class ItemAwaitingPickerHandler extends ItemHandler{


	@Override
	public void handleRequest(Item item, ItemState request) {
		if (request == ItemState.AWAITING_PICKER) {
			item.setCurrentState(request);
			System.out.println("Item is now Awaiting Picker " + request);
		} else {
			System.out.println("Item Picker Handler doesn't handle " + request);
			if (successor != null) {
				successor.handleRequest(item,request);
			}
		}
		
	}

}
