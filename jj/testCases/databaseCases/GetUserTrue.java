package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import coreClasses.User;
import coreClasses.UserFactory;
import database.Database;

public class GetUserTrue {

	@Test
	public void test() 
	{
		
		Database db =  Database.getInstance();
		UserFactory factory = new UserFactory();
		User user = factory.makeUser(1 , 4,"" ,"", "", "", "");
		User user1 = db.getUser(4);
		
		assertEquals(true, user.equals(user1));
	
		
	}

}
