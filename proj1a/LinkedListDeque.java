/** Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue.
 * Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends (either its front or its back).
 * @author Yue Hu
 */

public class LinkedListDeque<T> {
    private class StuffNode{
        public  StuffNode prev;
        public T item;
        public StuffNode next;

        private StuffNode(StuffNode p,T i, StuffNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    /**Creates an empty linked list deque.
     *
     */
    public LinkedListDeque() {
        sentinel = new StuffNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other
     *
     * @param other LinkedListDeque to be copied
     */
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new StuffNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        T myNew;
        for(int i = 0;i < other.size; i++ ){
            myNew = (T) other.get(i);    // use (T) for casting
            this.addLast(myNew);
        }
    }

    /** Adds an item of type T to the front of the deque.
     *
     * @param item item to be addded
     */
    public void addFirst(T item){
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**Adds an item of type T to the back of the deque.
     *
     * @param item item to be added
     */
    public void addLast(T item){
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**  Returns true if deque is empty, false otherwise.
     *
     * @return boolean value
     */
    public boolean isEmpty(){
        if(size > 0){
            return false;
        }
        return true;
    }

    /** Returns the number of items in the deque.
     *
     * @return size of the List
     */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     *
     */
    public void printDeque(){
        StuffNode S = sentinel;
        int k = size;
        while (k >0){
            S = S.next;
            System.out.print(S.item + " ");
            k += -1;
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return the first
     */
    public T removeFirst(){
        T myitem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size += -1;
        return myitem;
    }

    /**  Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return the last
     */
    public T removeLast(){
        T myitem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size += -1;
        return myitem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     * @param index an int
     * @return a value
     */
    public T get(int index){
        if(index >= size){
            System.out.println("index should be less than size!");
            return null;
        }
        StuffNode S = sentinel;
        while (index >= 0){
            S = S.next;
            index += -1;
        }
        return S.item;
    }

    /* helper method for getRecursion */
    private T myGetRecursive (StuffNode S, int myindex){
        if(myindex >= size){
            System.out.println("index should be less than size!");
            return null;}
        else if(myindex == 0){
            return S.item;
        }
        return myGetRecursive(S.next, myindex-1 );
    }

    /**  Same as get, but uses recursion.
     *
     * @param index int value
     * @return item
     */
    public T getRecursive(int index){
        StuffNode Stuff = sentinel;
        return myGetRecursive(Stuff.next,index);
    }



}