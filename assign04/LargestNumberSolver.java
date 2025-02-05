package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;

public class LargestNumberSolver {
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {

    }
    
    public static BigInteger findLargestNumber(Integer[] arr) {
        if (arr.length == 0) 
            return new BigInteger("0");
        Integer[] temp = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) 
            temp[i] = arr[i];
        LargestNumberSolver.insertionSort(temp, (Comparator<? super Integer>) (o1, o2) -> {return ("" + o1 + o2).compareTo("" + o2 + o1);});
        
        String number = "";
        for (Integer integer : temp)
            number += integer;
        
        return new BigInteger(number);
    }
    
    public static int findLargestInt(Integer[] arr) {
        BigInteger n = findLargestNumber(arr);
        if (n.compareTo(new BigInteger("2147483648")) < 0) 
            return n.intValue();
        
        throw new OutOfRangeException("int");
    }
    
    public static long findLargestLong(Integer[] arr) {
        BigInteger n = findLargestNumber(arr);
        if (n.compareTo(new BigInteger("9223372036854775807")) < 0) 
            return n.longValue();
        
        throw new OutOfRangeException("long");
    }
    
    public BigInteger sum(List<Integer[]> list) {
        BigInteger n = new BigInteger("0");
        BigInteger[] largest = new BigInteger[list.size()];
        
        for (int i = 0; i < list.size(); i++) 
            largest[i] = LargestNumberSolver.findLargestNumber(list.get(i));
        
        for (BigInteger num: largest)
            n = n.add(num);
        
        return n;
    }
    
    public Integer[] findKthLargest(List<Integer[]> list, int k) {
        Integer[][] a = new Integer[list.size()][];
        for (int i = 0; i < list.size(); i++)
            a[i] = list.get(i);
        
        Comparator<Integer[]> cmp = (o1, o2) -> {return LargestNumberSolver.findLargestNumber(o1).compareTo(LargestNumberSolver.findLargestNumber(o2));};
        insertionSort(a, cmp);
        return a[k];
    }
    
    public static List<Integer[]> readFile(String filename) {
        
    }
}
