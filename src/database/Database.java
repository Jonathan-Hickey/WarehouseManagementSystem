package database;


import java.util.ArrayList;

import coreClasses.CubbyFactory;
import coreClasses.I_Cubby;
import coreClasses.I_Sector;
import coreClasses.I_Shelf;
import coreClasses.Item;
import coreClasses.Order;
import coreClasses.Picker;
import coreClasses.Product;
import coreClasses.SectorFactory;
import coreClasses.ShelfFactory;
import coreClasses.Stocker;
import coreClasses.User;
import coreClasses.PriorityFactory;
import coreClasses.I_Priority;
import coreClasses.UserFactory;
import coreClasses.ItemState;


public class Database implements I_Database {

	private ArrayList<User> users;
	private int userIndexer;
	
	private ArrayList<Item> items;
	private int itemIndexer;
	
	private ArrayList<Product> products;
	private int productIndexer;
	
	private ArrayList<Order> orders;
	private int orderIndexer;
	
	private ArrayList<I_Cubby> cubbies;
	private int cubbyIndexer;
	
	private ArrayList<I_Shelf> shelves; 
	private int shelveIndexer;
	
	private ArrayList<I_Sector> sectors;
	private int sectorIndexer;
	
	private CubbyFactory cubbyFactory;
	private ShelfFactory shelveFactory;
	private SectorFactory sectorFactory;
	private UserFactory userFactory;
	
	private static Database databaseInstance = null;
	
