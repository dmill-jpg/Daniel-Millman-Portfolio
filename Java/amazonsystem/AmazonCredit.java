package amazonsystem;

enum PaymentType{Cash, Check, Credit};

public abstract class AmazonCredit {
	PaymentType type;
	float amount;
	
	AmazonCredit(float amount){
		this.amount = amount;
	}
	
	public static AmazonCredit createCredit(String[] in) {
		AmazonCredit temp = null;
		
		if(in.length == 2 && (in[1].equals("Cash") || in[1].equals("Card") || in[1].equals("Check"))) {
			switch(in[1]) {
			case "Cash":
				temp = new AmazonCash(Float.parseFloat(in[0]));
				break;
			case "Card":
				temp = new AmazonCard("", Float.parseFloat(in[0]));
				break;
			case "Check":
				temp = new AmazonCheck("", Float.parseFloat(in[0]));
				break;
			}
		}
		
		return temp;
	}

	@Override
	public String toString() {
		return String.format("AmazonCredit [type=%s, amount=%s]", type, amount);
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
