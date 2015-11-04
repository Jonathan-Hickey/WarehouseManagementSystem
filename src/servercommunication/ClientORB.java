package servercommunication;

import java.util.ArrayList;

public class ClientORB {

	ArrayList<ServerCommunicator> communictions;
	private int counter;
	private int communictionsSize;
	
	public ClientORB()	{
		communictions = new ArrayList<ServerCommunicator>();
		communictions.add(new ServerCommunicator("127.0.0.1", 9091));
		communictions.add(new ServerCommunicator("127.0.0.1", 9092));
		communictions.add(new ServerCommunicator("127.0.0.1", 9093));
		communictions.add(new ServerCommunicator("127.0.0.1", 9094));
		counter = 0;
		communictionsSize = communictions.size();
	}
	
	public ClientORB(String endpoint, int port)	{
		communictions = new ArrayList<ServerCommunicator>();
		communictions.add(new ServerCommunicator(endpoint, port));
		counter = 0;
		communictionsSize = communictions.size();
	}
	
	public boolean addServerCommincator(String endpoint, int port){
		
		for(ServerCommunicator coms : communictions)
		{
			if(coms.getEndpoint().equalsIgnoreCase(endpoint) && coms.getPort() == port)
			{
				return false;
			}
		}
		
		if (communictions.add(new ServerCommunicator(endpoint, port))){
			communictionsSize = communictions.size();
			return true;
		}
		
		return false;
		
	}
	
	
	public boolean addServerCommunicator(ServerCommunicator communicator){
		for(ServerCommunicator coms : communictions)
		{
			if(coms.equals(communicator))
				return false;
		}
		
		if (communictions.add(communicator)){
			communictionsSize = communictions.size();
			return true;
		}
		
		return false;
		
	}
	
	public boolean removeServerCommunicator(ServerCommunicator communicator){
//		TODO needs to be tested
		for(ServerCommunicator coms : communictions)
		{
			if(coms.equals(communicator))
				if (communictions.remove(coms)){
					communictionsSize = communictions.size();
					return true;
				}
		}
	
		return false;
	}
	
	public ServerCommunicator getServerCommunicator()	{
		
		if(communictionsSize == 0){
			return null;
		}
		
		int index = counter%communictionsSize;
		counter++;
		if(counter == communictionsSize)
			counter = 0;
		
		return communictions.get(index);
		
	}
	
	
}