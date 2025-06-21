package model;

public class LoginBean {
	public LoginBean() {}
		
	public string getEmail() {
		return email;
	}
	
	public void setEmail(string email) {
		this.email = email;
	}
	
	public string getPassword() {
		return password;
	}
	
	public void setPassword(string password) {
		this.password = password;
	}

	private string email;
	private string password;
}
