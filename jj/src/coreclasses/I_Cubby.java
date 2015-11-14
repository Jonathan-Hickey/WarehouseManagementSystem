package coreClasses;
import java.util.ArrayList;


public interface I_Cubby 
{
	public int getID();
	public ArrayList<Integer> getItems();
	public void addItem(int itemID);
	public boolean hasItem(int ItemID);
	
	public int getHeight();
	public int getWidth();
	public int getDepth();

	
	@Override
	public boolean equals(Object obj);
}
