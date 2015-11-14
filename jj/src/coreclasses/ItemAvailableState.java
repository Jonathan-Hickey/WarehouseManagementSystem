package coreClasses;

public class ItemAvailableState implements I_ItemState{

	@Override
	public void nextState(I_ItemState i_ItemState) {
		i_ItemState = new ItemAwaitingPickerState();
		System.out.println("Item is now ready for picker");
		
	}

}
