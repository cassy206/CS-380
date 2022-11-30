package application;

public class savingsAccount extends mainAccount {
	double balance;
	String accountName;
	
	savingsAccount(String name) {
		balance = 0;
		accountName = name;
	}
	
	savingsAccount(String name, double amount) {
		balance = amount;
		accountName = name;
	}
	
	void addToFund(double amount) {
		if(amount > 0) {
			balance += amount;
		}
	}
	
	void subtractFund(double amount) {
		if(amount > 0) {
			balance -= amount;
		}
	}
	
	void setName(String name) {
		accountName = name;
	}
}

