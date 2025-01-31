package assign03;

import assign03.*;

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
        
        // Testing with a set of elements (containsAll)

        Collection<Integer> desiredIntegers = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            desiredIntegers.add(i);
        assertTrue(intQ100.containsAll(desiredIntegers));

        // Collection<String> coll2 = strQ;
        // assertTrue(strQ.containsAll(coll2);

        // assertFalse(intQ.containsAll(coll2);

        // Testing with a large set of elements
        
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
        assertEquals("a", lengthQ.findMax());
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
        
        // Test insertAll
        ArrayList<Integer> toAdd = new ArrayList<>(Arrays.asList(10, 12, 8));
        newQ.insertAll(toAdd);
        assertEquals(8, newQ.size());
        assertEquals(12, newQ.findMax());
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
