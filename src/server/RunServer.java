package server;

import database.Database;
import database.I_Database;


public class RunServer {

	public static void main(String args[])throws Exception  {
		
		int portOne = 9091;
		int portTwo = 9092;
		int portThree = 9093;
		int portFour = 9094;
		
		I_Database database = Database.getInstance();
		
		Server serverOne =  new Server(portOne,database);
		Server serverTwo =  new Server(portTwo,database);
		Server serverThree =  new Server(portThree,database);
		Server serverFour =  new Server(portFour,database);
		
		Thread t1 = new Thread(serverOne);
		Thread t2 = new Thread(serverTwo);
		Thread t3 = new Thread(serverThree);
		Thread t4 = new Thread(serverFour);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
