package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import database.Database;

public class IsValidLogInNULL {

	@Test
	public void test() 
	{

		Database db =  Database.getInstance();
		assertEquals(false, db.isValidLogin(null, null));
		
	}

}
