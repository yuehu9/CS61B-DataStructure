package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] Stats;
    double T;

    /**  perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Parameter should be possitve");
        }
        this.T = T;
        Stats = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perlo = pf.make(N);
            while (!perlo.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                perlo.open(x, y);
            }
            double fraction = ((double) perlo.numberOfOpenSites()) / (double) (N * N);
            Stats[i] = fraction;
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(Stats);
    }

    /**sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(Stats);
    }

    /**  low endpoint of 95% confidence interval */
    public double confidenceLow() {
        double low = mean() - 1.96 * stddev() / Math.sqrt(T);
        return low;
    }

    /**  low endpoint of 95% confidence interval */
    public double confidenceHigh() {
        double high = mean() + 1.96 * stddev() / Math.sqrt(T);
        return high;
    }


}
