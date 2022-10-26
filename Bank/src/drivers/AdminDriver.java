package drivers;

import java.util.*;

import Authentication.AdminEntry;
import Authentication.Authenticat;
import bank.Account;
import bank.BankMain;
import bank.Banking;
import bank.CurrentAccount;
import bank.Request;
import bank.SavingAccount;
import bank.Transaction;
import database.BankDatabase;
import users.CurrentAccountUser;
import users.SavingAccountUser;
import users.User;

public class AdminDriver implements Banking {
	private static AdminDriver adminDriver = new AdminDriver();
	Scanner in = new Scanner(System.in);

	private AdminDriver() {

	}

	public static AdminDriver getInstance() {
		return adminDriver;
	}

	public void adminLoginMenu(int attempt) {
		System.out.println();
		System.out.println("------------Admin Login--------------");
		System.out.println("UserName: ");
		String userName = in.next();
		System.out.println("Password:");
		String password = in.next();
		System.out.println("-------------------------------------");
		System.out.println();
		Authenticat auth = new AdminEntry();
		if (auth.authentication(userName, password)) {
			adminMenu();
		} else if (attempt <= 2) {
			System.out.println("invalid login credentials");
			adminLoginMenu(++attempt);
		} else {
			System.err.println("invalid login credentials");
			return;
		}

	}

