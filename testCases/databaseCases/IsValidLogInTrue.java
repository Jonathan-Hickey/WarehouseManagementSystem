package databaseCases;

import static org.junit.Assert.*;

import org.junit.Test;

import database.Database;

public class IsValidLogInTrue {


	
	@Test
	public void test() 
	{

		Database db =  Database.getInstance();
		assertEquals(true, db.isValidLogin("picker", "1234"));
		
	}

}
