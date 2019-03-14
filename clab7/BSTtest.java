/**
 * @author : Yue
 */

import org.junit.Test;
import static org.junit.Assert.*;


public class BSTtest {
    @Test
    public void testDepth() {
        BST<Integer> b = new BST<>();
        b.add(3);
        assertEquals(0,b.depthAv(),0.01);
        b.add(2);
        assertEquals(0.5,b.depthAv(),0.01);
        b.add(1);
        assertEquals(1,b.depthAv(),0.01);
    }

    @Test
    public void testDepth2() {
        BST<Integer> b = new BST<>();
        b.add(3);
        assertEquals(0,b.depthAv(),0.01);
        b.add(2);
        assertEquals(0.5,b.depthAv(),0.01);
        b.add(4);
        assertEquals(2./3.,b.depthAv(),0.01);
        b.add(1);
        assertEquals(1., b.depthAv(), 0.01);
    }
}
