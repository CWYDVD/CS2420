package assign04;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * A static class that allows you to calculate the highest possible number that can be made by combining the digits of a list of integers.
 * 
 * @author Eric Heisler and Mi Zeng and Aiden Maxwell
 * @version Feb 02, 2024
 */

public class LargestNumberSolver {
	
	/**
	 * The insertionSort method performs an in-place insertion sort on an array of generic type T. The sorting is 
	 * done based on the provided comparator (cmp).
	 * @param arr: An array of generic type T to be sorted.
	 * @param cmp: A comparator that defines the sorting order. It should be able to compare elements of type T.
	 */

	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		for (int i = 1; i < arr.length; i++) {

			int index = i;
			while (index > 0  && cmp.compare(arr[index - 1], arr[index]) > 0) {

				T temp = arr[index];
				arr[index] = arr[index - 1];
				arr[index - 1] = temp;
				index--;
			}

		}

	}
	
	/**
	 * The findLargestNumber method takes an array of integers and rearranges them to form the largest possible number. 
	 * @param   An array of integers.
	 * @return  A BigInteger representing the largest number that can be formed by concatenating the digits of the elements of the 
	 * input array.
	 */
	public static BigInteger findLargestNumber(Integer[] in) {	
		if(in.length == 0) {
			return new BigInteger("0");
		}
		
		Comparator<Integer> cmp = (o1, o2) -> ("" + o2 + o1).compareTo("" + o1 + o2);
		
		Integer[] array = new Integer[in.length];
		for(int i =0; i<in.length; i++)
			array[i] = in[i];
			
	
		insertionSort(array, cmp);
		
		StringBuilder numbers = new StringBuilder();
		
		for(Integer i: array) {
			numbers.append(i);
		}
		
		return new BigInteger(numbers.toString());
				
		
		
		
	}
	/**
	 * The findLargestInt method determines the largest possible integer value that can be formed by concatenating the 
	 * elements of the input array. It uses the findLargestNumber method and checks if the result exceeds the int range.
	 * @param arr: An array of integers.
	 * @return   An integer representing the largest number that can be formed by concatenating the elements of the input array.
	 * @throws OutOfRangeException:  If the resulting value exceeds the int range.

	 */
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
		
		BigInteger value = findLargestNumber(arr);
		
		if(value.compareTo(new BigInteger("2147483647")) > 0) throw new OutOfRangeException("int");
		
		int biggestValue = value.intValue();
		
		return biggestValue;
		
		
		
	}
	/**
	 * The findLargestLong method determines the largest possible long value that can be formed by concatenating the elements
	 * of the input array. It uses the findLargestNumber method and checks if the result exceeds the long range.
	 * @param arr: An array of integers.
	 * @return  A long representing the largest number that can be formed by concatenating the elements of the input array.
	 * @throws OutOfRangeException : If the resulting value exceeds the long range.
	 */
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
		
		BigInteger value = findLargestNumber(arr);
		
		if(value.compareTo(new BigInteger("9223372036854775807")) > 0) throw new OutOfRangeException("long");
		
		
		return value.longValue();
		
		
	}
	/**
	 * The sum method calculates the sum of the largest possible numbers formed by concatenating the elements of each 
	 * Integer array in the given list. It utilizes the findLargestNumber method for each array.
	 * @param list: A List of Integer arrays.
	 * @return A BigInteger representing the sum of the largest possible numbers formed by concatenating the elements 
	 * of each array in the list.
	 */
	
	public static BigInteger sum(List<Integer[]> list) {
				
		BigInteger total = BigInteger.ZERO;
		
		for(int i=0; i<list.size(); i++) {
			total = total.add(findLargestNumber(list.get(i)));
		}
		
		return total;
		
	}
	/**
	 * The findKthLargest method determines the kth largest element in the list by comparing the largest possible 
	 * numbers formed by concatenating the elements of each Integer array. It uses the findLargestNumber method and 
	 * an insertion sort with a custom comparator.
	 * @param list:  A List of Integer arrays.
	 * @param k: An integer representing the position of the desired kth largest element.
	 * @return  An Integer array representing the kth largest element.
	 * @throws IllegalArgumentException: If the provided k value is negative or greater than or equal to the size of the list.
	 */
	
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
		
		if (k < 0 || k >= list.size()) throw new IllegalArgumentException("Invalid value");
		
		Comparator<Integer[]> cmp = (a, b) -> findLargestNumber(b).compareTo(findLargestNumber(a));
		
		Integer[][] list2 = new Integer[list.size()][];
				
		for (int i = 0; i < list.size(); i++)
			list2[i] = list.get(i);
		
		insertionSort(list2, cmp);
		
		return list2[k];
	}
	
	public static Integer[] findKthLargest2(List<Integer[]> list, int k) throws IllegalArgumentException{
		
		if (k < 0 || k >= list.size()) throw new IllegalArgumentException("Invalid value");
		
		Comparator<Integer[]> cmp = (a, b) -> findLargestNumber(b).compareTo(findLargestNumber(a));
		
		ArrayList<Integer[]> list2 = new ArrayList<>();
				
		for (int i = 0; i < list.size(); i++)
			list2.add(list.get(i));
		
		list2.sort(cmp);;
		
		return list2.get(k);
	}
	
	/**
	 * The readFile method reads an input file containing space-separated integers and transforms each line into an Integer array. It returns a list of these arrays.
	 * @param filename: A String representing the path to the input file.
	 * @return  A List of Integer arrays, where each array corresponds to a line in the input file.


	 */
	public static List<Integer[]> readFile(String filename){
		 List<Integer[]> result = new ArrayList<>();

	        try {
	        	Scanner scanner = new Scanner(new File(filename));
	            while (scanner.hasNextLine()) {
	                String[] tokens = scanner.nextLine().split("\\s+");
	                Integer[] arr = Arrays.stream(tokens)
	                        .map(Integer::parseInt)
	                        .toArray(Integer[]::new);
	                result.add(arr);
	            }
	        } catch (FileNotFoundException e) {
	            // Do nothing, return empty list
	        }

	        return result;
		
	
	}
	

}
