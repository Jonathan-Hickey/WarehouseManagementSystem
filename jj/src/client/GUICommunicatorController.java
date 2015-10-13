package client;

import java.util.ArrayList;

import servercommunication.ServerCommunicator;
import servercommunication.ServerMessage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GUICommunicatorController 
{
	private ServerCommunicator communicator;
	private ServerMessage serverResult;
	private JsonObject user;
	private JsonObject currentSector;

	
	private Gson gson;
	
	public GUICommunicatorController() 
	{
		communicator = new ServerCommunicator();
		gson = new Gson();
	}

	private void  init()
	{
		user = new JsonObject();
		currentSector = new JsonObject();
	}
	
	public boolean loginUser(String email, String password)
	{
	
		if ( email == null || password == null)
			return false;

		init();
		user.addProperty("email", email);
		user.addProperty("password", password);
		
		serverResult = communicator.sendServerMessage(new ServerMessage("Login", user.toString()));
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		
		if(credentials.get("isValid").getAsBoolean())
			user.addProperty("type", credentials.get("type").getAsInt());
		else 
			user = null;
		
		return credentials.get("isValid").getAsBoolean();
	}
	
	public boolean logOut()
	{
		user = null;
		currentSector = null;
		return true;
	}
	
	public int getUserType()
	{
		if(user != null)	
			return user.get("type").getAsInt();
		
		return -1;
	}
	
	public void setCurrentSectorLocation(int sectorID)
	{
		if(user != null)
			currentSector.addProperty("sector", sectorID);
	}
	
	
	public ArrayList<String> getPickerCurrentBasket()
	{
		serverResult = communicator.sendServerMessage(new ServerMessage("GetItemsForPicker", "" ,user.toString() ));
		
		ArrayList<String> basket = new ArrayList<String>();
		
		if(serverResult == null)
			return basket;
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		JsonArray jsonArray = credentials.getAsJsonArray("items");
		
		
		for(JsonElement element : jsonArray)
				basket.add(" Item Id: "+ element.getAsJsonObject().get("ID") + "  Location: " + "XXXX");
		
		return basket;
	}
	
	
	public void collectItem(int itemID)
	{
		JsonObject items = new JsonObject();
		items.add("items", gson.toJsonTree(new int[] {itemID}).getAsJsonArray());
		serverResult = communicator.sendServerMessage(new ServerMessage("MarkItemAsPicked", items.toString() ,user.toString() ));
		
	}
	
	public boolean requestItemsForPickerBasket()
	{
		serverResult = communicator.sendServerMessage(new ServerMessage("AssignItemsToPicker", currentSector.toString() ,user.toString() ));
		
		if(serverResult == null)
			return false;
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		return credentials.get("isSuccess").getAsBoolean();
	}
		
	public ArrayList<String> getStockerCurrentBasket()
	{
		serverResult = communicator.sendServerMessage(new ServerMessage("GetItemsForStocker", currentSector.toString() ,user.toString() ));
		
		ArrayList<String> basket = new ArrayList<String>();
		
		if(serverResult == null)
			return basket;
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		JsonArray jsonArray = credentials.getAsJsonArray("items");
		
		JsonObject obj;
		
		for(JsonElement element : jsonArray )
		{
			obj = element.getAsJsonObject(); 
			basket.add("Product ID: " + obj.get("ID").getAsString() + " Location: " + "XXXXXX");
		}
		
		return basket;
	}
	
	public ArrayList<Integer> getSectorsIDs()
	{
		ArrayList<Integer> sectors = new ArrayList<Integer>();
		
		serverResult = communicator.sendServerMessage(new ServerMessage("GetSectors", "" ,user.toString() ));
		
		if(serverResult == null)
			return sectors;
	
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		JsonArray jsonArray = credentials.getAsJsonArray("sectors");
		
		for(JsonElement element : jsonArray )
			sectors.add(gson.fromJson(element, JsonObject.class).get("ID").getAsInt());
		
		if(sectors.size() > 0)
			currentSector.addProperty("sector", sectors.get(0));
		
		return sectors;
	}
	
	public ArrayList<String> searchForProduct(String product)
	{

		JsonObject obj = new JsonObject();
		obj.addProperty("searchTerm", product);
		
		serverResult = communicator.sendServerMessage(new ServerMessage("SearchProduct", obj.toString() ,user.toString() ));
		
		if(serverResult == null)
			return null;
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		
		JsonArray jsonArray = credentials.getAsJsonArray("products");
		ArrayList<String> products = new ArrayList<String>();
		

		for(JsonElement element : jsonArray )
		{
			obj = gson.fromJson(element, JsonObject.class); 
			products.add("Product Name: " + obj.get("name").getAsString() + " Product ID: " + obj.get("ID").getAsString());
		}
		
		return products;
	}
	
	public int registorItem(int productID, String manDate, String expDate)
	{
		JsonObject obj = new JsonObject();
		
		obj.addProperty("sector", currentSector.get("sector").getAsInt());
		JsonArray itemInfo = new JsonArray();
		JsonObject tempObj = new JsonObject();
		
		tempObj.addProperty("productID", productID);
		tempObj.addProperty("manufactureDate", manDate);
		tempObj.addProperty("expiryDate", expDate);
		
		itemInfo.add(tempObj);
		
		obj.add("items", itemInfo);
		
		serverResult = communicator.sendServerMessage(new ServerMessage("StockItems", obj.toString() ,user.toString() ));
		
		if(serverResult == null)
			return -1;
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		JsonArray jsonArray = credentials.get("results").getAsJsonArray();
		
		obj = (jsonArray.get(0)).getAsJsonObject();
		
		if(!obj.get("isSuccess").getAsBoolean())
			return -1;
		
//		TODO return sku Id number
		return 1;
	}
	
	public boolean putItemInCubby(int itemID)
	{

		JsonObject items = new JsonObject();
		items.add("items", gson.toJsonTree(new int[] {itemID}).getAsJsonArray());
		serverResult = communicator.sendServerMessage(new ServerMessage("MarkItemAsStocked", items.toString() ,user.toString() ));
		
		JsonObject credentials = new JsonParser().parse(serverResult.getData()).getAsJsonObject();
		
		JsonArray jsonArray = credentials.get("state").getAsJsonArray();
		JsonObject obj = (jsonArray.get(0)).getAsJsonObject();

		return obj.get("result").getAsBoolean();
	}
}


