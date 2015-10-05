package coreClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import coreClasses.ItemState;


public class Item{

	private final int PRODUCT_ID;
	private final int ID;
	
	private Date manufactureDate;
	private Date expriryDate;
	
	private String sku;
	//private String currentState;
	private ItemState state;
	private int assignedUserID;
	private int xPlacementPoint;
	
	public Item(int productID, int id, String manufactureDate, String expriryDate) throws Exception
	{
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		 
		
		this.PRODUCT_ID = productID;
		this.ID = id;
		
		try {
			if(manufactureDate == null)
			{
				this.manufactureDate = null;
			}
			else 
			{	
				this.manufactureDate = (Date) format.parse(manufactureDate);
			}
		} catch (ParseException e) 
		{
			throw new Exception("Cannot convert: " + manufactureDate + " to valid date format: \"yyyy-mm-dd\"  ");
		}
		
		try {
			
			if(expriryDate == null)
			{
				this.expriryDate = null;
			} 
			else
			{
				this.expriryDate = (Date) format.parse(expriryDate);
			}
		
		} 
		catch (ParseException e)
		{
			throw new Exception("Cannot convert: " + expriryDate + " to valid date format: \"yyyy-mm-dd\"  ");
		}
		
		this.setCurrentState(ItemState.AWAITING_STOCKER);
		//this.currentState = "AWAITING_STOCKER";

		this.xPlacementPoint = 0;
		this.sku = "";
	}
	
	
	
	/*public String getCurrentState()
	{
		return currentState;
	}/*

	/*
	 * Options are:
	 *  AWAITING_STOCKER
	 *	PENDING_STOCKING
	 * 	AVAILABLE
	 *	AWAITING_PICKER
	 *	AWAITING_CHECK_IN
	 *	AWAITING_PACKER
	 *	PACKED
	 *	SHIPPED
	 * 
	 */
	/*public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}*/

	public int getAssignedUserID() {
		return assignedUserID;
	}

	public void setAssignedUserID(int assignedUserID) {
		this.assignedUserID = assignedUserID;
	}

	public int getxPlacementPoint() {
		return xPlacementPoint;
	}

	public void setxPlacementPoint(int xPlacementPoint) {
		this.xPlacementPoint = xPlacementPoint;
	}

	public int getProductID() {
		return PRODUCT_ID;
	}

	public int getID() {
		return ID;
	}

	public Date getDateOfManufacture() {
		return manufactureDate;
	}
	
	public Date getDateOfExpriry() {
		return expriryDate;
	}
	
	@Override
	public String toString()
	{
		return "ItemID: " + ID + " Product ID: " + PRODUCT_ID;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return (((obj instanceof Item)) && ((Item) obj) != null && this.ID == ((Item) obj).getID());     
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) throws Exception
	{
		//Barcode must satisfy EAN-13, UPC-A, EAN-8 or UPC-E standards
		if(!sku.matches("[0-9]{8,13}"))
			throw new Exception("Invalid SKU given. Must satisfy EAN-13, UPC-A, EAN-8 or UPC-E standards");
		this.sku = sku;
	}


	public ItemState getCurrentState() {
		return state;
	}


	public void setCurrentState(ItemState state) {
		this.state = state;
	}
	
	
}
