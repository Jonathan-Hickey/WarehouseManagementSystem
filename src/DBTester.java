import coreclasses.database.Database;
import coreclasses.database.I_Database;
import coreclasses.users.User;


public class DBTester {


	public static void main(String[] args) {
		
		I_Database db = Database.getInstance();
		
		myThread t1 = new myThread(db,1);
		
		myThreadtwo t2 = new myThreadtwo(db,2);
		
		t1.start();
		t2.start();
		System.out.println("Finished");
	}

}
class myThread extends Thread
{
	protected I_Database db;
	protected int t;
	public myThread(I_Database db, int t) {
		super();
		this.db = db;
		this.t =t;
	}
	
	@Override
	public void run(){
		for(int i=1; i<100; i++){

		db.getCubby(i).getID();
		}
		System.out.println("Thread " + t +": is finished");
	}
	
}

class myThreadtwo extends Thread
{
	protected I_Database db;
	protected int t;
	public myThreadtwo(I_Database db, int t) {
		super();
		this.db = db;
		this.t =t;
	}
	
	@Override
	public void run(){
		for(int i=1; i<10000; i++){ 
			db.createCubby(2).getID();
		}
		System.out.println("Thread " + t +": is finished");
	}
	
}


