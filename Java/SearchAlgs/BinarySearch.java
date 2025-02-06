package SearchAlgs;

/**
 * Class containing both an interative and a recursive implementation of the binary search algorithm
 * @author Daniel Millman
 */
public class BinarySearch {
	/**
	 * Iterative implementation of the binary search algorithm
	 * @param key Search value
	 * @param numbers Sorted array of integers
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int iterativeSearch(int key, int[] numbers) {
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
	 * Recursive implementation of the binary search algorithm
	 * @param key Search value
	 * @param numbers Sorted array of integers
	 * @param firstIndex Start of the current section of the array being searched
	 * @param lastIndex End of the current section of the array being searched
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int recursiveSearch(int key, int[] numbers, int firstIndex, int lastIndex) {
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
			return recursiveSearch(key, numbers, firstIndex + ((lastIndex-firstIndex)/2), lastIndex);
		}
		//Less than recursive call, decreases last index
		else if(key < numbers[firstIndex + ((lastIndex-firstIndex)/2)]) {
			return recursiveSearch(key, numbers, firstIndex, lastIndex - ((lastIndex-firstIndex)/2));
		}
		//Default Case, cannot be reached
		return -1;
	}

	/**
	 * Displays the values between first and last index (inclusive
	 * @param numbers An integer array
	 * @param firstIndex Start of print section
	 * @param lastIndex End of print section
	 */
	public static void remainingElements(int[] numbers, int firstIndex, int lastIndex) {
		//New line
		System.out.println("");
		//Prints each element followed by a space from firstIndex to lastIndex
		for(int i = firstIndex; i < numbers.length && i < lastIndex; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

	
}