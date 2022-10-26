package users;

import drivers.InputDriver;

public class SavingAccountUser extends User {
	private String aaddhar;

	public SavingAccountUser() {
		super.createAccount();
		this.craeteAccount();
	}

	public String getAaddhar() {
		return aaddhar;
	}

	public void craeteAccount() {
		aaddhar = InputDriver.getInstance().getAaddharNumber();
	}

}
