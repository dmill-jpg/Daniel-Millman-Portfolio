package amazonsystem;

public class AmazonCheck extends AmazonCredit {
	private String accountNumber;
	
	AmazonCheck(String accountNumber, float amount){
		super(amount);
		this.setAccountNumber(accountNumber);
		type = PaymentType.Check;
	}
	
	public static AmazonCheck createCheck(String[] in) {
		AmazonCheck temp = null;
		if(AmazonUtil.isValidFloat(in[1])) {
			temp =  new AmazonCheck(in[0], Float.parseFloat(in[1]));
		}
		return temp;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
