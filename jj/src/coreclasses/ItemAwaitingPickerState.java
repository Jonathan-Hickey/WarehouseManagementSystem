package coreClasses;

public class ItemAwaitingPickerState implements I_ItemState{

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemAwaitingCheckinState();
		System.out.println("Item is now awaiting check in");
		
	}

}
