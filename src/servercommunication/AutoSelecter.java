package servercommunication;

import java.util.ArrayList;

public class AutoSelecter {

	ArrayList<ServerCommunicator> communictions;
	private int counter;
	private int communictionsSize;
	
	public AutoSelecter()	{
		communictions = new ArrayList<ServerCommunicator>();
		communictions.add(new ServerCommunicator());
		counter = 0;
		communictionsSize = communictions.size();
	}
	
	public AutoSelecter(String endpoint, int port)	{
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
		
		int index = counter%communictionsSize;
		counter++;
		if(counter == communictionsSize)
			counter = 0;
		
		return communictions.get(index);
		
	}
	
	
}