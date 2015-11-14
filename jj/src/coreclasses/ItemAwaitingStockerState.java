package coreClasses;

public class ItemAwaitingStockerState implements I_ItemState {

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemPendingStockingState();
		System.out.println("Item is now Pending Stocking");
	}

}
