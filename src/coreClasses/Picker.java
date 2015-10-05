package coreClasses;

import java.util.ArrayList;



public class Picker extends User{

	private ArrayList<Item> itemBasket;
	
	protected Picker(int userID, String firstName, String surname, String email, String phone, String password) 
	{
		super(userID, firstName, surname,  email, phone, password);
		itemBasket = new ArrayList<Item>();
	}
	
	public boolean addItemToBasket(Item tempItem)
	{
		return itemBasket.add(tempItem);
	}

	public boolean removeItemFromBasket(Item item)
	{
		return itemBasket.remove(item);
	}

	
	public ArrayList<Item> getItemBasket()
	{
		return itemBasket;
	}

}
