package amazonsystem;

import java.util.ArrayList;

public class AmazonProductCategory {
	private String categoryName;
	public static ArrayList<AmazonProductCategory> catList = new ArrayList<AmazonProductCategory>();
	
	public AmazonProductCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
