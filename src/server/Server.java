package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import coreClasses.*;
import servercommunication.*;
import database.Database;
import database.I_Database;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//Needs the server message object
public class Server implements I_Server
{
	//Needs more atts, will come up with whats needed during implementation!
	private ServerTool serverTools;
	private Map<String, Command> messageFunctionMap;
	private I_Database database;
	private Gson gson;
	//Refactor exception handling.
	public Server()throws Exception
	{
		messageFunctionMap = new HashMap<String, Command>();
		buildMessageFunctionMap();
		database = Database.getInstance();
		serverTools = new ServerTool(database);
		gson = new Gson();
		setUpSectorTools();
	}
	
	public void runServer() throws IOException
	{
		ServerSocket listener = new ServerSocket(9090);
        try 
        {
            while (true) 
            {
                Socket socket = listener.accept();
                try 
                {
                	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                	ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
                	try
                	{
                		//Get the ServerMessage object passed from the client
                		ServerMessage incomingMessage = (ServerMessage) in.readObject();
                		//Debug Message, remove before shipping
                		System.out.println("This was sent from client: Message=" + incomingMessage.getMessage() + "," + "Data=" + incomingMessage.getData());
                		//Using the command design pattern, we can execute necessary logic in a single call.
                		ServerMessage outgoingMessage = messageFunctionMap.get(incomingMessage.getMessage()).runCommand(incomingMessage);
                		//Return the result to the client.
                		outToServer.writeObject(outgoingMessage);
                	}
                	catch(Exception e)
                	{
                		System.out.println("oh oh :( " + e.toString());
                		e.printStackTrace();
                	}
                	finally
                	{
                		
                	}
                } 
                catch(Exception e)
                {
                	//Bad request or Zeus Server Selector checking if server is alive.
                }
                finally 
                {
                    socket.close();
                }
            }
        }
        finally 
        {
            listener.close();
        }
	}
	
	private void buildMessageFunctionMap()
	{
		/*Command Design Pattern
		  Allows us to map functions to messages here so no change needs to be made to the incoming system.
		  All the server needs to know is that using the command interface it passes the server message object
		  it receives from the client to the contained function and then it gets a server message object back to 
		  respond with. Allows for easy expansion of server side framework and adding of functions with no changes 
		  to pre-existing architecture.
		*/
		messageFunctionMap.put("Login", new Command() {public ServerMessage runCommand(ServerMessage m) {return login(m);}});
		messageFunctionMap.put("RegisterUser", new Command() {public ServerMessage runCommand(ServerMessage m) {return register(m);}});
		messageFunctionMap.put("NewOrder", new Command() {public ServerMessage runCommand(ServerMessage m) {return processIncomingOrder(m);}});
		//----Picker related commands
		messageFunctionMap.put("AssignItemsToPicker", new Command() {public ServerMessage runCommand(ServerMessage m) {return assignPickerItems(m);}});
		messageFunctionMap.put("GetItemsForPicker", new Command() {public ServerMessage runCommand(ServerMessage m) {return getItemsForPicker(m);}});
		messageFunctionMap.put("MarkItemAsPicked", new Command() {public ServerMessage runCommand(ServerMessage m) {return markItemAsPicked(m);}});
		//----end of picker commands
		//----Stocker related commands
		messageFunctionMap.put("StockItems", new Command() {public ServerMessage runCommand(ServerMessage m) {return assignItemsToStocker(m);}});
		messageFunctionMap.put("GetSectors", new Command() {public ServerMessage runCommand(ServerMessage m) {return getSectors(m);}});
		messageFunctionMap.put("GetItemsForStocker", new Command() {public ServerMessage runCommand(ServerMessage m) {return getItemsForStocker(m);}});
		messageFunctionMap.put("SearchProduct", new Command() {public ServerMessage runCommand(ServerMessage m) {return searchProducts(m);}});
		messageFunctionMap.put("MarkItemAsStocked", new Command() {public ServerMessage runCommand(ServerMessage m) {return markItemAsStocked(m);}});
		//messageFunctionMap.put("SearchProduct", new Command() {public ServerMessage runCommand(ServerMessage m) {return })
		//For StockItem example: jsonData should be in format : {"items": [{"productID": 0, "manufactureDate": "some_date", "expiryDate": "some_date"}, .....]}
		//Stock items: Get item info including product ID -> create the product -> find first available cubby to put it in -> return result.
	}
	
