package drivers;

import bank.Request;
import database.BankDatabase;
import users.CurrentAccountUser;
import users.SavingAccountUser;
import users.User;

public class CreateAccountDriver {

	private static CreateAccountDriver createAccountDriver = new CreateAccountDriver();

	private CreateAccountDriver() {

	}

	public static CreateAccountDriver getInstance() {
		return createAccountDriver;
	}

	public void createAccountMenu() {

		System.out.println("------------------------------------");
		System.out.println("1)-> SavingBank Account");
		System.out.println("2)-> CurrentBank Account");
		System.out.println("3)-> Go Back");
		System.out.println("------------------------------------");
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
			createSavingAccount();
			break;
		}
		case 2: {
			createCurrentAccount();
			break;
		}
		case 3: {
			return;
		}
		}
	}

	void createSavingAccount() {
		BankDatabase DB = BankDatabase.getInstance();
		User user = new SavingAccountUser();
		Request request = new Request(DB.getRequestId(), user);
		DB.setRequestId(DB.getRequestId() + 1);
		DB.getRequest_list().put(request.getRequestId(), request);
		System.out.println("REQUEST SUBMITED SUCESSFULLY");
		System.out.println("YOUR REQUEST ID: " + request.getRequestId());
	}

	void createCurrentAccount() {
		BankDatabase DB = BankDatabase.getInstance();
		User user = new CurrentAccountUser();
		Request request = new Request(DB.getRequestId(), user);
		DB.setRequestId(DB.getRequestId() + 1);
		DB.getRequest_list().put(request.getRequestId(), request);
		System.out.println("REQUEST SUBMITED SUCESSFULLY");
		System.out.println("YOUR REQUEST ID: " + request.getRequestId());
	}

}
