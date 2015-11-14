package coreClasses;

public class ItemPendingStockingState implements I_ItemState {

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemAvailableState();
		System.out.println("Item is now Available for Stocking");
		
	}

}
