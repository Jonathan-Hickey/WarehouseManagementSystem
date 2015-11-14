package coreClasses;

public class PriorityFactory implements I_PriorityFactory
{

	public PriorityFactory()
	{
		
	}
	
	public I_Priority makePriority(int priorityID)
	{
		switch(priorityID)
		{
			case 0: return new Priority(priorityID);
			default: return null;
		}
	}
}
