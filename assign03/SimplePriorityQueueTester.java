package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * This class contains tests for SimplePriorityQueue.
 * @author Maxwell and David
 * @version January 26, 2025
 */
public class SimplePriorityQueueTester {
    private SimplePriorityQueue<Integer> intQ;
    private SimplePriorityQueue<String> strQ;
    private SimplePriorityQueue<Character> charQ;

    private SimplePriorityQueue<Integer> intQ100;
    private SimplePriorityQueue<Integer> intQ10000;
    private SimplePriorityQueue<Integer> intQ1000000;

    private Collection<Integer> desiredIntegers;

    @BeforeEach
    public void setup() throws Exception {
        intQ = new SimplePriorityQueue<>();
        intQ.insertAll(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8)));

        strQ = new SimplePriorityQueue<>();
        strQ.insertAll(new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8")));

        charQ = new SimplePriorityQueue<>();
        charQ.insertAll(new ArrayList<Character>(Arrays.asList('1','2','3','4','5','6','7','8')));

        intQ100 = new SimplePriorityQueue<>();
        ArrayList<Integer> adding100 = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            adding100.add(i);
        intQ100.insertAll(adding100);

        intQ10000 = new SimplePriorityQueue<>();
        ArrayList<Integer> adding10000 = new ArrayList<>();
        for (int i = 0; i < 10000; i++)
            adding10000.add(i);
        intQ10000.insertAll(adding10000);

        intQ1000000 = new SimplePriorityQueue<>();
        ArrayList<Integer> adding1000000 = new ArrayList<>();
        for (int i = 0; i < 1000000; i++)
            adding1000000.add(i);
        intQ1000000.insertAll(adding1000000);
    }

    @Test
    public void testConstructor() {
        SimplePriorityQueue<Integer> a = new SimplePriorityQueue<>();
        assertEquals(0, a.size());
    }

    @Test
    public void testClear() {
        // assume findMax/Size function working correctly
        // Testing on finding size
        assertEquals(8, intQ.size());
        assertEquals(8, intQ.findMax());

        // Testing clear() function
        intQ.clear();
        assertEquals(0, intQ.size());
        assertThrows(NoSuchElementException.class, () -> {intQ.findMax();});

        // Continue testing isEmpty function
        assertTrue(intQ.isEmpty());

        assertEquals(8, strQ.size());
        assertEquals("8", strQ.findMax());
        strQ.clear();
        assertEquals(0, strQ.size());
        assertThrows(NoSuchElementException.class, () -> {strQ.findMax();});

        assertEquals(8, charQ.size());
        assertEquals('8', charQ.findMax());
        charQ.clear();
        assertEquals(0, charQ.size());
        assertThrows(NoSuchElementException.class, () -> {charQ.findMax();});

        // Testing on meadium size array
        assertEquals(100, intQ100.size());
        assertEquals(99, intQ100.findMax());
        intQ100.clear();
        assertEquals(0, intQ100.size());
        assertThrows(NoSuchElementException.class, () -> {intQ100.findMax();});

        // Testing on large size array
        assertEquals(1000000, intQ1000000.size());
        assertEquals(999999, intQ1000000.findMax());
        intQ1000000.clear();
        assertEquals(0, intQ1000000.size());
        assertThrows(NoSuchElementException.class, () -> {intQ1000000.findMax();});
    }

    @Test
    public void testContains() {
        // Testing with one specific element
        assertTrue(intQ.contains(1));
        assertTrue(strQ.contains("3"));
        assertTrue(charQ.contains('5'));
        assertTrue(intQ100.contains(99));
        assertTrue(intQ10000.contains(2000));
        assertTrue(intQ1000000.contains(999999));

        // (containsAll)
        // Testing with a set of consecutive elements
        Collection<Integer> collInt = new ArrayList<Integer>();
        collInt.add(1);
        collInt.add(2);
        collInt.add(3);
        assertTrue(intQ.containsAll(collInt));

        Collection<String> collStr = new ArrayList<String>();
        collStr.add("1");
        collStr.add("2");
        collStr.add("3");
        assertTrue(strQ.containsAll(collStr));

        Collection<Character> collChar = new ArrayList<Character>();
        collChar.add('1');
        collChar.add('2');
        collChar.add('3');
        assertTrue(charQ.containsAll(collChar));

        // Testing with non consecutive elements on median size
        Collection<Integer> collNotCon = new ArrayList<Integer>();
        collNotCon.add(5);
        collNotCon.add(22);
        collNotCon.add(55);
        assertTrue(intQ100.containsAll(collNotCon));

        // Testing with a large set of elements
        Collection<Integer> collLarge = new ArrayList<Integer>();
        collLarge.add(5);
        collLarge.add(22);
        collLarge.add(55);
        collLarge.add(67);
        collLarge.add(80);
        collLarge.add(92);
        collLarge.add(99);
        collLarge.add(200);
        collLarge.add(300);
        collLarge.add(450);
        collLarge.add(500);
        collLarge.add(9998);
        assertTrue(intQ10000.containsAll(collLarge));

        Collection<Integer> collLarge1000000 = new ArrayList<Integer>();
        collLarge1000000.add(1000);
        collLarge1000000.add(2000);
        collLarge1000000.add(10003);
        collLarge1000000.add(11130);
        collLarge1000000.add(44010);
        collLarge1000000.add(55010);
        collLarge1000000.add(66010);
        collLarge1000000.add(79590);
        collLarge1000000.add(88891);
        collLarge1000000.add(91911);
        collLarge1000000.add(192919);
        collLarge1000000.add(200303);
        collLarge1000000.add(600010);
        collLarge1000000.add(999190);
        collLarge1000000.add(999999);
        assertTrue(intQ1000000.containsAll(collLarge1000000));
    }

    @Test
    public void testAltComparator() {
        // Test priority queue with custom comparator (reverse order)
        SimplePriorityQueue<Integer> revQ = new SimplePriorityQueue<>(Comparator.reverseOrder());
        revQ.insert(1);
        revQ.insert(5);
        revQ.insert(3);
        assertEquals(1, revQ.findMax());

        // Test with strings using length comparator
        Comparator<String> lengthComp = (s1, s2) -> s1.length() - s2.length();
        SimplePriorityQueue<String> lengthQ = new SimplePriorityQueue<>(lengthComp);
        lengthQ.insert("a");
        lengthQ.insert("bbb");
        lengthQ.insert("cc");
        assertEquals("bbb", lengthQ.findMax());
    }

    @Test
    public void testMax() {
        // Test findMax and deleteMax on integer queue
        assertEquals(8, intQ.findMax());
        assertEquals(8, intQ.deleteMax());
        assertEquals(7, intQ.findMax());

        // Test on string queue
        assertEquals("8", strQ.findMax());
        assertEquals("8", strQ.deleteMax());
        assertEquals("7", strQ.findMax());

        // Test on empty queue
        SimplePriorityQueue<Integer> emptyQ = new SimplePriorityQueue<>();
        assertThrows(NoSuchElementException.class, () -> emptyQ.findMax());
        assertThrows(NoSuchElementException.class, () -> emptyQ.deleteMax());

        // Test on queue with one element
        SimplePriorityQueue<Integer> singleQ = new SimplePriorityQueue<>();
        singleQ.insert(1);
        assertEquals(1, singleQ.findMax());
        assertEquals(1, singleQ.deleteMax());
        assertTrue(singleQ.isEmpty());
    }

    @Test
    public void testInsert() {
        // Test inserting into empty queue
        SimplePriorityQueue<Integer> newQ = new SimplePriorityQueue<>();
        newQ.insert(5);
        assertEquals(1, newQ.size());
        assertEquals(5, newQ.findMax());

        // Test inserting multiple elements
        newQ.insert(3);
        newQ.insert(7);
        newQ.insert(1);
        assertEquals(4, newQ.size());
        assertEquals(7, newQ.findMax());

        // Test inserting duplicate elements
        newQ.insert(7);
        assertEquals(5, newQ.size());
        assertEquals(7, newQ.findMax());

        // Test inserting null
        assertThrows(NullPointerException.class, () -> newQ.insert(null));

        // Testing larger Size
        intQ100.insert(99);
        assertEquals(101, intQ100.size());

        intQ10000.insert(99);
        assertEquals(10001, intQ10000.size());

        intQ1000000.insert(100000);
        assertEquals(1000001, intQ1000000.size());

        // Test insertAll
        ArrayList<Integer> toAdd = new ArrayList<>(Arrays.asList(10, 12, 8));
        newQ.insertAll(toAdd);
        assertEquals(8, newQ.size());
        assertEquals(12, newQ.findMax());

        ArrayList<Integer> toAddLarger = new ArrayList<>(Arrays.asList(8, 10, 12, 1000, 1222, 10202, 111121, 10234));
        intQ10000.insertAll(toAddLarger);
        intQ1000000.insertAll(toAddLarger);
        // Adding 8, 10009 because we add 1 in the previous, same with Q1000000
        assertEquals(10009, intQ10000.size());
        assertEquals(1000009, intQ1000000.size());
    }

    @Test
    public void testSize() {
        // Test size of empty queue
        SimplePriorityQueue<Integer> emptyQ = new SimplePriorityQueue<>();
        assertEquals(0, emptyQ.size());
        assertTrue(emptyQ.isEmpty());

        // Test size after insertions
        emptyQ.insert(1);
        assertEquals(1, emptyQ.size());
        assertFalse(emptyQ.isEmpty());

        emptyQ.insert(2);
        assertEquals(2, emptyQ.size());

        // Test size after deletions
        emptyQ.deleteMax();
        assertEquals(1, emptyQ.size());

        emptyQ.deleteMax();
        assertEquals(0, emptyQ.size());
        assertTrue(emptyQ.isEmpty());

        // Test size with larger number of elements
        assertEquals(100, intQ100.size());
        assertEquals(10000, intQ10000.size());
        assertEquals(1000000, intQ1000000.size());
    }

    @Test
    public void testBinarySearch() {
        // This test indirectly tests the binary search functionality
        // through insert operations that should maintain sorted order

        SimplePriorityQueue<Integer> testQ = new SimplePriorityQueue<>();

        // Insert elements in random order
        testQ.insert(5);
        testQ.insert(3);
        testQ.insert(7);
        testQ.insert(1);
        testQ.insert(6);
        testQ.insert(4);
        testQ.insert(2);

        // Verify elements come out in sorted order
        assertEquals(7, testQ.deleteMax());
        assertEquals(6, testQ.deleteMax());
        assertEquals(5, testQ.deleteMax());
        assertEquals(4, testQ.deleteMax());
        assertEquals(3, testQ.deleteMax());
        assertEquals(2, testQ.deleteMax());
        assertEquals(1, testQ.deleteMax());

        // Verify queue is empty
        assertTrue(testQ.isEmpty());
        assertEquals(0, testQ.size());
    }
}