	private void setUpSectorTools()
	{
		ArrayList<I_Sector> sectors = database.getAllSectors();
		for(I_Sector sector : sectors)
		{
			serverTools.addSectorTool(new SectorTool(sector));
		}
	}
	
	private ServerMessage login(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		boolean isLogin = authenticate(message.getData());
		result.addProperty("isValid", isLogin);
		if(isLogin)
		{
			//Convert the userData JSON string in a JsonObject 
			JsonObject credentials = new JsonParser().parse(message.getData()).getAsJsonObject();
			//Return the result of isValidLogin where true denotes a valid set of credentials.
			result.addProperty("type", getUserTypeAsInt(database.getUser(credentials.get("email").getAsString(), credentials.get("password").getAsString())));
		}
		return new ServerMessage(message.getMessage()+"Result", result.toString());		
	}
	
	private ServerMessage register(ServerMessage message)
	{
		//This function is faked, in the interest of time
		return new ServerMessage("Default", "Message");
	}
	
	private ServerMessage assignPickerItems(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Picker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		JsonObject messageData = new JsonParser().parse(message.getData()).getAsJsonObject();
		ArrayList<Item> items = serverTools.processPickerItemAssignments((Picker) user, database.getSector(messageData.get("sector").getAsInt()));
		result.addProperty("isSuccess", !items.isEmpty());
		result.add("items", gson.toJsonTree(items).getAsJsonArray());
		return new ServerMessage(message.getMessage()+"Result", result.toString());
	}
	
	private ServerMessage processIncomingOrder(ServerMessage message)
	{
		JsonObject orderData = new JsonParser().parse(message.getData()).getAsJsonObject();
		JsonArray jsonArray =  orderData.get("productIDs").getAsJsonArray();
		ArrayList<Integer> productIDs = new ArrayList<Integer>();
		for(JsonElement ID : jsonArray)
		{
			productIDs.add(ID.getAsInt());
		}
		JsonObject result = new JsonObject();
		result.addProperty("isSubmitted", serverTools.processNewOrder(database.registerOrder(productIDs, orderData.get("shippingAddress").getAsString())));
		return new ServerMessage(message.getMessage()+"Result", result.toString());
	}
		
