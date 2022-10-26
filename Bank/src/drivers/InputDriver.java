package drivers;

import java.util.InputMismatchException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bank.Address;

public class InputDriver {
	Scanner in = new Scanner(System.in);
	private static InputDriver inputDriver = new InputDriver();

	private InputDriver() {

	}

	public static InputDriver getInstance() {
		return inputDriver;
	}

	public String getName() {
		System.out.println("Enter Name");
		String name = in.nextLine();
		return name;
	}

	public String getMobile() {
		System.out.println("Enter Mobile");
		String mobile = in.nextLine();
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Matcher matcher = pattern.matcher(mobile);
		if (!matcher.matches()) {
			System.out.println("Enter 10 digit MobileNumber");
			mobile = getMobile();
		}
		return mobile;
	}

	public String getAaddharNumber() {
		System.out.println("Enter Aaddhar");
		String aaddhar = in.next();
		Pattern pattern = Pattern.compile("^\\d{8}$");
		Matcher matcher = pattern.matcher(aaddhar);
		if (!matcher.matches()) {
			System.out.println("Enter 8 Digit AadharNumber");
			aaddhar = getAaddharNumber();
		}
		return aaddhar;
	}

	public String getGstNumber() {
		System.out.println("Enter GST Number");
		return in.nextLine();
	}

	public Address getAddress() {
		String doorNumber, street, city, pinCode;
		System.out.println("Address");
		System.out.println("Enter doorNumber");
		doorNumber = in.nextLine();
		System.out.println("Enter Street");
		street = in.nextLine();
		System.out.println("Enter City");
		city = in.nextLine();
		System.out.println("Enter PionCode");
		pinCode = in.nextLine();
		return new Address(doorNumber, street, city, pinCode);
	}

	public byte getByteInput() {
		byte choice;
		try {
			System.out.println("Enter choice: ");
			choice = in.nextByte();
			in.nextLine();
			return choice;
		} catch (InputMismatchException e) {
			in.nextLine();
			PrintDriver.getInstance().enterOnlyNumberMessage();
			return getByteInput();
		} catch (Exception e) {
			in.nextLine();
			System.err.println(e.getMessage());
			return getByteInput();
		}
	}

	public long getLongInput() {
		long input;
		try {
			input = in.nextLong();
			in.nextLine();
			return input;
		} catch (InputMismatchException e) {
			in.nextLine();
			PrintDriver.getInstance().enterOnlyNumberMessage();
			return getLongInput();
		} catch (Exception e) {
			in.nextLine();
			System.err.println(e.getMessage());
			return getByteInput();
		}
	}

	public float getPositiveNumber() {
		float input;
		try {
			input = in.nextFloat();
			in.nextLine();
			return input;
		} catch (InputMismatchException e) {
			in.nextLine();
			System.err.println("ENTER ONLY POSSITIVE NUMBERS");
			return getPositiveNumber();
		} catch (Exception e) {
			in.nextLine();
			System.err.println(e.getMessage());
			return getByteInput();
		}
	}

}
