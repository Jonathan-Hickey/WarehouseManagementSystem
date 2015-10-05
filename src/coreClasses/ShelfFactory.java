package coreClasses;

public class ShelfFactory implements I_ShelfFactory{

	public ShelfFactory() 
	{
		
	}
	
	public I_Shelf makeShelve(int type, int id)
	{
		
		
		switch(type)
		{
		case 1:
			return new Shelf(id, 400, 1000, 100);
		case 2:
			return new Shelf(id,400, 1000, 100);
		case 3:
			return new Shelf(id,400, 1000, 100);
		case 4:
			return new Shelf(id,400, 1000, 100);
		default:
			return null;
		}

	}

}
