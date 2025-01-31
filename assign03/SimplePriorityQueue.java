package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E> {
    private E[] arr;
    private int size;
    private Comparator<? super E> cmp;

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        this((Comparator<? super E>) Comparator.naturalOrder());
    }

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        this.cmp = cmp;
        this.arr = (E[]) new Object[16];
        this.size = 0;
    }

    @Override
    public void clear() {
        arr = (E[]) new Object[16];
        size = 0;
    }

    @Override
    public boolean contains(E item) {
        if (size == 0) return false; // If the queue is empty, return false
        int index = binarySearch(item);
        return index < size && cmp.compare(arr[index], item) == 0;
    }

    @Override
    public boolean containsAll(Collection<? extends E> coll) {
        for (E item : coll)
            if (!this.contains(item))
                return false;
        return true;
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if (this.size == 0) throw new NoSuchElementException();
        return arr[--this.size];
    }

    @Override
    public E findMax() throws NoSuchElementException {
        if (this.size == 0)
            throw new NoSuchElementException();
        return arr[this.size - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insert(E item) {
        if (this.size == this.arr.length) {
            // Resize the array
            E[] newArr = (E[]) new Object[this.arr.length * 2];
            for (int i = 0; i < this.arr.length; i++)
                newArr[i] = this.arr[i];
            this.arr = newArr;
        }
        int index = binarySearch(item);
        for (int i = this.size; i > index; i--)
            this.arr[i] = this.arr[i - 1];
        this.arr[index] = item;
        this.size++;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        for (E item : coll)
            insert(item);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Private binary search function to locate the predicted index of a given target within the data structure.
     *
     * @param target The target
     * @return the index
     */
    private int binarySearch(E target) {
        int i = 0;
        int j = size;
        while (i < j) {
            int mid = i + (j - i) / 2;
            E midElement = arr[mid];
            int compare;
            if (midElement == null) {
                // Handle null values (treat them as less than non-null values)
                compare = (target == null) ? 0 : -1;
            } else if (target == null) {
                // Treat null target as greater than non-null values
                compare = 1;
            } else {
                compare = cmp.compare(midElement, target);
            }
            if (compare == 0) {
                return mid; // Found the target
            } else if (compare < 0) {
                i = mid + 1; // Target is in the right half
            } else {
                j = mid; // Target is in the left half
            }
        }
        return i; // Return the insertion point if not found
    }
}
