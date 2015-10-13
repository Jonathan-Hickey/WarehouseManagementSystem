package coreClasses;

public class ItemAwaitingPackerState implements I_ItemState {

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemPackedState();
		System.out.println("Item is now Packed");
		
	}

}
