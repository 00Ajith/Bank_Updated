package bank;

import drivers.MainDriver;

public class BankMain {
	public static enum accountType {
		SAVING_ACCOUNT, CURRENT_ACCOUNT
	}

	public static enum transactionType {
		CREDIT, DEBIT, TRANSFER
	}

	public static void main(String[] args) { // main Method

		MainDriver driver = MainDriver.getInstance();
		driver.loadMain();

	}

}
