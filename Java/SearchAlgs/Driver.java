package SearchAlgs;


//Imports
import java.util.Random;
import java.util.Scanner;

/**
 * Driver Class to demonstrate my implementations of search algorithms
 * @author Daniel Millman
 */
public class Driver {
	//Initialize class objects
		private Scanner uIn = new Scanner(System.in);
		private int[] numbers;
		
		/**
		 * Main method
		 * @param args Unused
		 */ 
		public static void main(String[] args) {
			//Creates instance of main class
			Driver run = new Driver();
			//Generates a sorted array of random integers between 10 and 100 (Not inclusive)
			run.numbers = generateRandomInts();
			//Declarations
			boolean loop = true;
			int option = -1;
			String userIn;
			int searchKey = -1;
			
			//Loops until variable is flipped (only done with user exit prompt)
			while(loop) {
				//Resets option variable
				option = -1;
				//Resets search variable
				searchKey = -1;
				//While option variable not in range (non-int inputs wont change option variable keeping the loop going
				while(!(5 > option && 0 < option )) {
					//Prints menu
					System.out.println(""
						+ "\nSelect your option in the menu below:\n"
						+ "1. Initialize and populate an array of 32 random integers.\n"
						+ "2. Perform a recursive binary and linear search.\n"
						+ "3. Perform iterative binary and linear search.\n"
						+ "4. Exit.\n");	
					//Pulls user input as string and writes it to a temporary string
					userIn = run.uIn.next();
					//Tries to parse int 
					try {
						option = Integer.parseInt(userIn);
					}
					catch (NumberFormatException badInput) {}
				}
				//Switch on selected option after input validation
				switch(option) {
				case 1: //Initialize and populate a 32 int array
					//Initialize it
					run.numbers = new int[32];
					//Populate it with an ordered array
					run.numbers = generateRandomInts();
					break;
				case 2: //Recursive searches called through the timer method
					//Assumed that only search keys in bounds should be accepted, same structure as input validation seen at lines 36-52
					while(!(100 > searchKey && 10 < searchKey)) {
						System.out.println("\nEnter an integer between 10 and 100 to search for: ");	
						userIn = run.uIn.next();
						try {
							searchKey = Integer.parseInt(userIn);
						}
						catch (NumberFormatException badInput) {}
					}
					//Calls timer method with option recursive linear
					timedSearch(1, searchKey, run.numbers);
					//Calls timer method with option recursive binary
					timedSearch(2, searchKey, run.numbers);
					break;
				case 3: //Iterative searches
					//Whole case same as case 2 but with iterative instead of recursive calls through timer method
					while(!(100 > searchKey && 10 < searchKey)) {
						System.out.println("\nEnter an integer between 10 and 100 to search for: ");	
						userIn = run.uIn.next();
						try {
							searchKey = Integer.parseInt(userIn);
						}
						catch (NumberFormatException badInput) {}
					}
					timedSearch(3, searchKey, run.numbers);
					timedSearch(4, searchKey, run.numbers);
					break;
				case 4: //Exit
					//Sets loop variable to false interrupting the while
					loop = false;
					break;
				}
			}
		}
		/**
		 * Generates a random integer array
		 * @return A sorted array of 32 integers between 10 and 100 (non-inclusive)
		 */
		public static int[] generateRandomInts() {
			//Initialize and Seed pseudo-random number generator
			Random r = new Random();
			r.setSeed(System.currentTimeMillis());
			
			//Initialize output array
			int[] randomArr = new int[32];
			
			//Populate and print unsorted array
			System.out.print("\nUnsorted array: ");
			for(int i = 0; i < randomArr.length; i++) {
				randomArr[i] = r.nextInt(89)+11;
				System.out.print(randomArr[i] + " ");
			}
			
			//Create a copy of the array
			int[] sortArr = randomArr;
			
			//Basic nonrecursive selection sort algorithm
			for(int i = 0; i < sortArr.length; i++) {
				//Stores value at current pos
				int temp = sortArr[i];
				//Stores index of current minimum number
				int min = i;
				
				//Finds the index of the smallest number in the unsorted range
				for(int j = i+1; j < sortArr.length; j++) {
					if(sortArr[min] > sortArr[j]) {
						min = j;
					}
				}
				//Swaps position of smallest num in range and current position
				sortArr[i] = sortArr[min];
				sortArr[min] = temp;
			}
			
			//Prints sorted array
			System.out.print("\nSorted Array: ");
			for(int i = 0; i < sortArr.length; i++) {
				System.out.print(sortArr[i] + " ");
			}
			
			//Returns the array with the specified array name
			randomArr = sortArr;
			return randomArr;
		}
		
		/**
		 * Runs a timer around the search algorithm calls (will be inaccurate due to print statements and some flow control statements)  
		 * @param algorithm Which alogrithm to use
		 * @param key Value to search for
		 * @param numbers An array of integers (Must be sorted for binary search to work)
		 * 
		 * Algorithm values:
		 * 1: Iterative Linear Search
		 * 2: Iterative Binary Search
		 * 3: Recursive Linear Search
		 * 4: Recursive Binary Search
		 */
		public static void timedSearch(int algorithm, int key, int[] numbers) {
			//pull times at start
			long startMilli = System.currentTimeMillis();
			long startNano = System.nanoTime();
			//declares index variable
			int index;
			//Switch on which algorithm is to be used
			switch(algorithm) {
			case 1: //Recursive Linear
				index = LinearSearch.recursiveSearch(key, numbers, numbers.length);
				if(index != -1) {
					System.out.println("\n" + key + " was found at index position " + index + ": recursive linear search.");
				}
				else {
					System.out.println("\n" + key + " not found: recursive linear search.");
				}
				break;
			case 2: //Recursive Binary
				index = BinarySearch.recursiveSearch(key, numbers, 0, numbers.length);
				if(index != -1) {
					System.out.println("\n" + key + " was found at index position " + index + ": recursive binary search.");
				}
				else {
					System.out.println("\n" + key + " not found: recursive binary search.");
				}
				break;
			case 3: //Iterative linear
				//Finds index or sentinel value
				index = LinearSearch.iterativeSearch(key, numbers);
				//Prints key, statement, index, algorithm if not sentinel value
				if(index != -1) {
					System.out.println("\n" + key + " was found at index position " + index + ": iterative linear search.");
				}
				//Only shows up if sentinel value is returned, prints not found
				else {
					System.out.println("\n" + key + " not found: iterative linear search.");
				}
				//Prevents running down the switch cases
				break;
				//Pattern repeats for all following switch cases
			case 4: //Iterative Binary
				index = BinarySearch.iterativeSearch(key, numbers);
				if(index != -1) {
					System.out.println("\n" + key + " was found at index position " + index + ": iterative binary search.");
				}
				else {
					System.out.println("\n" + key + " not found: iterative binary search.");
				}
				break;
			}
			
			//Print end times (current - start)
			System.out.println("\nTime taken in nano: " + (System.nanoTime() - startNano));
			System.out.println("Time taken in milliseconds: " + (System.currentTimeMillis() - startMilli));
		}
}
