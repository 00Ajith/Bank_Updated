package users;


public class Admin{
	private String name;
	private String userName;
	private String password;

	public Admin(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
}
