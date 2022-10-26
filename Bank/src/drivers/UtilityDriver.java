package drivers;

import bank.Transaction;
import database.BankDatabase;
import users.User;

public class UtilityDriver {

	private static UtilityDriver utilityDriver = new UtilityDriver();

	private UtilityDriver() {

	}

	public static UtilityDriver getInstance() {
		return utilityDriver;
	}

	public boolean passwordValidation(String password) {
		if (password.length() >= 6 && password.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	public User getCustomer(String accountNumber) {
		BankDatabase DB = BankDatabase.getInstance();
		User user = DB.getCustomer_list().get(accountNumber);
		return user;
	}

	public void displayTransaction(User user) {
		System.out.println("A/C:" + user.getAccount().getAccountNumber());
		System.out
				.println("----------------------------------------------------------------------------------------------");
		for (Transaction transaction : user.getAccount().getTransaction()) {
			System.out.println(transaction.getDate() + "\t" + transaction.getDetails() + "\t\t\t"
					+ transaction.getAmount() + "\t\t\t" + transaction.getBalance());
		}
		System.out
				.println("----------------------------------------------------------------------------------------------");
	}

	public String newAccountNumber() {
		BankDatabase DB = BankDatabase.getInstance();
		String newAccountNumber = (DB.getBankCode()) + Long.toString(DB.getAccountNumber());
		DB.setAccountNumber(DB.getAccountNumber() + 1);
		return newAccountNumber;
	}

}
