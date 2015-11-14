package coreClasses;

public class ItemPackedState implements I_ItemState{

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemShippedState();
		System.out.println("Item is now Shipped");
		
	}

}
