package amazonsystem;

public class AmazonCard extends AmazonCredit {
	private String expiration;
	AmazonCard(String expiration, float amount) {
		super(amount);
		this.expiration = expiration;
	}
	
	public static AmazonCard createCard(String[] in) throws AmazonException {
		AmazonCard temp = null;
		
		temp = new AmazonCard(in[0], Float.parseFloat(in[1]));
		
		return temp;
	}
	
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
}