package users;

import drivers.InputDriver;

public class CurrentAccountUser extends User {
	private String gstNumber;

	public CurrentAccountUser() {
		super.createAccount();
		this.craeteAccount();
	}

	public String getGst() {
		return gstNumber;
	}

	public void craeteAccount() {
		gstNumber = InputDriver.getInstance().getGstNumber();
	}

}
