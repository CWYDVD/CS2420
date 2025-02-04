import assign04.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * For testing the LargestNumberSolver class.
 * 
 * @author David and Maxwell
 * @version February 4, 2025
 */
public class LargestNumberSolverTester {
    private int[] arr1;
    private String[] arr2;
    private int[] arr3;
    private ArrayList<Integer> arr4;
    


    @BeforeEach
    public void setUp() throws Exception {
        arr1 = new int[32];
        for (int i = 0; i < 32; i++)
            arr1[i] = (i * 9 + 7) % 32;
            
        arr2 = new String[9];
        arr2[0] = "We";
        arr2[1] = "hold";
        arr2[2] = "these";
        arr2[3] = "truths";
        arr2[4] = "to";
        arr2[5] = "be";
        arr2[6] = "self";
        arr2[7] = "evident.";
        arr2[8] = "That";

        arr3 = new int[12129];
        for (int i = 0; i < 12129; i++)
            arr3[i] = 12129 - i;

        arr4 = new ArrayList<>();
        arr4.add(2);
        arr4.add(7);
        arr4.add(2);
        arr4.add(4);
        arr4.add(1);
        arr4.add(89);
        arr4.add(13);
        arr4.add(9);
        arr4.add(10000000);
        arr4.add(1); 
    }

    @Test
    public void testInsertionSort() {
        LargestNumberSolver.insertionSort(arr1, Comparator.naturalOrder());
        for (int i = 0; i < 32; i++)
            assertEquals(arr1[i], i);
        LargestNumberSolver.insertionSort(arr2, Comparator.naturalOrder());
        assertEquals(arr2[4], "That");
        assertEquals(arr2[4], "We");
        assertEquals(arr2[0], "be");
        assertEquals(arr2[1], "evident.");
        assertEquals(arr2[2], "hold");
        assertEquals(arr2[3], "self");
        assertEquals(arr2[5], "these");
        assertEquals(arr2[6], "to");
        assertEquals(arr2[7], "trust");
        assertEquals(arr2[8], "truths");
        LargestNumberSolver.insertionSort(arr3, Comparator.naturalOrder());
        for (int i = 1; i <= 12129; i++)
            assertEquals(arr3[i], i);
        
    }

    @Test
    public void testFindLargestNumber(){
        assertEquals(new BigInteger("9897422131110000000"), LargestNumberSolver.findLargestNumber(arr4));
        ArrayList<Integer> copyArr4 = new ArrayList<>();
        copyArr4.add(2);
        copyArr4.add(7);
        copyArr4.add(2);
        copyArr4.add(4);
        copyArr4.add(1);
        copyArr4.add(89);
        copyArr4.add(13);
        copyArr4.add(9);
        copyArr4.add(10000000);
        copyArr4.add(1);
        assertEquals(copyArr4, arr4);


        ArrayList<Integer> empty = new ArrayList<>();
        ArrayList<Integer> emptyCopy = new ArrayList<>();
        assertEquals(new BigInteger("0"), LargestNumberSolver.findLargestNumber(empty));
        assertEquals(emptyCopy, empty);
    

        ArrayList<Integer> shortList = new ArrayList<>();
        shortList.add(1);
        ArrayList<Integer> shortListCopy = new ArrayList<>();
        shortListCopy.add(1);
        assertEquals(new BigInteger("1"), LargestNumberSolver.findLargestNumber(shortList));
        assertEquals(shortListCopy, shortList);
    }

    @Test
    public void testFindLargestInt(){
        assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(arr1);});

        Integer[] largestInt = new Integer[2];
        largestInt[0] = 2;
        largestInt[1] = 147483647;
        assertEquals(2147483647, LargestNumberSolver.findLargestInt(largestInt));

        Integer[] defaultInts = new Integer[5];
        defaultInts[0] = 2;
        defaultInts[0] = 1;
        defaultInts[0] = 0;
        defaultInts[0] = 10;
        defaultInts[0] = 4;
        assertEquals(421100, LargestNumberSolver.findLargestInt(defaultInts));
    }

    @Test
    public void testFindLargestLong() {
        assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(arr1);});

        Integer[] largestLong = new Integer[3];
        largestLong[0] = 7203685;
        largestLong[1] = 92233;
        largestLong[1] = 4775807;
        assertEquals(Long.MAX_VALUE, LargestNumberSolver.findLargestLong(largestLong));

        Integer[] defaultInts = new Integer[5];
        defaultInts[0] = 2;
        defaultInts[0] = 1;
        defaultInts[0] = 0;
        defaultInts[0] = 10;
        defaultInts[0] = 4;
        assertEquals((long) 421100, LargestNumberSolver.findLargestLong(defaultInts));
    }

}

