package mainpackage;

import java.time.LocalDateTime;

public class Transaction {
	private String type;
	private double amount;
	private LocalDateTime time_stamp;

	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
		this.time_stamp = LocalDateTime.now();
	}
	
	public String toString() {
		return "Transaction Type: " + type + ", Amount: " + amount + ", Time: " + time_stamp;
	}
}
