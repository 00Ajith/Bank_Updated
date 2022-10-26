package drivers;

public class MainDriver {
	private static MainDriver mainDriver = new MainDriver();

	private MainDriver() {

	}

	public static MainDriver getInstance() {
		return mainDriver;
	}

	public void mainMenu() {
		System.out.println();
		System.out.println("          Z-Bank       ");
		System.out.println("------------------------------------");
		System.out.println("1)-> Admin Login");
		System.out.println("2)-> User Login");
		System.out.println("3)-> Create New Account");
		System.out.println("4)-> Check Status");
		System.out.println("5)-> Exit");
		System.out.println("------------------------------------");
	}

	public void loadMain() {
		while (true) {
			mainMenu();
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
				AdminDriver driver = AdminDriver.getInstance();
				driver.adminLoginMenu(1);
				break;
			}
			case 2: {
				UserDriver driver = UserDriver.getInstance();
				driver.userLoginMenu(1);
				break;
			}
			case 3: {
				CreateAccountDriver driver = CreateAccountDriver.getInstance();
				driver.createAccountMenu();
				break;
			}
			case 4: {
				CheckStatusDriver driver = CheckStatusDriver.getInstance();
				driver.checkStatus();
				break;
			}
			case 5: {
				System.out.println("Exit...!");
				System.exit(0);
			}
			}
		}

	}
}
