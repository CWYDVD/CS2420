package assign03;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePriorityQueueTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	private SimplePriorityQueue<TestClass> testQueue1;
	private SimplePriorityQueue<TestClass> testQueue2;
	private SimplePriorityQueue<Integer> integerQueue;
	private SimplePriorityQueue<Double> doubleQueue;

	@BeforeEach
	void setUp() throws Exception {
		integerQueue = new SimplePriorityQueue<>();
		integerQueue.insert(2);
		integerQueue.insert(20);
		integerQueue.insert(200);
		integerQueue.insert(2000);
		integerQueue.insert(20000);

		doubleQueue = new SimplePriorityQueue<>();
		doubleQueue.insert(1.0);
		doubleQueue.insert(2.0);
		doubleQueue.insert(3.0);
		
		testQueue1 = new SimplePriorityQueue<>(new OrderByWord());
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("AA", 5, 'z'));
		testQueue1.insert(new TestClass("BB", 6, 'y'));
		testQueue1.insert(new TestClass("CC", 4, 'x'));
		testQueue1.insert(new TestClass("DD", 7, 'w'));
		testQueue1.insert(new TestClass("EE", 3, 'u'));
		testQueue1.insert(new TestClass("FF", 8, 't'));
		testQueue1.insert(new TestClass("GG", 2, 's'));
		testQueue1.insert(new TestClass("HH", 9, 'r'));
		testQueue1.insert(new TestClass("II", 1, 'q'));
		testQueue1.insert(new TestClass("JJ", 10, 'p'));

		testQueue2 = new SimplePriorityQueue<>(new OrderByLetter());
		testQueue2.insert(new TestClass("AA", 5, 'z'));
		testQueue2.insert(new TestClass("BB", 6, 'y'));
		testQueue2.insert(new TestClass("CC", 4, 'x'));
		testQueue2.insert(new TestClass("DD", 7, 'w'));
		testQueue2.insert(new TestClass("EE", 3, 'v'));
		testQueue2.insert(new TestClass("FF", 8, 'u'));
		testQueue2.insert(new TestClass("GG", 2, 't'));
		testQueue2.insert(new TestClass("HH", 9, 's'));
		testQueue2.insert(new TestClass("II", 1, 'r'));
		testQueue2.insert(new TestClass("JJ", 10, 'q'));
		
	}

	@Test
	void testFindMax() {
		assertEquals(20000, integerQueue.findMax(), "The maximum element should be 20000");
		assertEquals(3.0, doubleQueue.findMax(), "The maximum element should be 3.0");
		assertEquals("JJ", testQueue1.findMax().getWord());
		assertEquals('z', testQueue2.findMax().getLetter());
	}

	@Test
	void testFindMaxEmptyQueue() {
		SimplePriorityQueue<Integer> emptyQueue = new SimplePriorityQueue<>();
		assertThrows(NoSuchElementException.class, emptyQueue::findMax,
				"Finding max in an empty queue should throw NoSuchElementException");
	}

	@Test
	void testDeleteMax() {
		assertEquals(20000, integerQueue.deleteMax(), "The deleted maximum element should be '20000'");
		assertEquals(2000, integerQueue.findMax(), "The highest element after deletion should be '2000'");
		assertEquals(3.0, doubleQueue.deleteMax(), "The deleted maximum element should be '3.0'");
		assertEquals(2.0, doubleQueue.findMax(), "The highest element after deletion should be '2.0'");
		assertEquals("JJ", testQueue1.deleteMax().getWord());
		assertEquals("II", testQueue1.findMax().getWord());
		assertEquals('z', testQueue2.deleteMax().getLetter());
		assertEquals('y', testQueue2.findMax().getLetter());
	}

	@Test
	void testDeleteMaxEmptyQueue() {
		SimplePriorityQueue<String> emptyQueue = new SimplePriorityQueue<>();SimplePriorityQueue<TestClass> emptyQueue2 = new SimplePriorityQueue<> (new OrderByWord());
		assertThrows(NoSuchElementException.class, emptyQueue::deleteMax);
		assertThrows(NoSuchElementException.class, emptyQueue2::deleteMax,
				"Deleting max from an empty queue should throw NoSuchElementException");
		emptyQueue.insert("Something");
		assertEquals("Something", emptyQueue.findMax(), "DeleteMax should have no lasting effect on the object beyond the deletion");
		
		
	}

	@Test
	void testStringDeleteMax() {
		SimplePriorityQueue<String> stringQueue = new SimplePriorityQueue<>();
		stringQueue.insert("box");
		stringQueue.insert("paper");
		stringQueue.insert("pencil");
		assertEquals("pencil", stringQueue.deleteMax(), "The deleted maximum element should be 'pencil'");
		assertEquals("paper", stringQueue.findMax(), "The new maximum element should be 'paper'");
	}

	@Test
	void testCharacterInsert() {
		SimplePriorityQueue<Character> characterQueue = new SimplePriorityQueue<>();
		characterQueue.insert('A');
		characterQueue.insert('B');
		characterQueue.insert('C');
		characterQueue.insert('D');
		characterQueue.insert('E');
		assertEquals(5, characterQueue.size(), " THe element's size should be 5");
		characterQueue.deleteMax();
		assertEquals(4, characterQueue.size());
		assertEquals('D', characterQueue.deleteMax(), "The deleted maximum element should be 'D'");
		assertEquals('C', characterQueue.findMax(), "The maximum element should be C");

	}

	@Test
	void testInsertAll() {
		SimplePriorityQueue<Integer> insertAllQueue = new SimplePriorityQueue<>();
		List<Integer> numbers = Arrays.asList(1, 5, 9, 7, 6, 0);
		insertAllQueue.insertAll(numbers);
		assertEquals(numbers.size(), insertAllQueue.size(), "Size after insertAll should match the input list size");
		insertAllQueue.insert(0);
		assertEquals(numbers.size() + 1, insertAllQueue.size(), "Insert all should not impede further inserts");
		assertEquals(9, insertAllQueue.findMax());
		
		SimplePriorityQueue<TestClass> insertAllQueue2 = new SimplePriorityQueue<> (new OrderByLetter());
		List<TestClass> letters = Arrays.asList(new TestClass("", 1, 'b'), new TestClass("", 1, 'f'), new TestClass("", 1, 'a'), new TestClass("", 1, 'x'),new TestClass("", 1, 'n'),new TestClass("", 1, 'r'),new TestClass("", 1, 'a'));
		insertAllQueue2.insertAll(letters);
		assertEquals(letters.size(), insertAllQueue2.size(), "Size after insertAll should match the input list size");
		insertAllQueue2.insert(new TestClass("", 1, 'l'));
		assertEquals(letters.size() + 1, insertAllQueue2.size(), "Insert all should not impede further inserts");
		assertEquals('x', insertAllQueue2.findMax().getLetter());
	}

	@Test
	void testContains() {
		SimplePriorityQueue<String> containsQueue = new SimplePriorityQueue<>();
		containsQueue.insert("cat");
		containsQueue.insert("dog");

		assertTrue(containsQueue.contains("cat"), "The queue should contain 'cat'");
		assertFalse(containsQueue.contains("fish"), "The queue should not contain 'fish'");
		assertTrue(integerQueue.contains(2), "The queue should contain '2'");
		assertFalse(integerQueue.contains(3), "The queue should not contain '3'");
		assertTrue(testQueue1.contains(new TestClass("AA", 5, 'z')));
		assertFalse(testQueue1.contains(new TestClass("NN", 1, 'i')));
		assertTrue(testQueue1.contains(new TestClass("AA", 100000, '1')));
		assertFalse(testQueue1.contains(new TestClass("XX", 5, 'z')));
	}

	@Test
	void testSize() {
		SimplePriorityQueue<Double> sizeQueue = new SimplePriorityQueue<>();
		assertEquals(0, sizeQueue.size(), "Initial size should be 0");
		sizeQueue.insert(6.0);
		sizeQueue.insert(6.0);
		assertEquals(2, sizeQueue.size(), "Size after inserting elements should be 2");
		for (int i = 0; i < 100; i ++) {
			sizeQueue.insert(1.0);
		}
		assertEquals(102, sizeQueue.size(), "Size after adding 100 duplicates should be 102");
	}

	@Test
	void testIsEmpty() {
		SimplePriorityQueue<String> emptyQueue = new SimplePriorityQueue<>();
		assertTrue(emptyQueue.isEmpty(), "Newly created queue should be empty");
		emptyQueue.insert("Miss");
		assertFalse(emptyQueue.isEmpty(), "Queue with elements should not be empty");
	}

	@Test
	void testClear() {
		SimplePriorityQueue<Integer> clearQueue = new SimplePriorityQueue<>();
		clearQueue.insert(5);
		clearQueue.insert(10);
		clearQueue.clear();
		assertTrue(clearQueue.isEmpty(), "After clearing, the queue should be empty");
		assertThrows(NoSuchElementException.class, clearQueue::findMax,
				"Finding max in an empty queue should throw NoSuchElementException");
		clearQueue.insert(11);
		assertEquals(11, clearQueue.findMax());
		
	}


	protected class OrderByWord implements Comparator<TestClass> {

		@Override
		public int compare(TestClass arg0, TestClass arg1) {
			return arg0.getWord().compareTo(arg1.getWord());
		}
	}
	
	protected class OrderByLetter implements Comparator<TestClass> {

		@Override
		public int compare(TestClass arg0, TestClass arg1) {
			return arg0.getLetter() - arg1.getLetter();
		}
		
	}
	
}

