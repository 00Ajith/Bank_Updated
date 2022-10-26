package drivers;

import bank.Request;
import database.BankDatabase;
import users.User;

public class CheckStatusDriver {
	private static CheckStatusDriver checkStatusDriver = new CheckStatusDriver();

	private CheckStatusDriver() {

	}

	public static CheckStatusDriver getInstance() {
		return checkStatusDriver;
	}

	public void checkStatus() {
		BankDatabase DB = BankDatabase.getInstance();
		System.out.println("Enter RequestId");
		long requestId = InputDriver.getInstance().getLongInput();
		if (DB.getRequest_list().containsKey(requestId)) {
			Request request = DB.getRequest_list().get(requestId);
			if (request.getStatus() == 'P') {
				System.out.println("Your Request Under Processing");
			} else if (request.getStatus() == 'R') {
				System.out.println("Your Request Rejected");
				DB.getRequest_list().remove(requestId);
			} else {
				System.out.println("Your Request Approved");
				System.out.println("A/c:  " + request.getUser().getAccount().getAccountNumber());
				User user = DB.getCustomer_list().get(request.getUser().getAccount().getAccountNumber());
				System.out.println();
				UserDriver.getInstance().setPassword(user);
				DB.getRequest_list().remove(requestId);
			}
		} else {
			System.out.println("Request Id Not Found..!");
		}

	}

}
