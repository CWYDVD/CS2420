import assign03.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        ArrayList<Integer> contents = new ArrayList<>();
        for (int i = 0; i < 100; i++) contents.add(i);
        intQ100.insertAll(contents);

        intQ10000 = new SimplePriorityQueue<>();
        contents = new ArrayList<>();
        for (int i = 0; i < 10000; i++) contents.add(i);
        intQ10000.insertAll(contents);

        intQ1000000 = new SimplePriorityQueue<>();
        contents = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) contents.add(i);
        intQ1000000.insertAll(contents);
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
        assertEquals(8, intQ.size());
        assertEquals(8, intQ.findMax());
        intQ.clear();
        assertEquals(0, intQ.size());
        assertThrows(NoSuchElementException.class, () -> {intQ.findMax();});

        assertEquals(8, intQ.size());
        assertEquals(8, intQ.findMax());
        intQ.clear();
        assertEquals(0, intQ.size());
        assertThrows(NoSuchElementException.class, () -> {intQ.findMax();});

        assertEquals(8, intQ.size());
        assertEquals(8, intQ.findMax());
        intQ.clear();
        assertEquals(0, intQ.size());
        assertThrows(NoSuchElementException.class, () -> {intQ.findMax();});
    }

    @Test
    public void testContains() {

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
}
