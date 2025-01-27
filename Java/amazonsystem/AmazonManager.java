package amazonsystem;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AmazonManager {
	static private ArrayList<AmazonCustomer> customers = new ArrayList<AmazonCustomer>();
	static private ArrayList<AmazonProduct> products = new ArrayList<AmazonProduct>();
	Scanner uIn = new Scanner(System.in);
	boolean mainLoop = true;
	
	
	public static void main(String[] args) throws AmazonException {
		AmazonManager current = new AmazonManager();
		String temp = "";
		
		while(current.mainLoop) {
			showMenu();
			temp = current.uIn.next();
			
			switch(temp.charAt(0)) {
				case 'A', 'a':
					current.loadProductList();
					break;
				case 'B', 'b':
					current.showProductList();
					break;
				case 'C', 'c':
					current.searchInProducts();
					break;
				case 'D', 'd':
					current.addCustomer();
					break;
				case 'E', 'e':
					current.showCustomers();
					break;
				case 'F', 'f':
					current.addCreditToCustomer();
					break;
				case 'G', 'g':
					current.showCreditFromCustomer();
					break;
				case 'H', 'h':
					current.addProductInWishlist();
					break;
				case 'I', 'i':
					current.removeProductInWishlist();
					break;
				case 'J', 'j':
					current.showWishlist();
					break;
				case 'K', 'k':
					current.addProductInCart();
					break;
				case 'L', 'l':
					current.removeProductInCart();
					break;
				case 'M', 'm':
					current.showProductsInCart();
					break;
				case 'N', 'n':
					current.payCart();
					break;
				case 'O', 'o':
					current.addCommentToProduct();
					break;
				case 'P', 'p':
					current.showComments();
					break;
				case 'Q', 'q':
					current.exit();
					break;
				default:
					break;
			}
		}

		current.uIn.close();
		System.out.println(""
				+ "=========================================================================\n"
				+ "|| [End of Application (Author: Daniel Millman 041 161 208)]           ||\n"
				+ "=========================================================================\n"
				);
	}
	
	public static void showMenu() {
		System.out.println("" 
				+ "=========================================================================\n"
				+ "||         A M A Z O N                            M E N U              ||\n"
				+ "=========================================================================\n"
				+ "||           ADMIN                 ||              USER                ||\n"
				+ "=========================================================================\n"
				+ "|| Product options ................|| Credit options ..................||\n"
				+ "|| [A] Load product list           || [F] Add credit to customer       ||\n"
				+ "|| [B] Show product list           || [G] Show credits from customer   ||\n"
				+ "|| [C] Search products             ||                                  ||\n"
				+ "||                                 || Wishlist options ................||\n"
				+ "|| Customer options ...............|| [H] Add product in wishlist      ||\n"
				+ "|| [D] Add customer                || [I] Remove product from wishlist ||\n"
				+ "|| [E] Show customers              || [J] Show products from wishlist  ||\n"
				+ "||                                 ||                                  ||\n"
				+ "||                                 || Cart options ....................||\n"
				+ "||                                 || [K] Add product in cart          ||\n"
				+ "||                                 || [L] Remove product from cart     ||\n"
				+ "||                                 || [M] Show products from cart      ||\n"
				+ "||                                 || [N] Buy products from cart       ||\n"
				+ "||                                 ||                                  ||\n"
				+ "||                                 || Comment options .................||\n"
				+ "|| ................................|| [O] Comment products bought      ||\n"
				+ "|| [Q] Exit                        || [P] List comments from products  ||\n"
				+ "=========================================================================\n"
				+ "                         Choose an option: "
				);
	}
	
	public void loadProductList() throws AmazonException {
		System.out.println("Enter a file path or d for the default product list: ");
		String temp = uIn.next();
		File prodCSV = null;
		
		while(true) {
			if(temp.charAt(0) == 'd') {
				prodCSV = new File("C:/CST8132EclipseWorkspace/Assignment02/src/products.csv");
				break;
			}
			else {
				prodCSV = new File(temp);
				if(prodCSV.exists() && prodCSV.canRead()) {
					break;
				}
				else {
					System.out.println("Enter a valid file path or d for the default product list: ");
					uIn.next();
				}
			}
		}
		
		Scanner csvReader = null;
		
		try {
			csvReader = new Scanner(prodCSV);
		} catch (FileNotFoundException prodLoad404) {
			prodLoad404.printStackTrace();
		}
		
		csvReader.next();
		
		while(csvReader.hasNext()) {
			products.add(AmazonProduct.createAmazonProduct(AmazonUtil.lineReader(csvReader.next())));
		}	
	}
	
	public void showProductList() {
		System.out.println("Printing products...................................");
		for(int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).toString());
		}
	}
	
	public void searchInProducts() throws AmazonException {
		String temp = null;
		while(true) {
			System.out.println("\n What field would you like to search by: "
					+ "\n 1) ID"
					+ "\n 2) Name"
					+ "\n 3) Number of Ratings"
					+ "\n 4) Average Rating"
					+ "\n 5) Image URL"
					+ "\n 6) Link"
					+ "\n 7) Discount Price"
					+ "\n 8) Actual Price"
					+ "\n 9) Category Name"
					+ "\n 0) Subcategory Name");
			
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				if(Integer.parseInt(temp) < 10 && Integer.parseInt(temp) > 0) {
					break;
				}
			}
		}

		int opt = Integer.parseInt(temp);
		boolean loop = true;
		
		while(loop) {
			switch (opt) {
				case 1,3: 
					System.out.println("\n Please enter a valid integer to search for: ");
					temp = uIn.next();
					if(AmazonUtil.isValidInt(temp)) {
						loop = false;
					}
					break;
				case 2,5,6,9,0:
					System.out.println("\n Please enter a valid string to search for: ");
					temp = uIn.next();
					if(AmazonUtil.isValidString(temp)) {
						loop = false;
					}
					break;
				case 4,7,8:
					System.out.println("\n Please enter a valid float to search for: ");
					temp = uIn.next();
					if(AmazonUtil.isValidFloat(temp)) {
						loop = false;
					}
					break;
			}
		}
		
		ArrayList<AmazonProduct> searchResults = new ArrayList<AmazonProduct>();
		
		switch (opt) {
			case 1:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getId() == Integer.parseInt(temp)) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 2:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getName() == temp) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 3:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getnRatings() == Integer.parseInt(temp)) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 4:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getRating() == Float.parseFloat(temp)) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 5:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getImageURL() == temp) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 6:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getLink() == temp) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 7:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getDiscountPrice() == Float.parseFloat(temp)) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 8:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getActualPrice() == Float.parseFloat(temp)) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 9:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getCategory().getCategoryName() == temp) {
						searchResults.add(products.get(i));
					}
				}
				break;
			case 0:
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getSubCategory().getSubCategoryName() == temp) {
						searchResults.add(products.get(i));
					}
				}
				break;
		}
		
		System.out.println("\n Displaying Search Results............");
		for(int i = 0; i < searchResults.size(); i++) {
			System.out.println(searchResults.get(i).toString());
		}
	}

	public void addCustomer() throws AmazonException {
		System.out.println("Creating new customer....");
		boolean loop = true;
		String temp = null;
		
		String[] info = new String[3];
		
		while(loop) {
			System.out.println("Choose ID type:"
					+ "\n 1) Autogenerated"
					+ "\n 2) Manual");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				if(Integer.parseInt(temp) == 1 || Integer.parseInt(temp) == 2) {
					loop = false;
				}
			}
		}
		
		loop = true;
		
		switch (Integer.parseInt(temp)) {
			case 1:
				if(customers.size() > 0) {
					info[0] = "" + (customers.getLast().getId() + 1);
				}
				else {
					info[0] = "0";
				}
				break;
			case 2:
				while(loop) {
					System.out.println("Please enter a valid ID (Integer): ");
					temp = uIn.next();
					if(AmazonUtil.isValidInt(temp)) {
						info[0] = "" + Integer.parseInt(temp);
						loop = false;
					}
				}
				break;
		}
		
		loop = true;
		
		while(loop) {
			System.out.println("Enter the customers name: ");
			temp = uIn.next();
			if(AmazonUtil.isValidString(temp)) {
				info[1] = temp;
				loop = false;
			}
		}
		
		loop = true;
		
		while(loop) {
			System.out.println("Enter the customers address: ");
			temp = uIn.next();
			if(AmazonUtil.isValidString(temp)) {
				info[2] = temp;
				loop = false;
			}
		}
		
		customers.add(AmazonCustomer.createAmazonCustomer(info));
	}

	public void showCustomers() {
		System.out.println("Displaying customers...........");
		for(int i = 0; i < customers.size(); i++) {
			System.out.println(customers.toString());
		}
	}

	public void addCreditToCustomer() throws AmazonException {
		String temp = null;
		boolean loop = true;
		int id = 0;
		String[] info = new String[3];
		
		while(loop) {
			System.out.println("Select a customer to add a credit to (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						id = Integer.parseInt(temp);
						loop = false;
					}
				}
			}
		}
		
		loop = true;
		int opt = 0;
		while(loop) {
			System.out.println("Choose a type: \n 1) Cash \n 2) Card \n 3) Check \n");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				if(Integer.parseInt(temp) >= 1 && Integer.parseInt(temp) <= 3) {
					opt = Integer.parseInt(temp);
					loop = false;
				}
			}
		}
		
		switch(opt) {
			case 2: 
				loop = true;
				while(loop) {
					System.out.println("Enter the expiry date: ");
					temp = uIn.next();
					
					if(AmazonUtil.isValidString(temp)) {
						info[0] = temp;
						loop = false;
					}
				}
				break;
			case 3:
				loop = true;
				while(loop) {
					System.out.println("Enter the Account Number: ");
					temp = uIn.next();
					
					if(AmazonUtil.isValidString(temp)) {
						info[0] = temp;
						loop = false;
					}
				}
				break;
		}
		
		loop = true;
		while(loop) {
			System.out.println("Enter the amount: ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidFloat(temp)) {
				if(Float.parseFloat(temp) > 0) {
					info[1] = temp;
					loop = false;
				}
			}
		}
		
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getId() == id) {
				switch(opt) {
					case 1:
						info[0] = info[1];
						customers.get(i).addCredit(AmazonCash.createCash(info));
						break;
					case 2:
						customers.get(i).addCredit(AmazonCard.createCard(info));
						break;
					case 3:
						customers.get(i).addCredit(AmazonCheck.createCheck(info));
						break;
				}
				break;
			}
		}
		System.out.println("Credit added");
	}

	public void showCreditFromCustomer() {
		String temp = null;
		boolean loop = true;
	
		while(loop) {
			System.out.println("Select a customer to add a credit to (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						System.out.println("Printing credits for customer: " + customers.get(i) + "   ID: " + customers.get(i).getId());
						customers.get(i).showCredits();
						loop = false;
						break;
					}
				}
			}
		}
	}

	public void showWishlist() {
		String temp = null;
		boolean loop = true;
		
		while(loop) {
			System.out.println("Select a customer to view wishlist of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						customers.get(i).showCart();
						loop = false;

						break;
					}
				}
			}
		}
	}

	public void addProductInWishlist() throws AmazonException {
		String temp = null;
		boolean loop = true;
		int index = 0;
		
		
		while(loop) {
			System.out.println("Select a customer to view wishlist of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		loop = true;
		
		while(loop) {
			showProductList();
			System.out.println("Select an item to add to wishlist (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getId() == Integer.parseInt(temp)) {
						customers.get(index).addProductInWishList(products.get(i));
						
						loop = false;
						break;
					}
				}
			}
		}
		System.out.println("Item added to wishlist successfully");
		
	}

	public void removeProductInWishlist() {
		String temp = null;
		boolean loop = true;
		int index = 0;
		
		
		while(loop) {
			System.out.println("Select a customer to view wishlist of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		loop = true;
		
		while(loop) {
			customers.get(index).showWishList();;
			System.out.println("Select an item to remove (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.get(index).getWishlist().size(); i++) {
					if(customers.get(index).getWishlist().get(i).getId() == Integer.parseInt(temp)) {
						customers.get(index).getWishlist().remove(i);
						loop = false;
						break;
					}
				}
			}
		}
		System.out.println("Item removed successfully");
		
	}

	public void addProductInCart() throws AmazonException {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer to remove an item from the cart of (Use ID):");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		while(loop) {
			showProductList();
			System.out.println("Select an item to add to cart (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getId() == Integer.parseInt(temp)) {
						AmazonProduct item = products.get(i);
						
						while(true) {
							System.out.println("How many would you like to add to cart: ");
							temp = uIn.next();
							if(AmazonUtil.isValidInt(temp)) {
								if(Integer.parseInt(temp) > 0) {
									customers.get(index).addItemInCart(new AmazonCartItem(item, Integer.parseInt(temp)));
									break;	
								}
							}
						}
						
						loop = false;
						break;
					}
				}
			}
		}
		System.out.println("Item added successfully");
	}

	public void removeProductInCart() {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer to remove an item from the cart of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		while(loop) {
			customers.get(index).showCart();
			System.out.println("Select an item to remove from cart (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.get(index).getMyCart().getItems().size(); i++) {
					if(customers.get(index).getMyCart().getItems().get(i).getProduct().getId() == Integer.parseInt(temp)) {
						customers.get(index).getMyCart().removeItem(customers.get(index).getMyCart().getItems().get(i));
						
						loop = false;
						break;
					}
				}
			}
		}
		System.out.println("Item removed successfully");
	}

	public void showProductsInCart() {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer to view the cart of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		customers.get(index).showCart();
		
	}

	public void payCart() {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer to pay the cart of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		loop = true;
		
		while(loop) {
			customers.get(index).showCredits();
			System.out.println("Select a credit: (Use index)");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				if(customers.get(index).getCredits().size() > Integer.parseInt(temp) && Integer.parseInt(temp) >= 0) {
					if(customers.get(index).getCredits().get(Integer.parseInt(temp)).getAmount() >= customers.get(index).getMyCart().calcSubTotal()) {
						customers.get(index).pay(customers.get(index).getCredits().get(Integer.parseInt(temp)));
						loop = false;
						break;
					}
				}
			}
		}
	}

	public void addCommentToProduct() {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer to comment as (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		loop = true;

		System.out.println("You can only comment on products bought.");
		while(loop) {
			showProductList();
			System.out.println("Select an item to comment on (Use id): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < products.size(); i++) {
					if(products.get(i).getId() == Integer.parseInt(temp)) {
						AmazonProduct item = products.get(i);
						float rating = 0;
						String comm = "";
						
						while(true) {
							System.out.println("Enter your rating: (Float)");
							temp = uIn.next();
							
							if(AmazonUtil.isValidFloat(temp)) {
								rating = Float.parseFloat(temp);
								break;
							}
						}
						
						while(true) {
							System.out.println("Enter your comment: ");
							temp = uIn.next();
							
							if(AmazonUtil.isValidString(temp)) {
								comm = temp;
								break;
							}
						}
						
						customers.get(index).setComment(item, comm, rating);
						loop = false;
						break;
					}
				}
			}
		}
	}

	public void showComments() {
		boolean loop = true;
		String temp = null;
		int index = 0;
		
		while(loop) {
			System.out.println("Select a customer view the comments of (Use ID): ");
			temp = uIn.next();
			
			if(AmazonUtil.isValidInt(temp)) {
				for(int i = 0; i < customers.size(); i++) {
					if(customers.get(i).getId() == Integer.parseInt(temp)) {
						index = i;
						loop = false;
						break;
					}
				}
			}
		}
		
		customers.get(index).showComments();
	}

	public void exit() {
		mainLoop = false;
	}
}