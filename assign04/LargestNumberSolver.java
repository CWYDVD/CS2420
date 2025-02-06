package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LargestNumberSolver {
    private static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            String xy = x.toString() + y.toString();
            String yx = y.toString() + x.toString();
            return yx.compareTo(xy);
        }
    }

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        for (int i = 1; i < arr.length; i++) {
            T copy = arr[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(arr[j], copy) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = copy;
        }
    }

    public static BigInteger findLargestNumber(Integer[] arr) {
        if (arr.length == 0)
            return new BigInteger("0");
        Integer[] temp = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++)
            temp[i] = arr[i];

        LargestNumberSolver.insertionSort(temp, (Comparator<? super Integer>) (o1, o2) -> {
            return ("" + o2 + o1).compareTo("" + o1 + o2);
        });

        if (temp[0] == 0)
            return new BigInteger("0");
        String number = "";
        for (Integer integer : temp)
            number += integer;

        return new BigInteger(number);
    }

    public static int findLargestInt(Integer[] arr) {
        BigInteger n = findLargestNumber(arr);
        if (n.compareTo(new BigInteger("2147483648")) < 0)
            return n.intValue();

        throw new OutOfRangeException("The largest number is out side the range of size int");
    }

    public static long findLargestLong(Integer[] arr) {
        BigInteger n = findLargestNumber(arr);
        if (n.compareTo(new BigInteger("9223372036854775807")) < 0)
            return n.longValue();

        throw new OutOfRangeException("The largest number is out side the range of size long");
    }

    public static BigInteger sum(List<Integer[]> list) {
        BigInteger n = new BigInteger("0");
        BigInteger[] largest = new BigInteger[list.size()];

        for (int i = 0; i < list.size(); i++)
            largest[i] = LargestNumberSolver.findLargestNumber(list.get(i));

        for (BigInteger num : largest)
            n = n.add(num);

        return n;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) {
        Integer[][] a = new Integer[list.size()][];
        for (int i = 0; i < list.size(); i++)
            a[i] = list.get(i);

        Comparator<Integer[]> cmp = (o1, o2) -> {
            return LargestNumberSolver.findLargestNumber(o2).compareTo(LargestNumberSolver.findLargestNumber(o1));
        };
        insertionSort(a, cmp);
        return a[k];
    }

    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> listOfNumbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                List<Integer> numbers = new ArrayList<>();

                while (lineScanner.hasNextInt()) {
                    numbers.add(lineScanner.nextInt());
                }

                Integer[] arr = new Integer[numbers.size()];
                for (int i = 0; i < numbers.size(); i++) {
                    arr[i] = numbers.get(i);
                }

                listOfNumbers.add(arr);
                lineScanner.close();
            }
        }

        catch (FileNotFoundException e) {
            return new ArrayList<>();
        }

        return listOfNumbers;
    }
}
