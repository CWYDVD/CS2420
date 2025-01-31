package assign04;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LargestNumberSolverTester {
	private Random r;
	private Integer[] array;
	private Integer[] array2;
	private Integer[] array3;
	@BeforeEach
	void setUp() {
		r = new Random();
		array = new Integer[10];
		for (int i = 0; i < 10; i ++)
			array[i] = (i * 7) % 10;
		array2 = new Integer[20];
		for (int i = 0; i < 20; i++)
			array2[i] = (i * 143) % 100;
		array3 = new Integer[5];
		for (int i = 0; i < 5; i ++) {
			array3[i] = (i * 7) % 10;
		}
		
	}
	
	//Insertion sort
	@Test
	void testInsertionSort() {
		
		Integer[] result = new Integer[10];
		for (int i = 0; i < 10; i ++)
			result[i] = 9 - i;
		
		LargestNumberSolver.<Integer>insertionSort(array, new Inverse());
		for (int i = 0; i < 10; i++)
			assertEquals(result[i], array[i]);
		
		for (int i = 0; i < 10; i ++)
			result[i] = i;
		
		LargestNumberSolver.<Integer>insertionSort(array, new Standard());
		for (int i = 0; i < 10; i++)
			assertEquals(result[i], array[i]);
	}
	
	//Find Largest Number
	
	@Test
	void testFindLargestNumber() {
		BigInteger result = new BigInteger("9876543210");
		assertEquals(result, LargestNumberSolver.findLargestNumber(array));
		
		result = new BigInteger("0");
		assertEquals(result, LargestNumberSolver.findLargestNumber(new Integer[0]));
		
		result = new BigInteger("8887867473725958454443313029217161510");
		assertEquals(result, LargestNumberSolver.findLargestNumber(array2));
		
		for (int i = 0; i < 10; i ++)
			assertEquals(array[i], (i * 7) % 10);
		for (int i = 0; i < 10; i++)
			assertEquals(array2[i], (i * 143) % 100);
		for (int i = 0; i < 5; i ++) 
			assertEquals(array3[i], (i * 7) % 10);
	}
	
	//Find Largest Int
	@Test
	void testFindLargestInt() {
		int result = 87410;
		assertEquals(result, LargestNumberSolver.findLargestInt(array3));
		
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(array);});
		
		assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestInt(array2);});
		
		for (int i = 0; i < 10; i ++)
			assertEquals(array[i], (i * 7) % 10);
		for (int i = 0; i < 10; i++)
			assertEquals(array2[i], (i * 143) % 100);
		for (int i = 0; i < 5; i ++) 
			assertEquals(array3[i], (i * 7) % 10);
	}
	
	//Find Largest Long
		@Test
		void testFindLargestLong() {
			long result = 87410;
			assertEquals(result, LargestNumberSolver.findLargestLong(array3));
			
			result = new BigInteger("9876543210").longValue();
			assertEquals(result, LargestNumberSolver.findLargestLong(array));
			
			assertThrows(OutOfRangeException.class, () -> { LargestNumberSolver.findLargestLong(array2);});
			
			for (int i = 0; i < 10; i ++)
				assertEquals(array[i], (i * 7) % 10);
			for (int i = 0; i < 10; i++)
				assertEquals(array2[i], (i * 143) % 100);
			for (int i = 0; i < 5; i ++) 
				assertEquals(array3[i], (i * 7) % 10);
		}
		
	//Sum
		@Test
		void testSum() {
			BigInteger result = new BigInteger("8887867473725958454443313029217161510").add(new BigInteger("9876543210").add(new BigInteger("87410")));
			List<Integer[]> list = new ArrayList<Integer[]>();
			list.add(array);
			list.add(array2);
			list.add(array3);
			
			assertEquals(result, LargestNumberSolver.sum(list));
		}
		
	//kTh Highest Number
		@Test
		void testKthHighestNumber() {
			Integer[] array1 = {88, 51};
			Integer[] array2 = {7, 42, 97};
			
			List<Integer[]> value = new ArrayList<Integer[]>();
			value.add(array1);
			value.add(array2);
			assertEquals(array1, LargestNumberSolver.findKthLargest(value, 1));
			assertEquals(array2, LargestNumberSolver.findKthLargest(value, 0));
			
			
			r.setSeed(2005);
			ArrayList<Integer[]> list = new ArrayList<>();
			for (int i = 0; i < 10; i ++) {
				Integer[] sublist = new Integer[10];
				for (int j = 0; j < 10; j ++)
					sublist[j] = r.nextInt(100);
				list.add(sublist);
			}
			Integer[] result = list.get(7);
			assertEquals(result, LargestNumberSolver.findKthLargest(list, 0));
			result = list.get(6);
			assertEquals(result, LargestNumberSolver.findKthLargest(list, 1));
			result = list.get(2);
			assertEquals(result, LargestNumberSolver.findKthLargest(list, 9));
			
			assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(list, 10);});
			assertThrows(IllegalArgumentException.class, () -> {LargestNumberSolver.findKthLargest(list, -1);});
			
			r.setSeed(2005);
			for (int i = 0; i < 10; i ++)
				for (int j = 0; j < 10; j ++)
					assertEquals(r.nextInt(100), list.get(i)[j]);
		}
		
	//read file
		@Test
		void testReadFile() {
			List<Integer[]> result = new ArrayList<>();
			result.add(new Integer[] {1,2,3,4,5,6,7,8,9,0});
			result.add(new Integer[] {0,9,8,7,6,5,4,3,2,9});
			result.add(new Integer[] {1,2,3,4,5,6,7,9,9,9});
			result.add(new Integer[] {0,1,2,3,4,5,9,9,9,9});
			List<Integer[]> actual = LargestNumberSolver.readFile("integers2.txt");
			for (int i = 0; i < 4; i ++)
				for (int j = 0; j < 10; j++)
					assertEquals(result.get(i)[j], actual.get(i)[j]);
			List<Integer[]> actual2 = LargestNumberSolver.readFile("integers.txt");
			Integer[] result2 = new Integer[] {88, 51};
			assertEquals(result2[0], actual2.get(7)[0]);
			assertEquals(result2[1], actual2.get(7)[1]);
		}
		
	protected class Standard implements Comparator<Integer> {
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0.compareTo(arg1);
		}
	}
	protected class Inverse implements Comparator<Integer> {
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg1.compareTo(arg0);
		}
	}
}
