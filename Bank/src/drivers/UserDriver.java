package drivers;

import java.util.Scanner;
import Authentication.Authenticat;
import Authentication.UserEntry;
import bank.BankMain;
import bank.FundTransfer;
import bank.Transaction;
import database.BankDatabase;
import users.User;

public class UserDriver implements FundTransfer{

	private static UserDriver userDriver = new UserDriver();
	Scanner in = new Scanner(System.in);

	private UserDriver() {

	}

	public static UserDriver getInstance() {
		return userDriver;
	}

	public void userLoginMenu(int attempt) {
		BankDatabase DB = BankDatabase.getInstance();
		System.out.println();
		System.out.println("------------User Login--------------");
		System.out.println("UserName: ");
		String userName = in.next();
		System.out.println("Password:");
		String password = in.next();
		System.out.println("------------------------------------");
		System.out.println();
		Authenticat auth = new UserEntry();
		if (auth.authentication(userName, password)) {
			userMenu(DB.getCustomer_list().get(userName));
		} else if (attempt <= 2) {
			PrintDriver.getInstance().autheticationFaildMessage();
			userLoginMenu(++attempt);
		} else {
			return;
		}
	}

	public void displayBalance(User user) {
		System.out.println("------------------------------------");
		System.out.println("AccountBalance -> " + user.getAccount().getBalance());
		System.out.println("------------------------------------");
	}

	public void setPassword(User user) {
		System.out.println("Set new Password ->");
		System.out.println("Enter Password");
		String password = in.next();
		if (UtilityDriver.getInstance().passwordValidation(password)) {
			user.setPassword(password);
			System.out.println("Password Setted Successfully");
		} else {
			System.out.println("Password validation faild");
			setPassword(user);
		}
	}

	public void fundTransfer(User user) {
		System.out.println("------------------------------------");
		System.out.println("Enter Reciver AccountNumber:");
		String reciverAccountNumber = in.next();
		User reciver = UtilityDriver.getInstance().getCustomer(reciverAccountNumber);
		if (reciver != null) {
			System.out.println("Enter Amount :");
			float amount = InputDriver.getInstance().getPositiveNumber();

			if ((user.getAccount().getTransferLimit() - user.getAccount().getUsedTransferlimit()) >= amount) {
				if (user.getAccount().getBalance() + user.getAccount().getMinimumBalance() >= amount) {
					String senderTransactionDetaile = BankMain.transactionType.TRANSFER.toString() + "-TO "
							+ reciver.getName();
					Transaction sendrTransaction = new Transaction(senderTransactionDetaile, amount,
							user.getAccount().getBalance() - amount);
					user.getAccount().withdraw(amount, sendrTransaction);
					user.getAccount().setUsedTransferlimit(user.getAccount().getUsedTransferlimit() + amount);

					String reciverTransactionDetaile = BankMain.transactionType.TRANSFER.toString() + "-FROM "
							+ user.getName();
					Transaction reciverTransaction = new Transaction(reciverTransactionDetaile, amount,
							user.getAccount().getBalance() + amount);
					reciver.getAccount().deposit(amount, reciverTransaction);
					System.out.println("Amount Transfered Sucessfully...!");
				} else {
					System.out.println("Insufficient Account Balance ");
				}
			} else {
				System.out.println("Transfer Limit Exceed...");
				System.out.println("Avilable Limit : "+user.getAccount().getUsedTransferlimit());
			}
		} else {
			System.out.println("Account Not Found ");
		}
		System.out.println("------------------------------------");

	}

	public void userMenu(User user) {
		System.out.println("------------User Menu-------------");
		System.out.println("welcome -> " + user.getName());
		System.out.println();
		System.out.println("1)-> Check Balance");
		System.out.println("2)-> Fund Transfer");
		System.out.println("3)-> Transaction History");
		System.out.println("4)-> Logout");
		System.out.println("------------------------------------");
		byte choice;
		while (true) {
			choice = InputDriver.getInstance().getByteInput();
			if (choice > 0 && choice < 6)
				break;
			else
				PrintDriver.getInstance().enterGivenChoiceOnlyMessage();
		}
		switch (choice) {
		case 1: {
			displayBalance(user);
			break;
		}
		case 2: {
			fundTransfer(user);
			break;
		}
		case 3: {
			UtilityDriver.getInstance().displayTransaction(user);
			break;
		}
		case 4: {
			System.out.println("Log Out..!");
			return;
		}
		}
		userMenu(user);
	}

}
