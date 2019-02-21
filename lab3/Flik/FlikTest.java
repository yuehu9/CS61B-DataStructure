import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests Filk.java
 @author Yue
 */

public class FlikTest {
    @Test
    public void testEqual() {
        int i = 9;
        int j = 0;

        assertTrue(!Flik.isSameNumber(i, j));
        assertTrue(Flik.isSameNumber(i, i));
        assertTrue(Flik.isSameNumber(j,j));
        assertTrue(Flik.isSameNumber(1290, 1290));
//        assertTrue(Flik.isSameNumber(128, 128));


    }
}