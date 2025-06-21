package model;

public interface UserDAO {
	//Creazione dell'utente è già a carico della RegistrationDS
	String getNameByMail(String email);
	String getSurnameByMail(String email);
	String getTypeByMail(String email);
	void removeUserByMail(String email);
}
