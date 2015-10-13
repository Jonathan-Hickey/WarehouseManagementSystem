package coreClasses;

import java.util.ArrayList;
import java.util.Queue;

public interface I_Sector {

	public Queue<Item> getItemQueue();
	public void setItemQueue(Queue<Item> itemQueue);
	public Item getNextItem();
	public void putItemInQueue(Item item);
	
	public ArrayList<Integer> getShelves();
	public void setShelves(ArrayList<Integer> shelves);
	public void addShelve(int shelveID);
	public boolean hasShelf(int shelfID);
	
	public int getID();
	public int getHeight();
	public int getWidth();
	public int getDepth();
	
	public void setHeight(int height);
	public void setWidth(int width);
	public void setDepth(int depth);
	public void setID(int id);
	
	@Override
	public boolean equals(Object obj);
	
}
