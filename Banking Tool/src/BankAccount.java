
public class BankAccount {
	private String accountNumber;
	public int balance,type;
	public BankAccount(String accountNumber, int balance, int type) {
		super();
		this.setAccountNumber(accountNumber);
		this.balance = balance;
		this.type = type;
	}

	//@Override
	public String getType() {
		if (type == 1) return "Checking";
		else return "Saving";
	}

	//@Override
	public void deposit(int money) {
		this.balance += money;
	}

	//@Override
	public void withdraw(int money) {
		this.balance -= money;
		
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
