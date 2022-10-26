package Authentication;

import database.BankDatabase;
import users.User;

public class UserEntry implements Authenticat{
	public boolean authentication(String userName,String password)
	{
		  BankDatabase DB=BankDatabase.getInstance();
	      User user=DB.getCustomer_list().get(userName);
		  if(user==null)
		  {
		      return false;
		  }
	      else if(userName.equals(user.getAccount().getAccountNumber())&&password.equals(user.getPassword()))
	      {
			  return true;
	      }
		  else
			  return false;
	}

}
