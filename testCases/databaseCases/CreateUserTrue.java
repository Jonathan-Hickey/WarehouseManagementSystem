package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import coreclasses.database.Database;
import coreclasses.users.User;
import coreclasses.users.UserFactory;

public class CreateUserTrue {

	@Test
	public void test() 
	{
		Database db =  Database.getInstance();
		UserFactory factory = new UserFactory();
		User user = factory.makeUser(1 , 9,"" ,"", "", "", "");
		User user1 = db.createUser(1, "", "", "", "", "");
		
		assertEquals(true, user.equals(user1) && db.getUser(9).equals(user1));
	
	}

}
