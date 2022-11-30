package application;

public class expenseFund extends mainAccount {
	
	double balance;
	String accountName;
	
	Fund[] myFunds;
	
	expenseFund(String name) {
		balance = 0;
		accountName = name;
	}
	
	expenseFund(String name, double amount) {
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

