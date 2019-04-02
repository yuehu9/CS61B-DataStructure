package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/** @author Yue
 * @source https://www.youtube.com/watch?v=lp80raQvE5c&feature=youtu.be
 */
public class KDTreeTest {

    private static Random r = new Random(500);

    @Test
    /* test the case on the slides*/
    public void nearestTest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point near = kd.nearest(0, 7);

        Point p7 = new Point(1, 5);
        assertEquals(p7, near);

    }

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomNPoints(int N) {
        List<Point> points = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }

    /**funciton for randomized test*/
    private void randomTest(int pointCount, int queryCount) {
        List<Point> points = randomNPoints(pointCount);
        PointSet nps = new NaivePointSet(points);
        PointSet kd = new KDTree(points);
        for (int i = 0; i < queryCount; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            Point expect = nps.nearest(x, y);
            Point actual = kd.nearest(x, y);
            assertEquals(expect, actual);
        }
    }

    @Test
    public void nearest1000PTest() {
        randomTest(1000, 200);
    }


    @Test
    public void nearest1000000PTest() {
        randomTest(100000, 10000);
    }

    /** function for time test
     *
     */
    private void timeTest(int pointCount, int queryCount) {
        List<Point> points = randomNPoints(pointCount);
        PointSet nps = new NaivePointSet(points);
        PointSet kd = new KDTree(points);
        Stopwatch sw = new Stopwatch();

        for (int i = 0; i < queryCount; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            nps.nearest(x, y);
        }
        System.out.println("time elapsed for naive approach," + pointCount
                + " point and " + queryCount + " queries : " + sw.elapsedTime()
                + " seconds");

        sw = new Stopwatch();
        for (int i = 0; i < queryCount; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            kd.nearest(x, y);
        }
        System.out.println("time elapsed for kdTree approach," + pointCount
                + "point and " + queryCount + "queries : " + sw.elapsedTime()
                + " seconds");
    }

    @Test
    public void timeTest1000000P() {
        timeTest(1000000, 10000);
    }

    @Test
    public void timeTestVaryingQ() {
        List<Integer> queryCounts = List.of(200, 1000, 10000);
        for (int i : queryCounts) {
            timeTest(100000, i);
        }
    }

}
