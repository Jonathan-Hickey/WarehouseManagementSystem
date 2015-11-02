
import servercommunication.*;


public class ReferenceForServerMessages 
{
	public static void main(String [] args)
	{
		//The Zeus server selection service will determine which server should be used to service the request.
		ServerCommunicator t = new ServerCommunicator();
		ServerMessage result = t.sendServerMessage(new ServerMessage("NewOrder", "{\"productIDs\": [1], \"shippingAddress\": \"yellow\"}"));
		//t.sendServerMessage(new ServerMessage("GetItemsForPicker", "", "{\"email\": \"picker\", \"password\": \"1234\"}"));
		//t.sendServerMessage(new ServerMessage("MarkItemAsPicked", "{\"items\": [9]}", "{\"email\": \"picker\", \"password\": \"1234\"}"));
		//t.sendServerMessage(new ServerMessage("RegisterUser", ""));
		//t.sendServerMessage(new ServerMessage("SearchProduct", "{\"searchTerm\": \"xbox\"}", "{\"email\": \"stocker\", \"password\": \"1234\"}"));
		//t.sendServerMessage(new ServerMessage("GetItemsForStocker", "", "{\"email\": \"stocker\", \"password\": \"1234\"}"));
		//t.sendServerMessage(new ServerMessage("GetSectors", "", "{\"email\": \"stocker\", \"password\": \"1234\"}"));
		//t.sendServerMessage(new ServerMessage("StockItems", "{\"sector\": 1, \"items\": [{\"productID\": 1, \"manufactureDate\": \"2015-04-12\", \"expiryDate\": \"2015-05-12\"}]}", "{\"email\": \"stocker\", \"password\": \"1234\"}"));
		//result = t.sendServerMessage(new ServerMessage("AssignItemsToPicker", "{\"sector\": 1}", "{\"email\": \"picker\", \"password\": \"1234\"}"));
		//System.out.println(result.getData());
	}
}
