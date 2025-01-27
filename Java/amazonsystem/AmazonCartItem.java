package amazonsystem;

public class AmazonCartItem {
	private int quantity;
	private AmazonProduct product;
	
	public AmazonCartItem(AmazonProduct product, int quantity) {
		super();
		this.quantity = quantity;
		this.product = product;
	}
	
	public float calcSubTotal() {
		return (product.getActualPrice() * quantity);
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public AmazonProduct getProduct() {
		return product;
	}
	public void setProduct(AmazonProduct product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return String.format("Item: %s --- Quantity", product, quantity);
	}
}
