import java.util.*;
import java.io.*;
public class Banking{
	static Vector<Customer> customerList = new Vector<Customer>();
	static Scanner input = new Scanner(System.in);
	public static void main(String args[]) throws IOException {
		getOldData();	//getting customer informations in Customers.txt
		Helper.printDisplay();	//displaying the default screen
		while (true) {
			System.out.print("Choose the option you want: ");
			int choice = input.nextInt();
			if (choice == 8) break;
			switch (choice) {
				case 1: option1();
						break;
				case 2: option2();
						break;
				case 3: option3();
						break;
				case 4: option4();
						break;
				case 5: option5();
						break;
				case 6: option6();
						break;
				case 7: option7();
						break;
			}
			update();	//update information into Customers.txt
		}
		
		input.close();
	}
	public static void getOldData() throws IOException {
		BufferedReader iStream = new BufferedReader(new FileReader("Customers.txt"));
		while (true) {
			String string = iStream.readLine();
			System.out.println(string);
			if (string == null) break; //done reading the file
			else if ((string.equals(""))) {
				continue;
			}
			else {//Reading the file and transfer the information into data
				if (string.startsWith("Name: ")) {
					String name = string.substring(6);
					customerList.add(new Customer(name, "",""));
				}
				else if (string.startsWith("SSN: ")) {
					String SSN = string.substring(5);
					customerList.get(customerList.size()-1).setSSN(SSN);
				}
				else if (string.startsWith("Email: ")) {
					String email = string.substring(7);
					customerList.get(customerList.size()-1).setEmail(email);
				}
				else if (string.startsWith("AccountNo: ")) {
					customerList.get(customerList.size()-1).increaseAccount();
					String ID = string.substring(11);
					customerList.get(customerList.size()-1)
					.account[customerList.get(customerList.size()-1).numberOfAccount-1] = new BankAccount(ID, 0, 0);
				}
				else if (string.startsWith("Balance: ")) {
					int balance = Integer.parseInt(string.substring(9));
					customerList.get(customerList.size()-1)
					.account[customerList.get(customerList.size()-1).numberOfAccount-1].balance = balance;
				}
				else if (string.startsWith("TypeofAccount: ")) {
					String type = string.substring(15);
					int typeNum;
					if (type.equals("Checking")) typeNum = 1;
					else typeNum = 2;
					customerList.get(customerList.size()-1)
					.account[customerList.get(customerList.size()-1).numberOfAccount-1].type = typeNum;
				}
				
				
			}
			
		}
		iStream.close();	
	}
	
	public static void update() throws IOException {//updating information into Customers.txt
		FileWriter oStream = new FileWriter("Customers.txt");
		for(Customer customer: customerList){
			oStream.write("Name: " + customer.getName() + "\n");
			oStream.write("SSN: " + customer.getSSN() + "\n");
			oStream.write("Email: " + customer.getEmail() + "\n");
			oStream.write("NAccounts: " + customer.numberOfAccount + "\n");
			for(BankAccount account: customer.account) {
				oStream.write("AccountNo: " + account.getAccountNumber() + "\n");
				oStream.write("Balance: " + account.balance + "\n");
				oStream.write("TypeofAccount: " + account.getType() + "\n");
				
			oStream.write("\n");
			}
		}
		oStream.write("\n");
		oStream.close();
		
	}

	public static void option7() {	//printing an user's data
		input.nextLine();
		System.out.print("Enter user SSN: ");	String SSN = input.nextLine();
		boolean accountExist = false;
		for(Customer customer: customerList) {
			if (customer.getSSN().equals(SSN)) {
				accountExist = true;
				System.out.println("Displaying customer data..");
				System.out.println("Name: "+ customer.getName());
				System.out.println("SSN: " + customer.getSSN());
				System.out.println("Email: " + customer.getEmail());
				System.out.println("NAccounts: " + customer.numberOfAccount);
				for(BankAccount account: customer.account) { 
					System.out.println(account.getAccountNumber());
					System.out.println(account.getType());
					System.out.println("$" + account.balance);
				}
				
			}
			if (!accountExist) {
				System.out.println("Error, SSN is incorrect.");
			}
		}
	}
	
	public static void option6() {	//print all user with more than $1000 in Checking
		for(Customer customer: customerList) {
			
			for(BankAccount account: customer.account) { 
				if ((account.balance > 1000) && (account.type == 1)) {
					System.out.println(customer.getName());
					break;	
				}
			}
		}	
	}
	
