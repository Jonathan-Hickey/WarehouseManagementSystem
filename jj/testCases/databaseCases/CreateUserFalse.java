package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import coreClasses.User;
import coreClasses.UserFactory;
import database.Database;

public class CreateUserFalse {

	@Test
	public void test() {
		
		Database db = Database.getInstance();
		UserFactory factory = new UserFactory();
		User user = factory.makeUser(1 , 10,"" ,"", "", "", "");
		User user1 = db.createUser(10, "", "", "", "", "");
		
		assertEquals(false, user.equals(user1) );

	}

}
