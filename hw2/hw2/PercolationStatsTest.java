package hw2;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

public class PercolationStatsTest {
    @Test
    public void smallTest() {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20,20,pf);
        System.out.println("mean thrshold : " + ps.mean());
        System.out.println("standard deviation: " + ps.stddev());
        double low = ps.confidenceLow();
        double high = ps.confidenceHigh();
        System.out.println("95% confidence leve: " + low + "," + high);

    }
}
