package Authentication;

import database.BankDatabase;

public class AdminEntry implements Authenticat{
	public boolean authentication(String userName,String password)
	{
	  BankDatabase DB=BankDatabase.getInstance();
	  if(userName.equals(DB.getAdmin().getUsername())&&password.equals(DB.getAdmin().getPassword()))
		  return true;
	  else
		  return false;
	}

}  