	public void adminMenu() {
		System.out.println("------------Admin Menu-------------");
		System.out.println("1)-> View Requests");
		System.out.println("2)-> Withdraw");
		System.out.println("3)-> Deposit");
		System.out.println("4)-> View User");
		System.out.println("5)-> Logout");
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
			viewRequestMenu();
			break;
		}
		case 2: {
			withdraw();
			break;
		}
		case 3: {
			deposit();
			break;
		}
		case 4: {
			viewUserMenu();
			break;
		}
		case 5: {
			System.out.println("LOG OUT..!");
			return;
		}
		}
		adminMenu();

	}

	public void viewRequestMenu() {
		if (displayAllsRequests()) {
			adminRequestOptionMenu();
		} else {
			System.out.println("No Request Found");
		}
	}

	public void viewUserMenu() {
		System.out.println("------------------------------------");
		System.out.println("Enter AccountNumber");
		String accountNumber = in.next();
		User user = UtilityDriver.getInstance().getCustomer(accountNumber);
		if (user != null) {
			System.out.println("Name: " + user.getName() + "\t" + "Mobile: " + user.getMobile());
			System.out.println("A/c Balance: " + user.getAccount().getBalance() + "\t");

			System.out.println();
			UtilityDriver.getInstance().displayTransaction(user);
			System.out.println();
		} else {
			System.err.println("Not Valid User");
		}
		System.out.println("------------------------------------");
	}

	public void displayRequest(Request request) {
		System.out
				.println("Name :" + request.getUser().getName() + "    " + "Mobile : " + request.getUser().getMobile());
		request.getUser().getAddress().display();
		if (request.getUser() instanceof SavingAccountUser) {
			System.out.println("Aaddhar : " + ((SavingAccountUser) request.getUser()).getAaddhar() + "\t\t"
					+ BankMain.accountType.SAVING_ACCOUNT);
		} else {
			System.out.println("Gst : " + ((CurrentAccountUser) request.getUser()).getGst() + "\t\t"
					+ BankMain.accountType.CURRENT_ACCOUNT);
		}
	}

	public void adminRequestOptionMenu() {
		System.out.println();
		System.out.println("1)-> Action");
		System.out.println("2)-> Go Back");
		byte choice;
		while (true) {
			choice = InputDriver.getInstance().getByteInput();
			if (choice > 0 && choice < 3)
				break;
			else
				PrintDriver.getInstance().enterGivenChoiceOnlyMessage();
		}
		switch (choice) {
		case 1: {
			adminRequestActionMenu();
			break;
		}
		case 2: {
			return;
		}
		}
		adminRequestOptionMenu();
	}

	public void adminRequestActionMenu() {
		BankDatabase DB = BankDatabase.getInstance();
		System.out.println("Enter Request Id ");
		long requestId = in.nextLong();
		Request request = DB.getRequest_list().get(requestId);
		if (request != null && request.getStatus() == 'P') {
			AdminDriver.getInstance().displayRequest(request);
			System.out.println("1)-> Approve");
			System.out.println("2)-> Reject");
			System.out.println("3)-> Go back");
			byte choice;
			while (true) {
				choice = InputDriver.getInstance().getByteInput();
				if (choice > 0 && choice < 4)
					break;
				else
					PrintDriver.getInstance().enterGivenChoiceOnlyMessage();
			}
			switch (choice) {
			case 1: {
				approveRequest(request);
				break;
			}
			case 2: {
				rejectRequest(request);
				break;
			}
			case 3: {
				return;
			}
			}

		} else {
			System.out.println("Request Id Not Found");
		}
	}

	public void approveRequest(Request request) {
		BankDatabase DB = BankDatabase.getInstance();

		String accountNumber = UtilityDriver.getInstance().newAccountNumber();
		Account account = new SavingAccount(accountNumber);
		if (request.getUser() instanceof SavingAccountUser) {
			account = new SavingAccount(accountNumber);
		} else {
			account = new CurrentAccount(accountNumber);
		}
		request.setStatus('A');
		request.getUser().setAccount(account);
		DB.getCustomer_list().put(accountNumber, request.getUser());
		System.out.println("Request id:"+request.getRequestId()+" Approved");
	}

	public void rejectRequest(Request request) {
		request.setStatus('R');
		System.out.println("Request id:"+request.getRequestId()+" Rejected");
	}

	public boolean displayAllsRequests() {
		int count = 0;
		BankDatabase DB = BankDatabase.getInstance();
		if (DB.getRequest_list().size() == 0) {
			return false;
		}
		for (Map.Entry<Long, Request> map : DB.getRequest_list().entrySet()) {
			Request request = map.getValue();
			if (request.getStatus() == 'P') {
				if (request.getUser() instanceof SavingAccountUser) {
					System.out.println(request.getRequestId() + "      " + request.getUser().getName() + "      "
							+ BankMain.accountType.SAVING_ACCOUNT);
				} else {
					System.out.println(request.getRequestId() + "      " + request.getUser().getName() + "      "
							+ BankMain.accountType.CURRENT_ACCOUNT);
				}
				count++;
			}
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	public void deposit() {

		System.out.println("Enter AccountNumber");
		String accountNumber = in.next();
		User user = UtilityDriver.getInstance().getCustomer(accountNumber);
		if (user != null) {
			System.out.println("Enter amount ");
			float amount = InputDriver.getInstance().getPositiveNumber();
			String transactionDetaile = BankMain.transactionType.CREDIT.toString() + "-BY BANK";
			Transaction transaction = new Transaction(transactionDetaile, amount,
					user.getAccount().getBalance() + amount);
			user.getAccount().deposit(amount, transaction);
			System.out.println("Amount deposited sucessfully...!");
		} else {
			System.out.println("Not valid User");
		}
	}

	public void withdraw() {
		System.out.println("Enter AccountNumber");
		String accountNumber = in.next();
		User user = UtilityDriver.getInstance().getCustomer(accountNumber);
		if (user != null) {
			System.out.println("Enter Amount");
			float amount = InputDriver.getInstance().getPositiveNumber();
			if (user.getAccount().getBalance() >= user.getAccount().getMinimumBalance() + amount) {
				String transactionDetaile = BankMain.transactionType.DEBIT.toString() + "-BY BANK";
				Transaction transaction = new Transaction(transactionDetaile, amount,
						user.getAccount().getBalance() - amount);
				user.getAccount().withdraw(amount, transaction);
				System.out.println("Amount Withdraw Sucessfully...!");
			} else {
				System.out.println("Insufficient Account Balance");
			}

		} else {
			System.out.println("Not Valid User");
		}
	}

}
