package coreClasses;

import java.util.ArrayList;

public interface I_Shelf {

	public boolean hasCubby(int cubbyID);
	public boolean addCubby(I_Cubby cubby);
	public boolean removeCubby(I_Cubby cubby);
	public ArrayList<Integer> getCubbies();
	public int getID();
	public void setID(int id);
	public void setCubbies(ArrayList<Integer> cubbies);
	
	public int getHeight();
	public int getWidth();
	public int getDepth();
	public void setHeight(int height);
	public void setWidth(int width);
	public void setDepth(int depth);

	@Override
	public boolean equals(Object obj);
}
