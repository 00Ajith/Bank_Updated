package bank;
import java.time.LocalDateTime;
import java.util.*;
public class Account{
	private final String ACCOUNT_NUMBER;
	private float balance;
	private float minimumBalance;
    private float transferLimit;
    private float usedTransferlimit;
    private LocalDateTime lastReload;
    private List<Transaction> transaction_list=new LinkedList<Transaction>();
	
    public Account(String accountNumber,float minimumBalance,float transferLimit) {
		this.ACCOUNT_NUMBER = accountNumber;
		this.minimumBalance=minimumBalance;
		this.usedTransferlimit=0;
		this.transferLimit=transferLimit;
		this.lastReload=LocalDateTime.now(); 
	}

	public List<Transaction> getTransaction() {
		return transaction_list;
	}
	public void addTransaction(Transaction transaction)
	{
		this.transaction_list.add(transaction);
	}
	
	public float getBalance() {
		return balance;
	}

	public float getMinimumBalance() {
		return minimumBalance;
	}

	public float getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(float transferLimit) {
		this.transferLimit = transferLimit;
	}

	public float getUsedTransferlimit() {
		return usedTransferlimit;
	}

	public void setUsedTransferlimit(float usedTransferlimit) {
		this.usedTransferlimit = usedTransferlimit;
	}

	public String getAccountNumber() {
		return ACCOUNT_NUMBER;
	}
	
    public void deposit(float ammount,Transaction transaction)
    {
        balance+=ammount;
        this.transaction_list.add(transaction);
    }
    public void withdraw(float amount,Transaction transaction)
    {
    	balance-=amount;
    	this.transaction_list.add(transaction);
    }
    
    public void relodeLimit()
	{
		 LocalDateTime now=LocalDateTime.now(); 
		 int diffrence=now.compareTo(lastReload);
		 if(diffrence!=0)
		 {
			lastReload=now;
			this.setUsedTransferlimit(0);
		 }
	}
	
}

