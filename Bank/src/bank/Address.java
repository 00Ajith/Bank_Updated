package bank;

public class Address {
	private String doorNumber;
	private String street;
	private String city;
	private String pinCode;

	public Address(String doorNumber, String street, String city,String pinCode) {
		this.doorNumber = doorNumber;
		this.street = street;
		this.city = city;
		this.pinCode = pinCode;
	}

	public void display() {
		System.out.println("Address:");
		System.out.println(this.doorNumber + " , " + this.street);
		System.out.println(this.city );
		System.out.println(this.pinCode);
		System.out.println();
	}

}
