import edu.princeton.cs.algs4.Queue;
import static org.junit.Assert.*;
import org.junit.Test;

/** @author Yue hu
 *  April 2019
 */

public class TestSortAlgs {

    @Test
    public void testMergeSort() {
        Queue<String> stringQ = new Queue<>();
        stringQ.enqueue("I");
        stringQ.enqueue("am");
        stringQ.enqueue("4");
        stringQ.enqueue("dog");
        Queue<String> actualQ = MergeSort.mergeSort(stringQ);
        assertTrue(isSorted(actualQ));
    }

    @Test
    public void testQuickSort() {
        Queue<String> stringQ = new Queue<>();
//        stringQ.enqueue("I");
//        stringQ.enqueue("am");
//        stringQ.enqueue("4");
//        stringQ.enqueue("dog");
        Queue<String> actualQ = QuickSort.quickSort(stringQ);
        assertTrue(isSorted(actualQ));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
