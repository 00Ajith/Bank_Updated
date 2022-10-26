package bank;
import java.time.LocalDateTime;

public class Transaction {
	private LocalDateTime date;
	private String details;
	private float amount;
	private float balance;
	public LocalDateTime getDate() {
		return date;
	}
	
	public Transaction(String details, float amount, float balance) {
		this.date= LocalDateTime.now(); 
		this.details = details;
		this.amount = amount;
		this.balance = balance;
	}
	public String getDetails() {
		return details;
	}
	public float getAmount() {
		return amount;
	}
	public float getBalance() {
		return balance;
	}
	
	
}
