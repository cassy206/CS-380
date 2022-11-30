package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class Fund {
	public Double balance;
	public String fundName;
	
	
	public Fund() {
		balance = 0.00;
		//balance.set(0.00);
	}
	
	public Fund(double amount){
		balance = amount;
		//balance.set(amount);
	}
	
	public Fund(String name, double amount){
		balance = amount;
		this.fundName = name;
		//fundName.set(name);
		//balance.set(amount);
	}
	
	public void addToFund(double amount) {
		if(amount > 0) {
			balance += amount;
			//balance.add(amount);
		}
	}
	
	public void removeFromFund(double amount) {
		if(amount > 0) {
			balance -= amount;
			//balance.subtract(amount);
		}
	}
	
	public void setName(String name) {
		this.fundName = name;
		//fundName.set(name);
	}
	
	public void setBalance(double amount) {
		this.balance = amount;
		//balance.set(amount);
	}
	
	
	
}

	
	
	