	public static Database getInstance()
	{
		if(databaseInstance==null)
		{
			try 
			{
				databaseInstance = new Database();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return databaseInstance;
	}
	private Database() throws Exception
	{
	
		users = new ArrayList<User>();
		
		userFactory = new UserFactory();
		
		users.add(userFactory.makeUser(1, 1, "Cookie", "Monster",  "Cookie.Monster@gmail.com", "087XXXXXXX0", "cookies"));
		users.add(userFactory.makeUser(2, 2,"Big", "Bird","Big.Bird@gmail.com", "087XXXXXXX2", "yellow"));
		users.add(userFactory.makeUser(3, 3, "Kermit", "the Frog", "Kermit.the.Frog@gmail.com", "087XXXXXXX1", "swamp"));
		users.add(userFactory.makeUser(4, 4, "Power", "Rangers", "Power.Rangers@gmail.com", "087XXXXXXX4", "morphin"));
		
		users.add(userFactory.makeUser(1, 5, "Mr", "Picker",  "picker", "087XXXXXXX0", "1234"));
		users.add(userFactory.makeUser(2, 6,"Mr", "Packer","packer", "087XXXXXXX2", "1234"));
		users.add(userFactory.makeUser(3, 7, "Mr", "Manager", "manager", "087XXXXXXX1", "1234"));
		users.add(userFactory.makeUser(4, 8, "Mr", "Stocker", "stocker", "087XXXXXXX4", "1234"));
		userIndexer = 9;
		
		products = new ArrayList<Product>();
		products.add(new Product(1,"XBox One","This is description one.", 10.00f, 100.0f, 40.0f, 30.0f, 100.0f, 0));
		products.add(new Product(2,"The Book of life","This is description two.", 10.00f, 100.0f, 40.0f, 30.0f, 100.0f, 0));
		products.add(new Product(3,"Pizza","This is description three.", 10.00f, 100.0f, 40.0f, 30.0f, 100.0f, 0));
		productIndexer = 4;
		
		
		items = new ArrayList<Item>();
		items.add(new Item(3, 1, null, "2015-04-12"));
		items.add(new Item(2, 2, "2013-12-06", null));
		items.add(new Item(2, 3, "2014-12-05", null));
		items.add(new Item(1, 4, "2015-01-12", null));
		items.add(new Item(3, 5, null, "2015-04-03"));
		items.add(new Item(1, 6, "2014-08-07", null));
		items.add(new Item(2, 7, "2012-12-05", null));
		items.add(new Item(3, 8, null, "2014-12-05"));
		items.add(new Item(3, 9, null, "2015-04-12"));
		items.add(new Item(2, 10, "2013-12-06", null));
		items.add(new Item(2, 11, "2014-12-05", null));
		items.add(new Item(1, 12, "2015-01-12", null));
		items.add(new Item(3, 13, null, "2015-04-03"));
		items.add(new Item(1, 14, "2015-01-12", null));
		items.add(new Item(3, 15, null, "2015-04-03"));
		items.add(new Item(3, 16, null, "2015-04-03"));
		
		itemIndexer = 17;
		
	
		items.get(0).setCurrentState(ItemState.AWAITING_STOCKER);
		items.get(1).setCurrentState(ItemState.AWAITING_STOCKER);
		
		items.get(2).setCurrentState(ItemState.PENDING_STOCKING);
		items.get(3).setCurrentState(ItemState.PENDING_STOCKING);
		((Stocker)(users.get(7))).addItem(items.get(2));
		items.get(2).setAssignedUserID(users.get(7).getID());
		((Stocker)(users.get(7))).addItem(items.get(3));
		items.get(3).setAssignedUserID(users.get(7).getID());
		
		items.get(4).setCurrentState(ItemState.AVAILABLE);
		items.get(5).setCurrentState(ItemState.AVAILABLE);
		
		items.get(6).setCurrentState(ItemState.AWAITING_PICKER);
		items.get(7).setCurrentState(ItemState.AWAITING_PICKER);
		
		items.get(8).setCurrentState(ItemState.AWAITING_CHECK_IN);
		items.get(9).setCurrentState(ItemState.AWAITING_CHECK_IN);
		
		//----Code is out of control here ... there has to be better way
		items.get(8).setAssignedUserID(users.get(4).getID());
		((Picker) (users.get(4))).addItemToBasket(items.get(8));
		items.get(9).setAssignedUserID(users.get(4).getID());
		((Picker) (users.get(4))).addItemToBasket(items.get(9));
		
		items.get(10).setCurrentState(ItemState.AWAITING_PACKER);
		items.get(11).setCurrentState(ItemState.AWAITING_PACKER);
		items.get(12).setCurrentState(ItemState.PACKED);
		items.get(13).setCurrentState(ItemState.PACKED);
		items.get(14).setCurrentState(ItemState.SHIPPED);
		items.get(15).setCurrentState(ItemState.SHIPPED);
		
		orders = new ArrayList<Order>();
		ArrayList<Integer> tempOrderList = new ArrayList<Integer>();
		tempOrderList.add(3);
		tempOrderList.add(2);
		tempOrderList.add(1);
		orders.add(new Order(1,tempOrderList, "Order Address one \n Address two \n Town"));
		tempOrderList.clear();
		
		tempOrderList.add(1);
		tempOrderList.add(1);
		tempOrderList.add(3);
		orders.add(new Order(2,tempOrderList, "Order Address two \n Address two \n Town"));
		tempOrderList.clear();
		
		tempOrderList.add(1);
		tempOrderList.add(3);
		tempOrderList.add(2);
		orders.add(new Order(3,tempOrderList, "Order Address Three \n Address two \n Town"));
		tempOrderList.clear();
		
		orderIndexer = 4;
		
		
		this.cubbyFactory = new CubbyFactory();
		this.cubbyIndexer = 1;
		
		this.shelveFactory = new ShelfFactory();
		this.shelveIndexer = 1;
		shelves = new ArrayList<I_Shelf>();
		for(int i = 0; i < 10; i++)
		{
			if(shelves == null)
				System.out.println("Shelves is null");
			if(shelveFactory == null)
				System.out.println("factory is null");
			shelves.add(shelveFactory.makeShelve(1, shelveIndexer));
			this.shelveIndexer++;
		}
		
		
		ArrayList<Integer> tempCubbies;
		I_Cubby tempCub;
		cubbies = new ArrayList<I_Cubby>();
		for(I_Shelf shelve : shelves)
		{
			
			tempCubbies = new ArrayList<Integer>();
			
			for(int i = 0; i < 8; i++)
			{
				tempCub = cubbyFactory.makeCubby(1, cubbyIndexer);
				cubbies.add(tempCub);
				tempCubbies.add(tempCub.getID());
				cubbyIndexer++;
			}
			
			for(int i = 0; i < 4; i++)
			{
				tempCub = cubbyFactory.makeCubby(2, cubbyIndexer);
				cubbies.add(tempCub);
				tempCubbies.add(tempCub.getID());
				cubbyIndexer++;
			}
		
			for(int i = 0; i < 2; i++)
			{
				tempCub = cubbyFactory.makeCubby(3, cubbyIndexer);
				cubbies.add(tempCub);
				tempCubbies.add(tempCub.getID());
				cubbyIndexer++;
			}
			
			shelve.setCubbies(tempCubbies);
		}
		

		sectors = new ArrayList<I_Sector>();
		sectorIndexer = 1;
		sectorFactory = new SectorFactory();
		
		sectors.add(sectorFactory.makeSector(1, sectorIndexer));
		sectorIndexer++;
		
		ArrayList<Integer> tempShelve = new ArrayList<Integer>();
		//This should all be in its own function ... doing it this way for testing purposes...disgusting
		//perhaps use automation ? to use server functions to begin population
		sectors.get(0).putItemInQueue(items.get(6));
		sectors.get(0).putItemInQueue(items.get(7));
		cubbies.get(0).addItem(items.get(4).getID());
		cubbies.get(0).addItem(items.get(5).getID());
		cubbies.get(0).addItem(items.get(6).getID());
		items.get(6).setxPlacementPoint(0);
		cubbies.get(0).addItem(items.get(7).getID());
		items.get(7).setxPlacementPoint((int) (products.get(0).getWidth() + 5));
		//--end of test code
		for(I_Shelf shelve : shelves)
		{
			tempShelve.add(shelve.getID());
		}
		
		sectors.get(0).setShelves(tempShelve);
		
	}
	
	@Override
	public User createUser(int type, String firstName, String surname, String email, String phone, String password)
	{
		
		User temp = userFactory.makeUser(type, userIndexer, firstName, surname, email, phone, password);
		
		if(temp != null)
		{
			userIndexer++;
			users.add(temp);
		}
		
		return temp;
	}

	@Override
	public boolean isValidLogin(String email, String password) 
	{
		if(email == null && password == null) 
			return false;
		
		for(User user: users)
			if(email.equals(user.getEmail()) && password.equals(user.getPassword())) 
				return true;
		
		return false;
	}

	@Override
	public Order registerOrder(ArrayList<Integer> productIDs, String shippingAddress) 
	{

		Order order = new Order(orderIndexer, productIDs, shippingAddress);
		
		if(order != null)
		{
			orders.add(order);
			orderIndexer++;
		}
		
		return order;
	}

	@Override
	public Order getOrder(int orderID) 
	{
		for(Order order : orders)
			if(order.getID() == orderID)  
				return order;
		
		return null;
	}

	@Override
	public Item createItem(int productID, String manufactureDate, String expriryDate) throws Exception
	{
		Item item= new Item(productID, itemIndexer, manufactureDate, expriryDate);
		
		if (item != null)
		{
			items.add(item);
			itemIndexer++;
		}
		
		return item;
	}

	@Override
	public Product createProduct(String name, String description, double price, float height, float width, float depth,float weight, int priorityID) 
	{
		
		Product product = new Product(productIndexer, name, description, price, height, width, depth, weight, priorityID);
		
		if(product != null)
		{
			productIndexer++;
			products.add(product);
		}
		
		return product;
	}

	@Override
	public ArrayList<Order> getOpenOrders()
	{
		
		ArrayList<Order> tempOrders = new ArrayList<Order>();
		
		for(Order order : orders)
			if(order.getStatus().equalsIgnoreCase( "OPEN"))
				tempOrders.add(order);
		
		return tempOrders;
	}

	@Override
	public ArrayList<Item> getItems() 
	{
		return items;	
	}
	
	@Override
	public ArrayList<Item> getItems(int productID) 
	{
		
		ArrayList<Item> tempItems = new ArrayList<Item>();
		
		for(Item item : items)
			if(item.getProductID() == productID)
				tempItems.add(item);
		
		return tempItems;
	}

	@Override
	public ArrayList<Product> getProducts() 
	{
		return products;
	}
	
	@Override
	public Product getProduct(int productID) 
	{
		
		for(Product product : products)
			if(product.getID() == productID)  
				return product;
			

		return null;
	}
	
	@Override
	public Item getItem(int itemID) 
	{
		
		for(Item item : items)
			if(item.getID() == itemID)
				return item;
			
		return null;
	}
	

	@Override
	public int itemBelonngsTo(int itemID)
	{
		for(Order order : orders)
			if(order.hasItem(itemID))
				return order.getID();
		
		return 0;
	}
	



	
	@Override
	public I_Cubby createCubby(int type) 
	{
		I_Cubby cubby = cubbyFactory.makeCubby(type, cubbyIndexer );
		
		if(cubby != null)
		{
			cubbies.add(cubby);
			cubbyIndexer++;
		}
		return cubby;
	}
	
	@Override
	public I_Shelf createShelve( int type) 
	{
		I_Shelf temp = shelveFactory.makeShelve(type, shelveIndexer);
		
		if(temp != null)
		{
			shelveIndexer++;
			shelves.add(temp);
		}
		
		return temp;
	}
	
	@Override
	public I_Sector createSector(int type)
	{
		I_Sector temp = sectorFactory.makeSector(type, sectorIndexer);
		
		if(temp != null)
		{
			sectorIndexer++;
			sectors.add(temp);
		}
		return temp;
	}
	
	@Override
	public void deleteItem(int itemID)
	{
		for(int index = 0; index < items.size(); index++)
			if(items.get(index).getID() == itemID)
			{
				items.remove(index);
				return;
			};
	}
	
	@Override
	public void deleteProduct(int productID)
	{
		for(int index = 0; index <products.size(); index++)
			if(products.get(index).getID() == productID)
			{
				products.remove(index);
				return;
			};
	}
	
	@Override
	public void deleteOrder(int orderID) 
	{
		for(int index = 0; index <orders.size(); index++)
			if(orders.get(index).getID() == orderID)
			{
				orders.remove(index);
			};
	}

	
	@Override
	public I_Priority getPriority(int priorityID)
	{
		return new PriorityFactory().makePriority(priorityID);
	}
	
	@Override
	public void updateProduct(Product product)
	{
		for(int i = 0; i < products.size(); i++)
			if(products.equals(product))
			{
				products.set(i, product);
				return;
			};
	}
	
	@Override
	public void updateItem(Item item)
	{
		for(int i = 0; i < items.size(); i++)
			if(items.get(i).getID() == item.getID())
			{
				items.set(i, item);
				return;
			};
	}

	@Override
	public ArrayList<I_Sector> getAllSectors()
	{
		return sectors;
	}
	
	public void updateOrder(Order order)
	{
		for(int index = 0; index < orders.size(); index++)
			if(orders.get(index).equals(order))
			{
				orders.set(index, order);
				return;
			};
	}
	
	@Override
	public I_Sector createSector()
	{
		return sectorFactory.makeSector(1, sectorIndexer);
	}
	
	public void updateSector(I_Sector sector)
	{
		for(int index = 0; index < sectors.size(); index++)
			if(sectors.get(index).equals(sector))
			{
				sectors.set(index, sector);
				return;
			};
	}
	
	@Override
	public I_Sector getSector(int ID)
	{
		for(I_Sector sec : sectors)
			if(sec.getID() == ID ) 
				return sec;
	
		
		return null;
	}

	@Override
	public I_Cubby itemBelongsToCubby(int itemID )
	{
		for(I_Cubby cubby : cubbies)
			if(cubby.hasItem(itemID)) 
				return cubby;
		
		return null;
	}
	
	@Override
	public I_Shelf cubbyBelongsToShelf(int cubbyID)
	{
		for(I_Shelf shelf : shelves)
			if (shelf.hasCubby(cubbyID))
				return shelf;
		
		return null;
	}
	
	@Override
	public I_Sector shelfBelongsToSector(int shelfID)
	{
		for(I_Sector sector : sectors)
			if (sector.hasShelf(shelfID)) 
				return sector;
		
		return null;
	}
	
	@Override
	public User getUser(String email, String password) {
		
		for(User user : users)
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password))
				return user;
		
		return null;
	}
	
