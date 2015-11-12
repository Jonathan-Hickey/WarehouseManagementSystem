package coreClasses;

import java.util.ArrayList;

public class ShelfSmall extends ShelfDecorator{

	public ShelfSmall(I_Shelf i_Shelf) {
		super(i_Shelf);
	}

	@Override
	public int getHeight() {
		return i_Shelf.getHeight() + 25;
	}
	@Override
	public int getWidth() {
		return i_Shelf.getWidth() + 50;
	}
	@Override
	public int getDepth() {
		return i_Shelf.getDepth() + 20;
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
	public boolean hasCubby(int cubbyID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCubby(I_Cubby cubby) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCubbies(ArrayList<Integer> cubbies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDepth(int depth) {
		// TODO Auto-generated method stub
		
	}
	
}
