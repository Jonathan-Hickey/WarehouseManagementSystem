package databaseCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateUserFalse.class, CreateUserNagativeUser.class,
		CreateUserTrue.class, GetUserFalse.class, GetUserTrue.class,
		IsValidLogInFalse.class, IsValidLogInNULL.class, IsValidLogInTrue.class })
public class AllDatabaseTests {

}
