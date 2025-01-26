package assign03;
import java.util.Collection;
import java.util.NoSuchElementException;
/**
* A priority queue that supports access of the maximum element only.
*
* @author CS 2420 course staff
* @version January 23, 2025
*
* @param <E> - the type of elements contained in this priority queue
*/
public interface PriorityQueue<E> {
/**
* Removes all of the elements from this priority queue. The queue will be
* empty when this call returns.
*/
public void clear();
/**
* Indicates whether this priority queue contains the specified element.
*
* @param item - the element to be checked for containment in this priority
queue
*/
public boolean contains(E item);
/**
* Indicates whether this priority contains all of the specified elements.
*
* @param coll - the collection of elements to be checked for containment in
this priority queue
* @return true if this priority queue contains every element in the
specified collection;
* otherwise, returns false
*/
public boolean containsAll(Collection<? extends E> coll);
/**
* Retrieves and removes the maximum element in this priority queue.
*
* @return the maximum element
* @throws NoSuchElementException if the priority queue is empty
*/
public E deleteMax() throws NoSuchElementException;
/**
* Retrieves, but does not remove, the maximum element in this priority
* queue.
*
* @return the maximum element
* @throws NoSuchElementException if the priority queue is empty
*/
public E findMax() throws NoSuchElementException;
/**
* Inserts the specified element into this priority queue.
*
* @param item - the element to insert
*/
public void insert(E item);
/**
* Inserts the specified elements into this priority queue.
*
* @param coll - the collection of elements to insert
*/
public void insertAll(Collection<? extends E> coll);
/**
* Indicates whether priority queue contains any elements.
*
* @return true if this priority queue is empty; false otherwise
*/
public boolean isEmpty();
/**
* Determines the number of elements in this priority queue.
*
* @return the number of elements in this priority queue
*/
public int size();
}
