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
     * @param item - the element to be checked for containment in this priority queue
     */
    public boolean contains(E item) {
        return cmp.compare(arr[binarySearch(item)], item) == 0;
    }

    /**
     * Indicates whether this priority contains all of the specified elements.
     *
     * @param coll - the collection of elements to be checked for containment in this priority queue
     * @return true if this priority queue contains every element in the specified collection;
     *         otherwise, returns false
     */
    public boolean containsAll(Collection<? extends E> coll){
        for (E item : coll)
            if (! this.contains(item))
                return false;
        return true;
    }

    /**
    * Retrieves and removes the maximum element in this priority queue.
    *
    * @return the maximum element
    * @throws NoSuchElementException if the priority queue is empty
    */
    public E deleteMax() throws NoSuchElementException {
        if(this.size == 0) throw new NoSuchElementException();
        return arr[--this.size];
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
    }

    // /**
    // * Inserts the specified element into this priority queue.
    // *
    // * @param item - the element to insertch
    // */
    // @SuppressWarnings("unchecked")
    // public void insert(E item){
    //     if (this.size == this.arr.length) {
    //         E[] newArr = (E[]) new Object[this.arr.length * 2];
    //         for (int i = 0; i < this.arr.length; i++)
    //             newArr[i] = this.arr[i];
    //         this.arr = newArr;
    //         int index = binarySearch(item);
    //         this.arr[index] = item;
    //     } else {
    //         int index = binarySearch(item);
    //         for (int i = this.size; i > index; i--) {
    //             this.arr[i] = this.arr[i - 1];
    //         }
    //         this.arr[index] = item;
    //     }
    //     this.size ++;
    // }

    /**
    * Inserts the specified element into this priority queue.
    *
    * @param item - the element to insertch
    */
    @SuppressWarnings("unchecked")
    public void insert(E item){
        if (this.size == this.arr.length) {
            E[] newArr = (E[]) new Object[this.arr.length * 2];
            for (int i = 0; i < this.arr.length; i++)
                newArr[i] = this.arr[i];
            this.arr = newArr;
            int index = binarySearch(item);
            this.arr[index] = item;
        } else {
            int index = binarySearch(item);
            for (int i = this.size; i > index; i--) {
                this.arr[i] = this.arr[i - 1];
            }
            this.arr[index] = item;
        }
        this.size ++;
    }
    
    /**
    * Inserts the specified e   lements into this priority queue.
    *
    * @param coll - the collection of elements to insert
    */
    public void insertAll(Collection<? extends E> coll) {
        for (E item:coll)
            insert(item);
    }

    /**
    * Indicates whether priority queue contains any elements.
    *
    * @return true if this priority queue is empty; false otherwise
    */
    public boolean isEmpty() {
        return this.size == 0;
    };
    /**
    * Determines the number of elements in this priority queue.
    *
    * @return the number of elements in this priority queue
    */
    public int size() {
        return this.size;
    }

    private int binarySearch(E target) {
        if (this.size == 0) return 0;
        return binarySearchRecursive(target, 0, this.size);
    }

    private int binarySearchRecursive(E target, int i, int j) {
        if (i >= j)
            return(j);
        int index = i + (j - i)/2;
        E val = arr[index];
        if (val == null)
            return(i);

        int compare = cmp.compare(target, val);
        if (compare == 0)
            return (index);
        else if (compare > 0)
            return(binarySearchRecursive(target, index + 1, j));
        else
            return(binarySearchRecursive(target, i, index));
    }
}
