public interface Deque<T> {
    /** Adds an item of type T to the front of the deque.
     *
     * @param item item to be addded
     */
    public void addFirst(T item);

    /**Adds an item of type T to the back of the deque.
     *
     * @param item item to be added
     */
    public void addLast(T item);

    /**  Returns true if deque is empty, false otherwise.
     *
     * @return boolean value
     */
    default public boolean isEmpty(){
        if(size() > 0){
            return false;
        }
        return true;
    }

    /** Returns the number of items in the deque.
     *
     * @return size of the List
     */
    public int size();

    /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     *
     */
    public void printDeque();

    /**  Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return the first
     */
    public T removeFirst();

    /**  Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return the last
     */
    public T removeLast();

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
     * @param index an int
     * @return a value
     */
    public T get(int index);

}
