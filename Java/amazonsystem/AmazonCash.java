package amazonsystem;

public class AmazonCash extends AmazonCredit {
	AmazonCash(float amount) {
		super(amount);
		type = PaymentType.Cash;
	}
	
	public static AmazonCash createCash(String[] in) {
		AmazonCash temp = null;
		temp = new AmazonCash(Float.parseFloat(in[0]));
		return temp;
	}

}
