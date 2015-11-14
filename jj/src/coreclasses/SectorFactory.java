package coreClasses;

public class SectorFactory implements I_SectorFactory {

	public SectorFactory() 
	{
	
	}
	
	public I_Sector makeSector(int type, int id)
	{
		switch(type)
		{
			case 1:
				return new Sector(id, 10000, 10000, 5000 );
			default:
				return null;
		}
	}
}
