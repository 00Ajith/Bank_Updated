package users;

import bank.Account;
import bank.Address;
import drivers.InputDriver;

public class User {
	private String name;
	private String mobile;
	private Address address;
	private String password;
	private Account account;

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public Address getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void createAccount() {
		name = InputDriver.getInstance().getName();
		mobile = InputDriver.getInstance().getMobile();
		address = InputDriver.getInstance().getAddress();
	}

}
