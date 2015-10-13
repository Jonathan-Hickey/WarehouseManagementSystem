package database;

import java.util.ArrayList;

import coreClasses.I_Cubby;
import coreClasses.I_Priority;
import coreClasses.I_Sector;
import coreClasses.I_Shelf;
import coreClasses.Item;
import coreClasses.Order;
import coreClasses.Product;
import coreClasses.User;

public interface I_Database {


	public User createUser(int type, String firstName, String surname, String email, String phone, String password);
	public boolean isValidLogin(String email, String password);
	public Order registerOrder(ArrayList<Integer> productIDs, String shippingAddress);
	public Order getOrder(int orderID);
	public Item createItem(int productID, String manufactureDate, String expriryDate) throws Exception;
	public Product createProduct(String name, String description, double price, float height, float width, float depth,float weight, int priorityID);
	public ArrayList<Order> getOpenOrders();
	public ArrayList<Item> getItems(int productID);
	public ArrayList<Product> getProducts();
	public Product getProduct(int productID);
	//Shane
	public ArrayList<Product> getProducts(String searchTerm);
	public I_Shelf getShelf(int ID);
	public I_Cubby getCubby(int ID);
	public void updateCubby(I_Cubby cubby);
	//--
	public Item getItem(int itemID);
	public int itemBelonngsTo(int itemID);
	public I_Cubby createCubby(int type);
	public I_Shelf createShelve( int type);
	public I_Sector createSector(int type);
	public void deleteItem(int itemID);
	public void deleteProduct(int productID);
	public void deleteOrder(int orderID);
	public I_Priority getPriority(int priorityID);
	public void updateProduct(Product product);
	public void updateItem(Item item);
	public ArrayList<I_Sector> getAllSectors();
	public void updateOrder(Order order);
	public I_Sector createSector();
	public void updateSector(I_Sector sector);
	public I_Sector getSector(int ID);
	public I_Cubby itemBelongsToCubby(int itemID );
	public I_Shelf cubbyBelongsToShelf(int cubbyID);
	public I_Sector shelfBelongsToSector(int shelfID);
	public User getUser(String email, String password);
	public User getUser(int userID);
	public void updateUser(User user);
	public ArrayList<Item> getItems();
	
	//---Not sure if it belongs here
	public boolean isItemSkuUnique(String sku);

}
