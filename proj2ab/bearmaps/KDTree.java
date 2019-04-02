package bearmaps;

import java.util.List;

/** @author Yue Hu
 * works with KD tree of 2 dimension
 * This class is immuatable
 */
public class KDTree implements PointSet {
    private Node root;

    private class Node {
        private Point p;
        private Node left;
        private Node right;
        private boolean compareX;

        public Node(Point pt, Boolean compX) {
            p = pt;
            compareX = compX;
        }

        public double compareTO(Point pt) {
            if (compareX) {
                return p.getX() - pt.getX();
            }
            return p.getY() - pt.getY();
        }
    }

    public KDTree() {
        root = null;
    }

    public KDTree(List<Point> points) {
        root = null;
        for (Point pt : points) {
            add(pt, true);
        }

    }


    /** helper method for constructing tree
     */
    private void add(Point pt, Boolean compX) {
        root = add(root, pt, compX);
    }

    private Node add(Node n, Point pt, Boolean compX) {
        if (n == null) {
            return new Node(pt, compX);
        }
        double ind = n.compareTO(pt);
        if (ind < 0) {
            n.right = add(n.right, pt, !compX);
        } else if (ind > 0) {
            n.left = add(n.left, pt, !compX);
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = myNearest(root, goal, root);
        return best.p;
    }

    private Node myNearest(Node n, Point goal, Node best) {
        if (n == null) return best;
        if (Point.distance(n.p, goal) < Point.distance(best.p, goal)) {
            best = n;
        }
        if (n.compareTO(goal) < 0) {
            best = myNearest(n.right, goal, best);
            Point refP;
            if (n.compareX) {
                refP = new Point(n.p.getX(), goal.getY());
            } else {
                refP = new Point(goal.getX(), n.p.getY());
            }
            if (Point.distance(goal, refP) < Point.distance(best.p, goal)) {
                best = myNearest(n.left, goal, best);
            }
        } else {
            best = myNearest(n.left, goal, best);
            Point refP;
            if (n.compareX) {
                refP = new Point(n.p.getX(), goal.getY());
            } else {
                refP = new Point(goal.getX(), n.p.getY());
            }
            if (Point.distance(goal, refP) < Point.distance(best.p, goal)) {
                best = myNearest(n.right, goal, best);
            }
        }

        return best;
    }



    /** tests construction method*/
    public static void main(String[] args) {

        Point p1 = new Point(2, 3);
        Point p7 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p7, p2, p3, p4, p5, p6));



    }

}
