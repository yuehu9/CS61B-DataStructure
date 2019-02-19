/** Implementation of Array list
 * @author Yue Hu
 */



/* invariants
    1. addFirst always adds an item to the position of nextFirst;
    2. addLast always adds an item to the position of nextLast.
    3. size: The number of items in the list should be size.
    4. The actuall array starts with nextFirst +1.
    5.The amount of memory that your program uses at any given time must be proportional to the number of items. For example, if you add 10,000 items to the deque, and then remove 9,999 items, you shouldn’t still be using an array of length 10,000ish. For arrays of length 16 or more, your usage factor should always be at least 25%. For smaller arrays, your usage factor can be arbitrarily low.
    6. We strongly recommend that you treat your array as circular for this exercise. In other words, if your front pointer is at position zero, and you addFirst, the front pointer should loop back around to the end of the array (so the new front item in the deque will be the last item in the underlying array). This will result in far fewer headaches than non-circular approaches.
    */


public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**Creates an empty linked array deque.
     *
     */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**Creates a deep copy of other.
     *
     * @param other ArrayDeque to be copied.
     */
    public ArrayDeque(ArrayDeque other){
        size = other.size();
        items =(T[]) other.copyArray(size);
        nextFirst = minusOne(0);
        nextLast = size;
    }


    /* helper method for decreasing the position.
    if reach 0 go the end of array
     */
    private int minusOne(int pos){
        if(pos == 0){
            return items.length-1;
        }
        return pos-1;
    }

     /* helper method for increasing the position.
    if reach array length go the front of array
     */
     private int  plusOne(int pos){
         return (pos + 1) % items.length;
     }


    /** Adds an item of type T to the front of the deque.
     *
     * @param x item to be addded
     */
    public void addFirst(T x){
        if(size == items.length){
            resize(size*2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /**Adds an item of type T to the back of the deque.
     *
     * @param x item to be added
     */
    public void addLast(T x){
        if(size == items.length){
            resize(size*2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
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
        int j = nextFirst;
        for(int i = 0; i < size; i += 1) {
            j = plusOne(j);
            System.out.print(items[j]  + " ");
        }
        System.out.println();
    }

    /**  Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return the first
     */
    public T removeFirst(){
        int index = plusOne(nextFirst);
        T x = items[index];
        items[index] = null;
        size -= 1;
        nextFirst = plusOne(nextFirst);
        // resize for space efficency.
        if((float)size/items.length < 0.25 && items.length >= 16){
            resize(items.length /2);
        }
        return x;
    }

    /**  Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return the last
     */
    public T removeLast(){
        int index = minusOne(nextLast);
        T x = items[index];
        items[index] = null;
        size -= 1;
        nextLast = minusOne(nextLast);
        // resize for space efficency.
        if((float)size/items.length < 0.25 && items.length >= 16){
            resize(items.length /2);
        }
        return x;
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
        int myInd = (nextFirst + 1 + index) % items.length;
        return items[myInd];
    }

    /* helper method to make copy of the pure naked array.
      * usef for constructor from other, and resize. */
    private T[] copyArray(int mySize){
        T[] copy = (T[]) new Object[mySize];

        int FirstInd = plusOne(nextFirst);
        int LastInd = minusOne(nextLast);
        // if firstInd < lastInd, directly copy; other wise copy in two pieces.
        if(FirstInd < LastInd){
            System.arraycopy(items,FirstInd,copy,0,size);
        }else {
            System.arraycopy(items,FirstInd,copy,0,items.length-FirstInd);
            System.arraycopy(items,0,copy,items.length-FirstInd,size - items.length+FirstInd);
        }
        return copy;
    }


    /* helper method to resize the array */
    private void resize(int newSize){
        T[] newItems = copyArray(newSize);
        items = newItems;
        nextFirst = minusOne(0);
        nextLast = size;
    }

}