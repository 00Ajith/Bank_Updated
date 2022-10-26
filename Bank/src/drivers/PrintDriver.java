package drivers;

public class PrintDriver {
	private static PrintDriver printDriver = new PrintDriver();

	private PrintDriver() {

	}

	public static PrintDriver getInstance() {
		return printDriver;
	}

	void enterOnlyNumberMessage() {
		System.err.println("ENTER ONLY NUMBER..!");
	}

	void enterGivenChoiceOnlyMessage() {
		System.err.println("ENTER ONLY GIVEN CHOICES..!");
	}
	void autheticationFaildMessage()
	{
		System.err.println("AUTHENTICATION FAILED..! ENTER VALID USERNAME AND PASSWORD");
	}

}
