package coreClasses;

public class ItemContext implements I_ItemState {
	
	// item state interface
	private I_ItemState state;
	
	// method that sets the state
	public void setState(I_ItemState state)
	{
		this.state = state;
	}
	
	// method that gets the current state
	public I_ItemState getState()
	{
		return this.state;
	}

	@Override
	public void nextState(I_ItemState i_ItemState) {
		this.state.nextState(i_ItemState);
	}

}
