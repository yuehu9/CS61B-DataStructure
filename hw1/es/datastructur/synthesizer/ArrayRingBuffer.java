package es.datastructur.synthesizer;
import java.util.Iterator;


/** modified by Yue
 * Mar. 6 2019.
 */
public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayIterator implements Iterator<T> {
        private int wizPos;

        public ArrayIterator() {
            wizPos = first;
        }

        @Override
        public boolean hasNext() {
            return wizPos < last;
        }

        @Override
        public T next() {
            T item = rb[wizPos];
            wizPos += 1;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /** helper method for increase position
     *
     * @param x
     * @return next position of x
     */
    private int myincrease(int x) {
        return (x + 1) % rb.length;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (first == last && fillCount != 0) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last = myincrease(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T out = rb[first];
        fillCount -= 1;
        first = myincrease(first);
        return out;
    }

    /**
     * return size of the buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * return number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }


    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T out = rb[first];
        return out;
    }

    /**
     * This method should return true only if the other object is an ArrayRingBuffer
     * with the exact same values. This method should be nondestructive.
     */
    @Override
    public boolean equals( Object o) {
        boolean result = true;
        if (this == o) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (other.fillCount != this.fillCount) {
            return false;
        }
        for (T item : this) {
            T itemo = other.dequeue();
            if (!itemo.equals(item)) {
                result = false;
            }
            other.enqueue(item);
        }
        return result;
    }

    /*
     * check if array contains a value

    public boolean contains(T x) {
        for (T item: this) {
            if (item.equals(x)) {
                return true;
            }
        }
        return false;
    }
    */
}
