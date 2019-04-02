package bearmaps;

/** @author Yue Hu
 *  Apr.1 2019
 *   a naive linear-time solution to solve the problem of
 *   finding the closest point to a given coordinate.
 *   he goal of creating this class is that you will have a alternative, albeit slower, solution that you can use to
 *   easily verify if the result of your k-d tree’s nearest is correct.
 */

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> pointList;

    public NaivePointSet(List<Point> points) {
        pointList = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x,y);
        Point ret = pointList.get(0);
        double distanceMin = Double.POSITIVE_INFINITY;
        for (Point pt : pointList) {
            double dist = Point.distance(pt, target);
            if (dist < distanceMin) {
                ret = pt;
                distanceMin = dist;
            }
        }
        return ret;
    }
}
