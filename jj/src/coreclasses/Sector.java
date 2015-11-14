package coreClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sector implements I_Sector
{
	private Queue<Item> itemQueue;
	private ArrayList<Integer> shelves;
	private int height;
	private int width;
	private int depth;
	private int ID;
	
	
	protected Sector(int ID, int height, int width, int depth)
	{
		this.ID = ID;
		this.height = height;
		this.width = width;
		this.depth = depth;
		shelves = new ArrayList<Integer>();
		itemQueue = new LinkedList<Item>();
	}
	
	@Override
	public ArrayList<Integer> getShelves()
	{
		return shelves;
	}
	
	@Override
	public void setShelves(ArrayList<Integer> shelves)
	{
		this.shelves = shelves;
	}
	
	@Override
	public void addShelve(int shelveID)
	{
		shelves.add(shelveID);
	}
	
	@Override
	public Queue<Item> getItemQueue()
	{
		return itemQueue;
	}
	
	@Override
	public Item getNextItem()
	{
		return itemQueue.poll();
	}
	
	@Override
	public void putItemInQueue(Item item)
	{
		itemQueue.add(item);
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) 
	{
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width)
	{
		this.width = width;
	}

	@Override
	public int getDepth() 
	{
		return depth;
	}

	@Override
	public void setDepth(int depth)
	{
		this.depth = depth;
	}

	@Override
	public int getID()
	{
		return ID;
	}

	@Override
	public void setID(int ID)
	{
		this.ID = ID;
	}

	@Override
	public void setItemQueue(Queue<Item> itemQueue) 
	{
		this.itemQueue = itemQueue;
	}

	@Override
	public boolean hasShelf(int shelfID) {
		
		for(Integer shelf : shelves)
			if(shelf == shelfID) return true;

		return false;
	}

	@Override
	public String toString()
	{
		return "SectorId: " + ID + " Shevles: " + shelves;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (((obj instanceof Sector)) && ((Sector) obj) != null && this.ID == ((Sector) obj).getID());     
	}
	
	
}
