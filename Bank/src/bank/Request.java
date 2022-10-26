package bank;

import users.User;

public class Request {
	private long requestId;
	private User user;
	private char status;
	public Request(long requestId, User user) {
		this.requestId = requestId;
		this.user = user;
		this.status = 'P';
	}
	public long getRequestId() {
		return requestId;
	}
	public User getUser() {
		return user;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
