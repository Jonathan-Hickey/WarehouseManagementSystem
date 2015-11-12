package coreclasses.priority;

import coreclasses.items.Item;
import coreclasses.items.Product;

public interface I_Priority 
{
	public int getPriorityId();
	public int calculatePriority(Item item, Product product);
}
