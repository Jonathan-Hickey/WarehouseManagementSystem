package server;
import servercommunication.*;

public interface Command 
{
	public ServerMessage runCommand(ServerMessage message);
}
