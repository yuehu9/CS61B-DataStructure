package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void endeque() {
        ArrayRingBuffer<Integer> ab = new ArrayRingBuffer<>(3);
        ab.enqueue(3);
        int fir = ab.peek();
        assertEquals(3,fir);

        ab.enqueue(2);
        fir = ab.dequeue();
        assertEquals(3,fir);
        ab.enqueue(4);
        ab.enqueue(5);

        fir = ab.peek();
        assertEquals(2,fir);

//        ab.enqueue(3);

    }

    @Test
    public void equalTest() {
        ArrayRingBuffer<Integer> ab = new ArrayRingBuffer<>(4);
        ArrayRingBuffer<Integer> ab2 = new ArrayRingBuffer<>(4);

        ab.enqueue(2);
        ab.enqueue(3);
        ab.enqueue(4);
        ab2.enqueue(2);
        ab2.enqueue(3);
        ab2.enqueue(4);
        assertEquals(ab,ab2);

        ab2.dequeue();
        assertNotEquals(ab,ab2);


    }

    @Test
    public void IterTest() {
        ArrayRingBuffer<Integer> ab = new ArrayRingBuffer<>(4);

        ab.enqueue(2);
        ab.enqueue(3);
        ab.enqueue(4);

        for(int item : ab) {
            System.out.print(item + " ");
        }
    }
}
