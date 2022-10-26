package database;

import java.util.HashMap;

import bank.Request;
import users.Admin;
import users.User;

public class BankDatabase {
	private HashMap<Long, Request> request_list = new HashMap<Long, Request>(); // list for store Account requests
	private HashMap<String, User> customer_list = new HashMap<String, User>();
	private final String bankCode = "1234";
	private long RequestId = 12345;
	private long accountNumber = 1000;
	private Admin admin = new Admin("Admin", "1", "1");

	private static BankDatabase bankDB = new BankDatabase();

	private BankDatabase() {

	}

	public static BankDatabase getInstance() {
		return bankDB;
	}

	public HashMap<Long, Request> getRequest_list() {
		return request_list;
	}

	public HashMap<String, User> getCustomer_list() {
		return customer_list;
	}

	public long getRequestId() {
		return RequestId;
	}

	public void setRequestId(long requestId) {
		RequestId = requestId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Admin getAdmin() {
		return admin;
	}

	public String getBankCode() {
		return bankCode;
	}

}
