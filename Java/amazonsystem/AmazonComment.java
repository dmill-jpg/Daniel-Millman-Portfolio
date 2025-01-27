package amazonsystem;

public class AmazonComment {
	private String comment;
	private float rating;
	private AmazonProduct product;
	
	public AmazonComment(AmazonProduct product){
		this.product=product;
	}
	
	AmazonComment(AmazonProduct product, float rating, String comment){
		this.product=product;
		this.rating=rating;
		this.comment=comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public AmazonProduct getProduct() {
		return product;
	}

	public void setProduct(AmazonProduct product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return String.format("AmazonComment [comment=%s, rating=%s, product=%s]", comment, rating, product);
	}
}
