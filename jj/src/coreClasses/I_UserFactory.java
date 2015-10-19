package coreClasses;

public interface I_UserFactory {

	public User makeUser(int type, int userID, String firstName, String surname, String email, String phone, String password);
}
