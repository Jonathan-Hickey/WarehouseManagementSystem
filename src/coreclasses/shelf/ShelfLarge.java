package coreclasses.shelf;

import java.util.ArrayList;

import coreclasses.cubby.I_Cubby;

public class ShelfLarge extends ShelfDecorator {

	public ShelfLarge(I_Shelf i_Shelf) {
		super(i_Shelf);
	}

	@Override
	public int getHeight() {
		return i_Shelf.getHeight() + 100;
	}
	@Override
	public int getWidth() {
		return i_Shelf.getWidth() + 200;
	}
	@Override
	public int getDepth() {
		return i_Shelf.getDepth() + 75;
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
