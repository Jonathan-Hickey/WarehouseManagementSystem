package coreClasses;

public class UserFactory implements I_UserFactory {

	public UserFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User makeUser(int type, int userID, String firstName, String surname, String email, String phone, String password)
	{
		
		switch(type)
		{
			case 1: return new Picker(userID, firstName, surname, email, phone, password);
			case 2: return new Packer(userID, firstName, surname, email, phone, password);
			case 3: return new Manager(userID, firstName, surname, email, phone, password);
			case 4: return new Stocker(userID, firstName, surname, email, phone, password);
			default: return null;
		}
	}

}
