package server;
import java.io.IOException;


public class RunServer {

	public static void main(String args[])throws Exception  {
		Server server =  new Server();
		
		try {
			server.runServer();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
