package amazonsystem;

public class AmazonProductSubCategory {
	private String subCategoryName;
	private AmazonProductCategory category;
	
	public AmazonProductSubCategory(String subCategoryName, AmazonProductCategory category) {
		this.subCategoryName = subCategoryName;
		this.category = category;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public AmazonProductCategory getCategory() {
		return category;
	}
	public void setCategory(AmazonProductCategory category) {
		this.category = category;
	}
}