	@Override
	public User getUser(int userID) {
		
		for(User user : users)
			if(user.getID() == userID) 
				return user;
		
		return null;
	}
	
	@Override
	public void updateUser(User user)
	{
		for(int index = 0; index < users.size(); index++)
			if(users.get(index).equals(user)) 
			{
				users.set(index, user);
				return;
			};
	}
	
	//--Not sure if this belongs here
	@Override
	public boolean isItemSkuUnique(String sku)
	{
		for(Item item: items)
		{
			if(item.getSku().equals(sku))
				return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Product> getProducts(String searchTerm)
	{
		ArrayList<Product> results = new ArrayList<Product>();
		for(Product product: products)
		{
			if(product.getName().toLowerCase().contains(searchTerm.toLowerCase()) || product.getSku().toLowerCase().contains(searchTerm.toLowerCase()))
				results.add(product);
		}
		return results;
	}
	
	@Override 
	public I_Shelf getShelf(int ID)
	{
		for(I_Shelf shelf: shelves)
		{
			if(shelf.getID() == ID)
				return shelf;
		}
		return null;
	}
	
	@Override
	public I_Cubby getCubby(int ID)
	{
		for(I_Cubby cubby: cubbies)
		{
			if(cubby.getID() == ID)
				return cubby;
		}
		return null;
	}
	
	@Override
	public void updateCubby(I_Cubby cubby)
	{
		for(int i = 0; i < cubbies.size(); i++)
			if(cubbies.get(i).equals(cubby)) 
			{
				cubbies.set(i, cubby);
				break;
			}
	}

}
