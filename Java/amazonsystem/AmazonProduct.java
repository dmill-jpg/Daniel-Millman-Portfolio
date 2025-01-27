package amazonsystem;

public class AmazonProduct {
	private int id, nRatings;
	private String name, imageURL, link;
	private float rating, discountPrice, actualPrice;
	private AmazonProductCategory category;
	private AmazonProductSubCategory subCategory;
	
	public AmazonProduct(int id, String name, AmazonProductCategory category, AmazonProductSubCategory subCategory, String imageURL, String link, float rating, int nRatings, float discountPrice, float actualPrice){
		this.id = id;
		this.nRatings = nRatings;
		this.name = name;
		this.imageURL = imageURL;
		this.link = link;
		this.rating = rating;
		this.discountPrice = discountPrice;
		this.actualPrice = actualPrice;
		this.category = category;
		this.subCategory = subCategory;
	}
	
	public AmazonProduct(String[] in) {
		switch(in.length){
			case 2:
				try {
					id = Integer.parseInt(in[0]);
				} catch (NumberFormatException invalidID) {
					invalidID.printStackTrace();
				}
				name = in[1];
				break;
			case 3:
				try {
					id = Integer.parseInt(in[0]);
				} catch (NumberFormatException invalidID) {
					invalidID.printStackTrace();
				}
				name = in[1];
				break;
		}
			
		
	}
	
	public AmazonProduct() {
		
	}
	
	public static AmazonProduct createAmazonProduct(String[] in) {
		AmazonProduct temp = null;
		boolean valid = true;
		for(int i = 0; i < in.length; i++) {
			if(in[i].isBlank() || in[i].isEmpty()) {
				valid = false;
			}
		}
		
		if(in.length == 10 && valid) {
			try {
				AmazonProductCategory cat = new AmazonProductCategory(in[2]);
				temp = new AmazonProduct(Integer.parseInt(in[0]), in[1], cat, new AmazonProductSubCategory(in[3], cat) , in[4], in[5], Float.parseFloat(in[6]), Integer.parseInt(in[7]), Float.parseFloat(in[8]), Float.parseFloat(in[9]));
			} catch (NumberFormatException createProduct) {
				createProduct.printStackTrace();
			}
		}
		return temp;
	}
	
	//TODO ask pablo about purpose since its not on A1 and not defined in a2
	public String getProductDetails() {
		return toString();
	}
	
	public void setTitle(String[] in) {
		String out = "";
		for(int i = 0; i < in.length; i++) {
			out += in[i];
		}
		System.out.print(out);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getnRatings() {
		return nRatings;
	}
	public void setnRatings(int nRatings) {
		this.nRatings = nRatings;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public float getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(float actualPrice) {
		this.actualPrice = actualPrice;
	}
	public AmazonProductCategory getCategory() {
		return category;
	}
	public void setCategory(AmazonProductCategory category) {
		this.category = category;
	}
	public AmazonProductSubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(AmazonProductSubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",id,name,category,subCategory,imageURL,link,rating,nRatings,discountPrice,actualPrice);
	}
}
