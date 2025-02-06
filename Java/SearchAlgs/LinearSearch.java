package SearchAlgs;

/**
 * Class containing both an interative and a recursive implementation of the linear search algorithm
 * @author Daniel Millman
 */
public class LinearSearch {
	/**
	 * Iterative implementation of the linear search algorithm
	 * @param key Search key
	 * @param numbers An integer array
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int iterativeSearch(int key, int[] numbers) {
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
	 * Recursive implementation of the linear search algorithm
	 * @param key Search key
	 * @param numbers An integer array
	 * @param arrSize Remaining search area 
	 * @return the integer index of key in numbers or -1 as a sentinel value if key not found
	 */
	public static int recursiveSearch(int key, int[] numbers, int arrSize) {
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
			return recursiveSearch(key, numbers, arrSize-1);
		}
	}
}
