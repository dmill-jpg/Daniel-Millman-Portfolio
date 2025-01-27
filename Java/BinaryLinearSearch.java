/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains an array, a way to populate it, and 2 different search algorithms
 * @author: Daniel
 * Student Name:Daniel Millman 
 * Student Number: 041161208
 * Section #:  302 
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 */
package lab3;

//Imports
import java.util.Random;
import java.util.Scanner;


/**
 */
public class BinaryLinearSearch {
	//Initialize class objects
	private Scanner uIn = new Scanner(System.in);
	private int[] numbers;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Creates instance of main class
		BinaryLinearSearch run = new BinaryLinearSearch();
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
		for(int i = 0; i < 32; i++) {
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
	 * @param key
	 * @param numbers
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int iterativeLinearSearch(int key, int[] numbers) {
		//Start from 0 and work down the line
		for(int i = 0; i < numbers.length; i++) {
			//if found return the position
			if(numbers[i] == key) {
				return i;
			}
		}
		//Default return (key not found
		return -1;
	}

	/**
	 * @param key
	 * @param numbers
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int iterativeBinarySearch(int key, int[] numbers) {
		//Declarations
		int index = 0, len  = numbers.length;
		
		//While search length > 0
		while(len > 0) {
			//returns once found
			if(numbers[index + len/2] == key) {
				return index + len/2;
			}
			//adds (search length)/2 to the index if the key is in the greater half
			else if (numbers[index + len/2] < key) {
				index += len/2;
			}
			//Divides search length by 2
			len /= 2;
		}
		//Sentinel number return if key not found
		return -1;
	}
	
	/**
	 * @param key
	 * @param numbers
	 * @param arrSize
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int recursiveLinearSearch(int key, int[] numbers, int arrSize) {
		//Base Case 0 because it works from the end of the array backwards
		if (arrSize == 0) {
			return -1;
		}
		//Begins the return chain if found
		else if(numbers[arrSize-1] == key) {
			return arrSize-1;
		} 
		//Recursive call if not found and not at base case
		else {
			return recursiveLinearSearch(key, numbers, arrSize-1);
		}
	}

	/**
	 * @param key
	 * @param numbers
	 * @param firstIndex
	 * @param lastIndex
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int recursiveBinarySearch(int key, int[] numbers, int firstIndex, int lastIndex) {
		//Calls print method to display current halved array
		remainingElements(numbers, firstIndex, lastIndex);
		//base case (ignores if found because array size will be 1 either way
		if(lastIndex-firstIndex <= 1 && key != numbers[firstIndex + ((lastIndex-firstIndex)/2)]) {
			return -1;
		}
		//Index return for found
		else if(key == numbers[firstIndex + ((lastIndex-firstIndex)/2)]) {
			return firstIndex + ((lastIndex-firstIndex)/2);
		}
		//Greater than recursive call, increases firstIndex
		else if(key > numbers[firstIndex + ((lastIndex-firstIndex)/2)]) {
			return recursiveBinarySearch(key, numbers, firstIndex + ((lastIndex-firstIndex)/2), lastIndex);
		}
		//Less than recursive call, decreases last index
		else if(key < numbers[firstIndex + ((lastIndex-firstIndex)/2)]) {
			return recursiveBinarySearch(key, numbers, firstIndex, lastIndex - ((lastIndex-firstIndex)/2));
		}
		//Default Case, cannot be reached
		return -1;
	}

	/**
	 * @param numbers
	 * @param firstIndex
	 * @param lastIndex
	 */
	public static void remainingElements(int[] numbers, int firstIndex, int lastIndex) {
		//New line
		System.out.println("");
		//Prints each element followed by a space from firstIndex to lastIndex
		for(int i = firstIndex; i < numbers.length && i < lastIndex; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

	/**
	 * @param algorithm
	 * @param key
	 * @param numbers
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
		case 3: //Iterative linear
			//Finds index or sentinel value
			index = iterativeLinearSearch(key, numbers);
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
			index = iterativeBinarySearch(key, numbers);
			if(index != -1) {
				System.out.println("\n" + key + " was found at index position " + index + ": iterative binary search.");
			}
			else {
				System.out.println("\n" + key + " not found: iterative binary search.");
			}
			break;
		case 1: //Recursive Linear
			index = recursiveLinearSearch(key, numbers, numbers.length);
			if(index != -1) {
				System.out.println("\n" + key + " was found at index position " + index + ": recursive linear search.");
			}
			else {
				System.out.println("\n" + key + " not found: recursive linear search.");
			}
		case 2: //Recursive Binary
			index = recursiveBinarySearch(key, numbers, 0, numbers.length);
			if(index != -1) {
				System.out.println("\n" + key + " was found at index position " + index + ": recursive binary search.");
			}
			else {
				System.out.println("\n" + key + " not found: recursive binary search.");
			}
		}
		
		//Print end times (current - start)
		System.out.println("\nTime taken in nano: " + (System.nanoTime() - startNano));
		System.out.println("Time taken in milliseconds: " + (System.currentTimeMillis() - startMilli));
	}
}