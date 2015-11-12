package servercommunication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerCommunicator 
{
	//This class will be used to open a connection to the server.
	//Will be used to transmit server messages;
	
	private Socket serverSocket;
	private String endpoint;
	private int port;
	
	//For the purposes of this project the default constructor should be used.
	public ServerCommunicator()
	{
		autoServerSelection();
	}
	public ServerCommunicator(String endpoint)
	{
		this.endpoint = endpoint;
		this.port = 9090;
	}
	public ServerCommunicator(String endpoint, int port)
	{
		this.endpoint = endpoint;
		this.port = port;
	}
	
	private boolean openServerConnection()
	{
		try
		{
			serverSocket = new Socket(endpoint, port);
			return true;
		}
		catch (Exception e) 
		{
			//DELETE ME For final version and add error handling
			System.out.println("Error - Failed to connect to the server. Is it online ?");
			return false;
		}
	}
	
	/** Use this function if you want the Zeus Server Selector to determine the best server to connect to*/
	public void autoServerSelection()
	{
		try
		{
			//URL to the server Selector service
			setEndpoint("127.0.0.1");
			setPort(9091);
			//TODO: Debug Message, remove before finished version
			JOptionPane.showMessageDialog(null, "Welcome to the Zeus Client Application, You are connected to the following Server:\nServer Name: " + "Localhost" + "\nIP Address: " + endpoint + "\nPort: " + port, "Zeus Server Connector", 1);
		}
		catch(Exception e)
		{
			//Failed to auto select, lets use defaults in this case
			setEndpoint("127.0.0.1");
			setPort(9090);
			//TODO: debug message, remove before finished version
			System.out.println("Defaults used");
		}
	}
	
	public String getEndpoint()
	{
		return endpoint;
	}
	
	public void setEndpoint(String endpoint)
	{
		this.endpoint = endpoint;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public boolean closeConnection()
	{
		try
		{
			serverSocket.close();
			return true;
		}
		catch(IOException e )
		{
			return false;
		}
	}
	
	public ServerMessage sendServerMessage(ServerMessage serverMessage)
	{
		ServerMessage response = null;
		try
		{
			if(openServerConnection())
			{
				ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());
				out.writeObject(serverMessage);
				response = (ServerMessage) in.readObject();
				//Debug message
				System.out.println("This was returned from server: Message=" + response.getMessage() + "," + "Data=" + response.getData());
				
			}
			else
			{
				throw new Exception("Failed to open Connection to server");
			}
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong :( , " + e.toString());
		}
		return response;
	}
}
	