	public static void option5() {	//print all user with >$1000 Saving acocunt
		for(Customer customer: customerList) {
			for(BankAccount account: customer.account) { 
				if ((account.balance > 1000) && (account.type == 2)) {
					System.out.println(customer.getName());
					break;	
				}
			}
		}	
	}
	
	public static void option4() { //withdraw money
		input.nextLine();
		System.out.print("Enter user account: ");	String ID = input.nextLine();
		
		//a run-through checks the account datas
		boolean IDExist = false;
		Customer rightCustomer = new Customer("","","");
		int theOne = 0;
		int counting = -1;
		for(Customer customer: customerList) {
			if (IDExist) break;
			for(BankAccount account: customer.account) {
				counting++;
				if (account.getAccountNumber().equals(ID)) {
					IDExist = true;
					theOne = counting;
					rightCustomer = customer;
					break;
				}
			}
		}
		//Checking if inputs is appropriate 
		if (!IDExist) {
			System.out.println("Your account ID is incorrect");
			return;
		}
		System.out.print("Enter the amount you want to withdraw: ");	int money = input.nextInt();
		if (money > rightCustomer.account[theOne].balance) {
			System.out.println("Your account does not have enough cash to withdraw!");
			return;
		}
		//update the money
		rightCustomer.account[theOne].withdraw(money);
		System.out.println("Account is updated sucessfully!");
	}
	
	public static void option3() { //deposit money
		input.nextLine();
		System.out.print("Enter user account: ");	String ID = input.nextLine();
		//a run-through check the data if the input is appropriate
		boolean IDExist = false;
		Customer rightCustomer = new Customer("","","");
		int theOne = 0;
		int counting =-1;
		for(Customer customer: customerList) {
			if (IDExist) break;
			for(BankAccount account: customer.account) { 
				counting++;
				if (account.getAccountNumber().equals(ID)) {
					IDExist = true;
					theOne = counting;
					rightCustomer = customer;
					break;
				}
			}
		}
		if (!IDExist) {
			System.out.println("Your account ID is incorrect");
			return;
		}
		//updating and outputing
		System.out.print("Enter the amount you want to deposit: ");
		rightCustomer.account[theOne].deposit(input.nextInt());
		System.out.println("Account is updated sucessfully!");
	}

	public static void option2() {
		input.nextLine();
		System.out.print("Enter user SSN: "); String SSN = input.nextLine();
		System.out.print("Enter user account: ");	String ID = input.nextLine();
		System.out.print("Enter type of the account (1: Checking, 2: Saving): ");	
		int type = input.nextInt();
		//run-through check
		boolean SSNExist = false;
		boolean IDExist = false;
		int theOne = 0;
		int counting = -1;
		for(Customer customer: customerList) {
			counting++;
			if (IDExist) break;
			if (customer.getSSN().equals(SSN)) {
				SSNExist = true;
				theOne = counting;
			}
			for(BankAccount account: customer.account) {
				if (account.getAccountNumber().equals(ID)) {
					IDExist = true;
					break;
				}
			}
		}
		if (!SSNExist) {
			System.out.println("Invalid SSN. Please check again or create an user if you haven't created one");
			return;
		}
		if (IDExist) {
			System.out.println("User ID already exist, please choose a different ID");
			return;
		}
		//update the account
		Customer customer = customerList.get(theOne);
		if (customer.numberOfAccount == 3) {
			System.out.println("Exceeded account limit");
			return;
		}
		customerList.get(theOne).increaseAccount();
		customerList.get(theOne).account[customerList.get(theOne).numberOfAccount-1]= new BankAccount(ID, 0, type);
		System.out.println("Account is created for user " + customer.getName() + "!");
		
	}	
	public static void option1() {
		input.nextLine();
		System.out.print("Enter user name: ");		String name = input.nextLine();
		System.out.print("Enter user SSN: ");		String SSN = input.nextLine();
		//checking 
		boolean alreadyExist = false;
		for(Customer customer: customerList) {
			if (customer.getSSN().equals(SSN)) {
				alreadyExist = true;
				break;
			}
		}
		if (alreadyExist) {
			System.out.println("You already have an account!");
			return;
		}
		//adding a new user
		System.out.print("Enter user email: ");	String email = input.nextLine();
		Customer costumer = new Customer(name ,SSN, email);
		customerList.add(costumer);	
		System.out.println("User is created succesfully!");
	}
}