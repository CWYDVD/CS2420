package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 * This class is a simple priority queue data structure which store an associated type of object according to a given Comparator (default natural ordering using Comparable). Only the maximum object can be accessed or deleted.
 * 
 * @author Maxwell and David
 * @version January 26, 2025
 * 
 * @param E The type of object the SimplePriorityQueue contains. Either it extends Comparable or a Comparator class must be provided. 
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
    private E[] arr;
    private int size;
    private Comparator<? super E> cmp;
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(){
        this((Comparator<? super E>) Comparator.naturalOrder());
    }

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp){
        this.cmp = cmp;
        this.arr = (E[]) new Object[16];
        this.size = 0;
    }
    /**
    * Removes all of the elements from this priority queue. The queue will be
    * empty when this call returns.
    */
    @SuppressWarnings("unchecked")
    public void clear(){
        arr = (E[]) new Object[16];
        size = 0;
    }
    /**
    * Indicates whether this priority queue contains the specified element.
    *
    * @param item - the element to be checked for containment in this priority
    queue
    */
    public boolean contains(E item) {
        return binarySearch(item) != -1;
    };
    /**
    * Indicates whether this priority contains all of the specified elements.
    *
    * @param coll - the collection of elements to be checked for containment in
    this priority queue
    * @return true if this priority queue contains every element in the
    specified collection;
    * otherwise, returns false
    */
    public boolean containsAll(Collection<? extends E> coll){
        for (E item : arr)
            if (! this.contains(item))
                return false;
        return true;
    };
    /**
    * Retrieves and removes the maximum element in this priority queue.
    *
    * @return the maximum element
    * @throws NoSuchElementException if the priority queue is empty
    */
    public E deleteMax() throws NoSuchElementException {
        if(this.size == 0) throw new NoSuchElementException();
        return(arr[--this.size]);
    }
    /**
    * Retrieves, but does not remove, the maximum element in this priority
    * queue.
    *
    * @return the maximum element
    * @throws NoSuchElementException if the priority queue is empty
    */
    public E findMax() throws NoSuchElementException{
        if (this.size == 0)
            throw new NoSuchElementException();
        return arr[this.size - 1];
    };
    /**
    * Inserts the specified element into this priority queue.
    *
    * @param item - the element to insertch
    */
    @SuppressWarnings("unchecked")
    public void insert(E item){
        this.size ++;
        if (this.size == arr.length) {
            E[] newArr = (E[]) new Object[arr.length * 2];
            for (int i = 0; i < this.size; i++)
                newArr[i] = arr[i];
            this.arr = newArr;
            this.insert(item);
        } else {
            int index = binarySearch(item);
            E buffer = arr[index];
            arr[index] = item;
            for (int i = index + 1; i < this.size; i++) {
                arr[i] = buffer;
                buffer = arr[i+1];
            }
        }
    };
    /**
    * Inserts the specified elements into this priority queue.
    *
    * @param coll - the collection of elements to insert
    */
    public void insertAll(Collection<? extends E> coll) {
        for (E item:coll)
            insert(item);
    };
    /**
    * Indicates whether priority queue contains any elements.
    *
    * @return true if this priority queue is empty; false otherwise
    */
    public boolean isEmpty() {
        return (this.size == 0);
    };
    /**
    * Determines the number of elements in this priority queue.
    *
    * @return the number of elements in this priority queue
    */
    public int size() {
        return (this.size);
    };

    private int binarySearch(E target) {
        E val = arr[size/2];
        if (val.equals(target))
            return (size/2);
        else if (cmp.compare(target, val) > 0)
            return(binarySearchRecursive(target, size/2 + 1, size));
        else
            return(binarySearchRecursive(target, 0, size/2));
    }

    private int binarySearchRecursive(E target, int i, int j) {
        if (i == j) 
            return(-1);
        int index = i/2 + j/2;
        E val = arr[index];
        if (cmp.compare(target, val) == 0)
            return (index);
        else if (cmp.compare(target, val) > 0)
            return(binarySearchRecursive(target, index, j));
        else
            return(binarySearchRecursive(target, i, index));
    }
}
