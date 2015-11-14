package coreclasses.shelf;

import java.util.ArrayList;

import coreclasses.cubby.I_Cubby;

public abstract class ShelfDecorator implements I_Shelf {
	
	protected I_Shelf i_Shelf;
	
	public ShelfDecorator(I_Shelf i_Shelf)
	{
		this.i_Shelf = i_Shelf;
	}

	@Override
	public int getHeight() {
		return i_Shelf.getHeight();
	}
	@Override
	public int getWidth() {
		return i_Shelf.getWidth();
	}
	@Override
	public int getDepth() {
		return i_Shelf.getDepth();
	}

	@Override
	public int getID() {
		return i_Shelf.getID();
	}


	@Override
	public ArrayList<Integer> getCubbies()
	{
		return i_Shelf.getCubbies();
	}

	@Override
	public String toString()
	{
		return "ShelfId: " + i_Shelf.getID() + " Items: " + i_Shelf.getCubbies();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (((obj instanceof Shelf)) && ((Shelf) obj) != null && this.i_Shelf.getID() == ((Shelf) obj).getID());     
	}

	@Override
	public boolean addCubby(I_Cubby cubby) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
