package amazonsystem;

import java.util.ArrayList;
//import java.util.Date;

public class AmazonCart implements AmazonPayable{
	private AmazonCustomer customer;
	private ArrayList<AmazonCartItem> items;
	private float totalValue;
	//private Date date;
	
	public AmazonCart(AmazonCustomer customer) {
		this.customer = customer;
		items = new ArrayList<AmazonCartItem>();
		totalValue = 0;
		//date = Date.from(null);
	}
	
	public float calcSubTotal() {
		float out = 0;
		for(int i = 0; i < items.size(); i++) {
			out += items.get(i).calcSubTotal();
		}
		return out;
	}
	
	public AmazonCartItem getItem(int i) {
		return items.get(i);
	}
	
	public boolean hasItem(AmazonProduct in) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getProduct().equals(in)) {
				return true;
			}
		}
		return false;
	}
	
	public void addItem(AmazonCartItem in) {
		items.add(in);
		totalValue=calcSubTotal();
	}
	
	public void removeItem(AmazonCartItem in) {
		try {
			items.remove(items.indexOf(in));
		} catch (IndexOutOfBoundsException removeItem) {
			removeItem.printStackTrace();
		}
	}
	
	public String getCartDetails() {
		return toString();
	}
	
	public AmazonCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(AmazonCustomer customer) {
		this.customer = customer;
	}
	public ArrayList<AmazonCartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<AmazonCartItem> items) {
		this.items = items;
	}
	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	/*public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}*/

	@Override
	public String toString() {
		return String.format("AmazonCart [customer=%s, items=%s, totalValue=%s]", customer, items, totalValue);
	}

	@Override
	public boolean pay() {
		int selectedCred = -1;
		for(int i = 0; i < customer.getCredits().size(); i++) {		
			if(customer.getCredits().get(i).getAmount() >= calcSubTotal()) {
				selectedCred = i;
				break;
			}
		}
		
		if(selectedCred != -1) {
			customer.getCredits().get(selectedCred).setAmount(customer.getCredits().get(selectedCred).getAmount() - calcSubTotal());
			for(int i = 0; i < items.size(); i++) {
				customer.addComment(new AmazonComment(items.get(i).getProduct()));
			}
			for(int i = items.size(); i > 0; i--) {
				items.remove(0);
			}
			return true;
		}
		
		return false;
	}
}
