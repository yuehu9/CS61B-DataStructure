/** @author Yue
 * March 2019
  PriorPQ keeps the weight and do the bubbleup/down; but need another itemPQ to change simutaniously so that
  we can know what item to return for "get smallest". Also need a HashMap so that we can do change priority 
  quickly, since we need to know where it currently is in the priority queue.
 */
package bearmaps.proj2ab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<T> itemPQ;
    private ArrayList<Double> priorPQ;
    private int size;
    private HashMap<T, Integer> itemMap;
    // itemMap contains the item as key and its position in Arraylist as value

    /** Initialize a pq with specialzed capacity */
    public ArrayHeapMinPQ(int capacity) {
        size = 0;
        itemPQ = new ArrayList<>(capacity + 1);
        priorPQ = new ArrayList<>(capacity + 1);
        itemPQ.add(null);
        priorPQ.add(Double.NEGATIVE_INFINITY);
        itemMap = new HashMap<>(capacity);
    }

    /** Initialize an empty pq */
    public ArrayHeapMinPQ() {
        this(1);
    }

    @Override
    public void add(T item, double priority) {
        if (itemMap.containsKey(item)) {
            throw new IllegalArgumentException("item already exists.");
        }
        itemPQ.add(item);
        priorPQ.add(priority);
        size += 1;
        itemMap.put(item,size);
        bubbleUp(size);

    }

    /* helper method for add: bubble an item up*/
    private void bubbleUp(int k) {
        while (k > 1 && priorPQ.get(k) < priorPQ.get(k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    /* helper method: exchange items */
    private void exch(int i, int j) {
        T tempT = itemPQ.get(i);
        Double tempPr = priorPQ.get(i);
        itemMap.put(itemPQ.get(i), j);
        itemMap.put(itemPQ.get(j),i);
        itemPQ.set(i, itemPQ.get(j));
        priorPQ.set(i, priorPQ.get(j));
        itemPQ.set(j, tempT);
        priorPQ.set(j, tempPr);
    }

    /* helper method for delete: bubble down items */
    private void bubbleDown(int k) {
        while (k * 2 <= size) {
            int j = k * 2;
            if (j < size && priorPQ.get(j) > priorPQ.get(j+1)) j++;
            if (priorPQ.get(j) > priorPQ.get(k)) break;
            exch(j, k);
            k = j;
        }
    }

    /** Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return (itemMap.get(item) != null);
    }


    /** Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ empty");
        }
        return itemPQ.get(1);
    }

    /** Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ empty");
        }
        T item = itemPQ.get(1);
        exch(1, size);
        itemMap.remove(item);
        itemPQ.remove(size);
        priorPQ.remove(size);
        size -= 1;
        bubbleDown(1);
        return item;
    }

    /** Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /** Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if(itemMap.get(item) == null) {
            throw new NoSuchElementException("NO such item");
        }
        Integer pos = itemMap.get(item);
        Double oldpr =  priorPQ.get(pos);
        priorPQ.set(pos, priority);
        if(priority > oldpr) bubbleDown(pos);
        if(priority <= oldpr) bubbleUp(pos);
    }
}
