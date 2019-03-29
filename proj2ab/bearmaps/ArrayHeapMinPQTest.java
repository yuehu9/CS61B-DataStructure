/** @author Yue
 * March 2019
 */

package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.security.interfaces.DSAPublicKey;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void addTest() {
        ArrayHeapMinPQ<String> myPQ = new ArrayHeapMinPQ<>();
        myPQ.add("wee", 1.2);
        myPQ.add("are", 1.3);
        myPQ.add("eew", 0.9);
        assertEquals(3, myPQ.size());
    }

    @Test
    public void DeleteContainsTest() {
        ArrayHeapMinPQ<String> myPQ = new ArrayHeapMinPQ<>();
        assertEquals(0, myPQ.size());
        myPQ.add("wee", 1.2);
        myPQ.add("are", 1.3);
        myPQ.add("eew", 0.9);
        myPQ.add("fsdfa", 3.9);
        myPQ.add("eewada", 4.64);
        assertEquals("eew", myPQ.getSmallest());
        String mm = myPQ.removeSmallest();
        assertFalse(myPQ.contains("eew"));
        assertEquals("eew", mm);
        assertEquals("wee", myPQ.getSmallest());
        myPQ.add("sd", 10.);
        assertEquals(5, myPQ.size());
        assertTrue(myPQ.contains("wee"));
        assertTrue(myPQ.contains("are"));
    }

    @Test
    public void changePriorTest() {
        ArrayHeapMinPQ<String> myPQ = new ArrayHeapMinPQ<>();
        assertEquals(0, myPQ.size());
        myPQ.add("wee", 1.2);
        myPQ.add("are", 1.3);
        myPQ.add("eew", 0.9);
        myPQ.add("fsdfa", 3.9);
        myPQ.add("eewada", 4.64);
        myPQ.changePriority("eew", 4.44);
        assertEquals("wee", myPQ.getSmallest());
        myPQ.removeSmallest();
        myPQ.changePriority("eewada", 0.1);
        assertEquals("eewada", myPQ.getSmallest());

    }

    @Test
    public void TimeTestContains() {
        ExtrinsicMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> slowPQ = new NaiveMinPQ<>();
        Random r = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            minPQ.add(i, j);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed for ArrayHeap: " + (end - start)/1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            slowPQ.add(i, j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed for slowHeap: " + (end2 - start2)/1000.0 +  " seconds.");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            minPQ.contains(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed for contains ArrayHeap: " + (end3 - start3)/1000.0 +  " seconds.");

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            slowPQ.contains(i);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed for contains slowHeap: " + (end4 - start4)/1000.0 +  " seconds.");


    }

    @Test
    public void TimeTestRemove() {
        ExtrinsicMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> slowPQ = new NaiveMinPQ<>();
        Random r = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            minPQ.add(i, j);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed for ArrayHeap: " + (end - start)/1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            slowPQ.add(i, j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed for slowHeap: " + (end2 - start2)/1000.0 +  " seconds.");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            minPQ.removeSmallest();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed for remove ArrayHeap: " + (end3 - start3)/1000.0 +  " seconds.");

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            slowPQ.removeSmallest();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed for remove slowHeap: " + (end4 - start4)/1000.0 +  " seconds.");


    }

    @Test
    public void TimeTestChangePrioprity() {
        ExtrinsicMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> slowPQ = new NaiveMinPQ<>();
        Random r = new Random();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            minPQ.add(i, j);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed for ArrayHeap: " + (end - start)/1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i += 1) {
            Double j = r.nextDouble();
            slowPQ.add(i, j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed for slowHeap: " + (end2 - start2)/1000.0 +  " seconds.");

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            Double j = r.nextDouble();
            minPQ.changePriority(i, j);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time elapsed for change ArrayHeap: " + (end3 - start3)/1000.0 +  " seconds.");

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i += 1) {
            Double j = r.nextDouble();
            slowPQ.changePriority(i, j);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("Total time elapsed for change slowHeap: " + (end4 - start4)/1000.0 +  " seconds.");


    }
}
