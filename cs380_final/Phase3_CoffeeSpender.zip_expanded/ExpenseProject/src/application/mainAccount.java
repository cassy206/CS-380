package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class mainAccount {
	DoubleProperty _balance;
	Double balance;
	String accountName;
	String customerName;
	String customerId;
	
	mainAccount() {
		this.balance = 500.00;
	}
	
	mainAccount(String cname, String cid) {
		this.balance = 0.00;
		customerName = cname;
		customerId = cid;
		accountName = "Main Account";
	}
	
	void deposit(double amount) {
		if(amount > 0) {
			this.balance+= amount;
		}
	}
	
	void withdraw(double amount) {
		if(amount > 0) {
			this.balance -= amount;
		}
	}
	
	/*double getBalance() {
		return this.balance;
	}*/
	
	
	
	String balanceDisplay1 = "$"+String.format("%.2f", this.balance);
	ObservableValue<? extends String> displayBalance = new SimpleStringProperty(balanceDisplay1);
		
	
}
