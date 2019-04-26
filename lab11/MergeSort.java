import edu.princeton.cs.algs4.Queue;

/** modified by Yue hu
 *  April 2019
 */

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     *
     * This method should take linear time.
     *
     * @param   items  A Queue of items.
     * @return         A Queue of queues, each containing an item from items.
     *
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> singleQue = new Queue<>();
        for (Item item : items) {
            Queue<Item> tempQue = new Queue<>();
            tempQue.enqueue(item);
            singleQue.enqueue(tempQue);
        }
        return singleQue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> mergedQue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            Item min = getMin(q1, q2);
            mergedQue.enqueue(min);
        }
        return mergedQue;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * This method should take roughly nlogn time where n is the size of "items"
     * this method should be non-destructive and not empty "items".
     *
     * @param   items  A Queue to be sorted.
     * @return         A Queue containing every item in "items".
     *
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        int N = items.size();
        if(N <= 1) { return items; }
        Queue<Queue<Item>> singleQue = makeSingleItemQueues(items);
        int a = N / 2;
        int b = N - a;
        Queue<Item> QueA = new Queue<>();
        Queue<Item> QueB = new Queue<>();
        for (int i = 0; i < a; i++) {
            Item currItem = singleQue.dequeue().dequeue();
            QueA.enqueue(currItem);
        }
        for (int i = 0; i < b; i++) {
            Item currItem = singleQue.dequeue().dequeue();
            QueB.enqueue(currItem);
        }
        return mergeSortedQueues(mergeSort(QueA), mergeSort(QueB));
    }

    public static void main(String[] args) {

        /* test private methods. */
        Queue<String> stringQ = new Queue<>();
        stringQ.enqueue("I");
        stringQ.enqueue("am");
        stringQ.enqueue("not");
        stringQ.enqueue("dog");
        Queue<Queue<String>> SQ = makeSingleItemQueues(stringQ);
        System.out.println(SQ.size());
    }
}
