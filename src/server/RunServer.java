package server;

public class RunServer {

	public static void main(String args[])throws Exception  {
		
		System.out.println("Server Main Thread: This is currently running on main thread, " +  
	            "the id is: " + Thread.currentThread().getId()); 
		
		Server server =  new Server();
		SectorTool sectorTool = new SectorTool();
		ServerTool serverTool = new ServerTool();
		serverTool.start();
		sectorTool.start();
		server.start();
	}

}
