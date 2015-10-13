package coreClasses;

public class ItemShippedState implements I_ItemState{

	@Override
	public void nextState(I_ItemState i_ItemState) {
		System.out.println("Item is now Shipped");
		
	}

}
