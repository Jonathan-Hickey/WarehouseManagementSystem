package databaseCases;


import org.junit.Test;


import coreclasses.cubby.I_Cubby;
import coreclasses.database.Database;

public class ThreadedDB {

	@Test
	public void test() {
		
		Database db = Database.getInstance();
		
		Thread t1 = new myThread(db);
		
//		Thread t2 = new myThread(db);
		
		t1.start();
	//	t2.start();
	}

}
class myThread extends Thread
{
	private Database db;
	public myThread(Database db) {
		super();
		this.db = db;
	}
	@Override
	public void run(){
		for(int i=0; i<5; i++){
			I_Cubby c = db.getCubby(i);
			System.out.println("Thread 1 cubby = ");
		}
	}
	
}
