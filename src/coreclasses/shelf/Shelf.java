package coreclasses.shelf;

import java.util.ArrayList;

import coreclasses.cubby.I_Cubby;

public class Shelf implements I_Shelf 
{
	private ArrayList<Integer> cubbies;
	private int ID;
	private int height;
	private int width;
	private int depth;
	
	//added constructor
	public Shelf(int id)
	{
		this.ID = id;
		this.height = 200;
		this.width = 1000;
		this.depth = 100;
		
		cubbies = new ArrayList<Integer>();
	}
	
	public Shelf(int id, int height, int width, int depth )
	{
		this.ID = id;
		
		this.height = height;
		this.width = width;
		this.depth = depth;
		
		cubbies = new ArrayList<Integer>();
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public int getDepth() {
		return depth;
	}
	
	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void setID(int id) {
		this.ID = id;
	}

	@Override
	public void setCubbies(ArrayList<Integer> cubbies) {
		this.cubbies = cubbies;
	}

	@Override
	public boolean hasCubby(int cubbyID) 
	{
		return cubbies.contains(cubbyID);
		
	}

	@Override
	public ArrayList<Integer> getCubbies()
	{
		return cubbies;
	}

	@Override
	public boolean addCubby(I_Cubby cubby) {
		
		return	cubbies.add(cubby.getID());
	}

	@Override
	public boolean removeCubby(I_Cubby cubby) {
		
		for(int index = 0 ; index < cubbies.size(); index++)
			if(cubbies.get(index).equals(cubby)){
				cubbies.remove(index);
				return true;
			};
		return false;
	}

	@Override
	public String toString()
	{
		return "ShelfId: " + ID + " Items: " + cubbies;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (((obj instanceof Shelf)) && ((Shelf) obj) != null && this.ID == ((Shelf) obj).getID());     
	}
	
}
