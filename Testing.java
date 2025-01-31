package assign04;

import java.math.BigInteger;

@SuppressWarnings("unused")
public class Testing {
	private static void test(Integer[] numbers) {
		for (int i = 0; i < numbers.length; i++)
			numbers[i] += 1;
	}
	public static void main(String[] args) {
		Integer[] n = new Integer[20];
		for (int i = 0; i < 20; i ++)
			n[i] = (i * 143) % 100;
		//0 43 86 29 72 15 58 1 44 87 
		// 30 73 16 59 2 45 88 31 74 17
		//     17
		// 88 87 86 74 73 72 59 58 45 44 43 31 30 29 2 17 16 15 1 0
		for (Integer i: n)
			System.out.println(i);
		//System.out.println(2147483647 + 10);
		
		//System.out.println(one.compareTo(two));
		
		//System.out.println(("9100").compareTo("1009"));
		//return ((o2 + o1) - (o1 + o2))
		//BigInteger bigInt = new BigInteger("99999999999999");
		//System.out.print(Long.MAX_VALUE);
		int[] arr = new int[9];
		for (int i = 0; i < 9; i++)
			arr[i] = i;
		System.out.print(Long.MAX_VALUE);
		
	}

}
