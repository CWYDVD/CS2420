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
    public void testAltComparator() {

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
        Collection<Integer> coll1 = Arrays.asList(1, 2, 3);
        assertTrue(intQ100.containsAll(coll1));

        // Testing with a large set of elements
    }

    @Test
    public void testMax() {

    }

    @Test
    public void testInsert() {

    }

    @Test
    public void testSize() {

    }

    @Test
    public void testBinarySearch() {

    }
}
