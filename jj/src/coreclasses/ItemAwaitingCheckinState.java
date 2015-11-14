package coreClasses;

public class ItemAwaitingCheckinState implements I_ItemState{

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemAwaitingPackerState();
		System.out.println("Item is now Available for Packing");
	}

}
