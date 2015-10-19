package coreClasses;


public class CubbyFactory implements I_CubbyFactory
{
	public CubbyFactory()
	{
		
	}
	
	/*
	 * Creates a cubby object depending on the type
	 * 0 = small cubby
	 * 1 = medium cubby
	 * 2 = large cubby
	 * Never Returns Null. Can throw Exception 
	 */
	public I_Cubby makeCubby(int type, int id) 
	{
		
		switch (type)
		{
		case 1:
			return new Cubby(id, 100, 100, 100);
		case 2:
			return new Cubby(id, 200, 200 ,100);
		case 3:
			return new Cubby(id, 400, 200, 100);
		default:
			return null;
		}
	}
	
}
