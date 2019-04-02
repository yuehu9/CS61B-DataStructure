package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/** @author Yue
 * tests NAivePointSet
 * @source https://sp19.datastructur.es/materials/proj/proj2ab/proj2ab
 */

public class PointSetTest {
    @Test
    public void Naivetest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.3, 4.3); // returns p2
        assertEquals(p2, ret);
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }

}
