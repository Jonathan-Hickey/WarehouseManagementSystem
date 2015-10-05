package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import coreClasses.User;
import database.Database;

public class CreateUserNagativeUser {

	@Test
	public void test() {
	
		Database db =  Database.getInstance();
		
		User user = db.createUser(-1000, "", "", "", "", "");
		assertEquals(true, user==null );
		
		user = db.createUser(1000, "", "", "", "", "");
		assertEquals(true, user==null );
		
	
	}

}
