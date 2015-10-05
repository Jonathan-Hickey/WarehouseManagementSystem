package coreClasses;

import java.util.ArrayList;
//Comment
public class Stocker extends User {

	private ArrayList<Item> stackBasket;
	
	
	public Stocker(int userID, String firstName, String surname, String email, String phone, String password)
	{
		super(userID, firstName, surname, email, phone, password);
		stackBasket = new ArrayList<Item>();
	}
	
	public boolean addItem(Item item)
	{
		return stackBasket.add(item);
	}
	
	public boolean removeItem(Item item)
	{
		return stackBasket.remove(item);
	}
	
	public ArrayList<Item> getStockerBasket()
	{
		return stackBasket;
	}
	
	
	
}
