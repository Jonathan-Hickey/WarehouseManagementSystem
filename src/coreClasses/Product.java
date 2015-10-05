package coreClasses;
import java.util.*;


public class Product 
{

	private final int ID;
	private String name;
	private String description;
	private String sku;
	private double price;
	private List<String> itemIds;
	private float heigth;
	private float width;
	private float depth;
	private float weight;
	private int priorityID;
	private String state;
	
	public Product(int id,String name, String description, double price, float height, float width, float depth, float weight, int priorityID)
	{
		this.ID = id;
		this.name = name;
		this.sku = "";
		this.description = description;
		this.price = price;
		this.heigth = height;
		this.depth = depth;
		this.width = width;
		this.weight = weight;
		this.priorityID = priorityID;
		this.state = "AVAILABLE";
	}
	public Product(int id,String name, String description, String sku, double price, float height, float width, float depth, float weight, int priorityID)throws Exception
	{
		this.ID = id;
		this.name = name;
		this.description = description;
		this.setSku(sku);
		this.price = price;
		this.heigth = height;
		this.depth = depth;
		this.width = width;
		this.weight = weight;
		this.priorityID = priorityID;
		this.state = "AVAILABLE";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPriorityID() {
		return priorityID;
	}

	public void setPriorityID(int basePriority) {
		this.priorityID = basePriority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<String> itemIds) {
		this.itemIds = itemIds;
	}

	public float getHeigth() {
		return heigth;
	}

	public void setHeigth(float heigth) {
		this.heigth = heigth;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getID() {
		return ID;
	}
	
	public String toString()
	{
		return "I am a product with an ID: " + this.ID + " and a name " + this.name;
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
}
