package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import database.Database;

public class IsValidLogInFalse {

	@Test
	public void test() 
	{
		
		Database db =  Database.getInstance();
		assertEquals(false, db.isValidLogin("NotAUser", "NotAPassWord"));
		assertEquals(false, db.isValidLogin("picker", "NotAPassWord"));
		assertEquals(false, db.isValidLogin("NotAUser", "1234"));
		
	}

}
