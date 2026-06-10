package mainpackage;

public class SavingsAccount extends BankAccount {

	private String ownerName;
	
	public SavingsAccount(String ownerName) {
		super();
		this.ownerName = ownerName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	
	
}
