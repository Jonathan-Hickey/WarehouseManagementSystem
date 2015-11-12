package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import coreclasses.database.Database;
import coreclasses.users.User;
import coreclasses.users.UserFactory;

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
