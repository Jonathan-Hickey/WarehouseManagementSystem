package server;


import coreclasses.database.Database;
import coreclasses.database.I_Database;
import interceptor.FactoryDispatcher;


public class RunServer {

	public static void main(String args[])throws Exception  {
		
		int portOne = 9091;
		int portTwo = 9092;
		int portThree = 9093;
		int portFour = 9094;
		
		I_Database database = Database.getInstance();
		
		InterceptorDispatcher dispatcher = FactoryDispatcher.makeDispatcher();
		
		Server serverOne =  new Server(portOne,database, dispatcher);
		Server serverTwo =  new Server(portTwo,database, dispatcher);
		Server serverThree =  new Server(portThree,database, dispatcher);
		Server serverFour =  new Server(portFour,database, dispatcher);
		
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
