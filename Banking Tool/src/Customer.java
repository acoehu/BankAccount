public class Customer {
	private String name;
	private String SSN;
	private String email;
	public int numberOfAccount;
	public BankAccount account[];
	
	public Customer(String name, String sSN, String email) {
		super();
		this.setName(name);
		this.SSN = sSN;
		this.setEmail(email);
		this.account = new BankAccount[3];
		this.numberOfAccount = 0;
	}
	public void increaseAccount() {
		this.numberOfAccount++;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		this.SSN = sSN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
