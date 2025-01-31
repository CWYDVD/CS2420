package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A priority queue that supports access of the maximum element only.
 * 
 * @author Eric Heisler and Mi Zeng and Aiden Maxwell
 * @version Jan 29, 2024
 * 
 * @param <E> - the type of elements contained in this priority queue
 */

public class SimplePriorityQueue<E> implements PriorityQueue<E> {

	private int size;

	private E[] elements;

	private Comparator<? super E> comparator;

	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		this.size = 0;
		this.elements = (E[]) new Object[16];
		this.comparator = null;

	}
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		this.size = 0;
		this.elements = (E[]) new Object[16];

		// Set the comparator to the provided comparator
		this.comparator = cmp;

	}

	/**
	 * Retrieves, but does not remove, the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return elements[size - 1];
	}

	/**
	 * Retrieves and removes the maximum element in this priority queue.
	 * 
	 * @return the maximum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E maxItem = elements[--size];
		elements[size] = null;

		return maxItem;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 * 
	 * @param item - the element to insert
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(E item) {

		if (size >= this.elements.length) {
			E[] placeholder = (E[]) new Object[this.elements.length * 2];
			for (int i = 0; i < this.elements.length; i++) {
				placeholder[i] = this.elements[i];
			}
			this.elements = placeholder;
		}

		int index = binarySearch(item);

		int position = index >= 0 ? index : -index - 1;

		for (int i = size; i > position; i--) {
			this.elements[i] = this.elements[i - 1];
		}
		this.elements[position] = item;

		size += 1;

	}

	/**
	 * Inserts the specified elements into this priority queue.
	 * 
	 * @param coll - the collection of elements to insert
	 */
	@Override
	public void insertAll(Collection<? extends E> coll) {
		for (E e : coll) {
			insert(e);
		}

	}

	/**
	 * Indicates whether this priority queue contains the specified element.
	 * 
	 * @param item - the element to be checked for containment in this priority
	 *             queue
	 * @return true if the item is contained in this priority queue
	 */
	@Override
	public boolean contains(E item) {
		// if binarySearch returns a negative value, that means the element does not
		// exist
		return binarySearch(item) < 0 ? false : true;
	}

	/**
	 * @return the number of elements in this priority queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this priority queue contains no elements, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this priority queue. The queue will be empty
	 * when this call returns.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.elements = (E[]) new Object[16];
		this.size = 0;

	}
	
	/**
	 * Private method which finds the index at which a given target should be placed in elements
	 * @param target The object to place
	 * @return The index of the target's place in elements
	 */
	@SuppressWarnings("unchecked")
	private int binarySearch( E target) {
		int left = 0;
		int right = size - 1;

		while (left <= right) {
			int center = (left +  right) / 2;

			if (comparator != null) {
				// found, return the index
				if (this.comparator.compare(elements[center], target) == 0)
					return center;
				// larger than current minimal, increase minimal
				if (this.comparator.compare(elements[center], target) < 0)
					left = center + 1;
				else // else, decrease maximal
					right = center - 1;
			} else {
				if (((Comparable<? super E>) (elements[center])).compareTo(target) == 0)
					return center;
				if (((Comparable<? super E>) (elements[center])).compareTo(target) < 0)
					left = center + 1;
				else
					right = center - 1;
			}
		}
		// not found, return -left - 1 (a negative value) where left indicates the
		// insertion position
		return -left - 1;
	}
	

}