	private ServerMessage getItemsForPicker(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		//This logic is repeating itself.......
		User user = authenticate(message.getUserData(), Picker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		//... all of this should be integrated into a function
		//I hate this next part ... this needs to be revised.
		Picker picker = (Picker) user;
		result.add("items", gson.toJsonTree(picker.getItemBasket()).getAsJsonArray());
		return new ServerMessage(message.getMessage()+"Result", result.toString());	
	}
	
	private ServerMessage markItemAsPicked(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Picker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		JsonObject itemData = new JsonParser().parse(message.getData()).getAsJsonObject();
		JsonArray items = itemData.get("items").getAsJsonArray();
		JsonArray markResults = new JsonArray();
		for(JsonElement item: items)
		{
			JsonObject markResult = new JsonObject();
			markResult.addProperty("item", item.getAsInt());
			markResult.addProperty("result", serverTools.markItemCollected(database.getItem(item.getAsInt()), (Picker) user));
			markResults.add(markResult);
		}
		result.add("state", markResults);
		return new ServerMessage(message.getMessage()+"Result", result.toString());
		
	}
	
	/** Searches the database for the given searchTerm, returning information on products that prove to be a positive match*/
	private ServerMessage searchProducts(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Stocker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		JsonObject searchData = new JsonParser().parse(message.getData()).getAsJsonObject();
		ArrayList<Product> results = database.getProducts(searchData.get("searchTerm").getAsString());
		result.add("products", gson.toJsonTree(results).getAsJsonArray());
		return new ServerMessage(message.getMessage()+"Result", result.toString());
	}
	
	/** Get all Sector ID's for the current deployment*/
	private ServerMessage getSectors(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		if(authenticate(message.getUserData(), Picker.class) == null && authenticate(message.getUserData(), Stocker.class) == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		ArrayList<I_Sector> sectors = database.getAllSectors();
		result.add("sectors", gson.toJsonTree(sectors).getAsJsonArray());
		return new ServerMessage(message.getMessage()+"Result", result.toString());
	}
	
	/** Get all items currently assigned to a stocker*/
	private ServerMessage getItemsForStocker(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Stocker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		
		Stocker stocker = (Stocker) user;
		result.add("items", gson.toJsonTree(stocker.getStockerBasket()).getAsJsonArray());
		return new ServerMessage(message.getMessage()+"Result", result.toString());	
	}
	
	
	/** Creates a new Item and assigns it for stocking to the given stocker.*/
	private ServerMessage assignItemsToStocker(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Stocker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		JsonObject itemInformation = new JsonParser().parse(message.getData()).getAsJsonObject();
		JsonArray itemArray = itemInformation.get("items").getAsJsonArray();
		JsonArray itemResults = new JsonArray();
		for(JsonElement item : itemArray)
		{
			JsonObject info = item.getAsJsonObject();
			JsonObject itemResult = new JsonObject();
			itemResult.addProperty("id", info.get("productID").getAsInt());
			try
			{
				Item newItem = database.createItem(info.get("productID").getAsInt(), info.get("manufactureDate").getAsString(), info.get("expiryDate").getAsString());
				if(newItem == null)
					System.out.println("newItem is null");
				int sectorID = itemInformation.get("sector").getAsInt();
				System.out.println(sectorID);
				I_Sector s  = database.getSector(sectorID);
				if(s == null)
					System.out.println("s is null");
				itemResult.addProperty("isSuccess", serverTools.processNewItem((Stocker) user, database.getSector(itemInformation.get("sector").getAsInt()), newItem, info.get("productID").getAsInt()));
			}
			catch(Exception e)
			{
				//Item failed to be created, add a failure to the list
				System.out.println("Failed in server");
				System.out.println(e.getMessage());
				e.printStackTrace();
				itemResult.addProperty("isSuccess", false);
			}
			itemResults.add(itemResult);
		}
		result.add("results", itemResults);
		return new ServerMessage(message.getMessage()+"Result",result.toString());
	}
	
	private ServerMessage markItemAsStocked(ServerMessage message)
	{
		JsonObject result = new JsonObject();
		User user = authenticate(message.getUserData(), Stocker.class);
		if(user == null)
		{
			result.addProperty("error", "Invalid Credentials");
			return new ServerMessage(message.getMessage()+"Result", result.toString());
		}
		JsonObject itemData = new JsonParser().parse(message.getData()).getAsJsonObject();
		JsonArray items = itemData.get("items").getAsJsonArray();
		JsonArray markResults = new JsonArray();
		for(JsonElement item: items)
		{
			JsonObject markResult = new JsonObject();
			markResult.addProperty("item", item.getAsInt());
			markResult.addProperty("result", serverTools.markItemStocked(database.getItem(item.getAsInt()), (Stocker) user));
			markResults.add(markResult);
		}
		result.add("state", markResults);
		return new ServerMessage(message.getMessage()+"Result", result.toString());
	}
	
	
	private boolean authenticate(String userData)
	{
		//TODO: This function currently has no way of knowing what type of user these credentials should be validated under.
		//TODO: look into this before submitting this portion of the framework
		//Convert the userData JSON string in a JsonObject 
		JsonObject credentials = new JsonParser().parse(userData).getAsJsonObject();
		//Return the result of isValidLogin where true denotes a valid set of credentials.
		return database.isValidLogin(credentials.get("email").getAsString(), credentials.get("password").getAsString());
	}
	
	private User authenticate(String userData, Class<?> userType)
	{
		//Convert the userData JSON string in a JsonObject 
		JsonObject credentials = new JsonParser().parse(userData).getAsJsonObject();
		//Return the result of isValidLogin where true denotes a valid set of credentials.
		User testUser = database.getUser(credentials.get("email").getAsString(), credentials.get("password").getAsString());
		try
		{
			return (User) userType.cast(testUser);
		}
		catch(ClassCastException e)
		{
			return null;
		}
	}
	
	private int getUserTypeAsInt(User user)
	{
		//Build a map which will map an Integer with a given user type.
		Map<Integer, Class<?>> test = new HashMap<Integer, Class<?>>();
		test.put(1, Picker.class);
		test.put(2, Packer.class);
		test.put(3, Manager.class);
		test.put(4, Stocker.class);
		for(int key : test.keySet())
		{
			try
			{
				test.get(key).cast(user);
				return key;
			}
			catch (ClassCastException e)
			{
				//If this happens its not a valid type
			}
		}
		//We should never encounter this case if a valid user is passed into the function. 
		return -1;
	}

}
