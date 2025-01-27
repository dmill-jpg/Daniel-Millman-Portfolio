package amazonsystem;

import java.util.ArrayList;

public class AmazonCustomer {
	private int id;
	private String name, address;
	private AmazonCart myCart;
	private ArrayList<AmazonComment> comments;
	private ArrayList<AmazonProduct> wishlist;
	private ArrayList<AmazonCredit> credits;
	
	public AmazonCustomer(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
		comments = new ArrayList<AmazonComment>();
		wishlist = new ArrayList<AmazonProduct>();
		credits = new ArrayList<AmazonCredit>();
		setMyCart(new AmazonCart(this));
	}
	
	public static AmazonCustomer createAmazonCustomer(String[] in) {
		AmazonCustomer temp = null;
		
		if(in.length == 3 && !in[0].isEmpty() && !in[0].isBlank() && !in[1].isEmpty() && !in[1].isBlank() && !in[2].isEmpty() && !in[2].isBlank()) {
			try {
				temp = new AmazonCustomer(Integer.parseInt(in[0]), in[1], in[2]);
			} catch (NumberFormatException createCustomer) {
				createCustomer.printStackTrace();
			}
		}
		return temp;
	}
	
	public void addCredit(AmazonCredit newCred) {
		credits.add(newCred);
	}
	
	public void showCredits() {
		System.out.println("Printing credits for " + name + ": ");
		
		if(credits.size() > 0) {
			for(int i = 0; i < credits.size(); i++) {
				System.out.println(i + ": " + credits.get(i).toString());
			}
		}
		else {
			System.out.println("No credits *megamind*");
		}
	}
	
	public void addProductInWishList(AmazonProduct toAdd) {
		wishlist.add(toAdd);
	}
	
	public void removeProductFromWishlist(AmazonProduct toRem) {
		wishlist.remove(wishlist.indexOf(toRem));
	}
	
	public boolean isProductInWishlist(AmazonProduct isWishlisted) {
		if(wishlist.indexOf(isWishlisted) != -1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void showWishList() {
		System.out.println("Printing wishlist for customer " + name);
		for(int i = 0; i < wishlist.size(); i++) {
			System.out.println(" - " + i + ": " + wishlist.get(i).toString());
		}
	}
	
	public void addItemInCart(AmazonCartItem toAdd) {
		myCart.addItem(toAdd);
	}
	
	public void removeProductFromCart(AmazonProduct toRem) {
		for(int i = 0; i < myCart.getItems().size(); i++) {
			if(myCart.getItem(i).getProduct() == toRem) {
				myCart.getItems().remove(i);
				break;
			}
		}
	}
	
	public void showCart() {
		System.out.println("Displaying Current Cart for customer " + name);
		for(int i = 0; i < myCart.getItems().size(); i++) {
			System.out.println(" - " + myCart.getItem(i).toString());
		}
	}
	
	public void pay(AmazonCredit credit) {
		if(credit.getAmount() >= myCart.calcSubTotal() && myCart.getItems().size() > 0) {
			System.out.println("Customer: " + name);
			System.out.println("Credit: " + credit.toString());
			System.out.println("Items: - - - - - - - - - - - - - ");
			showCart();
			System.out.println("- - - - - - - - - - - - - - - - -");
			System.out.println("Total: " + myCart.calcSubTotal());
			if(credit.amount > myCart.calcSubTotal()) {
				System.out.println("Change: " + (myCart.calcSubTotal() - credit.amount));
				System.out.println("Added to Customer Account.");
				credits.add(AmazonCredit.createCredit(new String[] {("" + (myCart.calcSubTotal() - credit.amount)), ("" + credit.getType())}));
			}
			moveFromCartToComments();
		}
		else if(credit.getAmount() < myCart.calcSubTotal()) {
			System.out.println("Insufficient Credit");
		}
		else if (myCart.getItems().size() == 0) {
			System.out.println("No Items in Cart");
		}
	}
	
	public void setComment(AmazonProduct prod, String comm, float rating) {
		if(hasProductToComment(prod)) {
			for(int i = 0; i < comments.size(); i++) {
				if(comments.get(i).getProduct() == prod) {
					comments.get(i).setComment(comm);
					comments.get(i).setRating(rating);
					break;
				}
			}
		}
	}
	
	public void showComments() {
		System.out.println("Printing comments from " + name);
		for(int i = 0; i < comments.size(); i++) {
			System.out.println(i + ") ------------------------------------");
			System.out.println(" - Product: " + comments.get(i).getProduct());
			System.out.println(" - Rating: " + comments.get(i).getRating());
			System.out.println(" - Comment: " + comments.get(i).getComment());
		}
	}
	
	public boolean hasProductToComment(AmazonProduct toComment) {
		for(int i = 0; i < comments.size(); i++) {
			if(comments.get(i).getProduct() == toComment) {
				return true;
			}
		}
		return false;
	}
	
	public void moveFromCartToComments() {
		for(int i = myCart.getItems().size(); i > 0; i--) {
			if(!hasProductToComment(myCart.getItem(i).getProduct())) {
				comments.add(new AmazonComment(myCart.getItems().get(0).getProduct()));
			}
			myCart.getItems().remove(0);
		}
	}
	
	public void addComment(AmazonComment newComm) {
		comments.add(newComm);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<AmazonComment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<AmazonComment> comments) {
		this.comments = comments;
	}
	public ArrayList<AmazonProduct> getWishlist() {
		return wishlist;
	}
	public void setWishlist(ArrayList<AmazonProduct> wishlist) {
		this.wishlist = wishlist;
	}
	public ArrayList<AmazonCredit> getCredits() {
		return credits;
	}
	public void setCredits(ArrayList<AmazonCredit> credits) {
		this.credits = credits;
	}
	
	public AmazonCart getMyCart() {
		return myCart;
	}

	public void setMyCart(AmazonCart myCart) {
		this.myCart = myCart;
	}

	@Override
	public String toString() {
		return String.format("AmazonCustomer [id=%s, name=%s, address=%s, comments=%s, wishlist=%s, credits=%s]", id, name, address, comments, wishlist, credits);
	}

	public int getCartSize() {
		return myCart.getItems().size();
	}

	public int getNumberOfComments() {
		return comments.size();
	}

	public int getNumberOfCredits() {
		return credits.size();
	}

	public int getWishlistSize() {
		return wishlist.size();
	}
}
